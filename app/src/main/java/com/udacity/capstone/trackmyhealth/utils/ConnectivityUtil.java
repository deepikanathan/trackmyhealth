package com.udacity.capstone.trackmyhealth.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectivityUtil extends ConnectivityManager.NetworkCallback {

    Context context;

    boolean isNetworkAvailable = true;

    public ConnectivityUtil(Context context) {
     this.context = context;
    }

    @Override
    public void onLost(Network network) {
        // handle network lost
        isNetworkAvailable = false;
        Toast.makeText(context, "Network Not Available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAvailable(Network network) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(network);
        boolean isConnected = (info != null && info.isConnectedOrConnecting());

        if (isConnected) {
            NetworkCapabilities nc = cm.getNetworkCapabilities(network);
            if (nc != null) {
                boolean isInternetValid = nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
                if (isInternetValid) {
                    // internet is valid
                    isNetworkAvailable = true;
                }
            }
        }
    }
}

//        NetworkRequest request = new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
//                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                connectivityManager.registerNetworkCallback(request, networkCallback);
//
//                }
