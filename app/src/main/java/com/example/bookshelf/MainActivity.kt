package com.example.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bookshelf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)

		val bot = binding.bottomMainNav
		val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
		val fragmentController = fragment.navController

		bot.setupWithNavController(fragmentController)

		setContentView(binding.root)
	}
}