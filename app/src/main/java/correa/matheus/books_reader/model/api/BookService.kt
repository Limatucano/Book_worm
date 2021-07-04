package correa.matheus.books_reader.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("volumes/")
    fun searchBook(@Query("q") isbn:String?): Call<BooksApiResult>
}