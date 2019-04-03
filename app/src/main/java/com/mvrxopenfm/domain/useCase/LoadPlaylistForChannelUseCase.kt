package com.mvrxopenfm.domain.useCase

import com.mvrxopenfm.Config
import com.mvrxopenfm.data.model.PlaylistResponse
import com.mvrxopenfm.data.network.ApiService
import com.mvrxopenfm.domain.model.Playlist
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoadPlaylistForChannelUseCase @Inject constructor(private val apiService: ApiService) {

    operator fun invoke(streamId: Long): Single<Playlist> {
        return apiService.loadPlaylist(streamId.toInt())
            .map { it.first() }
            .map(::mapper)
            .delay(1000, TimeUnit.MILLISECONDS)
    }

    private fun mapper(response: PlaylistResponse): Playlist {
        return response.tracks
            .asSequence()
            .map { Playlist(it.song.title, it.song.artist, Config.COVER_URL + it.song.album?.coverUri) }
            .first()
    }
}