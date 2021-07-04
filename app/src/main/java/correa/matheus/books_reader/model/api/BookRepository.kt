package correa.matheus.books_reader.model.api

import android.util.Log
import correa.matheus.books_reader.model.DataBook
import correa.matheus.books_reader.model.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {

    fun getBook(isbn:String, onResult : (Int?,BooksApiResult?) -> Unit){
        val call = ServiceBuilder.buildService(BookService::class.java)
        call.searchBook(isbn).enqueue(
            object : Callback<BooksApiResult>{
                override fun onResponse(call: Call<BooksApiResult>, response: Response<BooksApiResult>) {
                    val  addedClient = response.body()
                    val status = response.code()
                    onResult(status, addedClient)
                }

                override fun onFailure(call: Call<BooksApiResult>, t: Throwable) {
                    Log.d("TESTANDO",t.toString())
                }
            }
        )}
}