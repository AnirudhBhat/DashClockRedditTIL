package com.anirudh.reddittil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class HTTPData {
	
	private static final String TAG = "HTTPData";
	/* This method returns a connection to the specified URL,
	 * with necessary properties like timeout and user-agent
	 *  */
	
	public static HttpURLConnection getConnection(String url){
		HttpURLConnection hcon = null;
		try{
			hcon = (HttpURLConnection) new URL(url).openConnection();
			hcon.setReadTimeout(30000); // Timeout at 30 seconds
			hcon.setRequestProperty("User-Agent", "Reddittil V1.0");
		}catch(MalformedURLException e){
			Log.e(TAG, "Invalid URL: " + e.toString());
		}catch(IOException e){
			Log.e(TAG, "could not connect: " + e.toString()); 
		}
		return hcon;
	}
	
	
	/*
	 * Method to read the contents of URL and returns them as String
	 */
	
	public static String readContents(String url){
		HttpURLConnection hcon = getConnection(url);
		if (hcon == null)
			return null;
		try{
			StringBuffer sb = new StringBuffer(9000);
			String tmp = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(hcon.getInputStream()));
			while ((tmp = br.readLine())!= null)
				sb.append(tmp).append("\n");
			br.close();
			return sb.toString();
		}catch(IOException e){
			Log.e(TAG, "READ FAILED: " + e.toString());
			return null;
		}
	}
}
