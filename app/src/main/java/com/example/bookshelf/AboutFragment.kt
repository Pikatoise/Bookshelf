package com.example.bookshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookshelf.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
	private lateinit var binding: FragmentAboutBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentAboutBinding.inflate(inflater,container, false)

		return binding.root
	}
}