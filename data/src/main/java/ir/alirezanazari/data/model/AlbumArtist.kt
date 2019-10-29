package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class AlbumArtist(
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)