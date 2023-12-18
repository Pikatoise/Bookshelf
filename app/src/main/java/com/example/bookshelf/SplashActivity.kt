package com.example.bookshelf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookshelf.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
	private lateinit var binding: ActivitySplashBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivitySplashBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivIcon.alpha = 0f
		binding.ivIcon.animate().setDuration(2000).alpha(1f).withEndAction {
			val i = Intent(this, MainActivity::class.java)

			startActivity(i)

			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

			finish()
		}
	}
}