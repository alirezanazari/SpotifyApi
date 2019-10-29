package ir.alirezanazari.spotifyapi.ui.search

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.spotifyapi.R
import kotlinx.android.synthetic.main.row_artist.*


class ArtistSearchItem (
    val artist : ArtistEntity
): Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            ra_name.text = artist.name
            setArtistImage()
        }
    }

    override fun getLayout() = R.layout.row_artist


    private fun ViewHolder.setArtistImage() {
        //todo:// create image loader class and use instead of public glide
        Glide.with(this.containerView)
            .load(artist.picture)
            .into(ra_image)
    }

}