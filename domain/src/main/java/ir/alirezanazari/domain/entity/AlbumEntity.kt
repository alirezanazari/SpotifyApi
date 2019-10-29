package ir.alirezanazari.domain.entity


data class AlbumEntity (
    val id : String ,
    val name : String ,
    val artist : String , //can be list of artists
    val picture : String , //can be a single object of picture types
    val uri : String
)