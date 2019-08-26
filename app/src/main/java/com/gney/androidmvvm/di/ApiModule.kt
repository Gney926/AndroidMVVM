package com.gney.androidmvvm.di

import com.gney.androidmvvm.data.network.api.KakaoServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        get<Retrofit>().create(KakaoServiceApi::class.java)
    }
}