package com.example.friendsr

class User(
    var name: String,
    var rating: Float,
    var image: String, var detail: String
) {
    override fun toString(): String {
        return name + " (Rating: " + rating + ")"
    }
}