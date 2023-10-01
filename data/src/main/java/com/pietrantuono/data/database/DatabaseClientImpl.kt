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

    override suspend fun getLatestPosts(limit: Int) = redditDao.getLatestPosts(limit).map { postWithImagesEntityToPostMapper.map(it) }

    override suspend fun getPostsAfter(index: Long, limit: Int): List<Post> =
        redditDao.getPostsAfter(index, limit).map { postWithImagesEntityToPostMapper.map(it) }
}