package org.rcompton.buoy4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.rcompton.geometry.Location;

/**
 * 
 * Class to handle ndbc requests
 * 
 * @author ryan
 *
 */
public class Buoy4j {
	
	private Location requestLocation;
	private static final STATION_TYPE stationType = STATION_TYPE.STATION; //deal with boats later

	/**
	 * Construct a Buoy4j to search for Buoys near specified lat/lng
	 * @param lat
	 * @param lng
	 */
	public Buoy4j(double lat, double lng){
		requestLocation = new Location(lat,lng);	
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
				"&radius=100");
		
		InputStream ndbcStream = ndbcURL.openStream(); 	
		return IOUtils.toString(ndbcStream, "UTF-8");
	}
	
	

}
