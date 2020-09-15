package geotagg;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Geotag {
	private String location;
	public  Double lat;
	public  Double lon;
	
// Constructor
	
	public Geotag(String location) {
		this.location = location;
	}
	

	public void call_me() throws Exception {
	     String url = "https://www.mapquestapi.com/geocoding/v1/address?"
	     		+ "key=GEqmjo8muuQ4L04lMvEUWdUxSYNGR19N&inFormat=kvp&outFormat=json&location="+location+"&thumbMaps=false";
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
	     JSONObject obje= new JSONObject(response.toString()); 
	     lat = obje.getJSONArray("results").getJSONObject(0).getJSONArray("locations").getJSONObject(0).getJSONObject("latLng").getDouble("lat");
	     lon = obje.getJSONArray("results").getJSONObject(0).getJSONArray("locations").getJSONObject(0).getJSONObject("latLng").getDouble("lng");
	     System.out.println("Latitude  " +lat);
	     System.out.println("Longitude  " +lon);

	}
	public  Double getLat() {
		return lat;
	}
	public  Double getLon() {
		return lon;
	}
}
