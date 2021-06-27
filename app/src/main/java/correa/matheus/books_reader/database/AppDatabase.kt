package correa.matheus.books_reader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import correa.matheus.books_reader.database.dao.BookDAO
import correa.matheus.books_reader.model.Book

@Database(
    entities = [
        Book::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBookDAO(): BookDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "bookworm.db"
                ).build()
            }
            return INSTANCE as AppDatabase
        }
    }
}