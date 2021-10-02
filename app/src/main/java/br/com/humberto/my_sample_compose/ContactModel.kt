package br.com.humberto.my_sample_compose

data class ContactModel(
    val name: String,
    val number: String,
    val photo: Int,
    var isFavorite: Boolean = false
)