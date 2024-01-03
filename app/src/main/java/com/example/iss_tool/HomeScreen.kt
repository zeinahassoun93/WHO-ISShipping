package com.example.iss_tool

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.white

@Composable
fun HomeScreen(paddingModifier: Modifier) {
    Column (
        modifier = paddingModifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Show_logo(modifier = paddingModifier.align(Alignment.Start), id = R.drawable.who_logo, color = blue_who)
        Text(
            text = "Let's Get Started!",
            style = customTypography.bodyLarge,
            color = customColorScheme.primary)
        Text(text = "Choose your substance to be shipped",
            style = customTypography.bodyMedium,
            color = black
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SubstanceFrame(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                text = "Infectious Substance Affecting Humans",
                unNumber = 2814
            )
            SubstanceFrame(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                text = "Infectious Substance Affecting Animals",
                unNumber = 2900
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SubstanceFrame(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                text = "Biological Substances Category B",
                unNumber = 3373
            )
            SubstanceFrame(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                text = "Biomedical, Clinical or Medical Waste",
                unNumber = 3291
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(0.5f),
            horizontalArrangement = Arrangement.Center
        ) {
            SubstanceFrame(
                onClick = { /*TODO*/ },
                text = "Human/Animal Specimen Category Exempt"
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        FloatingActionButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            shape = customShapes.large,
            containerColor = customColorScheme.primary
        ) {
            Row (
                modifier = Modifier.padding(11.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 11.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_icon),
                    contentDescription = "Info icon"
                )
                Text(
                    modifier = Modifier.width(197.dp),
                    text = "Click here if you don’t know your substance type!",
                    style = customTypography.bodySmall.copy(fontSize = 15.sp),
                    color = white,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = "Info icon"
                )
            }
        }
    }
}



