package com.example.bookshelf

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookshelf.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
	private lateinit var binding: FragmentHomeBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentHomeBinding.inflate(inflater,container,false)

		binding.clFamousBooks.setOnClickListener {
			startActivity(Intent(this.requireContext(),FamousBooksActivity::class.java))
		}

		return binding.root
	}
}