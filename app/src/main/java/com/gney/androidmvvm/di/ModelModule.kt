package com.gney.androidmvvm.di

import com.gney.androidmvvm.data.network.KakaoClient
import org.koin.dsl.module

val modelModule = module {
    factory {
        KakaoClient(get())
    }
}
