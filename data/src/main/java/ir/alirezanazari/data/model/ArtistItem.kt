package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class ArtistItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ArtistImage>,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)