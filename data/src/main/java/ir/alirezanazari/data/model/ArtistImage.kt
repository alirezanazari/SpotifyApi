package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class ArtistImage(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)