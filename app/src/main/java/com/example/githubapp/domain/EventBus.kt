package com.example.githubapp.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class EventBus<T : Any> {
    private val bus = PublishSubject.create<T>()

    fun postValue(event: T) {
        bus.onNext(event)
    }

    fun get(): Observable<T> = bus
}