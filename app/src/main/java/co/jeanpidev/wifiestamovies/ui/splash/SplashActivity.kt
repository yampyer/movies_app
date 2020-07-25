package co.jeanpidev.wifiestamovies.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.jeanpidev.wifiestamovies.R
import co.jeanpidev.wifiestamovies.base.BaseActivity
import co.jeanpidev.wifiestamovies.databinding.ActivitySplashBinding
import co.jeanpidev.wifiestamovies.ui.listmovie.ListMovieActivity
import co.jeanpidev.wifiestamovies.repository.Result
import co.jeanpidev.wifiestamovies.utils.extension.app
import co.jeanpidev.wifiestamovies.utils.helpers.AccountHelper
import com.bumptech.glide.Glide
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var accountHelper: AccountHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.component.splashComponent().create().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)

        initUI()

        if (accountHelper.isTokenAvailable) goToListMovieActivity()
        else {
            initObservers()
            viewModel.getToken("getmytoken@domain.com")
        }
    }

    private fun goToListMovieActivity() {
        startActivity(Intent(this, ListMovieActivity::class.java))
        finish()
    }

    private fun initUI() {
        Glide.with(this).load(R.drawable.loader).into(binding.ivLoader)
    }

    private fun initObservers() {
        viewModel.getTokenData().observe(this, Observer {
            when (it) {
                is Result.Failure -> {
                    //toggleProgress(false)
                    //showSnackBar(getString(R.string.bad_credentials))
                }
                is Result.Success -> {
                    //toggleProgress(false)
                    goToListMovieActivity()
                }
            }
        })
    }
}
