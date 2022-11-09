package com.example.catapp.view.homescreen.homefragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.databinding.FragmentBreedBinding
import com.example.catapp.utils.BREED_ID
import com.example.catapp.utils.BREED_IMG_URL
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.shortToast
import com.example.catapp.view.adapter.BreedAdapter
import com.example.catapp.view.homescreen.breeddetail.BreedDetailActivity
import com.example.catapp.view.homescreen.breedscreenpresenter.BreedInterface
import java.lang.Exception

class BreedFragment : Fragment(), BreedInterface.View {

    private val binding by lazy { FragmentBreedBinding.inflate(layoutInflater) }
    private var breedPresenter: BreedInterface.Presenter? = null
    private val breedAdapter by lazy { BreedAdapter(::onClickItem)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        breedPresenter?.getRemoteBreed()
    }

    private fun setup(){
        binding.rvCatBreed.adapter = breedAdapter
        breedPresenter = PresenterProvider.breedPresenter(this@BreedFragment)
    }

    private fun onClickItem(breed: BreedItem) {
        val intent = Intent(context, BreedDetailActivity::class.java)
        intent.putExtra(BREED_ID, breed.id)
        intent.putExtra(BREED_IMG_URL, breed.image.url)
        startActivity(intent)
    }

    override fun onGetBreedSuccess(breed: MutableList<BreedItem>) {
        breedAdapter.setData(breed)
    }

    override fun onError(exception: Exception?) {
        context?.shortToast(exception.toString())
    }
}
