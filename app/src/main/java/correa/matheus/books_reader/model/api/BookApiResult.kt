package correa.matheus.books_reader.model.api

data class BooksApiResult(
    val items: List<BookResult>
)

data class BookResult(
    val volumeInfo: Book,
)

data class Book(
    val title: String,
    val description: String,
    val authors: List<String>,
    val imageLinks: Thumbnails,
)
data class Thumbnails(
    val smallThumbnail: String,
    val thumbnail: String
)