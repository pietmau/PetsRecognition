package com.pietrantuono.domain

interface Mapper<In, Out> {

    suspend fun map(input: In): Out
}
