package com.gney.androidmvvm.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchWebResponse(
    @SerializedName("documents")
    var documents: List<Document>,

    @SerializedName("meta")
    var meta: Meta
) {
    data class Document(
        @SerializedName("title")
        var title: String,

        @SerializedName("contents")
        var contents: String,

        @SerializedName("url")
        var url: String,

        @SerializedName("isbn")
        var isbn: String,

        @SerializedName("datetime")
        var datetime: String,

        @SerializedName("authors")
        var authors: List<String>,

        @SerializedName("publisher")
        var publisher: String,

        @SerializedName("translators")
        var translators: List<String>,

        @SerializedName("price")
        var price: String,

        @SerializedName("sale_price")
        var sale_price: String,

        @SerializedName("thumbnail")
        var thumbnail: String
    )

    data class Meta(
        @SerializedName("total_count")
        var total_count: Int,

        @SerializedName("pageable_count")
        var pageable_count: Int,

        @SerializedName("is_end")
        var is_end: Boolean
    )
}