package correa.matheus.books_reader.model

data class DataBook(
    val title: String,
    val description: String,
    val authors: List<String>,
    val thumbnail: String,
)