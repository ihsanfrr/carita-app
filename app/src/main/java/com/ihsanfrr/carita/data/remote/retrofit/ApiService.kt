package com.ihsanfrr.carita.data.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET
import okhttp3.RequestBody
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Field
import okhttp3.MultipartBody
import retrofit2.http.Query
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.FormUrlEncoded
import com.ihsanfrr.carita.data.remote.response.story.AddStoryResponse
import com.ihsanfrr.carita.data.remote.response.story.DetailStoryResponse
import com.ihsanfrr.carita.data.remote.response.authorization.LoginResponse
import com.ihsanfrr.carita.data.remote.response.story.GetAllStoriesResponse
import com.ihsanfrr.carita.data.remote.response.authorization.RegisterResponse

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("location") location: Int
    ) : Response<GetAllStoriesResponse>

    @Multipart
    @POST("stories")
    suspend fun postAddStory(
        @Header("Authorization") token: String,
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part
    ) : Response<AddStoryResponse>

    @GET("stories/{id}")
    suspend fun getDetailStory(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : Response<DetailStoryResponse>
}