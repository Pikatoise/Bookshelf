package com.example.bookshelf

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bookshelf.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {
	private lateinit var binding: FragmentBooksBinding
	private lateinit var dbBooks: BookRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentBooksBinding.inflate(inflater,container,false)

		dbBooks = BookRepository(this.requireContext())

		binding.rlAddBook.setOnClickListener {
			startActivity(Intent(this.requireContext(),AddBookActivity::class.java))
		}

		return binding.root
	}

	fun loadBooks(){
		binding.rvBooksOngoing.adapter = null
		binding.rvBooksAlready.adapter = null
		binding.rvBooksFuture.adapter = null

		var datasetOngoing: ArrayList<Book> = arrayListOf()
		var datasetAlready: ArrayList<Book> = arrayListOf()
		var datasetFuture: ArrayList<Book> = arrayListOf()
		var books = dbBooks.getAllBooks()

		books.forEach {
			if (it.status == -1)
				datasetFuture.add(it)
			else if (it.status == 0)
				datasetOngoing.add(it)
			else
				datasetAlready.add(it)
		}

		if (datasetOngoing.size == 0)
			binding.tvEmptyOngoing.visibility = View.VISIBLE
		else
			binding.tvEmptyOngoing.visibility = View.INVISIBLE

		if (datasetAlready.size == 0)
			binding.tvEmptyAlready.visibility = View.VISIBLE
		else
			binding.tvEmptyAlready.visibility = View.INVISIBLE

		if (datasetFuture.size == 0)
			binding.tvEmptyFuture.visibility = View.VISIBLE
		else
			binding.tvEmptyFuture.visibility = View.INVISIBLE

		binding.rvBooksOngoing.adapter = BooksRecyclerAdapter(datasetOngoing) {
			val dialogRemove = DialogRemoveItem( "Удалить книгу?") {
				dbBooks.removeBook(it)

				loadBooks()

				Toast.makeText(activity, "Готово", Toast.LENGTH_SHORT).show()
			}
			val manager = parentFragmentManager
			dialogRemove.show(manager,"removeDialog")
		}
		binding.rvBooksAlready.adapter = BooksRecyclerAdapter(datasetAlready) {
			val dialogRemove = DialogRemoveItem( "Удалить книгу?") {
				dbBooks.removeBook(it)

				loadBooks()

				Toast.makeText(activity, "Готово", Toast.LENGTH_SHORT).show()
			}
			val manager = parentFragmentManager
			dialogRemove.show(manager,"removeDialog")
		}
		binding.rvBooksFuture.adapter = BooksRecyclerAdapter(datasetFuture) {
			val dialogRemove = DialogRemoveItem( "Удалить книгу?") {
				dbBooks.removeBook(it)

				loadBooks()

				Toast.makeText(activity, "Готово", Toast.LENGTH_SHORT).show()
			}
			val manager = parentFragmentManager
			dialogRemove.show(manager,"removeDialog")
		}
	}

	override fun onResume() {
		super.onResume()

		loadBooks()
	}
}