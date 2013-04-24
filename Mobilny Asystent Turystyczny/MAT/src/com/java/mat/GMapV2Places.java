package com.java.mat;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GMapV2Places {
	
	public GMapV2Places() {
		
	}
	
	 public Document getDocument(LatLng loc, String placeName, int radius) {
	        String url = "https://maps.googleapis.com/maps/api/place/search/xml?" 
	                + "location=" + loc.latitude + "," + loc.longitude  
	                + "&radius=" + String.valueOf(radius)
	                + "&types=" + placeName
	                + "&sensor=false" 
	                + "&key=" + "AIzaSyASEwRkZt-0VSm5aPL32rbDD2CwpaZdxlE";
	        
	        try {
	            HttpClient httpClient = new DefaultHttpClient();
	            HttpContext localContext = new BasicHttpContext();
	            HttpPost httpPost = new HttpPost(url);
	            HttpResponse response = httpClient.execute(httpPost, localContext);
	            InputStream in = response.getEntity().getContent();
	            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            Document doc = builder.parse(in);
	            return doc;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	 }
	 
	 private String getAddress(NodeList nl)
	 {
		 Node addressNode = nl.item(getNodeIndex(nl, "vicinity"));
		 return addressNode.getTextContent();
	 }
	 
	 private String getName(NodeList nl)
	 {
		 Node nameNode = nl.item(getNodeIndex(nl, "name"));
		 return nameNode.getTextContent();
	 }
	 
	 public ArrayList<MarkerOptions> getPlaces (Document doc) {
		 NodeList nl1,nl2,nl3,nl4;
		 ArrayList<MarkerOptions> listGeopoints = new ArrayList<MarkerOptions>();
		 nl1 = doc.getElementsByTagName("result");
		 if(nl1.getLength() > 0){
			 for (int i=0;i<nl1.getLength();i++){
				 Node node1 = nl1.item(i);
				 nl2 = node1.getChildNodes();
				 
				 Node geometryNode = nl2.item(getNodeIndex(nl2,"geometry"));
				 nl3 = geometryNode.getChildNodes();
				 Node locationNode = nl3.item(getNodeIndex(nl3, "location"));
				 nl4 = locationNode.getChildNodes();
				 Node latNode = nl4.item(getNodeIndex(nl4, "lat"));
				 double lat = Double.parseDouble(latNode.getTextContent());
				 Node lngNode = nl4.item(getNodeIndex(nl4, "lng"));
				 double lng = Double.parseDouble(lngNode.getTextContent());
				 
				 MarkerOptions marker = new MarkerOptions();
				 marker.position(new LatLng(lat, lng));
				 marker.title(getName(nl2));
				 marker.snippet(getAddress(nl2));
				 listGeopoints.add(marker);
				 
			 }
		 }
		 return listGeopoints;
		 
	 }
	 
	 private int getNodeIndex(NodeList nl, String nodename) {
	        for(int i = 0 ; i < nl.getLength() ; i++) {
	            if(nl.item(i).getNodeName().equals(nodename))
	                return i;
	        }
	        return -1;
	    }
	 
	 
}
