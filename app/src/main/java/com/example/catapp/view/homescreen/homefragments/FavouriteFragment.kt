package com.example.catapp.view.homescreen.homefragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.databinding.FragmentFavouriteBinding
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.USER_API
import com.example.catapp.utils.IMAGE_ID
import com.example.catapp.utils.IMAGE_URL
import com.example.catapp.utils.SCREEN_ADDRESS
import com.example.catapp.utils.FAV_SCREEN_TAG
import com.example.catapp.utils.RELOAD_FAV
import com.example.catapp.utils.shortToast
import com.example.catapp.view.adapter.FavImageAdapter
import com.example.catapp.view.homescreen.BigImageActivity
import com.example.catapp.view.homescreen.HomeActivity
import com.example.catapp.view.homescreen.favpresenter.FavInterface
import java.lang.Exception

class FavouriteFragment : Fragment(), FavInterface.View {

    private val binding by lazy { FragmentFavouriteBinding.inflate(layoutInflater) }
    private var userApi: String? = null
    private var favPresenter: FavInterface.Presenter? = null
    private var isRegistered = false
    private val imgAdapter by lazy { FavImageAdapter(::onClickItem) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startReceiver()
        setup()
        if (userApi != null) {
            favPresenter?.getRemoteFav(userApi!!)
        }
    }

    private fun setup() {
        userApi = HomeActivity.userApi
        binding.rvCatFav.adapter = imgAdapter
        favPresenter = PresenterProvider.favPresenter(this@FavouriteFragment)
    }

    private fun onClickItem(data: FavouriteItem) {
        val intent = Intent(context, BigImageActivity::class.java)
        intent.putExtra(USER_API, userApi)
        intent.putExtra(IMAGE_ID, data.id.toString())
        intent.putExtra(IMAGE_URL, data.image.url)
        intent.putExtra(SCREEN_ADDRESS, FAV_SCREEN_TAG)
        startActivity(intent)
    }

    override fun onGetFavSuccess(favList: MutableList<FavouriteItem>) {
        imgAdapter.setData(favList.reversed())
    }

    override fun onError(exception: Exception?) {
        context?.shortToast(exception.toString())
    }

    private var favReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == RELOAD_FAV) {
                if (userApi != null) {
                    favPresenter?.getRemoteFav(userApi!!)
                }
            }
        }
    }

    private fun startReceiver() {
        if (!isRegistered) {
            val intentFilter = IntentFilter()
            intentFilter.addAction(RELOAD_FAV)
            context?.registerReceiver(favReceiver, intentFilter)
            isRegistered = true
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (isRegistered) {
            context?.unregisterReceiver(favReceiver)
        }
    }
}
