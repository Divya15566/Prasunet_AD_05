package com.divya.qrscanner.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.divya.qrscanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    CenterAlignedTopAppBar(
        modifier = Modifier
            .height(64.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.black)
        ),
        title = {
            Text(
                text = "QR CODE SCANNER",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic ,
                color = Color.White
            ) },
    )
}