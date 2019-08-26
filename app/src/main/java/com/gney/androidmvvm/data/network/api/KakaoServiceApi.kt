package com.gney.androidmvvm.data.network.api

import com.gney.androidmvvm.data.network.response.SearchWebResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoServiceApi {

    @GET("/v3/search/book")
    fun searchWeb(
        @Header("Authorization")
        authorization: String,

        @Query("query")
        query: String,

        @Query("sort")
        sort: String,

        @Query("page")
        page: Int,

        @Query("size")
        size: Int,

        @Query("target")
        target: String
    ): Single<SearchWebResponse>
}