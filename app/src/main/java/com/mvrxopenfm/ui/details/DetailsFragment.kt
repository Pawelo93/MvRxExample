package com.mvrxopenfm.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.mvrxopenfm.R
import com.mvrxopenfm.domain.model.Channel
import com.mvrxopenfm.domain.model.Playlist
import com.mvrxopenfm.ui.favoriteGroup.FavoriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseMvRxFragment() {

    private val viewModel: DetailsViewModel by fragmentViewModel()
    private val favoriteViewModel: FavoriteViewModel by existingViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun invalidate() = withState(viewModel) { state ->

        setupFavorite()

        when(state.request) {
            is Loading -> { progressBar.visibility = View.VISIBLE; return@withState }
            else -> progressBar.visibility = View.GONE
        }

        playlistTextView.text = getPlaylistName(state.playlist)

        state.playlist.albumCover?.let {
            Picasso.get()
                .load(it)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(albumCoverImageView)
        }
    }

    private fun setupFavorite() = withState(viewModel, favoriteViewModel) { state, favoriteState ->
        val channel: Channel? = favoriteState.favoriteChannelsGroup.channels
            .find { it.streamId == state.streamId }

        if (channel != null)
            favoriteImageView.setImageResource(R.drawable.ic_favorite_selected)
        else
            favoriteImageView.setImageResource(R.drawable.ic_favorite)

        favoriteImageView.setOnClickListener {
            favoriteViewModel.toggleFavorite(state.channel)
        }
    }

    private fun getPlaylistName(playlist: Playlist) = "${playlist.artist} - ${playlist.title}"
}