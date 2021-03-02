package com.ab.useralbum.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Aya Boussaadia on 01,March,2021
 */
@Entity(tableName = "user")

data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)