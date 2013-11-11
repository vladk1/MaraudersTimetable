package com.example.bluetoothletags;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	    // Stops scanning after 10 seconds.
   int SCAN_PERIOD = 1000;
   
   int MESSAGE_LENGTH_S1 = 10;
   int MESSAGE_LENGTH_S2 = 10;
      
   
   
   ArrayList<String> array_list;
	  
   ArrayList<String> array_listMAC1;
   ArrayList<String> array_listMAC2;
   ArrayList<String> array_listNAME1;
   ArrayList<String> array_listNAME2;
   
  
	
	  
   ArrayAdapter<String> aa;
	  
   String MACSource1 = "";
   String MACSource2 = "";
   
   String finalData1="";
   String finalData2="";
   
    
   private BluetoothAdapter mBluetoothAdapter;
   private Handler mHandler;

   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mHandler = new Handler();
		
		array_listNAME1 = new ArrayList<String>();
		array_listNAME2 = new ArrayList<String>();
		array_listMAC1 = new ArrayList<String>();
		array_listMAC2 = new ArrayList<String>();

		
        array_list = new ArrayList<String>();
	    aa= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array_list);

        ListView list = (ListView) findViewById(R.id.listview);
        list.setAdapter(aa);
		
        
	    // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Bluetooth is not suported", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
       
        mHandler.postAtTime(rScanRepeat, SystemClock.uptimeMillis());        
        
        Button resetButton = (Button) findViewById(R.id.resetButton); 
        resetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			array_list.clear();	
			aa.notifyDataSetChanged();
			array_listMAC1.clear();
			array_listMAC2.clear();
			
			}
        });
        
        final Intent intent=new Intent(this,DisplayInfo.class);
		
        Button displayInfo1 = (Button) findViewById(R.id.displayInfo1); 
        displayInfo1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(this, DispalyInfo.class);
				intent.putExtra("big_string", finalData1);
				
				startActivity(intent);
			}
        });
        
        Button displayInfo2 = (Button) findViewById(R.id.displayInfo2); 
        displayInfo2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(this, DispalyInfo.class);
				//Intent intent = new Intent(this, DispalyInfo.class);
				intent.putExtra("big_string", finalData2);
				
				startActivity(intent);
			}
        });
        
	}
	
	

	 @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	
	private void scanLeDevice(final boolean enable) {
	        if (enable) {
	            // Stops scanning after a pre-defined scan period.
	            mHandler.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                   mBluetoothAdapter.stopLeScan(mLeScanCallback);
	                    invalidateOptionsMenu();
	                }
	            }, SCAN_PERIOD);

	            mBluetoothAdapter.startLeScan(mLeScanCallback);
	        } else {
	            mBluetoothAdapter.stopLeScan(mLeScanCallback);
	       } 
	        
	        invalidateOptionsMenu();
	    }

	 

	 
	    // Device scan callback.
	    private BluetoothAdapter.LeScanCallback mLeScanCallback =
	            new BluetoothAdapter.LeScanCallback() {
	    	
	        @Override
	        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
	            runOnUiThread(new Runnable() {
	                @Override
	                public void run() {
	                	String deviceName = device.getName();
	                	String deviceMAC = device.getAddress();
	                    int checkYouHave = checkIfAlreadyGot(deviceName);
	                    if (checkYouHave == 0) {
	                    	
	                     	array_list.add(deviceName);
	                     	aa.notifyDataSetChanged();
	                     	            	
	                     	// set name to the same MAC array source 
	                     	// if has enough names, reads the message
	                     	addNameToSameMACSource(deviceName, deviceMAC);
	                    }
	                    
	                 }
	            });
	        }
	        
	        void addAndUploadStringArray1(String deviceName, String deviceMAC) {
	        	
	        	int vlad = MESSAGE_LENGTH_S1;	
	        	
	        	if (deviceName.contains("}")) 
	        		MESSAGE_LENGTH_S1 = Integer.parseInt(deviceMAC.substring(0, 2), 16) + 1; 
	        	
	        	
	        		
	        	MACSource1 = (String) deviceMAC.subSequence(2, deviceMAC.length()-1);
	        	array_listNAME1.add(deviceName);
	        	array_listMAC1.add(deviceMAC);
	        	
	        	ArrayList<String> vladarray1 = array_listMAC1;
	        	ArrayList<String> vladarray2 = array_listNAME1;
        	
	        	
        		
        		if (array_listMAC1.size() == MESSAGE_LENGTH_S1) {
        			TextView showMessage = (TextView) findViewById(R.id.displayMessage1);
        			finalData1 = getMessage(array_listNAME1, array_listMAC1, MESSAGE_LENGTH_S1);
        	    	showMessage.setText(finalData1);
        	    	//parseToArrays(finalData1);
        		}
	        }
	        void addAndUploadStringArray2(String deviceName, String deviceMAC) {
	        	
	        	int vlad = MESSAGE_LENGTH_S2;	
	        	
	        	
	        	if (deviceName.contains("}")) 
	        		MESSAGE_LENGTH_S2 = Integer.parseInt(deviceMAC.substring(0, 2), 16) + 1; 
	        		
	        	MACSource2 = (String) deviceMAC.subSequence(2, deviceMAC.length()-1);
	        	array_listNAME2.add(deviceName);
	        	array_listMAC2.add(deviceMAC);
	        	
        		if (array_listMAC2.size() == MESSAGE_LENGTH_S2) {
        			TextView showMessage = (TextView) findViewById(R.id.displayMessage2);
        			finalData2 = getMessage(array_listNAME2, array_listMAC2, MESSAGE_LENGTH_S2);
        	    	showMessage.setText(finalData2);
        	    	//parseToArrays(finalData2);
        		}
	        }
	        
	        
	        	
	        	
	        
	        
	        
	        void addNameToSameMACSource(String deviceName, String deviceMAC) {
	        	
	        	if (MACSource1.isEmpty()) {
	        		addAndUploadStringArray1(deviceName, deviceMAC);
	        	}
	        	else {
	        		if ( deviceMAC.substring(2, deviceMAC.length()-1).equals(MACSource1) ) {
	        			addAndUploadStringArray1(deviceName, deviceMAC);
	        		}
	        		else if  (MACSource2.isEmpty()) {
		        		addAndUploadStringArray2(deviceName, deviceMAC);
		        	}
	        		else {
		        		if (deviceMAC.substring(2, deviceMAC.length()-1).equals(MACSource2)) {
		        			addAndUploadStringArray2(deviceName, deviceMAC);
		        		}
		        	}
	        	}
	        	
	        }
	        
	        private int checkIfAlreadyGot(String name) {
	        	
	        	for (int counter = 0; counter < array_list.size(); counter++) {
	        		if ( array_list.get(counter).equals(name) ) return 1;
	        	}
	        	return 0;
	        }
	    };
	   
	
	    
	    

	    
	    private final Runnable rScanRepeat = new Runnable()
	    {
	      public void run()
	      {
	    	  scanLeDevice(true);
	          mHandler.postAtTime(this, SystemClock.uptimeMillis());
	      }
	       	      
	    };

	    

	    private String getMessage(ArrayList<String> name_array_list, ArrayList<String> address_array_list, int message_length) {
	    	
	    	int getMessage=1;
	    	int messageCounter=0;
	    	String firstMessage="";
	    	
	    	String forTag0 = Integer.toHexString(0);
			char tag=forTag0.charAt(0);
	    	
	    
	    	
			
	    	while (getMessage==1) {
	    	
	    	  for(int counter = 0; counter < name_array_list.size(); counter++) {
	    		  
	    		
	    		  // else just add strings in order
	    		  if (address_array_list.get(counter).charAt(1) == tag) {
	    			  firstMessage += name_array_list.get(counter);
	    		      messageCounter++;
	    		      String forTag = Integer.toHexString(messageCounter);
	    			  tag = forTag.charAt(0);
	    		  }
	          }
	    	  if ( messageCounter >= message_length ) 
	    		  getMessage=0;
	    	  
	    	}
	    	
	    	return firstMessage;
	    }
	    
	    
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
