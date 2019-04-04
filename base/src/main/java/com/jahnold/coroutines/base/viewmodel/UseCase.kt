package com.jahnold.coroutines.base.viewmodel

import io.reactivex.Observable

interface UseCase0<O> {
    fun getAction(): Observable<O>
}

interface UseCase1<in I, O> {
    fun getAction(input: I): Observable<O>
}

interface CoUseCase0<O> {
    suspend fun getAction(): O
}

interface CoUseCase1<I,O> {
    suspend fun getAction(input: I): O
}