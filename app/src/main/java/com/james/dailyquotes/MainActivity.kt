package com.james.dailyquotes


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.james.dailyquotes.ui.theme.DailyQuotesTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.json.JSONArray



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    QuoteApp()
                }
            }
        }
    }
}




// images randomly from resources
fun randomImage() : Int {
    val imageResourceIds = listOf(
        R.drawable.background1,
        R.drawable.background2,
        R.drawable.background3,
        R.drawable.background4,
        R.drawable.background5,
        R.drawable.background6,
        R.drawable.background7,
        R.drawable.background8,
        R.drawable.background9,
        R.drawable.background10,
        R.drawable.background11,
        R.drawable.background12,
        R.drawable.background13,
        R.drawable.background14,
        R.drawable.background15,
        R.drawable.background16,
        R.drawable.background17,
        R.drawable.background18,
        R.drawable.background19,
        R.drawable.background20,
        R.drawable.background21,
        R.drawable.background22,
        R.drawable.background23,
        R.drawable.background24,
        R.drawable.background25,
        R.drawable.background26,
        R.drawable.background27,
        R.drawable.background28,
        R.drawable.background29,
        R.drawable.background30,
        R.drawable.background31,
        R.drawable.background32,
        R.drawable.background33,
        R.drawable.background34,
        R.drawable.background35,
        R.drawable.background36,
        R.drawable.background37,
        R.drawable.background38,
        R.drawable.background39,
        R.drawable.background40
    )

    // get random index from imageResourceIds array
    val randomIndex = (0..imageResourceIds.size).shuffled().last()
    return imageResourceIds[randomIndex]
}


// Load quotes
@Composable
fun loadQuote(): List<QuoteEntity> {
    val context = LocalContext.current

    // Read Json file
    val jsonString = context.assets.open("data.json")
        .bufferedReader()
        .use { it.readText() }
    val jsonArray = JSONArray(jsonString)
    val quoteArrayLength = jsonArray.length()

    return listOf<QuoteEntity>(
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage()),
        QuoteEntity(jsonArray.getJSONObject((0..quoteArrayLength).shuffled().last()).getString("Quotes"),
            randomImage())
    )
}


@Composable
fun QuoteApp() {
    DailyQuotesTheme {
        QuoteList(quoteList = loadQuote())
    }
}

// List quote
@Composable
fun QuoteList(quoteList: List<QuoteEntity>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(quoteList) { quote ->
            val configuration = LocalConfiguration.current
            when(configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    // Landscape Mode
                    QuoteCardLandscape(quote)
                } else -> {
                // Portrait Mode
                    QuoteCardPortrait(quote)
                }
            }
        }
    }
}

// Portrait Mode display image and quote
@Composable
fun QuoteCardPortrait(quote: QuoteEntity, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            // display image
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = quote.quotes,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            // quote
            Text(
                text = quote.quotes,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}


// Landscape Mode display image and quote
@Composable
fun QuoteCardLandscape(quote: QuoteEntity, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Row {
            // display image
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = quote.quotes,
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text
            // quote
            Text(
                text = quote.quotes,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}


@Composable
fun ScreenOrientation() {
    val configuration = LocalConfiguration.current
    when(configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {}
        Configuration.ORIENTATION_PORTRAIT -> {}
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailyQuotesTheme {
        QuoteCardPortrait (QuoteEntity("The best and most beautiful things " +
                    "in the world cannot be seen or even touched - " +
                    "they must be felt with the heart. - Helen Keller", R.drawable.background1))
    }
}