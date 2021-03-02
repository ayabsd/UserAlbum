package com.ab.useralbum.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab.useralbum.model.User

/**
 * Created by Aya Boussaadia on 02,March,2021
 */


@Dao
interface Dao {

    @Query("SELECT * FROM user")
    fun getAllUsers() : LiveData<List<User>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(characters: List<User>)


}