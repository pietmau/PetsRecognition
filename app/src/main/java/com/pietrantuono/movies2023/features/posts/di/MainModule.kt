package com.pietrantuono.movies2023.features.posts.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import com.pietrantuono.data.network.RetrofitRepository
import com.pietrantuono.data.network.api.api.accesstoken.AccessTokenApiClient
import com.pietrantuono.data.network.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.network.api.api.reddit.RedditApiClient
import com.pietrantuono.data.network.api.api.reddit.RetrofitRedditApiClient
import com.pietrantuono.data.network.api.interceptor.BearerTokenAuthInterceptor
import com.pietrantuono.data.network.api.tokenmanager.SharedPreferencesTokenManager
import com.pietrantuono.data.network.api.tokenmanager.TokenManager
import com.pietrantuono.domain.RedditRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    fun bindTokenManager(tokenManagerImpl: SharedPreferencesTokenManager): TokenManager

    @Binds
    fun bindAccessTokenApiClient(apiClient: RetrofitAccessTokenApiClient): AccessTokenApiClient

    @Binds
    fun bindRedditApiClient(apiClient: RetrofitRedditApiClient): RedditApiClient

    @Binds
    fun bindRepository(repository: RetrofitRepository): RedditRepository

    companion object {

        @Provides
        fun provideBearerTokenAuthInterceptor(tokenManager: TokenManager, accessTokenApiClient: RetrofitAccessTokenApiClient) =
            BearerTokenAuthInterceptor(
                tokenManager = tokenManager, accessTokenApiClient = accessTokenApiClient
            )

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context) = EncryptedSharedPreferences.create(
            "secret_shared_prefs", MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), context, AES256_SIV, AES256_GCM
        )
    }
}