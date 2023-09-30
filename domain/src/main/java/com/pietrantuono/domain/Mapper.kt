package com.pietrantuono.domain

interface Mapper<In, Out> {

    fun map(input: In): Out
}
