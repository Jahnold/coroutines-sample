package com.jahnold.coroutines.base.transformers

interface Transformer<I, O> {
    fun transform(input: I): O
}