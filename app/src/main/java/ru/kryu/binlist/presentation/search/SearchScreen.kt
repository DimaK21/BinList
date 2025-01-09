package ru.kryu.binlist.presentation.search

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.kryu.binlist.R
import ru.kryu.binlist.presentation.cardlist.DetailColumn
import ru.kryu.binlist.presentation.cardlist.LoadingIndicator
import ru.kryu.binlist.presentation.theme.BinListTheme

@Composable
fun SearchScreen(
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val textState = rememberSaveable { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.value.errorMessage) {
        if (state.value.errorMessage.isNotEmpty()) {
            Toast.makeText(context, state.value.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 24.dp)
        ) {
            SearchTextField(
                textState = textState,
                onValueChange = { textState.value = it },
                onIconButtonClicked = { viewModel.getCardInfo(textState.value) },
                modifier = modifier
            )
            Spacer(Modifier.height(16.dp))
            DetailColumn(state.value.card, modifier)
        }
        FloatingActionButton(
            onClick = onFabClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.List, stringResource(R.string.to_history))
        }
    }

    if (state.value.isLoading) {
        LoadingIndicator()
    }
}

@Composable
fun SearchTextField(
    textState: MutableState<String>,
    onValueChange: (String) -> Unit,
    onIconButtonClicked: () -> Unit,
    modifier: Modifier,
) {
    val maxChars = 8
    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            if (it.length <= maxChars) {
                textState.value = it
                onValueChange(it)
            }
        },
        maxLines = 1,
        label = { Text(stringResource(R.string._6_to_8_digits)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions { onIconButtonClicked() },
        trailingIcon = {
            IconButton(onClick = onIconButtonClicked) {
                Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.search))
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    BinListTheme {
        SearchScreen({})
    }
}