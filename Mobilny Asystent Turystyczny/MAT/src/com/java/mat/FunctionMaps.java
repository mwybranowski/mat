package com.java.mat;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.java.mat.GMapV2Direction.Mode;

public class FunctionMaps {
	
	private ArrayList<MarkerOptions> markers;
	private GMapV2Direction md;
	private ArrayList<PolylineOptions> routes;
	private ArrayList<MarkerOptions> places;
	
	private GMapV2Places mp;
	
	//konstruktor bezarg.
	public FunctionMaps()
	{
		markers = new ArrayList<MarkerOptions>();
		md = new GMapV2Direction();
		routes = new ArrayList<PolylineOptions>();
		places = new ArrayList<MarkerOptions>();
		mp = new GMapV2Places();
		
		//testowe spinki
       	addLocation(50.061664,19.937217,"Tomek");
    	addLocation(50.259,20.020,"Mateusz");
    	addLocation(48.259,20.020,"Waldek");
    	
    	//testowe google places
    	addPlaces(markers.get(0),"restaurant",1000);
    	
    	//testowa trasa
    	addRoute(markers.get(0), markers.get(1),Mode.walking);
    	addRoute(places.get(0), markers.get(0), Mode.walking);
    	

	}
	
	//dodaje do listy jednego u�ytkownika jako spink�
	void setLocation(MarkerOptions marker)
	{
		markers.add(marker);
	}
	
	//zwraca list� u�ytkownik�w jako spinki
	ArrayList<MarkerOptions> getLocations()
	{
		return markers;
	}
	
	//dodaje do listy jedego u�ytkownika wraz z lokalizacj�
	void addLocation(double x,double y,String name)
	{
		LatLng position = new LatLng(x,y);
		MarkerOptions marker = new MarkerOptions();
		marker.position(position);
		marker.visible(true);
		marker.title(name);
		markers.add(marker);
	}
	
	//zwraca tras� 
	ArrayList<PolylineOptions> getRoutes()
	{
		return routes;
	}
	
	void updateLocation()
	{

	}
	
	void updateRoute()
	{
		for(int i=0;i<routes.size();i++)
		{
			//routes
		}
			
	}
	
	//dodaje tras� mi�dzy dwoma punktami (spinkami)
	void addRoute(MarkerOptions m1,MarkerOptions m2,Mode mode)
	{
		Document doc = md.getDocument(m1.getPosition(), m2.getPosition(), mode.toString());
		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.BLUE);
		
		rectLine.addAll(md.getDirection(doc));
		routes.add(rectLine);
	}
	
	//dodaje miejsca z Google Places jako spinki, parametry: lokalizacja zaczepu, nazwa miejsca oraz promie� wyszukiwania
	void addPlaces(MarkerOptions m,String placeName, int radius)
	{
		Document doc = mp.getDocument(m.getPosition(), placeName, radius);
		places.addAll(mp.getPlaces(doc));
	}
	
	//zwraca miejsca z Google Places
	ArrayList<MarkerOptions> gePlaces()
	{
		return places;
	}

}
