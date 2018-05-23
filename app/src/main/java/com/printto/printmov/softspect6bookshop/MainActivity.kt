package com.printto.printmov.softspect6bookshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.printto.printmov.softspect6bookshop.models.Book
import com.printto.printmov.softspect6bookshop.models.MockBookRepository
import com.printto.printmov.softspect6bookshop.models.OnlineBookRepository
import com.printto.printmov.softspect6bookshop.presenters.BookPresenter
import com.printto.printmov.softspect6bookshop.presenters.BookView
import kotlinx.android.synthetic.main.activity_main.*

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
        this.books.addAll(books)
        adapter?.notifyDataSetChanged()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var temp = 0
        when(item.title.toString()){
            "No sorting" -> temp = 0
            "Sort by title A-Z" -> temp = 1
            "Sort by year 1-10" -> temp = 2
            "Sort by title Z-A" -> temp = 3
            "Sort by year 10-1" -> temp = 4
        }
        setBookList(presenter.sortBook(temp))
        return true
        return super.onOptionsItemSelected(item)
    }

}
