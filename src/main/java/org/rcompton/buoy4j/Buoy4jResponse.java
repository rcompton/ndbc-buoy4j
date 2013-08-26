package org.rcompton.buoy4j;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Buoy4jResponse {

	private List<Buoy4jStation> stations;

	/**
	 * constructor takes a string of xml from the ndbc website
	 * @param xmlString
	 */
	public Buoy4jResponse(String xmlString){
		stations = parseStations(xmlString);
	}

	private List<Buoy4jStation> parseStations(String xmlString){
		List<Buoy4jStation> output = new ArrayList<Buoy4jStation>();

		Document ndbcDocument = null;
		try {
			ndbcDocument = loadXMLFromString(xmlString);
			NodeList itemNodeList = ndbcDocument.getElementsByTagName("item");
			for (int i = 0; i < itemNodeList.getLength(); i++) {
				Node itemNode = itemNodeList.item(i);		        
				if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
					Buoy4jStation buoy4jStation = new Buoy4jStation(itemNode);
					output.add(buoy4jStation);
					System.out.println(buoy4jStation.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		};

		return null;
		//

	}

	/**
	 * Parse an xml string into a Document
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	static Document loadXMLFromString(String xml) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}



}
