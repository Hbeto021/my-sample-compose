package br.com.humberto.my_sample_compose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.humberto.my_sample_compose.ui.theme.MysamplecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MysamplecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    InitView(contactList = getContacts())
                }
            }
        }
    }
}

@Composable
fun InitView(contactList: List<ContactModel>) {
    Column {
        AppTopBar()
        ContactList(contactList = contactList)
    }

}

@Composable
fun AppTopBar() {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        title = {
            Text(text = "Lista de contatos")
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    activity?.finish()
                }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Botão voltar")
            }
        }
    )
}

@Composable
fun ContactList(contactList: List<ContactModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(contactList) { contact ->
            ContactCard(contactModel = contact)
        }
    }
}

@Composable
fun ContactCard(contactModel: ContactModel) {
    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = contactModel.photo),
            contentDescription = "Foto de perfil do contato",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillHeight
        )
        Column(modifier = Modifier.padding(start = 4.dp)) {
            Text(text = contactModel.name)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = contactModel.number)
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            val icon = remember { mutableStateOf(R.drawable.ic_baseline_star_outline_24) }
            IconButton(
                onClick = {
                    favoriteIconClick(icon, contactModel)
                }
            ) {
                Image(painter = painterResource(id = icon.value), contentDescription = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MysamplecomposeTheme {
        InitView(contactList = getContacts())
    }
}

private fun favoriteIconClick(icon: MutableState<Int>, contactModel: ContactModel) {
    contactModel.isFavorite = !contactModel.isFavorite
    if (contactModel.isFavorite) {
        icon.value = R.drawable.ic_baseline_star_rate_24
    } else {
        icon.value = R.drawable.ic_baseline_star_outline_24
    }
}

fun getContacts() = listOf(
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