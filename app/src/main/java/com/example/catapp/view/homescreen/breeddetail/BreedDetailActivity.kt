package com.example.catapp.view.homescreen.breeddetail

import android.content.Intent
import android.os.Bundle
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.breeds.Rating
import com.example.catapp.databinding.ActivityBreedDetailBinding
import com.example.catapp.utils.BREED_ID
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.BREED_IMG_URL
import com.example.catapp.utils.WEB_URL
import com.example.catapp.utils.NONE
import com.example.catapp.utils.shortToast
import com.example.catapp.utils.loadCoverImage
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.WebViewActivity
import com.example.catapp.view.adapter.RatingAdapter
import java.lang.Exception

class BreedDetailActivity :
    BaseActivity<ActivityBreedDetailBinding>(ActivityBreedDetailBinding::inflate),
    BreedDetailInterface.View {

    private var breedDetailPresenter: BreedDetailInterface.Presenter? = null
    private val ratingAdapter by lazy { RatingAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        val id = intent.getStringExtra(BREED_ID)
        if (id != null) {
            breedDetailPresenter?.getRemoteBreed(id)
        }
    }

    private fun setup() {
        binding.rvRating.adapter = ratingAdapter
        breedDetailPresenter = PresenterProvider.breedDetailPresenter(this@BreedDetailActivity)
    }

    override fun onGetBreedSuccess(breed: BreedItem) {
        val url = intent.getStringExtra(BREED_IMG_URL)
        if (url != null) {
            this.loadCoverImage(url, binding.imgBreed)
        }
        binding.apply {
            textBreedName.text = breed.name
            textDetail.text = breed.description
            textBreedLifeSpanContent.text = breed.lifeSpan
            textBreedOriginContent.text = breed.origin
            textWiki.setOnClickListener {
                val intent = Intent(this@BreedDetailActivity, WebViewActivity::class.java)
                intent.putExtra(WEB_URL, breed.wikipediaUrl)
                startActivity(intent)
            }
        }
        val listRatingName = mutableListOf(
            "Child Friendly",
            "Dog Friendly",
            "Adaptability",
            "Health Issues",
            "Intelligence"
        )
        val listRating = mutableListOf(
            breed.childFriendly,
            breed.dogFriendly,
            breed.adaptability,
            breed.healthIssues,
            breed.intelligence
        )
        val list = mutableListOf<Rating>()
        var position = NONE
        listRatingName.forEach{
            list.add(Rating(it, listRating[position]))
            position += 1
        }
        ratingAdapter.setData(list)
    }

    override fun onError(exception: Exception?) {
        this.shortToast(exception.toString())
    }
}
