package com.dicoding.picodiploma.loginwithanimation.view.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_story)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(NAME_KEY)
        val photo = intent.getStringExtra(PHOTO_KEY)
        val date = intent.getStringExtra(DATE_KEY)
        val desc = intent.getStringExtra(DESC_KEY)

        if (name != null) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateParse = date?.let { dateFormat.parse(it) }
            val formattedDate = dateParse?.let { dateFormat.format(it) }

            with(binding) {
                tvDate.text = formattedDate
                tvItemUsername.text = name
                Glide.with(this@DetailActivity).load(photo).into(photoContent)
                tvDescription.text = desc
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                @Suppress("DEPRECATION")
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val NAME_KEY = "name_data"
        const val PHOTO_KEY = "photo_data"
        const val DATE_KEY = "date_data"
        const val DESC_KEY = "desc_data"
    }
}