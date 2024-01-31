package com.example.iss_tool

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.emergency_red_who
import com.example.iss_tool.theme.white

sealed class BottomBarScreen(
    val route: String, val label: String, val icon: Int
) {
    object Home : BottomBarScreen(
        route = "home", label = "Home", icon = R.drawable.home_icon
    )

    object Info : BottomBarScreen(
        route = "info", label = "Info", icon = R.drawable.info_icon
    )

    object Settings : BottomBarScreen(
        route = "settings", label = "Settings", icon = R.drawable.settings_icon
    )
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(
        navController = navController, startDestination = BottomBarScreen.Home.route
    ) {
        homeGraph(navController, modifier)
        composable(route = BottomBarScreen.Info.route) {
            InfoScreen(modifier)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(modifier)
        }
    }
}

@Composable
fun Show_logo(id: Int, color: Color) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "WHO Logo",
        modifier = Modifier
            .requiredWidth(width = 125.dp)
            .requiredHeight(height = 35.dp),
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)

    )
}

@Composable
fun SubstanceSelectionButton(
    modifier: Modifier = Modifier,
    text: String,
    unNumber: Int? = null,  // should be null only for Category Exempt
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.background
    ) {
        // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(customShapes.large)
                .background(customColorScheme.background), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = text,
                    style = customTypography.bodySmall,
                    color = customColorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .width(126.dp)
                        .clip(customShapes.medium)
                        .background(customColorScheme.primary.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (unNumber != null) "UN $unNumber" else "-",
                        style = customTypography.bodySmall.copy(fontSize = 15.sp),
                        color = customColorScheme.primary,
                        modifier = Modifier.padding(5.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ClassificationStartButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.primary
    ) {
        Row(
            modifier = Modifier.padding(11.dp), horizontalArrangement = Arrangement.spacedBy(
                space = 11.dp, alignment = Alignment.CenterHorizontally
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.info_icon),
                contentDescription = "Info icon"
            )
            Text(
                modifier = Modifier.width(195.dp), /*TODO: remove hardcoding of width*/
                text = text,
                style = customTypography.bodySmall.copy(fontSize = 15.sp),
                color = white,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "Arrow forward icon"
            )
        }
    }
}

@Composable
fun BoxedFAB(
    modifier: Modifier = Modifier,
    iconId: Int,
    iconLabel: String? = null,   // optional label over icon
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.background
    ) {
        // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(customShapes.large)
                .background(customColorScheme.background), contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(71.dp)
                    .clip(customShapes.medium)
                    .background(customColorScheme.primary.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (iconLabel != null) {
                        Text(
                            text = iconLabel,
                            style = customTypography.bodySmall,
                            color = customColorScheme.primary
                        )
                    }
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = LocalContext.current.resources.getResourceName(iconId)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoBody(infoText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier.weight(0.06f),
            painter = painterResource(id = R.drawable.info_icon),
            contentDescription = "Info icon",
        )
        Spacer(Modifier.width(3.dp))
        Text(
            modifier = Modifier.weight(0.94f),
            text = infoText,
            style = customTypography.bodyMedium.copy(fontSize = 11.sp),
        )
    }
}

@Composable
fun OutlinedTextFieldComponent(
    value: TextFieldValue,
    showError: Boolean,
    onValueChange: (TextFieldValue) -> Unit,
    onDoneAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = "Quantity in mL or g") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions(onDone = {
            onDoneAction.invoke()
        }),
        modifier = modifier
    )
}

@Composable
fun StartButton(
    onClick: () -> Unit, modifier: Modifier = Modifier, text: String = "Start"
) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blue_who),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
            border = BorderStroke(1.dp, blue_who),
            modifier = modifier
        ) {
            Text(
                text = text,
                color = white,
                textAlign = TextAlign.Center,
                lineHeight = 1.43.em,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ErrorMessage(
    message: String, modifier: Modifier = Modifier
) {
    Text(
        text = message,
        style = MaterialTheme.typography.bodySmall,
        color = emergency_red_who,
        modifier = modifier.padding(top = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDisplay(
    leaf: ClassificationLeaf,
    substanceList: List<String>,
    modifier: Modifier = Modifier,
    onDoneAction: () -> Unit
) {
    if (leaf.category == "Category A" || leaf.category == "Category B") {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        var assignedValue by remember { mutableStateOf<String?>(null) }
        var showError by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        var selectedSubstance by remember { mutableStateOf(substanceList[0]) }
        if (leaf.category == "Category A") {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Choose your substance to be shipped",
                style = customTypography.bodyMedium,
                modifier = Modifier
            )
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                expanded = !expanded
            }) {
                OutlinedTextField(modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                    readOnly = true,
                    value = selectedSubstance,
                    onValueChange = { },
                    label = { Text("Substances") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) })
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    substanceList.forEach { selectedSubstance_ ->
                        DropdownMenuItem(
                            text = { Text(selectedSubstance_) },
                            onClick = {
                                selectedSubstance = selectedSubstance_
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Write your shipped quantity per package in mL or g",
            style = customTypography.bodyMedium,
            modifier = Modifier
        )
        OutlinedTextFieldComponent(value = text, showError = showError, onValueChange = {
            text = if (it.text.isDigitsOnly()) it else text
            showError = false // Hide the error message when the user starts typing
        }, onDoneAction = {
            onDoneAction
        }, modifier = modifier.fillMaxWidth()
        )

        if (showError) {
            ErrorMessage("Quantity is required!", modifier = modifier)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StartButton(onClick = {
                if (text.text.isEmpty()) {
                    showError = true
                } else {
                    assignedValue = text.text
                }
            },modifier=Modifier)
        }
        if (assignedValue != null) {
            leaf.quantity = assignedValue?.toInt()
        }
    }
}

/**
 *  This custom version lets us specify custom height while avoiding padding problems and maintaining the aesthetics of OutlinedTextField
 *  **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    height: Dp = 36.dp,
    label:  @Composable() (() -> Unit)? = null
) {

    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(height),
        singleLine = singleLine,
        interactionSource = interactionSource
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            label = label,
            colors = OutlinedTextFieldDefaults.colors(),
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                top = 0.dp,
                bottom = 0.dp,
            ),
            container = {
                OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, OutlinedTextFieldDefaults.colors())
            },
        )
    }
}