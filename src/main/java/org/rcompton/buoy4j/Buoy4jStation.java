package org.rcompton.buoy4j;

import java.util.Map;

import org.jsoup.Jsoup;
import org.rcompton.geometry.Location;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

/**
 * 
 * Container/parser for the current data at a buoy
 * @author ryan
 *
 */
public class Buoy4jStation {
	
	String link;
	String pubDate;
	String title;
	Location buoyLatLng;
	boolean isShip; //sometimes the data gets sent from nearby ships in addition to buoys
	String distance;
	String windDirection;
	String windSpeed;
	String waveHeight;
	String wavePeriodDominant;
	String wavePeriodMean;
	String waveDirection;
	String waterTemperature;
	
	/**
	 * Construct from a Node after parsing some xml
	 * Sample input:
	 * *  <item>
      <pubDate>Sun, 25 Aug 2013 22:55:45 UT</pubDate>
      <title>Station 46086 - SAN CLEMENTE BASIN - 27NM SE OF SAN CLEMENTE IS, CA</title>
      <description><![CDATA[
        <strong>August 25, 2013 2:50 pm PDT</strong><br />
        <strong>Location:</strong> 32.491N 118.034W or 24 nautical miles NNE of search location of 32.12N 118.2W.<br />
        <strong>Wind Direction:</strong> NNW (330&#176;)<br />
        <strong>Wind Speed:</strong> 6 knots<br />
        <strong>Wind Gust:</strong> 8 knots<br />
        <strong>Significant Wave Height:</strong> 3 ft<br />
        <strong>Dominant Wave Period:</strong> 7 sec<br />
        <strong>Average Period:</strong> 6.2 sec<br />
        <strong>Mean Wave Direction:</strong> WNW (296&#176;)<br />
        <strong>Atmospheric Pressure:</strong> 29.89 in (1012.2 mb)<br />
        <strong>Pressure Tendency:</strong> -0.02 in (-0.7 mb)<br />
        <strong>Air Temperature:</strong> 64&#176;F (17.7&#176;C)<br />
        <strong>Water Temperature:</strong> 69&#176;F (20.7&#176;C)<br />
      ]]></description>
      <link>http://www.ndbc.noaa.gov/station_page.php?station=46086</link>
      <guid>http://www.ndbc.noaa.gov/station_page.php?station=46086&amp;ts=1377467400</guid>
      <georss:point>32.491 -118.034</georss:point>
    </item>
	 * 
	 * @param itemNode
	 */
	public Buoy4jStation(Node itemNode){
		super();
		
		this.isShip = false;
		NodeList measurementsNodeList = itemNode.getChildNodes(); 
	    for (int j = 0; j < measurementsNodeList.getLength(); j++) {
	    	Node measurementsNode = measurementsNodeList.item(j);
	        if(measurementsNode.getNodeType() == Node.ELEMENT_NODE) {      	
	        	//check if the measurement came from a SHIP
	        	if(measurementsNode.getNodeName().equals("title")){
	        		this.title = getNodeText(measurementsNode);
	        		if(!this.title.toLowerCase().contains("station")){
	        			this.isShip = true;
	        		}
	        	}
	        	
	        	if(measurementsNode.getNodeName().equals("link"))
	        		this.link = getNodeText(measurementsNode);
	        	
	        	if(measurementsNode.getNodeName().equals("pubDate"))
	        		this.pubDate = getNodeText(measurementsNode);
	        	
	        	if(measurementsNode.getNodeName().equals("georss:point"))
	        		this.buoyLatLng = new Location(getNodeText(measurementsNode).split(" "));
	        	
	        	if(measurementsNode.hasChildNodes()){
	        		if(measurementsNode.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE){
	        			CharacterData cdata = (CharacterData) measurementsNode.getFirstChild();
	        			descriptionParse(cdata.getData());
	        		}       			
	        	}		        	
	        }	        
	    }
	}
	
	/**
	 * the formatting changes inside the CDATA tag
	 * @param ndbcDescription
	 * @return
	 */
	private void descriptionParse(String ndbcDescription){
		for(String line : ndbcDescription.split("<br />")){
			org.jsoup.nodes.Document D = Jsoup.parse(line);
			String lineNoTags = D.text();
			String[] lineArr = lineNoTags.split(":");
			
			if(lineArr[0].contains("Location")){
				distance = lineArr[1].split("or")[1].split("of")[0].trim() + " of search location";
			}
			if(lineArr[0].contains("Wind Direction")){
				windDirection = lineArr[1].trim();
			}
			if(lineArr[0].contains("Wind Speed")){
				windSpeed = lineArr[1].trim();
			}
			if(lineArr[0].contains("Significant Wave Height")){
				waveHeight = lineArr[1].trim();
			}
			if(lineArr[0].contains("Dominant Wave Period")){
				wavePeriodDominant = lineArr[1].trim();
			}
			if(lineArr[0].contains("Average Period")){
				wavePeriodMean = lineArr[1].trim();
			}
			if(lineArr[0].contains("Mean Wave Direction")){
				waveDirection = lineArr[1].trim();
			}
			if(lineArr[0].contains("Water Temperature")){
				waterTemperature = lineArr[1].trim();
			}	
		}
	}
	
	@Override
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	/**
	 * Get the text inside a Node
	 * @param node
	 * @return
	 */
	private static String getNodeText(Node node) {
	    StringBuffer buf = new StringBuffer();
	    NodeList children = node.getChildNodes();
	    for (int i = 0; i < children.getLength(); i++) {
	        Node textChild = children.item(i);
	        if (textChild.getNodeType() != Node.TEXT_NODE) {
	            System.err.println("Mixed content! Skipping child element " + textChild.getNodeName());
	            continue;
	        }
	        buf.append(textChild.getNodeValue());
	    }
	    return buf.toString();
	}

	public String getLink() {
		return link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getTitle() {
		return title;
	}

	public Location getBuoyLatLng() {
		return buoyLatLng;
	}

	public boolean isShip() {
		return isShip;
	}

	public String getDistance() {
		return distance;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getWaveHeight() {
		return waveHeight;
	}

	public String getWavePeriodDominant() {
		return wavePeriodDominant;
	}

	public String getWavePeriodMean() {
		return wavePeriodMean;
	}

	public String getWaveDirection() {
		return waveDirection;
	}

	public String getWaterTemperature() {
		return waterTemperature;
	}

}
