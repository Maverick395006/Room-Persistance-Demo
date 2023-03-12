package com.maverick.roompersistancedemo.data.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    @Embedded
    val address: Address,
    val photo: Bitmap
) : Parcelable

@Parcelize
data class Address(
    val streetNumber: Int,
    val streetName: String,
) : Parcelable
