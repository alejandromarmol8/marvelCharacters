package com.app.marvelcharacters.data.detaildata

data class CharacterDetailData(
    val name: String = "",
    val description: String = "",
    val thumbnail: ThumbnailData,
    val comics: ComicsData,
    val series: SeriesData,
    val stories: StoriesData,
    val events: EventsData
)