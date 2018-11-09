package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


//将字符串写入文件中。 第一个参数为写文件的路径。第二个参数为写入文件的内容。
public class WriteText {
	 private static PrintStream ps;


	public  static void WriteStringToFile(String filePath,String mPaperContext) {  
	        try {  
	        	File file = new File(filePath);  
	            ps = new PrintStream(new FileOutputStream(file,true));  
	            ps.println(mPaperContext);// 往文件里写入字符串  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace(); 
	            ps.close();
	        }  
	    } 
}


