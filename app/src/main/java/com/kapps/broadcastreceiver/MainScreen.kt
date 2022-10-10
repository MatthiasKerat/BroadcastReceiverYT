package com.kapps.broadcastreceiver

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen(
    onBluetoothStateChanged:()->Unit,
) {

    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED){ receiverState ->
        val action = receiverState?.action ?: return@SystemBroadcastReceiver
        if(action == BluetoothAdapter.ACTION_STATE_CHANGED){
            onBluetoothStateChanged()
        }
    }

    SystemBroadcastReceiver(systemAction = WifiManager.WIFI_STATE_CHANGED_ACTION){ receiverState ->
        val action = receiverState?.action ?: return@SystemBroadcastReceiver
        if(action == WifiManager.WIFI_STATE_CHANGED_ACTION){
            println("Network State Changed")
            //React to Network change
        }
    }

    SystemBroadcastReceiver(systemAction = "CUSTOM_BROADCAST"){ receiverState ->
        val action = receiverState?.action ?: return@SystemBroadcastReceiver
        if(action == "CUSTOM_BROADCAST"){
            println("Custom Broadcast")
            //Do something
        }
    }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            val intent = Intent("CUSTOM_INTENT")
            intent.action = "CUSTOM_BROADCAST"
            context.sendBroadcast(intent)
        }) {
            Text("Send Broadcast")
        }
    }

}