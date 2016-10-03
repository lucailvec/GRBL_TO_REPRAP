package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public class GCODESanitizer{
	private static SanitizerRule sanitizer= null;
	
	public static void main(String [] args){

		System.out.println("STARTING");
		
		String originalName = getName(args);
		
		System.out.println("GETTING NAME: " + originalName);
		
		File file = new File(originalName+"_sanitized_" + ".gcode");

		System.out.println("CREATING NEW NAME: " + file.getName());
		
		OutputStream fileOut = tryCreateFile(file);	
		
		Reader reader = null;
		
		try {

			reader = new FileReader(originalName);

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("START SANITIZING");
		sanitizer= new SanitizerRule(reader,fileOut);
		
		try {
			sanitizer.sanitize();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("problemi di sanitizzazione");
		}
		
		
		try {
			fileOut.flush();
			fileOut.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FINISH");
	}

	private static FileOutputStream tryCreateFile(File file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			return fileOut;
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	private static String getName(String [] args){
		String originalName =null;
		try{
			
			originalName = args[args.length -1 ];
			
		}
		catch(Exception e ){
			e.printStackTrace();
			System.out.println("Add command line arguments!! (the path of the file)");
			System.exit(-1);
		}
		return originalName;
	}
	
	
}



 