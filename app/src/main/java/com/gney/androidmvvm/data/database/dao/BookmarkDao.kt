package com.gney.androidmvvm.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.gney.androidmvvm.data.database.entity.Bookmark


@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Bookmark>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmark: Bookmark)


    @Delete
    fun delete(bookmark: Bookmark)
}