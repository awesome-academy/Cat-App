package com.example.catapp.view.homescreen.homefragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.databinding.FragmentImageBinding
import com.example.catapp.utils.SCREEN_ADDRESS
import com.example.catapp.utils.ORDER_RANDOM
import com.example.catapp.utils.ORDER_NEWEST
import com.example.catapp.utils.ORDER_OLDEST
import com.example.catapp.utils.NONE
import com.example.catapp.utils.disable
import com.example.catapp.utils.enable
import com.example.catapp.utils.PAGE_ONE
import com.example.catapp.utils.CAT_IMAGE_MAX_SIZE
import com.example.catapp.utils.IMAGE_URL
import com.example.catapp.utils.USER_API
import com.example.catapp.utils.IMAGE_ID
import com.example.catapp.utils.IMAGE_SCREEN_TAG
import com.example.catapp.utils.shortToast
import com.example.catapp.utils.spinnerSelectedListener
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.view.adapter.CatImageAdapter
import com.example.catapp.view.homescreen.HomeActivity
import com.example.catapp.view.homescreen.imagescreenpresenter.CatInterface
import com.example.catapp.view.homescreen.BigImageActivity
import java.lang.Exception

class ImageFragment : Fragment(), CatInterface.View {

    private val binding by lazy { FragmentImageBinding.inflate(layoutInflater) }
    private var userApi: String? = null
    private val catAdapter by lazy { CatImageAdapter(::onClickItem) }
    private val breedNameList = mutableListOf<String>()
    private val orderList = mutableListOf(ORDER_RANDOM, ORDER_NEWEST, ORDER_OLDEST)
    private val breedList = mutableListOf<BreedItem>()
    private val categoryNameList = mutableListOf<String>()
    private val categoryList = mutableListOf<CategoriesItem>()
    private var catPresenter: CatInterface.Presenter? = null
    private var searchFilter = SearchModel()
    private var currentPage = searchFilter.pageNumber
    private var isRandom = true
    private val breedAdapter by lazy {
        context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, breedNameList) }
    }
    private val categoryAdapter by lazy {
        context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, categoryNameList) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userApi = HomeActivity.userApi
        setup()
        if (userApi != null) {
            catPresenter?.getRemoteCat(userApi!!, searchFilter)
        }
        catPresenter?.getRemoteBreed()
        catPresenter?.getRemoteCategory()
    }

    private fun setup() {
        catAdapter
        binding.rvCatImage.adapter = catAdapter
        catPresenter = PresenterProvider.catPresenter(this@ImageFragment)
        binding.selectorOrder.onItemSelectedListener =
            spinnerSelectedListener { adapterView, position ->
                val selection = adapterView?.getItemAtPosition(position).toString()
                val random = adapterView?.getItemAtPosition(0).toString()
                isRandom = selection == random
                val orderID = orderList[position]
                currentPage = 0
                binding.textPageNumber.text = (currentPage + 1).toString()
                searchFilter = searchFilter.copy(order = orderID, pageNumber = currentPage)
                callData()
            }

        breedAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.selectorBreed.adapter = breedAdapter
        binding.selectorBreed.onItemSelectedListener = spinnerSelectedListener { _, position ->
            val breedID = when (position) {
                NONE -> ""
                else -> breedList[position - 1].id
            }
            context?.let {
                binding.btnPrevious.disable(it)
                binding.btnNext.disable(it)
            }
            searchFilter = searchFilter.copy(breadID = breedID)
        }

        categoryAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.selectorCategory.adapter = categoryAdapter
        binding.selectorCategory.onItemSelectedListener = spinnerSelectedListener { _, position ->
            val categoryID = when (position) {
                NONE -> ""
                else -> categoryList[position - 1].id.toString()
            }
            context?.let {
                binding.btnPrevious.disable(it)
                binding.btnNext.disable(it)
            }
            searchFilter = searchFilter.copy(category = categoryID)
        }

        binding.btnRandom.setOnClickListener {
            binding.textPageNumber.text = PAGE_ONE.toString()
            currentPage = 0
            searchFilter = searchFilter.copy(pageNumber = currentPage)
            callData()
        }
        binding.btnNext.setOnClickListener {
            loadNewImage(true)
        }
        binding.btnPrevious.setOnClickListener {
            loadNewImage(false)
        }
    }

    override fun onGetBreedSuccess(breed: MutableList<BreedItem>) {
        breedNameList.clear()
        breedList.clear()
        breedList.addAll(breed)
        breedNameList.add(NONE, "None")
        breed.forEach {
            breedNameList.add(it.name)
        }
        breedAdapter?.notifyDataSetChanged()
    }

    override fun onGetCategorySuccess(breed: MutableList<CategoriesItem>) {
        categoryNameList.clear()
        categoryList.clear()
        categoryList.addAll(breed)
        categoryNameList.add(NONE, "None")
        breed.forEach {
            categoryNameList.add(it.name)
        }
        categoryAdapter?.notifyDataSetChanged()
    }

    private fun loadNewImage(buttonType: Boolean) {
        if (buttonType) {
            currentPage += 1
            searchFilter = searchFilter.copy(pageNumber = currentPage)
            val displayedPage = currentPage + 1
            binding.textPageNumber.text = displayedPage.toString()
            callData()
        } else {
            currentPage -= 1
            searchFilter = searchFilter.copy(pageNumber = currentPage)
            binding.textPageNumber.text = currentPage.toString()
            val displayedPage = currentPage + 1
            binding.textPageNumber.text = (displayedPage).toString()
            callData()
        }
    }

    private fun callData() {
        if (userApi != null) {
            binding.apply {
                context?.let {
                    btnRandom.disable(it)
                    btnPrevious.disable(it)
                    btnNext.disable(it)
                }
            }
            catPresenter?.getRemoteCat(userApi!!, searchFilter)
        }
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        binding.apply {
            binding.btnRandom.enable()
        }
        when (cat.size) {
            CAT_IMAGE_MAX_SIZE -> {
                binding.btnNext.enable()
                catAdapter.setData(cat)
            }
            NONE -> {
                context?.shortToast("Cat is Sleeping")
            }
            else -> {
                catAdapter.setData(cat)
            }
        }

        if (isRandom && currentPage == 0) {
            context?.let { binding.btnNext.disable(it) }
        }
        if (currentPage == NONE) {
            context?.let { binding.btnPrevious.disable(it) }
        } else {
            binding.btnPrevious.enable()
        }
    }

    private fun onClickItem(data: Cat) {
        val intent = Intent(context, BigImageActivity::class.java)
        intent.putExtra(IMAGE_URL, data.url)
        intent.putExtra(USER_API, userApi)
        intent.putExtra(IMAGE_ID, data.id)
        intent.putExtra(SCREEN_ADDRESS, IMAGE_SCREEN_TAG)
        startActivity(intent)
    }

    override fun onError(exception: Exception?) {
        context?.shortToast(exception.toString())
    }
}
