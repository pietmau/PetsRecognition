package com.pietrantuono.domain

interface UseCase<Params, Output> {

    suspend fun execute(input: Params): Output
}
