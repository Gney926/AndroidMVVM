package com.gney.androidmvvm.data.network

import com.gney.androidmvvm.BuildConfig
import com.gney.androidmvvm.data.network.api.KakaoServiceApi
import com.gney.androidmvvm.data.network.response.SearchWebResponse
import io.reactivex.Single

class KakaoClient(
    private val kakaoServiceApi: KakaoServiceApi
) : IKakaoClient {

    override fun getWebData(query: String, sort: String, page: Int, size: Int, target: String): Single<SearchWebResponse> {
        return kakaoServiceApi.searchWeb(
            authorization = "KakaoAK ${BuildConfig.API_KEY}",
            query = query,
            sort = sort,
            page = page,
            size = size,
            target = target
        )
    }
}