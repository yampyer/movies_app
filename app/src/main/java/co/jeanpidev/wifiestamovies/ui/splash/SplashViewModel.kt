package co.jeanpidev.wifiestamovies.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.jeanpidev.wifiestamovies.base.BaseViewModel
import co.jeanpidev.wifiestamovies.repository.Result
import co.jeanpidev.wifiestamovies.repository.TokenRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val tokenRepository: TokenRepository): BaseViewModel() {

    private val tokenData = MutableLiveData<Result<Boolean>>()

    fun getTokenData() = tokenData

    fun getToken(email: String) {
        tokenData.postValue(Result.loading(true))
        viewModelScope.launch {
            val token = tokenRepository.getToken(email)
            tokenData.postValue(token)
        }
    }
}
