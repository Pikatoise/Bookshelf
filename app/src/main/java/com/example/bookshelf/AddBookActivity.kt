package com.example.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookshelf.databinding.ActivityAddBookBinding

class AddBookActivity : AppCompatActivity() {
	private lateinit var binding: ActivityAddBookBinding
	private lateinit var dbBooks: BookRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityAddBookBinding.inflate(layoutInflater)
		dbBooks = BookRepository(this)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.rlAddBook.setOnClickListener {
			val name = binding.etName.text.toString()
			if (name.isEmpty()){
				Toast.makeText(this@AddBookActivity,"Введите название",Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val author = binding.etAuthor.text.toString()
			if (author.isEmpty()){
				Toast.makeText(this@AddBookActivity,"Введите автора",Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			var status = 0

			if (binding.rbAlready.isChecked)
				status = 1
			else if (binding.rbFuture.isChecked)
				status = -1

			dbBooks.addBook(Book(-1,name,author,status))

			finish()
		}

		setContentView(binding.root)
	}
}