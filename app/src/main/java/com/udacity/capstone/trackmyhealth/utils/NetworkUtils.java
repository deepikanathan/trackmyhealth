package com.udacity.capstone.trackmyhealth.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.udacity.capstone.trackmyhealth.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Utility class that provides network related methods
 */
public class NetworkUtils
{
    public static URL buildUrl(Context context, String med_name)
    {
        String uri;
        uri = context.getResources().getString(R.string.rx_api);

        Uri builtUri = Uri.parse(uri).buildUpon()
                .appendQueryParameter("term", med_name)
                .build();

        URL url = null;
        try
        {
            url = new URL(builtUri.toString());
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try
        {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        finally
        {
            urlConnection.disconnect();
        }
    }

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
