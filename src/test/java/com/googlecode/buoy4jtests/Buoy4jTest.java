package com.googlecode.buoy4jtests;

import java.io.IOException;

import org.junit.Test;

import com.googlecode.buoy4j.Buoy4j;
import com.googlecode.buoy4j.Buoy4jResponse;

public class Buoy4jTest {


	@Test
	public void buoy4jTest() throws IOException{
		Buoy4j buoy4j = new Buoy4j(32.12,-118.2,50.2);
		String responseString = buoy4j.downloadResponseString();
		System.out.println(responseString);
	}
	
	@Test
	public void buoy4jResponseTest() throws IOException{
		Buoy4j buoy4j = new Buoy4j(32.12,-118.2,50.2);
		String responseString = buoy4j.downloadResponseString();				
		Buoy4jResponse buoy4jResponse = new Buoy4jResponse(responseString);
		System.out.println(buoy4jResponse.toString());
	}
	
}
