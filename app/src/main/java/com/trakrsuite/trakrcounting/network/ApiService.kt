package com.trakrsuite.trakrcounting.network
import com.trakrsuite.trakrcounting.model.PasswordValidationRequest
import com.trakrsuite.trakrcounting.model.UserValidationApiResponse
import com.trakrsuite.trakrcounting.model.UserValidationRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("validate-account")
    fun validateUser(@Body request: UserValidationRequest): Call<ResponseBody>

    @POST("validate-password")
    fun validatePassword(@Body request: PasswordValidationRequest): Call<ResponseBody>
}
