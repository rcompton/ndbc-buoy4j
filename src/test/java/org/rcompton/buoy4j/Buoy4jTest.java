package org.rcompton.buoy4j;

import java.io.IOException;

import org.junit.Test;

public class Buoy4jTest {

	Buoy4j buoy4j;
	
	@Test
	public void buoy4jTest() throws IOException{
		buoy4j = new Buoy4j(32.12,-118.2);
//		buoy4j.downloadResponseString();
//		System.out.println(buoy4j.getResponseString());
	}
	
	@Test
	public void buoy4jResponseTest() throws IOException{
		Buoy4j buoy4j = new Buoy4j(32.12,-118.2);
		
		String xmlString = buoy4j.downloadResponseString();
		
		System.out.println(xmlString);
		
		//buoy4j.downloadResponseString();
		Buoy4jResponse buoy4jResponse = new Buoy4jResponse(xmlString);
	}
	
}
