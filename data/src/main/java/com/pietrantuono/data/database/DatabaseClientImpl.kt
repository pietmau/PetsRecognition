package com.pietrantuono.data.database

import androidx.room.withTransaction
import com.pietrantuono.domain.model.reddit.Post

class DatabaseClientImpl(
    private val redditDatabase: RedditDatabase,
    private val redditDao: RedditDao,
    private val postToPersistedPostEntityMapper: PostToPersistedPostEntityMapper,
    private val imageToPersistedImageEntityMapper: ImageToPersistedImageEntityMapper,
    private val postWithImagesEntityToPostMapper: PostWithImagesEntityToPostMapper,
) : DatabaseClient {

    override suspend fun insertPosts(posts: List<Post>) {
        redditDatabase.withTransaction {
            posts.forEach { post ->
                redditDao.insert(postToPersistedPostEntityMapper.map(post))
                post.images.forEach { image ->
                    redditDao.insert(imageToPersistedImageEntityMapper.map(post.name to image))
                }
            }
        }
    }

    override suspend fun getPosts(key: String?, limit: Int) =
        key?.let { redditDao.getPosts(key).map { postWithImagesEntityToPostMapper.map(it) } }
            ?: redditDao.getPosts(limit).map { postWithImagesEntityToPostMapper.map(it) }
}