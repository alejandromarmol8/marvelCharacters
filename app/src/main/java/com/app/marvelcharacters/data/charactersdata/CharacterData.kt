package com.app.marvelcharacters.data.charactersdata

import com.app.marvelcharacters.data.detaildata.ThumbnailData

data class CharacterData(
    val id: Int,
    val name: String = "",
    val thumbnail: ThumbnailData
)

