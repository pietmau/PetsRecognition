package com.pietrantuono.data.database.model

data class DatabaseImages(
    val resolutions: List<DatabaseResolutions> = emptyList(),
)

data class DatabaseResolutions(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)