package com.printto.printmov.softspect6bookshop.models

/**
 * Created by printto on 3/30/18.
 */
class Book(val id: Int,
           val title: String,
           val price: Double = 0.0,
           val publicationYear: Int = 0,
           val imageURL: String = "") {

    override fun toString(): String {
        return "${title} (${price})";
    }

}