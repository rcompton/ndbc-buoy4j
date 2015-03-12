=buoy4j=
==Java wrapper for the API at http://www.ndbc.noaa.gov/==

Example usage:
{{{
        //construct lat, lng, and maximum distance to search for a buoy (in nautical miles)
	Buoy4j buoy4j = new Buoy4j(32.12,-118.2,50.2);

        //download and parse
	String responseString = buoy4j.downloadResponseString();				
	Buoy4jResponse buoy4jResponse = new Buoy4jResponse(responseString);

	System.out.println(buoy4jResponse.toString());
}}}

Should yield the below json. Alternately, you can get the data from a {{{List<Buoy4jStation>}}} using {{{buoy4jResponse.getStations()}}}. The {{{List<Buoy4jStation>}}} is sorted by the distance between the search location and the buoy.

{{{
{
   "stations":[
      {
         "link":"http://www.ndbc.noaa.gov/station_page.php?station=46086",
         "pubDate":"Mon, 26 Aug 2013 04:01:14 UT",
         "title":"Station 46086 - SAN CLEMENTE BASIN - 27NM SE OF SAN CLEMENTE IS, CA",
         "buoyLatLng":{
            "latitude":32.491,
            "longitude":-118.034
         },
         "isShip":false,
         "distance":"24 nautical miles NNE of search location",
         "windDirection":"NW (310°)",
         "windSpeed":"8 knots",
         "waveHeight":"3 ft",
         "wavePeriodDominant":"15 sec",
         "wavePeriodMean":"5.2 sec",
         "waveDirection":"WSW (255°)",
         "waterTemperature":"69°F (20.4°C)"
      },
      {
         "link":"http://www.ndbc.noaa.gov/station_page.php?station=46232",
         "pubDate":"Mon, 26 Aug 2013 04:01:13 UT",
         "title":"Station 46232 - POINT LOMA SOUTH, CA  (191)",
         "buoyLatLng":{
            "latitude":32.53,
            "longitude":-117.431
         },
         "isShip":false,
         "distance":"46 nautical miles ENE of search location",
         "waveHeight":"3 ft",
         "wavePeriodDominant":"15 sec",
         "wavePeriodMean":"6.2 sec",
         "waveDirection":"S (184°)",
         "waterTemperature":"69°F (20.4°C)"
      }
   ]
}
}}}


Installation:

{{{
	<repositories>
		<repository>
			<id>sonatype-oss-public</id>
			<url>https://oss.sonatype.org/content/groups/public/</url>
			<releases>
				<enabled>true</enabled>
			</releases>
		</repository>
	</repositories>

	<dependencies>
		<dependency>
			<groupId>com.googlecode.ndbc-buoy4j</groupId>
			<artifactId>ndbc-buoy4j</artifactId>
			<version>1.0.3</version>
		</dependency>
	</dependencies>

}}}
