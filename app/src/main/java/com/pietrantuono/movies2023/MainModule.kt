package com.pietrantuono.movies2023

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import com.pietrantuono.data.api.api.accesstoken.AccessTokenApiClient
import com.pietrantuono.data.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.api.interceptor.BearerTokenAuthInterceptor
import com.pietrantuono.data.api.tokenmanager.SharedPreferencesTokenManager
import com.pietrantuono.data.api.tokenmanager.TokenManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Authenticator
import okhttp3.Interceptor

@Module
@InstallIn(ActivityComponent::class)
interface MainModule {

    @Binds
    fun bindTokenManager(tokenManagerImpl: SharedPreferencesTokenManager): TokenManager

    @Binds
    fun bindRedditApiClient(apiClient: RetrofitAccessTokenApiClient): AccessTokenApiClient

    companion object {

        @Provides
        fun provideBearerTokenAuthInterceptor(tokenManager: TokenManager, accessTokenApiClient: RetrofitAccessTokenApiClient) =
            BearerTokenAuthInterceptor(
                tokenManager = tokenManager,
                accessTokenApiClient = accessTokenApiClient
            )

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context) =
            EncryptedSharedPreferences.create(
                "secret_shared_prefs",
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                AES256_SIV,
                AES256_GCM
            )
    }
}