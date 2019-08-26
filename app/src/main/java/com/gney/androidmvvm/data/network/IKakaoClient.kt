package com.gney.androidmvvm.data.network

import com.gney.androidmvvm.data.network.response.SearchWebResponse
import io.reactivex.Single

interface IKakaoClient {

    fun getWebData(query: String, sort: String, page: Int, size: Int, target: String): Single<SearchWebResponse>
}