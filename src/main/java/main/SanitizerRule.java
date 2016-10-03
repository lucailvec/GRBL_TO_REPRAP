package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public class SanitizerRule {
	
	private String lastCommand=null;
	private BufferedReader in;
	private final OutputStream out;
	private char state;
	
	public SanitizerRule(Reader reader, OutputStream fileOut) {
			this.in = new BufferedReader(reader);
		this.out = fileOut;
	}

	public void sanitize() throws IOException {
		String sCurrentLine;
		String temp=null;
		state = 0;
		while ((sCurrentLine = in.readLine()) != null) {
			temp=sanitize(sCurrentLine);
			if(temp!=null){
				out.write((temp).getBytes());
				printSanite(sCurrentLine, temp);
			}
			else{
				printTrash(sCurrentLine);
			}
		}
		out.flush();
		
	}
	public String sanitize(String sCurrentLine){
		if(sCurrentLine.length()==0){
			return null;
		}
		else{
			switch(sCurrentLine.charAt(0)){
			case '(': case 'M': case '%'://ignore
					return null;
			case 'G': state='G';
					sCurrentLine= sCurrentLine.replace("G17","").replace("G18","").replace("G19","");
					sCurrentLine = sCurrentLine.trim();


					sCurrentLine= sCurrentLine.replace("G0 ","G1 ");
					state='G';


					if(sCurrentLine.length()<=1){
						return null;
					}
					else{
						String other = sCurrentLine;
						if(sCurrentLine.split(" ")[0].startsWith("G")){
							lastCommand=sCurrentLine.split(" ")[0];
							return sCurrentLine + "\n";
						}
						else{
							sCurrentLine=addCommand(other,'G',lastCommand);
							return sCurrentLine + "\n";
						}
					}
						
			default:
				if(state == 'G'){
					return (lastCommand + " " + sCurrentLine + "\n");
				}
				else{
					return null;
				}
			}
		}
	}
	
	public String addCommand(String sCurrentLine,char localChar, String localCommand){
		if(sCurrentLine.length()==0){
			return "";
		}
		else{
			switch(sCurrentLine.charAt(0)){
			default:
					return (lastCommand + " " + sCurrentLine );
				
			}
		}
	}
	
		private void printTrash(String str){
			System.out.println("Erase str: " + str);
		}
		private void printSanite(String strIn,String strOut){
			System.out.println("Change str: " + strIn + " Into : " + strOut);
		}
}
