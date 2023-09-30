package com.pietrantuono.data.database

import androidx.room.withTransaction
import com.pietrantuono.domain.model.reddit.Post

class DatabaseClientImpl(
    private val redditDatabase: RedditDatabase,
    private val redditDao: RedditDao,
    private val postMapper: PostMapper,
    private val imageMapper: ImageMapper,
) : DatabaseClient {

    override suspend fun insertPosts(posts: List<Post>) {
        redditDatabase.withTransaction {
            posts.forEach { post ->
                redditDao.insert(postMapper.map(post))
                post.images.forEach { image ->
                    redditDao.insert(imageMapper.map(post.name to image))
                }
            }
        }
    }
}