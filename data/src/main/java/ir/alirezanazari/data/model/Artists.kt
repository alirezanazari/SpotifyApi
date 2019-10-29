package ir.alirezanazari.data.model

data class Artists(
    val items: List<ArtistItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)