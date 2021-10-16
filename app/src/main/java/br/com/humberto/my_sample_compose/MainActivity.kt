package br.com.humberto.my_sample_compose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.humberto.my_sample_compose.ui.theme.MysamplecomposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MysamplecomposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    InitView(viewModel)
                }
            }
        }
    }
}

@Composable
fun InitView(viewModel: MainViewModel) {
    Column {
        AppTopBar()
        ContactList(viewModel)
    }

}

@Composable
fun AppTopBar() {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.title))
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    activity?.finish()
                }
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    stringResource(id = R.string.navigation_icon_content_description)
                )
            }
        }
    )
}

@Composable
fun ContactList(viewModel: MainViewModel) {
    val list: List<ContactModel> by viewModel.listContacts.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(list) { index, contact ->
            ContactCard(contactModel = contact, viewModel, index)
        }
    }
}

@Composable
fun ContactCard(contactModel: ContactModel, viewModel: MainViewModel, position: Int) {
    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = contactModel.photo),
            contentDescription = stringResource(id = R.string.contact_icon_content_description),
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
            IconButton(
                onClick = {
                    viewModel.updateItem(position)
                }
            ) {
                Image(painter = painterResource(id = contactModel.icon), contentDescription = "")
            }
        }
    }
}