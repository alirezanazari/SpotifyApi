package ir.alirezanazari.data.model

data class ArtistItem(
    val id: String,
    val images: List<ArtistImage>,
    val name: String,
    val type: String,
    val uri: String
)