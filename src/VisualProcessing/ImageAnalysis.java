package VisualProcessing;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ImageAnalysis {
	
	static Boolean forChanged = false;
	
	public static void main(String[] args) throws IOException {
		
		FileWriter errorFile = new FileWriter("stacktrace.log", true);
		PrintWriter pw = new PrintWriter(errorFile);
		
		/*try {
			
		    File file = new File("stacktrace.log");
		    FileInputStream ps = new FileInputStream(file);
		    
		} catch (FileNotFoundException exception) {
			
		    exception.printStackTrace();
		    
		}*/
		
		String path = "src/VisualProcessing/blueTap.png";
		try {
		File file = new File(path);
		
		BufferedImage img = ImageIO.read(file);
		
		double redAvg = 0;
		double greenAvg = 0;
		double blueAvg = 0;
		double redStDev = 0;
		double blueStDev = 0;
		double greenStDev = 0;
		double total = 0;
		
		for(int x = 0; x < img.getWidth(); x++) {
			
			forChanged = false;
			
			for(int y = 0; y < img.getHeight(); y++) {
				
				int rgb = img.getRGB(x, y);
				double red = (rgb >> 16) & 0x000000FF;
				double green = (rgb >>8 ) & 0x000000FF;
				double blue = (rgb) & 0x000000FF;
				
				redAvg += red;
				greenAvg += green;
				blueAvg += blue;
				
				total++;
			}
		}
		
		redAvg /= total;
		greenAvg /= total;
		blueAvg /= total;
		
		total = 0;
		
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				
				int rgb = img.getRGB(x, y);
				double red = (rgb >> 16) & 0x000000FF;
				double green = (rgb >>8 ) & 0x000000FF;
				double blue = (rgb) & 0x000000FF;
				
				redStDev += Math.pow(red - redAvg, 2);
				blueStDev += Math.pow(blue - blueAvg, 2);
				greenStDev += Math.pow(green - greenAvg, 2);
				
				total++;
			}
		}
		
		redStDev /= total;
		blueStDev /= total;
		greenStDev /= total;
		
		redStDev = Math.sqrt(redStDev);
		blueStDev = Math.sqrt(blueStDev);
		greenStDev = Math.sqrt(greenStDev);
		
		System.out.println("red: mean: " + redAvg + " stDev: " + redStDev);
		System.out.println("blue: mean: " + blueAvg + " stDev: " + blueStDev);
		System.out.println("green: mean: " + greenAvg + " stDev: " + greenStDev);
		
		} catch (Exception e) {
			e.printStackTrace(pw);
			pw.close();
		}
		
	}
	
}
