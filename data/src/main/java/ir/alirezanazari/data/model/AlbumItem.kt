package ir.alirezanazari.data.model

data class AlbumItem(
    val album_group: String,
    val album_type: String,
    val artists: List<AlbumArtist>,
    val id: String,
    val images: List<ArtistImage>,
    val name: String,
    val release_date: String,
    val type: String,
    val uri: String
)