package com.printto.printmov.softspect6bookshop.presenters

import android.support.annotation.IntegerRes
import com.printto.printmov.softspect6bookshop.models.Book
import com.printto.printmov.softspect6bookshop.models.BookRepository
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by printto on 3/30/18.
 */
class BookPresenter(val view: BookView, vararg val repositories: BookRepository) : Observer {

    var books = ArrayList<Book>()

    fun start() {
        for(i in 0..repositories.size - 1){
            repositories[i].addObserver(this)
            repositories[i].loadAllBooks()
        }
    }

    override fun update(obj: Observable?, arg: Any?) {
        if(repositories.contains(obj)){
            books.addAll((obj as BookRepository).getBooks())
        }
        updateBookList(books)
    }

    fun updateBookList(books:ArrayList<Book>){
        view.setBookList(books)
    }

    fun search(keyword: String) {

        if (keyword == null || keyword.trim().equals("")) {
            updateBookList(books)
            return
        }

        var temp = ArrayList<Book>()

        try {
            var parsedKeyword = Integer.parseInt(keyword)
            for (book in books) {
                if (book.publicationYear == parsedKeyword) {
                    temp.add(book)
                }
            }
        } catch (e: RuntimeException) {
        }
        for (book in books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase())) {
                temp.add(book)
            }
        }

        updateBookList(temp)
    }

}