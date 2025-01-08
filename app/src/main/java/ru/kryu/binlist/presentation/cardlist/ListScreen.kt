package ru.kryu.binlist.presentation.cardlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.kryu.binlist.R
import ru.kryu.binlist.domain.model.CardDetails
import ru.kryu.binlist.presentation.theme.BinListTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.value.errorMessage) {
        if (state.value.errorMessage.isNotEmpty()) {
            Toast.makeText(context, state.value.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        items(state.value.cardList) { card ->
            CardItem(card = card)
        }
    }

    if (state.value.isLoading) {
        LoadingIndicator()
    }
}

@Composable
fun CardItem(card: CardDetails, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        DetailColumn(card, modifier)
    }
}

@Composable
fun DetailColumn(card: CardDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        DetailRow(label = stringResource(R.string.scheme), value = card.scheme)
        DetailRow(label = stringResource(R.string.type), value = card.type)
        DetailRow(label = stringResource(R.string.brand), value = card.brand)
        DetailRow(label = stringResource(R.string.country), value = card.country)
        DetailRow(label = stringResource(R.string.city), value = card.city)
        DetailRow(label = stringResource(R.string.bank), value = card.bank)
        DetailRow(label = stringResource(R.string.website), value = card.website)
        DetailRow(label = stringResource(R.string.phone), value = card.phone)
    }
}

@Composable
fun DetailRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:")
        Text(text = value)
    }
}

@Preview
@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    BinListTheme {
        ListScreen()
    }
}