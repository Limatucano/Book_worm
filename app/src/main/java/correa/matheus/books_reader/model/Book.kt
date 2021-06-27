package correa.matheus.books_reader.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var isbn: String? = null,
    var title: String? = null,
    var description: String? = null,
)