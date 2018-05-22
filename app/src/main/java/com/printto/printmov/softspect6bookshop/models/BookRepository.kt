package com.printto.printmov.softspect6bookshop.models

import java.util.*

/**
 * Created by printto on 3/30/18.
 */
abstract class BookRepository : Observable() {

    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
    abstract fun searchBooks(keyword: String)

}