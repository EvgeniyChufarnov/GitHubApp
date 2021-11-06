package com.example.githubapp.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class ConnectionState(context: Context) {
    private val connectivityManager: ConnectivityManager = context.getSystemService(
        CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    private val networkRequestBuilder: NetworkRequest.Builder = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

    var isAvailable: Boolean = false

    init {
        registerCallbacks()
    }

    private fun registerCallbacks() {
        connectivityManager.registerNetworkCallback(networkRequestBuilder.build(), object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isAvailable = true
            }

            override fun onLost(network: Network) {
                isAvailable = false
            }
        })
    }
}