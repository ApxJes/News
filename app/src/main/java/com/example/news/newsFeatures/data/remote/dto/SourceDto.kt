package com.example.news.newsFeatures.data.remote.dto


import com.example.news.newsFeatures.domain.model.Source
import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) {
    fun toSource(): Source {
        return Source(
            id = id,
            name = name
        )
    }
}