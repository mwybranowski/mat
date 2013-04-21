package com.java.mat;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.java.mat.GMapV2Direction.Mode;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnLongClickListener;



public class MapActivity extends FragmentActivity implements LocationListener {

	private GoogleMap googlemap;
	private FunctionMaps moduleMap;
	
	//konstruktor bezarg.
	public MapActivity()
	{
		moduleMap = new FunctionMaps();
	}
	
	//wy�wietla na mapie pineski z listy
	void showLocations()
	{
		for(int i=0;i<moduleMap.getCountLocation();i++)
		{
			googlemap.addMarker(moduleMap.getMarker(i));
		}
	}
	
	void showRoute()
	{
		for(int i=0;i<moduleMap.getCountRoute();i++)
		{
			googlemap.addPolyline(moduleMap.getRoute(i));
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isGooglePlay())
        {
        	setContentView(R.layout.activity_map);
        	setUpMap();
    		showLocations();
    		showRoute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private boolean isGooglePlay()
    {
    	int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    	if(status == ConnectionResult.SUCCESS)
    	{
    		return true;
    	}
    	else
    	{
    		((Dialog)GooglePlayServicesUtil.getErrorDialog(status, this, 10)).show();
    	}
    	return false;
    }
    private void setUpMap(){
    	if(googlemap == null)
    	{
    		
    		googlemap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    		
    		if(googlemap !=null){
    			//add some code
    			googlemap.setMyLocationEnabled(true);
    			LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
    			String provider = lm.getBestProvider(new Criteria(), true);
    			
    			if(provider == null){
    				onProviderDisabled(provider);
    			}
    			
    			Location loc = lm.getLastKnownLocation(provider);
    			if(loc != null){
    				onLocationChanged(loc);
    			}
    			
    			googlemap.setOnMapClickListener(OnLongClickMapSettings());
    		}
    	}
    }


	private OnMapClickListener OnLongClickMapSettings() {
		// TODO Auto-generated method stub
		
		return new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				Log.i(arg0.toString(),"user long clicked");
			}
		};
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
		googlemap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
		googlemap.animateCamera(CameraUpdateFactory.zoomTo(10));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
    
}