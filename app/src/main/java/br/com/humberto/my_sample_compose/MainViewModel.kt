package br.com.humberto.my_sample_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val cachedList = getContacts()

    private val _listContacts = MutableLiveData(getContacts())
    val listContacts: LiveData<List<ContactModel>> = _listContacts

    fun updateItem(position: Int) {
        val updatedList = mutableListOf<ContactModel>()
        cachedList.forEachIndexed { index, contactModel ->
            if (position == index) {
                if (contactModel.isFavorite) {
                    contactModel.isFavorite = false
                    contactModel.icon = R.drawable.ic_baseline_star_outline_24
                } else {
                    contactModel.isFavorite = true
                    contactModel.icon = R.drawable.ic_baseline_star_rate_24
                }
            }
            updatedList.add(contactModel.copy())
        }
        _listContacts.value = updatedList
    }
}

private fun getContacts() = listOf(
    ContactModel("Goku", "11934659874", R.drawable.goku_avatar),
    ContactModel("Vegeta", "11978965412", R.drawable.vegeta_avatar),
    ContactModel("Jiren", "11978452321", R.drawable.jiren_avatar),
    ContactModel("Goku", "11934659874", R.drawable.goku_avatar),
    ContactModel("Vegeta", "11978965412", R.drawable.vegeta_avatar),
    ContactModel("Jiren", "11978452321", R.drawable.jiren_avatar),
    ContactModel("Goku", "11934659874", R.drawable.goku_avatar),
    ContactModel("Vegeta", "11978965412", R.drawable.vegeta_avatar),
    ContactModel("Jiren", "11978452321", R.drawable.jiren_avatar),
)