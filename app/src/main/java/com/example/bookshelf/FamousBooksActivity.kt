package com.example.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.bookshelf.databinding.ActivityFamousBooksBinding

class FamousBooksActivity : AppCompatActivity() {
	private lateinit var binding: ActivityFamousBooksBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityFamousBooksBinding.inflate(layoutInflater)

		setContentView(binding.root)

		binding.ivClose.setOnClickListener {
			finish()
		}

		binding.wvFamousBooks.settings.javaScriptEnabled = true
		binding.wvFamousBooks.webViewClient = WebViewClient()
		binding.wvFamousBooks.loadUrl("https://www.livelib.ru/selection/836114-50-samyh-populyarnyh-knig-v-mire")
	}
}