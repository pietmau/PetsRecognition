package com.pietrantuono.domain.model.reddit

data class Images(
    val resolutions: List<Resolutions> = emptyList(),
)

data class Resolutions(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)