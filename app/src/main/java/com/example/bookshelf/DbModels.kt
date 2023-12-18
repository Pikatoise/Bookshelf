package com.example.bookshelf

import android.os.Parcel
import android.os.Parcelable

data class Book(var id: Int, var name: String, var author: String, var status: Int): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString() as String,
		parcel.readString() as String,
		parcel.readInt()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(name)
		parcel.writeString(author)
		parcel.writeInt(status)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Book> {
		override fun createFromParcel(parcel: Parcel): Book {
			return Book(parcel)
		}

		override fun newArray(size: Int): Array<Book?> {
			return arrayOfNulls(size)
		}
	}
}