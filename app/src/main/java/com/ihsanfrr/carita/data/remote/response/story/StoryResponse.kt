package com.ihsanfrr.carita.data.remote.response.story

import com.google.gson.annotations.SerializedName

data class AddStoryResponse(
    @SerializedName("error")
    val error: Boolean? = null,

    @SerializedName("message")
    val message: String? = null
)

data class GetAllStoriesResponse(
    @SerializedName("listStory")
    val listStory: List<ListStoryItem>? = null,

    @SerializedName("error")
    val error: Boolean? = null,

    @SerializedName("message")
    val message: String? = null
)

data class ListStoryItem(
    @SerializedName("photoUrl")
    val photoUrl: String? = null,

    @SerializedName("createdAt")
    val createdAt: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("lon")
    val lon: Any? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("lat")
    val lat: Any? = null
)

data class DetailStoryResponse(
    @SerializedName("error")
    val error: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("story")
    val story: ListStoryItem? = null
)
