package geotagg;

import openWeather.Send_HTTP_Request2;

public class Test {

	public static void main(String[] args) throws Exception {
		Geotag located = new Geotag("Dodoma");
		located.call_me();
		Double latitude= located.lat;
		System.out.println(latitude);
		Double longitude = located.lon;
		System.out.println(longitude);
		Send_HTTP_Request2 request = new Send_HTTP_Request2(latitude, longitude);
		String htmlResponse = request.call_me();
		System.out.println(htmlResponse);

	}

}
