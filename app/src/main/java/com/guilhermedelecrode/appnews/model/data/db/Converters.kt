package com.guilhermedelecrode.appnews.model.data.db

import androidx.room.TypeConverter
import com.guilhermedelecrode.appnews.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.name
    }

    @TypeConverter
    fun toSource(name: String?): Source {
        return Source(name ?: "", name ?: "")
    }
}