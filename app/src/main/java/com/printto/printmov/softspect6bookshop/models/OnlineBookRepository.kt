package com.printto.printmov.softspect6bookshop.models

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.ArrayList
import java.util.jar.Manifest
import android.os.AsyncTask



class OnlineBookRepository : BookRepository() {

    val bookList = ArrayList<Book>()

    override fun loadAllBooks() {

        object : AsyncTask<String,Void,String>() {

            override fun doInBackground(vararg params: String): String? {
                var json = URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json").readText()
                return json
            }

            override fun onPostExecute(result: String) {
                val jsonObj = JSONArray(result.substring(result.indexOf("["), result.lastIndexOf("]") + 1))
                for (i in 0..jsonObj!!.length() - 1) {
                    val currentJson = jsonObj.getJSONObject(i)
                    val book = Book(currentJson.getInt("id")
                            , currentJson.getString("title")
                            , currentJson.getDouble("price")
                            , currentJson.getInt("pub_year")
                            , currentJson.getString("img_url"))
                    getBooks().add(book)
                }
                setChanged()
                notifyObservers()
            }
        }.execute()

    }

    override fun searchBooks(keyword: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

}