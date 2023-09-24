package com.pietrantuono.domain

class GetPostsUseCase : UseCase<String, String> {
    override suspend fun execute(input: String): String {
        return "Hello $input"
    }

    data class Params(val before: String? = null, val after: String? = null, val limit: Int? = null)
}