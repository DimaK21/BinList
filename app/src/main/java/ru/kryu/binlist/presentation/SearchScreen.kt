package ru.kryu.binlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kryu.binlist.R
import ru.kryu.binlist.presentation.theme.BinListTheme

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val textState = rememberSaveable { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxSize()
    ) {
        SearchTextField(
            textState = textState,
            onValueChange = { textState.value = it },
            onIconButtonClicked = {},
            modifier = modifier
        )
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
        label = { Text(stringResource(R.string._4_to_8_digits)) },
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
        SearchScreen()
    }
}