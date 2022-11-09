package com.example.catapp.utils

import com.example.catapp.data.source.remote.*
import com.example.catapp.data.source.repository.*
import com.example.catapp.view.homescreen.breeddetail.BreedDetailInterface
import com.example.catapp.view.homescreen.breeddetail.BreedDetailPresenter
import com.example.catapp.view.homescreen.breedscreenpresenter.BreedInterface
import com.example.catapp.view.homescreen.breedscreenpresenter.BreedPresenter
import com.example.catapp.view.homescreen.favpresenter.FavInterface
import com.example.catapp.view.homescreen.favpresenter.FavPresenter
import com.example.catapp.view.homescreen.favpresenter.singlefavpresenter.SingleFavInterface
import com.example.catapp.view.homescreen.favpresenter.singlefavpresenter.SingleFavPresenter
import com.example.catapp.view.homescreen.imagescreenpresenter.CatInterface
import com.example.catapp.view.homescreen.imagescreenpresenter.CatPresenter
import com.example.catapp.view.loginscreen.LoginInterface
import com.example.catapp.view.loginscreen.LoginPresenter

object PresenterProvider {

    fun catPresenter(view: CatInterface.View) = CatPresenter(
        CatRepository.getInstance(
            CatRemoteDataSource.getInstance()
        ), view
    )

    fun loginPresenter(view: LoginInterface.View) = LoginPresenter(
        LoginRepository.getInstance(
            LoginRemoteDataSource.getInstance()
        ), view
    )

    fun breedPresenter(view: BreedInterface.View) = BreedPresenter(
        BreedRepository.getInstance(
           BreedsRemoteDataSource.getInstance()
        ), view
    )

    fun breedDetailPresenter(view: BreedDetailInterface.View) = BreedDetailPresenter(
        BreedDetailRepository.getInstance(
            BreedsDetailRemoteDataSource.getInstance()
        ), view
    )

    fun favPresenter(view: FavInterface.View) = FavPresenter(
        FavRepository.getInstance(
            FavRemoteDataSource.getInstance()
        ), view
    )

    fun singleFavPresenter(view: SingleFavInterface.View) = SingleFavPresenter(
        SingleFavRepository.getInstance(
            SingleFavRemoteDataSource.getInstance()
        ), view
    )
}
