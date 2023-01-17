package com.example.housify.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.housify.R

@Composable
fun HousifyAboutScreen(modifier: Modifier = Modifier
    .background(MaterialTheme.colors.background)) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp)

    ) {
        Text(text = stringResource(R.string.about),
        style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.lorem),
        style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.design),
        style = MaterialTheme.typography.h1)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.dtt_banner),
                contentDescription = "DTTBanner",
                modifier
                    .width(120.dp)
                    .height(40.dp)
                    
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "by DTT", style = MaterialTheme.typography.caption)
                HyperlinkText(fullText = "d-tt.nl", linkText = listOf("d-tt.nl"),
                hyperlinks = listOf("https://www.d-tt.nl/"))
            }
        }
    }
}
@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Light,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    hyperlinks: List<String> = listOf(),
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}


@Preview
@Composable
fun HousifyAboutScreenPreview() {
    HousifyTheme {
        HousifyAboutScreen()
    }
}