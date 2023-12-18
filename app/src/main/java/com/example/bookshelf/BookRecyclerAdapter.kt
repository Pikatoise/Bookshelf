package com.example.bookshelf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class BooksRecyclerAdapter(private val dataSet: ArrayList<Book>, private val deleteCallBack: (id: Int) -> Unit) :
	RecyclerView.Adapter<BooksRecyclerAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvName: TextView
		val tvCount: TextView
		val clBookItem: ConstraintLayout

		init {
			tvName = view.findViewById(R.id.tv_item_book_name)
			tvCount = view.findViewById(R.id.tv_item_book_author)
			clBookItem = view.findViewById(R.id.cl_book_item)
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_recycler_book, viewGroup, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

		viewHolder.tvName.text = dataSet[position].name
		viewHolder.tvCount.text = dataSet[position].author
		viewHolder.clBookItem.setOnClickListener {
			deleteCallBack(dataSet[position].id)
		}
	}

	override fun getItemCount() = dataSet.size

}