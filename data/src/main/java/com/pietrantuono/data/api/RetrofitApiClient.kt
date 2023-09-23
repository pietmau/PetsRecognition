package com.pietrantuono.data.api

import javax.inject.Inject
import retrofit2.Retrofit


//class RetrofitApiClient @Inject constructor() : ApiClient {
//    private var api = Retrofit.Builder()// TODO reuse!!!
//        .baseUrl(BASE_URL)
//        .build().create(Api::class.java)
//
//    override suspend fun getTrendingMovies(page: Int) = api.getTrendingMovies(page)
//
//    private companion object {
//        private const val BASE_URL = "https://api.themoviedb.org/" // TODO move to gradle
//    }
//}