package com.example.dreamorganizer.UseCase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in T, out O> : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    abstract suspend fun execute(paramrs: T): O
}