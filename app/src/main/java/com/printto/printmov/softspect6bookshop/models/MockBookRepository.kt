package com.printto.printmov.softspect6bookshop.models

import java.util.ArrayList

/**
 * Created by printto on 3/30/18.
 */
class MockBookRepository : BookRepository(){

    val bookList = ArrayList<Book>()

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1,"How to win BNK election",500.0))
        bookList.add(Book(2,"Kotlin is hard",199.0))
        bookList.add(Book(3,"Wanna Sleep?",39.99))
        setChanged()
        notifyObservers()
    }

    override fun searchBooks(keyword: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

}