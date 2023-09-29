package com.pietrantuono.data.network

import com.pietrantuono.data.network.model.reddit.NetworkData
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() : Mapper<NetworkData, Post> {
    override suspend fun map(input: NetworkData): Post {
        TODO("Not yet implemented")
    }
}