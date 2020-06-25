package com.example.friendsr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_display_detail.*


const val EXTRA_NAME_DISPLAY = "com.example.customlistview.USER_NAME"
const val EXTRA_RATING_DISPLAY= "com.example.customlistview.USER_RATING"


class DisplayDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_detail)

        val name = intent.getStringExtra(EXTRA_NAME)
        val image = intent.getStringExtra(EXTRA_IMAGE)
        val rating = intent.getFloatExtra(EXTRA_RATING, 1.0F)
        val detail = intent.getStringExtra(EXTRA_DETAIL)

        // Capture the layout's TextView and set the string as its text
        val nameView = findViewById<TextView>(R.id.name).apply {
            text = name
        }

        val detailView = findViewById<TextView>(R.id.detail).apply {
            text = detail
        }

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.rating = rating

        val resID = resources.getIdentifier(image, "mipmap", packageName)
        val imageView = findViewById<ImageView>(R.id.image) as ImageView
        imageView.setImageResource(resID)

        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                rate()
            }
//        rateBtn.setOnClickListener { rate() }
    }

    fun rate() {
        var stars = ratingBar.rating;
        val nameView = findViewById<TextView>(R.id.name)

        val resultIntent = Intent().apply {
            putExtra(EXTRA_NAME_DISPLAY, nameView.text)
            putExtra(EXTRA_RATING_DISPLAY, stars)
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

}
