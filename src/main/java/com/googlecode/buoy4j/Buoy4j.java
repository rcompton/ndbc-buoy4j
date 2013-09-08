package com.googlecode.buoy4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.googlecode.buoy4j.geometry.Location;

/**
 * 
 * Class to handle ndbc requests
 * 
 * @author ryan
 *
 */
public class Buoy4j {
	
	private Location requestLocation;
	private double maxDistance;
	//private static final STATION_TYPE stationType = STATION_TYPE.STATION; //deal with boats later

	/**
	 * Construct a Buoy4j to search for Buoys near specified lat/lng
	 * default max distance is 100 nautical miles
	 * @param lat
	 * @param lng
	 */
	public Buoy4j(double lat, double lng){
		requestLocation = new Location(lat,lng);	
		maxDistance = 100;
	}
	
	/**
	 * Construct a Buoy4j to search for Buoys near specified lat/lng
	 * within the given radius
	 * @param lat
	 * @param lng
	 */
	public Buoy4j(double lat, double lng, double maxDistance){
		requestLocation = new Location(lat,lng);	
		this.maxDistance = maxDistance;
	}
		
	/**
	 * Connect to NDBC webservice and return the RSS feed
	 * @return
	 * @throws IOException 
	 */
	public String downloadResponseString() throws IOException{
		
		URL ndbcURL = new URL("http://www.ndbc.noaa.gov/rss/ndbc_obs_search.php?" +
				"lat="+requestLocation.getLatitude()+
				"&lon="+requestLocation.getLongitude()+
				"&radius="+maxDistance);
		
		InputStream ndbcStream = ndbcURL.openStream(); 	
		return IOUtils.toString(ndbcStream, "UTF-8");
	}
	
}
