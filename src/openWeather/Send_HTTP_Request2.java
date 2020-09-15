package openWeather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send_HTTP_Request2 {
	public Double lat;
	public Double lon;
	public Send_HTTP_Request2(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	public String call_me() throws Exception {
	     //String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&mode=html&uk&APPID=ee77e7d6ae6f74f04ca1c48f4c2948d2";
		//Double lat=39.738453;
		//Double lon = -104.984853;
		String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=metric&mode=html&uk&APPID=ee77e7d6ae6f74f04ca1c48f4c2948d2";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     return response.toString();
	   }
}
