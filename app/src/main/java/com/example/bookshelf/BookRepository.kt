package com.example.bookshelf

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import java.io.IOException

class BookRepository(context: Context) {
	private var mDbHelper: DatabaseHelper
	private var mDb: SQLiteDatabase

	init{
		mDbHelper = DatabaseHelper(context)

		try {
			mDbHelper.updateDataBase()
		} catch (mIOException: IOException) {
			throw Error("UnableToUpdateDatabase")
		}

		mDb = try {
			mDbHelper.writableDatabase
		} catch (mSQLException: SQLException) {
			throw mSQLException
		}
	}

	public fun getFirstBook(): Book {
		var book: Book = Book(-1,"","",-1)

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Books]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			book.id = cursor.getInt(0)
			book.name = cursor.getString(1)
			book.author = cursor.getString(2)
			book.status = cursor.getInt(3)
		}

		cursor.close()

		return book
	}

	public fun getAllBooks(): ArrayList<Book> {
		var books: ArrayList<Book> = arrayListOf()

		var cursor = mDb.rawQuery(
			"SELECT * FROM [Books]",
			null)

		if (cursor != null && cursor.count > 0){
			cursor.moveToFirst()

			while (!cursor.isAfterLast) {
				var id = cursor.getInt(0)
				var name = cursor.getString(1)
				var author = cursor.getString(2)
				var status = cursor.getInt(3)

				books.add(Book(id,name,author,status))

				cursor.moveToNext()
			}
		}
		cursor.close()

		return books;
	}

	public fun removeBook(id: Int){
		mDb.execSQL("DELETE FROM [Books] WHERE Id=${id}")
	}

	public fun addBook(book: Book){
		mDb.execSQL(
			"INSERT INTO [Books](Name,Author,Status) VALUES ('${book.name}','${book.author}',${book.status})")
	}

	public fun updateBookStatus(bookId: Int, status: Int){
		mDb.execSQL("UPDATE [Books] SET Status = $status WHERE Id = $bookId")
	}
}