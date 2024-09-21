package com.guilhermedelecrode.appnews.model.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guilhermedelecrode.appnews.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDAO(): ArticleDAO

    companion object {

        @Volatile
        private var instance: ArticleDataBase? = null
        private val Lock = Any()

        //Chamado quando é criado a instancia do DB e garante que o codigo so pode ser acessado por uma thread de cada vez
        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createdDatabase(context).also { articleDataBase ->
                instance = articleDataBase
            }
        }

        //Function para criar o data base
        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_db.db"
            ).build()
    }
}