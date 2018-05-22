package com.printto.printmov.softspect6bookshop

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import com.printto.printmov.softspect6bookshop.models.Book
import com.printto.printmov.softspect6bookshop.models.MockBookRepository
import com.printto.printmov.softspect6bookshop.models.OnlineBookRepository
import com.printto.printmov.softspect6bookshop.presenters.BookPresenter
import com.printto.printmov.softspect6bookshop.presenters.BookView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() , BookView {

    var presenter = BookPresenter(this,MockBookRepository(),OnlineBookRepository())
    var adapter: ArrayAdapter<Book>? = null
    var books = ArrayList<Book>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books)
        itemList.adapter = adapter
        presenter.start()
        search_box.addTextChangedListener(watcher(presenter))
    }

    override fun setBookList(books: ArrayList<Book>) {
        this.books.clear()
//        for(book in books){
//            this.books.add(book.toString())
//        }
        this.books.addAll(books)
        adapter?.notifyDataSetChanged()
    }

    fun clearButtonClicked(view: View){
        search_box.setText("")
    }

    class watcher(val presenter: BookPresenter) : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.search(p0.toString())
        }
    }
}
