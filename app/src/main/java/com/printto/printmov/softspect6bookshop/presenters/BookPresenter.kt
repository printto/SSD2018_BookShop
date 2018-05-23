package com.printto.printmov.softspect6bookshop.presenters

import com.printto.printmov.softspect6bookshop.models.Book
import com.printto.printmov.softspect6bookshop.models.BookRepository
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by printto on 3/30/18.
 */
class BookPresenter(val view: BookView, vararg val repositories: BookRepository) : Observer {

    companion object {
        val NONE = 0
        val TITLE = 1
        val YEAR = 2
        val TITLE_D = 3
        val YEAR_D = 4
    }

    var currentSorting = NONE
    var books = ArrayList<Book>()
    var filtered = ArrayList<Book>()
    var isFiltered = false

    fun start() {
        for (i in 0..repositories.size - 1) {
            repositories[i].addObserver(this)
            repositories[i].loadAllBooks()
        }
    }

    override fun update(obj: Observable?, arg: Any?) {
        if (repositories.contains(obj)) {
            books.addAll((obj as BookRepository).getBooks())
        }
        updateBookList(books)
    }

    fun updateBookList(books: ArrayList<Book>) {
        view.setBookList(books)
    }

    fun search(keyword: String) {
        if (keyword == null || keyword.trim().equals("")) {
            updateBookList(books)
            isFiltered = false
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
        filtered = temp
        isFiltered = true
        updateBookList(sortBook(currentSorting))
    }

    fun sortBook(sortMode: Int): ArrayList<Book>{
        var temp = ArrayList<Book>()
        if (isFiltered) {
            temp.addAll(filtered)
        } else {
            temp.addAll(books)
        }
        when (sortMode) {
            NONE -> {
                currentSorting = NONE
            }
            TITLE -> {
                temp.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.title }))
                currentSorting = TITLE
            }
            YEAR -> {
                temp.sortWith(compareBy(Book::publicationYear))
                currentSorting = YEAR
            }
            TITLE_D -> {
                temp.sortWith(compareByDescending(String.CASE_INSENSITIVE_ORDER, { it.title }))
                currentSorting = TITLE_D
            }
            YEAR_D -> {
                temp.sortWith(compareByDescending(Book::publicationYear))
                currentSorting = YEAR_D
            }
        }
        return temp
    }
}