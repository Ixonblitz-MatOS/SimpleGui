package test;
//Imports
import java.lang.String;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.io.*;

class ConsoleColors {public static final String RESET = "\033[0m"; public static final String BLACK = "\033[0;30m"; public static final String RED = "\033[0;31m";    public static final String GREEN = "\033[0;32m";  public static final String YELLOW = "\033[0;33m"; public static final String BLUE = "\033[0;34m";   public static final String PURPLE = "\033[0;35m";public static final String CYAN = "\033[0;36m";   public static final String WHITE = "\033[0;37m";   public static final String BLACK_BOLD = "\033[1;30m";  public static final String RED_BOLD = "\033[1;31m";     public static final String GREEN_BOLD = "\033[1;32m";   public static final String YELLOW_BOLD = "\033[1;33m"; public static final String BLUE_BOLD = "\033[1;34m";  public static final String PURPLE_BOLD = "\033[1;35m";public static final String CYAN_BOLD = "\033[1;36m";   public static final String WHITE_BOLD = "\033[1;37m";  public static final String BLACK_UNDERLINED = "\033[4;30m";  public static final String RED_UNDERLINED = "\033[4;31m";   public static final String GREEN_UNDERLINED = "\033[4;32m";   public static final String YELLOW_UNDERLINED = "\033[4;33m";public static final String BLUE_UNDERLINED = "\033[4;34m";  public static final String PURPLE_UNDERLINED = "\033[4;35m"; public static final String CYAN_UNDERLINED = "\033[4;36m";   public static final String WHITE_UNDERLINED = "\033[4;37m"; public static final String BLACK_BACKGROUND = "\033[40m"; public static final String RED_BACKGROUND = "\033[41m";    public static final String GREEN_BACKGROUND = "\033[42m"; public static final String YELLOW_BACKGROUND = "\033[43m"; public static final String BLUE_BACKGROUND = "\033[44m";   public static final String PURPLE_BACKGROUND = "\033[45m"; public static final String CYAN_BACKGROUND = "\033[46m";   public static final String WHITE_BACKGROUND = "\033[47m";  public static final String BLACK_BRIGHT = "\033[0;90m"; public static final String RED_BRIGHT = "\033[0;91m";  public static final String GREEN_BRIGHT = "\033[0;92m";  public static final String YELLOW_BRIGHT = "\033[0;93m";public static final String BLUE_BRIGHT = "\033[0;94m";   public static final String PURPLE_BRIGHT = "\033[0;95m";  public static final String CYAN_BRIGHT = "\033[0;96m";   public static final String WHITE_BRIGHT = "\033[0;97m";  public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";public static final String RED_BOLD_BRIGHT = "\033[1;91m";  public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m"; public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   }

//GUI CLASS Java -> Python
public class SimpleGUI {
	protected String[] javd;
	protected String[] py;
	protected String[] CurrentFile;
	private void Error(String Message) {
	System.out.printf(ConsoleColors.RED,Message + ConsoleColors.RESET);
	System.exit(0);
	}
	
	
	private static String isType(String str) {
		String[] Types = getTextFromGithub("https://raw.githubusercontent.com/Ixonblitz-MatOS/SimpleGui/main/types.java").replace("(", "").replace(")", "").split(",");//types.java github can be globally updated
		
		int i = 0;
		while(i <= Types.length){
			if(str.contains(Types[i])) {
				return Types[i];
				
			}else {
				i+=1;	
			}
		
		}
		return "None";	
		}
	private String FindStatementType(String Statement) {
		if(Statement.contains("class")) {
			//check if it is derived
			if(Statement.contains("extends") ) {
				//is derived
				return "Derived Class";}
			else{
				//is not derived is new
				return "New Class";}}
		else if(isType(Statement)!="None") {//is a type -> is a function definition
				return isType(Statement);
		}
			
		
		
		
		
		
		
		
		
		return "None";
	}
	private int IsFoundIn(String SearchValue, String[] StringArray) {
		for(int i = 0; i <= StringArray.length;i++) {
			if(StringArray[i].contains(SearchValue.split("(")[0])){ //function(args).split("(") -> function,args) [0]=function
				return i;
			}else {continue;}}
		return -1;
	}
	private String IndexValue(String[] StringArray,int Index) {
return StringArray[Index];
	}
	private int index(String SearchString,String[] StringArray) {
	
		int finals=0;
		for(int i=0;i<StringArray.length;i++) {
			if(StringArray[i]==SearchString){
				return i;//return index number
			}else {continue;}
		}
		
		return 0;
		
	}
	public static String getTextFromGithub(String link) {
	    URL Url = null;
	    try {
	        Url = new URL(link);
	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    }
	    HttpURLConnection Http = null;
	    try {
	        Http = (HttpURLConnection) Url.openConnection();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    Map<String, List<String>> Header = Http.getHeaderFields();
	    
	    for (String header : Header.get(null)) {
	        if (header.contains(" 302 ") || header.contains(" 301 ")) {
	            link = Header.get("Location").get(0);
	            try {
	                Url = new URL(link);
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }
	            try {
	                Http = (HttpURLConnection) Url.openConnection();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            Header = Http.getHeaderFields();
	        }
	    }
	    InputStream Stream = null;
	    try {
	        Stream = Http.getInputStream();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    String Response = null;
	    try {
	        Response = GetStringFromStream(Stream);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return Response;
	}
	private static String GetStringFromStream(InputStream Stream) throws IOException {
	    if (Stream != null) {
	        Writer Writer = new StringWriter();

	        char[] Buffer = new char[2048];
	        try {
	            Reader Reader = new BufferedReader(new InputStreamReader(Stream, "UTF-8"));
	            int counter;
	            while ((counter = Reader.read(Buffer)) != -1) {
	                Writer.write(Buffer, 0, counter);
	            }
	        } finally {
	            Stream.close();
	        }
	        return Writer.toString();
	    } else {
	        return "No Contents";
	    }
	}
	void getConstructs() {
	String strungArr = SimpleGUI.getTextFromGithub("https://raw.githubusercontent.com/Ixonblitz-MatOS/SimpleGui/main/latest.add");
	String[] jav = strungArr.split("\n")[0].replace("[", "").replace("]", "").split(","); // remove all brackets and break into string arrays by , 
	String[] pys = strungArr.split("\n")[1].replace("[", "").replace("]", "").split(",");
	this.javd = jav;
	this.py = pys; // assign to class vars
	}
	void getFile(String file,boolean isPath) {
		if(isPath) {
			File files = new File(file);
			String[] FileContext = files.toString().split("\r?\n|\r\t");//all lines in file
			this.CurrentFile = FileContext;
		}else { // file is in the string already
			String[] BrokenFile = file.split("\r?\n|\r\t"); // all lines in a file
			this.CurrentFile = BrokenFile;
			
			}
		}
	void create() {
	String[] file;	
	String FinalFile;
	for(int i = 0;i<=this.CurrentFile.length;i++) {
		/*
		 * Get the index of the string in question
		 * compare the function with the ones in py
		 * find that index of py and substitute args
		 * convert to java by matching index in javd
		 * */
		int index = index(this.CurrentFile[i],this.CurrentFile);
		int Found = IsFoundIn(CurrentFile[index],this.py);
		
	}
	}}
