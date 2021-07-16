package com.dmitri.projectapifootballv2.network

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface NetworkStatus {
    fun isOnline(): BehaviorSubject<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}