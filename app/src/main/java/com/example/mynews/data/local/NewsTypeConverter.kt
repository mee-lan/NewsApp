package com.example.mynews.data.local

import androidx.core.app.RemoteInput
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.model.Source


@ProvidedTypeConverter
class NewsTypeConverter{
    @TypeConverter
    fun sourceToString(source: Source):String{
        return "${source.id},${source.name}"
    }
    @TypeConverter
    fun stringToSource(string: String):Source{
        val source = string.split(",")
        return Source(source[0],source[1])
    }

}