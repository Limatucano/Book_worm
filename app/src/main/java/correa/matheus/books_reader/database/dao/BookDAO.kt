package correa.matheus.books_reader.database.dao

import androidx.room.*
import correa.matheus.books_reader.model.Book

@Dao
interface BookDAO {

    // CREATE
    @Insert
    suspend fun insert(book: Book)

    // READ
    @Transaction
    @Query("SELECT * FROM Book WHERE id = :bookId")
    suspend fun findById(bookId: Long): Book

    @Transaction
    @Query("SELECT * FROM Book WHERE isbn = :isbn")
    suspend fun findBy(isbn: String): Book

    // UPDATE
    @Update
    suspend fun update(book: Book)

    // DELETE
    @Delete
    suspend fun delete(book: Book)

    // LIST
    @Query("SELECT * FROM Book")
    suspend fun listAll(): List<Book>

    // SEARCH
//    @Transaction
//    @Query("SELECT * FROM Book WHERE isbn LIKE :isbn OR title LIKE :title OR description LIKE :description")
//    suspend fun search(
//        isbn: String = "",
//        title: String = "",
//        description: String = ""
//    ): List<Book>
}