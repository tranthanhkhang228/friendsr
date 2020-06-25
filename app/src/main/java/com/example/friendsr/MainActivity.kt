package com.example.friendsr

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


const val EXTRA_NAME = "com.example.customlistview.USER_NAME"
const val EXTRA_IMAGE = "com.example.customlistview.USER_IMAGE"
const val EXTRA_RATING = "com.example.customlistview.USER_RATING"
const val EXTRA_DETAIL = "com.example.customlistview.USER_DETAIL"


class MainActivity : AppCompatActivity() {
    lateinit var image_details: List<User>
    lateinit var gridView: GridView
    lateinit var adapter: CustomGridAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_details= listData
        val pref = getSharedPreferences("PREF", MODE_PRIVATE)
        if(pref != null)
        {

            image_details.forEach{user ->
                var rating = pref.getString(user.name, "1")
                user.rating = rating!!.toFloat()
            }
        }

        gridView = findViewById<View>(R.id.gridView) as GridView
        adapter = CustomGridAdapter(this, image_details)

        gridView.adapter = adapter

        gridView.onItemClickListener = OnItemClickListener { a, v, position, id ->

            val o = gridView.getItemAtPosition(position)

            val user: User = o as User


            showDetail(user)
        }
    }

    fun showDetail(user: User) {

        val intent = Intent(this, DisplayDetail::class.java).apply {
            putExtra(EXTRA_NAME, user.name)
            putExtra(EXTRA_IMAGE, user.image)
            putExtra(EXTRA_RATING, user.rating)
            putExtra(EXTRA_DETAIL, user.detail)
        }
        startActivityForResult(intent, 123)
    }

    private val listData: List<User>
        private get() {
            val list: MutableList<User> = ArrayList<User>()
            val pikachu = User(
                "Pikachu",
                2.0F,
                "pikachu",
                "Pikachu is a short, chubby rodent Pokémon. It is covered in yellow fur with two horizontal brown stripes on its back. It has a small mouth, long, pointed ears with black tips, and brown eyes"
            )
            val charmander = User(
                "Charmander",
                4.0F,
                "charmander",
                "Charmander is a bipedal, reptilian Pokémon with a primarily orange body and blue eyes. Its underside from the chest down and the soles of its feet are cream-colored."
            )
            val bulbasaur = User(
                "Bulbasaur",
                3.0F,
                "bulbasaur",
                "Bulbasaur is a small, quadruped Pokémon that has blue-green skin with darker patches. It has red eyes with white pupils, pointed, ear-like structures on top of its head, and a short, blunt snout with a wide mouth"
            )
            val squirtle = User(
                "Squirtle",
                3.0F,
                "squirtle",
                "Squirtle is a small Pokémon that resembles a light blue turtle. While it typically walks on its two short legs, it has been shown to run on all fours in Super Smash Bros."
            )
            val eevee = User(
                "Eevee",
                3.0F,
                "eevee",
                "Eevee is a mammalian, quadruped Pokémon with primarily brown fur. The tip of its bushy tail and its large furry collar are cream-colored."
            )
            val dragonite = User(
                "Dragonite",
                5.0F,
                "dragonite",
                "Dragonite is a draconic, bipedal Pokémon with light orange skin. It has large, grayish-green eyes and a round snout with small nostrils."
            )
            list.addAll(listOf(pikachu, charmander, bulbasaur, squirtle, eevee, dragonite))
            return list
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 123) {

            val returnName = data!!.getStringExtra(EXTRA_NAME_DISPLAY)
            val returnRating = data!!.getFloatExtra(EXTRA_RATING_DISPLAY, 1.0F)

            setRate(returnName, returnRating)
        }
    }

    fun setRate(name: String, rating: Float) {
        run loop@{
            image_details.forEach { user ->
                if (user.name == name) {
                    user.rating = rating
                    Log.d("MyActivity", "set")
                return@loop
                }
            }
        }

        adapter.notifyDataSetChanged()

        var pref: SharedPreferences = getSharedPreferences("PREF", MODE_PRIVATE)
        val editor: Editor = pref.edit()
        editor.putString(name, rating.toString())
        editor.commit()
    }
}

