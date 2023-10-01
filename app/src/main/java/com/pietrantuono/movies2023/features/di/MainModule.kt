package com.pietrantuono.movies2023.features.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.database.DatabaseClientImpl
import com.pietrantuono.data.database.ImageToPersistedImageEntityMapper
import com.pietrantuono.data.database.PostToPersistedPostEntityMapper
import com.pietrantuono.data.database.PostWithImagesEntityToPostMapper
import com.pietrantuono.data.database.RedditDatabase
import com.pietrantuono.data.network.api.api.accesstoken.AccessTokenApiClient
import com.pietrantuono.data.network.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.network.api.client.RedditApiClient
import com.pietrantuono.data.network.api.client.RetrofitRedditApiClient
import com.pietrantuono.data.network.api.interceptor.BearerTokenAuthInterceptor
import com.pietrantuono.data.network.api.tokenmanager.SharedPreferencesTokenManager
import com.pietrantuono.data.network.api.tokenmanager.TokenManager
import com.pietrantuono.data.networkchecker.NetworkChecker
import com.pietrantuono.data.networkchecker.NetworkCheckerImpl
import com.pietrantuono.data.repository.PostsRepositoryImpl
import com.pietrantuono.domain.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

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
    fun bindRedditRepository(repositoryImpl: PostsRepositoryImpl): PostsRepository

    @Binds
    fun bindNetworkChecker(networkCheckerImpl: NetworkCheckerImpl): NetworkChecker

    companion object {

        @Provides
        fun provideCoroutineContext():CoroutineContext = Dispatchers.IO

        @Provides
        fun provideConnectivityManager(@ApplicationContext context: Context) =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        @Provides
        fun provideBearerTokenAuthInterceptor(tokenManager: TokenManager, accessTokenApiClient: RetrofitAccessTokenApiClient) =
            BearerTokenAuthInterceptor(
                tokenManager = tokenManager, accessTokenApiClient = accessTokenApiClient
            )

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context) = EncryptedSharedPreferences.create(
            "secret_shared_prefs", MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), context, AES256_SIV, AES256_GCM
        )

        @Provides
        fun bindDatabaseClient(@ApplicationContext applicationContext: Context): DatabaseClient {
            val database = Room.databaseBuilder(
                applicationContext,
                RedditDatabase::class.java, "database-reddit"
            ).build()
            return DatabaseClientImpl(
                redditDatabase = database,
                redditDao = database.redditDao(),
                postToPersistedPostEntityMapper = PostToPersistedPostEntityMapper(),
                imageToPersistedImageEntityMapper = ImageToPersistedImageEntityMapper(),
                postWithImagesEntityToPostMapper = PostWithImagesEntityToPostMapper()
            )
        }
    }
}