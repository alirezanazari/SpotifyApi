package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    @SerializedName("album_group")
    val albumGroup: String,
    @SerializedName("album_type")
    val albumType: String,
    @SerializedName("artists")
    val artists: List<AlbumArtist>,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ArtistImage>,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)