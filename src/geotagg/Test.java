package geotagg;

public class Test {

	public static void main(String[] args) throws Exception {
		Geotag located = new Geotag("Dodoma");
		located.call_me();
		Double latitude= located.lat;
		System.out.println(latitude);
		Double longitude = located.lon;
		System.out.println(longitude);
		

	}

}
