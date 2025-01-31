package com.divya.qrscanner

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.divya.qrscanner.data.QrCode
import com.divya.qrscanner.ui.MainApp
import com.divya.qrscanner.ui.theme.QRScannerAppTheme
import com.divya.qrscanner.ui.utils.MainViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val barCodeLauncher = registerForActivityResult(ScanContract()){
            result ->
        if (result.contents == null){
            Toast.makeText(this@MainActivity,"Cancelled", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.changeResult(result.contents)
            viewModel.addQr(
                QrCode(
                    url = result.contents,
                    dateTime = System.currentTimeMillis()
                )
            )
        }
    }

    private fun showCamera(){
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR Code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)

        barCodeLauncher.launch(options)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
            isGranted ->
        if(isGranted){
            showCamera()
        }
    }

    private fun checkCameraPermission(context: Context){
        if(ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED){
            showCamera()
        } else if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
            Toast.makeText(context,"Cancelled", Toast.LENGTH_SHORT).show()
        }else{
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    MainApp(viewModel){
                        checkCameraPermission(context)
                    }
                }
            }
        }
    }
}
