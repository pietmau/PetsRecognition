package com.pietrantuono.movies2023

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import com.pietrantuono.data.api.RedditApiClient
import com.pietrantuono.data.api.RetrofitRedditApiClient
import com.pietrantuono.data.api.authenticator.RedditAuthenticator
import com.pietrantuono.data.api.tokenmanager.SharedPreferencesTokenManager
import com.pietrantuono.data.api.tokenmanager.TokenManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Authenticator

@Module
@InstallIn(ActivityComponent::class)
interface MainModule {

    @Binds
    fun bindTokenManager(tokenManagerImpl: SharedPreferencesTokenManager): TokenManager

    @Binds
    fun bindRedditApiClient(apiClient: RetrofitRedditApiClient): RedditApiClient

    @Binds
    fun bindAuthenticator(authenticator: RedditAuthenticator): Authenticator

    companion object {

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