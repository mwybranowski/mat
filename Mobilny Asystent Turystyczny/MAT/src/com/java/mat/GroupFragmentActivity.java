package com.java.mat;

import com.example.mat.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GroupFragmentActivity extends Fragment {
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (LinearLayout)inflater.inflate(R.layout.activity_tab03_grupa, container, false);
    }
    
    
}