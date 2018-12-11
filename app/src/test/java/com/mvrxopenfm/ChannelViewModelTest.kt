package com.mvrxopenfm

import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.withState
import com.mvrxopenfm.domain.model.ChannelsGroup
import com.mvrxopenfm.domain.useCase.LoadChannelGroupsUseCase
import com.mvrxopenfm.ui.channels.ChannelGroupState
import com.mvrxopenfm.ui.channels.ChannelViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChannelViewModelTest {

    @get:Rule
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var loadChannelGroupsUseCase: LoadChannelGroupsUseCase

    lateinit var viewModel: ChannelViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ChannelViewModel(
            ChannelGroupState(),
            loadChannelGroupsUseCase
        )
    }

    @Test
    fun testFetchChannels_Success() {
        val channelsGroup = listOf(ChannelsGroup(name = "Test"), ChannelsGroup(name = "Test2"))
        whenever(loadChannelGroupsUseCase()).thenReturn(Single.just(channelsGroup))

        viewModel.fetchChannels()

        withState(viewModel) {
            assertEquals(channelsGroup, it.channelsGroups)
        }
    }

    @Test
    fun testFetchChannels_Failure() {
        val throwable = Throwable("error")
        whenever(loadChannelGroupsUseCase()).thenReturn(Single.error(throwable))

        viewModel.fetchChannels()

        withState(viewModel) {
            assertTrue(it.request is Fail)
            assertEquals(throwable, (it.request as Fail).error)
        }
    }

    @Test
    fun testToggleExpand_Hide() {
        val channelGroupName = "Polecane"

        viewModel.toggleExpand(channelGroupName)

        withState(viewModel) {
            assertTrue(it.hiddenGroups.contains(channelGroupName))
        }
    }

    @Test
    fun testToggleExpand_Show() {
        val channelGroupName = "Polecane"
        val state = ChannelGroupState(hiddenGroups = listOf(channelGroupName))
        viewModel = ChannelViewModel(state, loadChannelGroupsUseCase)

        viewModel.toggleExpand(channelGroupName)

        withState(viewModel) {
            assertTrue(it.hiddenGroups.isEmpty())
        }
    }
}