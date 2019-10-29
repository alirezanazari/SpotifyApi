package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    @SerializedName("artists")
    val artists: Artists
)