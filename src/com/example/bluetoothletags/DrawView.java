package com.example.bluetoothletags;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

     /**
	 * DrawView.java 
	 * @author Vladyslav Kolesnyk 12/02/2013
	 * A house
	 */

	public class DrawView extends View {
		
      // here you can change parameters of the house, so you can create a house of any size
		int houseTopValue = 250;
		int houseBottomValue = 400;
        int houseLeftValue = 100;
        int houseRightValue = 250;
        
        int numberStrToDisplay = 3;
		
        int letterColor;
		
        boolean day = true;
		
		Point triangle_point1 = new Point();        
		Point triangle_point2 = new Point();    
		Point triangle_point3 = new Point();
		    
		private Paint backgroundPaint = new Paint();
		private Paint drawColour = new Paint();
		private Paint textPaint = new Paint();
		
		ArrayList<String> name_info_array;
		ArrayList<String> destination_info_array;
		ArrayList<String> time_info_array;
		
		public DrawView(Context context, 
				ArrayList<String> input_name_info_array, ArrayList<String> input_destination_info_array, 
				ArrayList<String> input_time_info_array) {
			
			super(context);
							
			name_info_array = input_name_info_array;
			destination_info_array = input_destination_info_array;
			time_info_array = input_time_info_array;
			
			
			setFocusable(true);
			setFocusableInTouchMode(true);
			
			backgroundPaint.setColor(Color.BLACK);
			backgroundPaint.setAntiAlias(true);
			backgroundPaint.setStyle(Style.FILL);

			
		}

		@Override
		public void onDraw(Canvas canvas)
		{
				letterColor = Color.rgb(255, 119, 0);
			   
				int counter = 0;
				while (counter<name_info_array.size() && counter<destination_info_array.size() && 
						counter<time_info_array.size() && counter<numberStrToDisplay) {
					
			        drawStringLn(canvas, name_info_array.get(counter), destination_info_array.get(counter), time_info_array.get(counter));
			        
				}
		}
		
		
		private void drawStringLn(Canvas canvas, String name, String destination, String time) {
		
						
   
			//draw a sun
			canvas.drawCircle(this.getLeft()+40, this.getTop()+40, 30, putColor(letterColor));
			
		}
		
		
		
		private Paint putColor(int colour)
		{
			
			drawColour.setColor(colour);
			drawColour.setStyle(Style.FILL);
			
			return drawColour;
		}
		
				
		
	}


