package com.java.mat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class NavigationFragmentActivity extends Fragment {
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (LinearLayout)inflater.inflate(R.layout.activity_tab02_nawigacja_zalog, container, false);
    }
}