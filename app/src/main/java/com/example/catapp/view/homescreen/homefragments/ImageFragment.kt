package com.example.catapp.view.homescreen.homefragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapp.R
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.remote.BreedsRemoteDataSource
import com.example.catapp.data.source.remote.CatRemoteDataSource
import com.example.catapp.data.source.repository.BreedRepository
import com.example.catapp.data.source.repository.CatRepository
import com.example.catapp.databinding.FragmentImageBinding
import com.example.catapp.utils.*
import com.example.catapp.utils.base.BaseFragment
import com.example.catapp.view.adapter.CatImageAdapter
import com.example.catapp.view.homescreen.HomeActivity
import com.example.catapp.view.homescreen.homepresenter.BreedInterface
import com.example.catapp.view.homescreen.homepresenter.BreedPresenter
import com.example.catapp.view.presenter.CatInterface
import com.example.catapp.view.presenter.CatPresenter
import com.sun.mvp.data.repository.source.local.CatLocalDataSource
import java.lang.Exception

class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate),
    CatInterface.View, BreedInterface.View {

    private var userApi: String? = null
    private val catAdapter by lazy { CatImageAdapter(requireContext()) }
    private val breedNameList = mutableListOf<String>()
    private val breedAdapter by lazy { ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breedNameList) }

    private var catPresenter: CatInterface.Presenter? = null
    private var breedPresenter: BreedInterface.Presenter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as HomeActivity
        userApi = activity.userApi
        setup()
        setupSpinners()
        bindingButton()
        catPresenter?.getRemoteCat(userApi!!, LIMIT_9)
        breedPresenter?.getRemoteBreed()

    }

    private fun setup() {
        catAdapter
        binding.rvCatImage.layoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        binding.rvCatImage.adapter = catAdapter

        catPresenter = CatPresenter(
            CatRepository.getInstance(
                CatRemoteDataSource.getInstance(),
                CatLocalDataSource.getInstance()
            ), this@ImageFragment
        )

        breedPresenter = BreedPresenter(
            BreedRepository.getInstance(BreedsRemoteDataSource.getInstance()),
            this@ImageFragment
        )
    }

    private fun bindingButton() {
        binding.buttonPrevious.disable(requireContext())

        binding.buttonRandom.setOnClickListener {
            if (userApi != null) {
                catPresenter?.getRemoteCat(userApi!!, LIMIT_9)
            }
        }
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        if (cat.size < 9) {
            binding.buttonNext.disable(requireContext())
        } else {
            binding.buttonNext.enable()
        }
        catAdapter.setData(cat)
    }

    override fun onGetBreedSuccess(breed: MutableList<BreedItem>) {
        breedNameList.clear()
        breedNameList.add(0, "None")
        breed.forEach {
            breedNameList.add(it.name)
        }
        breedAdapter.notifyDataSetChanged()
    }

    override fun onError(exception: Exception?) {
        requireContext().shortToast(exception.toString())
    }

    private fun setupSpinners() {
        binding.selectorOrder.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selection = p0?.getItemAtPosition(position).toString()
                val random = p0?.getItemAtPosition(0).toString()
                if (selection == random) {
                    binding.buttonRandom.text = RANDOM
                } else {
                    binding.buttonRandom.text = SEARCH
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.selectorBreed.adapter = breedAdapter
        binding.selectorBreed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}
