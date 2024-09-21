package com.guilhermedelecrode.appnews.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.guilhermedelecrode.appnews.model.data.db.Converters
import java.io.Serializable


@Entity(tableName = "articles")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @TypeConverters(Converters::class)
    val source: Source?, // Agora o Source pode ser nulo,
    val title: String?,
    val url: String?,
    val urlToImage: String?
):Serializable