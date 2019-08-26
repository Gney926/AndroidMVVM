package com.gney.androidmvvm.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gney.androidmvvm.data.database.converter.DateConverter
import com.gney.androidmvvm.data.network.response.SearchWebResponse
import java.io.Serializable
import java.util.*


@Entity(tableName = "bookmark")
@TypeConverters(DateConverter::class)
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "created")
    val created: Date
): Serializable {
    companion object {
        fun to(document: SearchWebResponse.Document): Bookmark {
            return Bookmark(
                thumbnail = document.thumbnail,
                title = document.title,
                price = document.sale_price,
                publisher = document.publisher,
                created = Date()
            )
        }
    }
}