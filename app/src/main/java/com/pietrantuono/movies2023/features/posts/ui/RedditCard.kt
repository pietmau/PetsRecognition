package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pietrantuono.domain.model.reddit.Post

@Composable
fun RedditCard(
    modifier: Modifier = Modifier,
    post: Post = Post(title = "Title", createdUtc = 0, name = "", id = ""),
) {
    Card(
        modifier = modifier
            .padding(top = 4.dp, start = 4.dp, end = 4.dp)
            .defaultMinSize(minHeight = 56.dp)//TODO Externzalize
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThumbNailImage(post)
            Column(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = post.author ?: "", // TODO add better default
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = post.title ?: "", // TODO add better default
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
private fun ThumbNailImage(post: Post = Post(title = "Title", createdUtc = 0, name = "", id = "")) {
    var hideImage by remember { mutableStateOf(false) }
    if (!hideImage) {
        AsyncImage(
            modifier = Modifier
                .width(56.dp)
                .height(56.dp)
                .padding(4.dp),
            model = post.thumbnail,
            contentDescription = post.title,
            onError = { hideImage = true }
        )
    } else {
        Spacer(modifier = Modifier.width(4.dp))
    }
}

