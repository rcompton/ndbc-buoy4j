package com.googlecode.buoy4j;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MessWithRandom {

	public static void main(String[] args) throws IOException{
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
        	inputStream = new FileReader("/dev/urandom");
            outputStream = new FileWriter("/home/ryan/urandom");

            int c;
            while ((c = inputStream.read()) != -1) {
            	outputStream.write(c);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        } finally{
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
		
	}
	
}
