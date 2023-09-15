package com.trakrsuite.trakrcounting.model

import com.google.gson.annotations.SerializedName

data class UserValidationApiResponse<T>(
    val result: String,
    val data: T,
    val hasVerifiedAccount: Boolean,
    val hasDuplicates: Boolean
)

data class UserValidationDataSingleLocation(
    val usrLocID: Int,
    val hasVerifiedAccount: Boolean,
    val hasDuplicates: Boolean
)

data class UserValidationDataMultipleLocations(
    val locations: List<Location>,
    val hasVerifiedAccount: Boolean,
    val hasDuplicates: Boolean
)

data class Location(
    val locID: Int,
    val locName: String
)

data class UserValidationRequest(
    @SerializedName("usrUsername") val usrUsername: String
)

data class PasswordValidationRequest(
    @SerializedName("usrUsername") val usrUsername: String,
    @SerializedName("usrPassword") val usrPassword: String,
    @SerializedName("usrLocID") val usrLocID: Int
)

