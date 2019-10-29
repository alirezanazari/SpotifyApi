package ir.alirezanazari.data.model

data class AlbumModel(
    val items: List<AlbumItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)