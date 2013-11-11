package com.example.bluetoothletags;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

     /**
	 * DrawView.java 
	 * @author Vladyslav Kolesnyk 12/02/2013
	 * A house
	 */

public class DrawView extends View {
	
    // here you can change parameters of the house, so you can create a house of any size
	  int numberStrToDisplay = 3;
		
      int letterColor, noletterColor;
		
    	    
		private Paint backgroundPaint = new Paint();
		private Paint drawColour = new Paint();
		
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
				noletterColor = Color.rgb(105, 49, 1);
			   
				int counter = 0;
				int line = 1;
				
				while (counter<name_info_array.size() && counter<destination_info_array.size() && 
						counter<time_info_array.size() && counter<numberStrToDisplay && counter<4) {
					
			       line= drawStringLn(canvas, name_info_array.get(counter), 
			        		destination_info_array.get(counter), time_info_array.get(counter),line);
			      counter++;
				}
		}
		
		
		private int drawStringLn(Canvas canvas, String name, String destination, String time, int line) {
		
		     
			prepareWord(canvas, name, line++);
			prepareWord(canvas, destination, line++);
			prepareWord(canvas, time, line++);
			prepareWord(canvas, " ", line++);
			
			
			 return line;
			 
		}
		
		
		private void prepareWord(Canvas canvas, String string, int line) {
		
			while (string.length()<14) {
				StringBuilder builder = new StringBuilder();
				builder.append(string);  
				builder.append(' ');
			    string=builder.toString();
			}
			drawWord(canvas, string, line);
		  	
		}
		
		
		private void drawWord(Canvas canvas, String string,int line) {
		
			for (int counter = 0; counter<string.length(); counter++) {
				drawChar(canvas, string.charAt(counter),counter,line);
			}
		
		}
		
		private void drawChar(Canvas canvas, char letter, int counter, int line) {
			
			switch(letter) {
			
			case 'a': 
				drawLetter(canvas, "0x18,0x3C,0x66,0x7E,0x66,0x66,0x66,0x00",counter+1,line);
				counter++;
		    break;
		    
		    case 'b': 
		    	drawLetter(canvas, "0x7C,0x66,0x66,0x7C,0x66,0x66,0x7C,0x0",counter+1,line);//
		    	counter++;
	        break;
		    case 'c': 
		    	drawLetter(canvas, "0x3C,0x66,0x60,0x60,0x60,0x66,0x3C,0x00",counter+1,line);//
		    	counter++;
	        break;
		    case 'd': 
		    	drawLetter(canvas, "0x78,0x6C,0x66,0x66,0x66,0x6C,0x78,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'e': 
		    	drawLetter(canvas, "0x7E,0x60,0x60,0x78,0x60,0x60,0x7E,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'f': 
		    	drawLetter(canvas, "0x7E,0x60,0x60,0x78,0x60,0x60,0x60,0x00",counter+1,line); //
		    	counter++;
	        break;
	        
		    case 'g': 
		    	drawLetter(canvas, "0x3C,0x66,0x60,0x6E,0x66,0x66,0x3C,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'h': 
		    	drawLetter(canvas, "0x66,0x66,0x66,0x7E,0x66,0x66,0x66,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'i': 
		    	drawLetter(canvas, "0x3C,0x18,0x18,0x18,0x18,0x18,0x3C,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'j': 
		    	drawLetter(canvas, "0x1E,0x0C,0x0C,0x0C,0x0C,0x6C,0x38,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'k': 
		    	drawLetter(canvas, "0x66,0x6C,0x7C,0x70,0x78,0x6C,0x66,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'l': 
		    	drawLetter(canvas, "0x60,0x60,0x60,0x60,0x60,0x60,0x7E,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'm': 
		    	drawLetter(canvas, "0x63,0x77,0x7F,0x6B,0x63,0x63,0x63,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'n': 
		    	drawLetter(canvas, "0x66,0x76,0x7E,0x7E,0x6E,0x66,0x66,0x00",counter+1,line);
		    	counter++;
	        break;
	        
		    case 'o': 
		    	drawLetter(canvas, "0x3C,0x66,0x66,0x66,0x66,0x66,0x3C,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'p': 
		    	drawLetter(canvas, "0x7C,0x66,0x66,0x7C,0x60,0x60,0x60,0x00",counter+1,line);//
		    	counter++;
	        break;
	        
		    case 'q': 
		    	drawLetter(canvas, "0x3C,0x66,0x66,0x66,0x66,0x3C,0x0E,0x00",counter+1,line);
		    	counter++;
	        break;
	        
		    case 'r': 
		    	drawLetter(canvas, "0x7C,0x66,0x66,0x7C,0x78,0x6C,0x66,0x00",counter+1,line);
		    	counter++;
	        break;
	        
		    case 's': 
		    	drawLetter(canvas, "0x3C,0x66,0x60,0x3C,0x06,0x66,0x3C,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 't': 
		    	drawLetter(canvas, "0x7E,0x18,0x18,0x18,0x18,0x18,0x18,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'u': 
		    	drawLetter(canvas, "0x66,0x66,0x66,0x66,0x66,0x66,0x3C,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'v': 
		    	drawLetter(canvas, "0x66,0x66,0x66,0x66,0x66,0x3C,0x18,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'w': 
		    	drawLetter(canvas, "0x63,0x63,0x63,0x6B,0x7F,0x77,0x63,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'x': 
		    	drawLetter(canvas, "0x66,0x66,0x3C,0x18,0x3C,0x66,0x66,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'y': 
		    	drawLetter(canvas, "0x66,0x66,0x66,0x3C,0x18,0x18,0x18,0x00",counter+1,line);
		    	counter++;
	        break;
		    case 'z': 
		    	drawLetter(canvas, "0x7E,0x06,0x0C,0x18,0x30,0x60,0x7E,0x00",counter+1,line);
		    	counter++;
	        break;
	        
		    case ' ': 
		    	drawLetter(canvas, "0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00",counter+1,line);
		    	counter++;
	        break;
	
			}
     		
		}
		
		
		private void drawLetter(Canvas canvas, String code, int row_order, int column_order) {
			
			 float left = this.getLeft()+20; // 0
			 float top = this.getTop()+20; // 1
			 float size = 1; // 2
			
			 ArrayList<String> array_code = getArrayCode(code);
			
			for (int row_count=0; row_count<8; row_count++) {
				
				 String rawDefinition = array_code.get(row_count);
				 for (int column_count=0; column_count<8; column_count++) {
					 
					 if (rawDefinition.charAt(column_count)=='1')
					  canvas.drawCircle((left*(row_order)+column_count*2), top*(column_order)+row_count*2, size, putColor(letterColor));
					 else
				      canvas.drawCircle((left*(row_order)+column_count*2), top*(column_order)+row_count*2, size, putColor(noletterColor));
				 }	 
			 }
		}
		
		private ArrayList<String> getArrayCode(String code){
			ArrayList<String> outputArray = new ArrayList<String>();
			int breakpoint=0;
			String seperateString="";
			
			for (int counter=0; counter<code.length(); counter++) {
				
				if (code.charAt(counter)==',') {
					seperateString=code.substring(breakpoint, counter);
					int translateFromHex = Integer.decode(seperateString);
					String toBinary = Integer.toBinaryString(translateFromHex);
					
					while (toBinary.length()<8) {
						StringBuilder builder = new StringBuilder();
						builder.append('0');
					    builder.append(toBinary);
					    toBinary=builder.toString();
					}
					
					outputArray.add(toBinary);
					 breakpoint=counter+1;
				}
					
			}
			seperateString=code.substring(breakpoint, code.length());
			int translateFromHex = Integer.decode(seperateString);
			String toBinary = Integer.toBinaryString(translateFromHex);
			StringBuilder builder = new StringBuilder();
			
			builder.append(toBinary);
			while (toBinary.length()<8) {
				builder.append('0');
			    builder.append(toBinary);
			    toBinary=builder.toString();
			}
			
			outputArray.add(toBinary);
			return outputArray;
		}
		
		
		private Paint putColor(int colour)
		{
			
			drawColour.setColor(colour);
			drawColour.setStyle(Style.FILL);
			
			return drawColour;
		}
		
				
		
	}


