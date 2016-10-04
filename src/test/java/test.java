import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;


import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import main.SanitizerRule;


public class test {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max

	//private final double DELTA = .1E-10;
	
	
	public void usefullMethod(String expected,String testString){
		StringReader strReader = new StringReader(testString);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		SanitizerRule sanitizer = new SanitizerRule(strReader, os);
		
		try {
			sanitizer.sanitize();
		} catch (IOException e1) {
			e1.printStackTrace();
			fail();
		}
		
		try {
			String aString = new String(os.toByteArray(),"UTF-8");
			assertEquals(expected,aString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void baseFunction1 (){
		String testString = "G1 X13.577 Y13.069\n"
				+ "Z15\n"
				+"Z5\n"; 
		String expected = "G1 X13.577 Y13.069\n"
				+ "G1 Z15\n"
				+"G1 Z5\n"; 
		usefullMethod(expected, testString);

	}
	
	@Test
	public void iNotWantG0 (){
		String testString = "G0 X13.577 Y13.069\n"
				+ "Z15\n"
				+"Z5\n"; 
		String expected = "G1 X13.577 Y13.069\n"
				+ "G1 Z15\n"
				+"G1 Z5\n"; 
		
		usefullMethod(expected, testString);
	}
	@Test
	public void removeSingleMultiG17G18G19(){
		String testString = "G17\n"
				+ "G18\n"
				+"G19\n"; 
		String expected = ""; 
		
		usefullMethod(expected, testString);
		
		String testString2 = "G17\n"
				+ "G18 G1 X0 Y1\n"; 
		String expected2 = "G1 X0 Y1\n"; 
		
		usefullMethod(expected2, testString2);
		
		String testString3 = "G17\n"
				+ "G18 G0 X2 Y3 Z15\n"
				+"G19 Z5\n"; 
		String expected3 = "G1 X2 Y3 Z15\n"
				+"G1 Z5\n"; 
		
		usefullMethod(expected3, testString3);
	}
	@Test
	public void particularG17G18G19(){
		String testString = "G17 G1 X1 Y2\n"
				+ "G18 Z3\n"
				+"G19 Z4\n"; 
		String expected = "G1 X1 Y2\n" +
				"G1 Z3\n" +
				"G1 Z4\n"; 
		
		usefullMethod(expected, testString);	
	}
	@Test
	public void negativeHigh(){
		String testString = "G1 Z2\n"
				+ "G1 Z-2\n"
				+"Z-0.3\n"; 
		String expected = "G1 Z2\n" +
				"G1 Z-2 S1\n" +
				"G1 Z-0.3 S1\n"; 
		
		usefullMethod(expected, testString);
		
		
	}
	
	
}
