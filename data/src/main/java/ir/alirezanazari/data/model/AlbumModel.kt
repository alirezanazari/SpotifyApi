package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName("items")
    val items: List<AlbumItem>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("total")
    val total: Int
)