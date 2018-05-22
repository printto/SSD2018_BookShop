package com.printto.printmov.softspect6bookshop.presenters

import com.printto.printmov.softspect6bookshop.models.Book

/**
 * Created by printto on 3/30/18.
 */
interface BookView {

    fun setBookList(books: ArrayList<Book>)

}