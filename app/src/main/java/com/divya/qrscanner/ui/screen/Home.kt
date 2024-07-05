package com.divya.qrscanner.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.divya.qrscanner.R
import com.divya.qrscanner.ui.utils.MainViewModel
import com.divya.qrscanner.ui.utils.TimeConverter

@Composable
fun HomeScreen(innerPadding:PaddingValues,viewModel: MainViewModel){
    val clipboardManager = LocalClipboardManager.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        if(viewModel.textResult.value == ""){

            Text(text = "Scan QR", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black,fontStyle = FontStyle.Italic)
        }else{
            Column(
                Modifier
                    .background(color = Color.White)
                    .clip(shape = RoundedCornerShape(10))) {
                Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        Icon(modifier = Modifier.size(24.dp),painter = painterResource(id = R.drawable.baseline_link_24), contentDescription = "url", tint = Color.Gray )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = viewModel.textResult.value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                    IconButton(onClick = {
                        clipboardManager.setText(AnnotatedString(viewModel.textResult.value))
                    }) {
                        Icon(modifier = Modifier.size(24.dp),painter = painterResource(id = R.drawable.baseline_content_copy_24), contentDescription = "copy", tint = Color.Gray )
                    }
                }
                Row(Modifier.padding(8.dp)) {
                    Icon(modifier = Modifier.size(24.dp),painter = painterResource(id = R.drawable.baseline_access_time_filled_24), contentDescription = "time", tint = Color.Gray )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = TimeConverter(System.currentTimeMillis()), fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }

        }
    }
}
