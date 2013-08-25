package org.rcompton.buoy4j;

import java.io.IOException;

import org.junit.Test;

public class Buoy4jTest {

	@Test
	public void buoy4jTest() throws IOException{
		Buoy4j buoy4j = new Buoy4j(32.12,-118.2);
		System.out.println(buoy4j.getResponseString());
	}
	
}
