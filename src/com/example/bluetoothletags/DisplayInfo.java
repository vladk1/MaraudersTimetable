package com.example.bluetoothletags;


import java.util.ArrayList;

import com.example.bluetoothletags.DrawView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DisplayInfo extends Activity {
	
	
	 ArrayList<String> name_info_array;
	 ArrayList<String> destination_info_array;
	 ArrayList<String> time_info_array;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.display_info);
		
		name_info_array = new ArrayList<String>();
		destination_info_array = new ArrayList<String>();
		time_info_array = new ArrayList<String>();

		
		
		String big_string = getIntent().getExtras().getString("big_string");
		
		
		
		parseToArrays(big_string);
		
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		DrawView dv = new DrawView(this,name_info_array, destination_info_array, time_info_array);
		setContentView(dv);
		dv.requestFocus();
				
		
	}
	
	

    void parseToArrays(String inputBigString) {
    	String name = "";
    	String destination = "";
    	String time = "";
    	
    	int attributeCounter = 1;
    	int lastBreakPoint = 1;
    	
    	for (int counter=0; inputBigString.charAt(counter)!='}'; counter++ ) {
    	
    		if (inputBigString.charAt(counter)==';') {
    		
    		if (attributeCounter == 3){
 				time = inputBigString.substring(lastBreakPoint, counter);
 				time_info_array.add(time);
 			}
    		lastBreakPoint = counter+1;
    		attributeCounter=1;
    		 
    		}
    		
    		if (inputBigString.charAt(counter)==',') {
    			
    			if (attributeCounter == 1) {
    		    	name = inputBigString.substring(lastBreakPoint, counter);
    		    	name_info_array.add(name);
    			}
    			
    			else if (attributeCounter == 2){
    				destination = inputBigString.substring(lastBreakPoint, counter);
    				destination_info_array.add(destination);
    			}
    			
    			
    	     	lastBreakPoint = counter+1;
    	     	attributeCounter++;
    		}    	
        	
    	}
    	
    	
    
    
    
    for (int counter = 0; counter<name_info_array.size();counter++) {
		
		if ((counter<name_info_array.size()) && (counter<destination_info_array.size()) && (counter<time_info_array.size()) ) {
			
			switch(counter) {
			case 0: {				
			}
			break;
			}
				
		}
		
		
			
    }
}
}
	


