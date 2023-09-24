package com.pietrantuono.data.database.model

data class DatabaseContent(
    val after: String? = null,
    val children: List<DatabasePost> = emptyList(),
    val before: String? = null
)