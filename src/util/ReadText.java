package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


//读取文档的类
public class ReadText {
	  /**
     * 以行为单位读取文件，常用于读面向行的格式化文件，这样可以将一行的文档连接起来。 需要指定从第几行进行读取。
	 * @throws IOException 
     */
    public static String readFileByLines(String fileName,int BeginReadLine) throws IOException {
        File file = new File(fileName);
        String mPaperContext = ""; //读取到的文件内容。	
        BufferedReader reader = null;
        int fileTotalLineNum = 0;   //文件总行
        int isThreeEnd = 1;
        
        fileTotalLineNum = GetFileTotalLine.getTotalLines(file);//获取总共的文件行数。
        
        System.out.println(fileTotalLineNum); 
        try {
        	//System.out.println("以行为单位读取文件内容，一次读一整行：");
        	
        	//reader = new BufferedReader(new FileReader(file));
        	//更改为用utf-8的格式读取文件。
        	FileInputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	boolean endFlag = tempString.endsWith("."); //判断是否是以.为结尾。
            	if (endFlag) {
            		System.out.println("line " + line + ": " + endFlag); // 找到以.为结尾的行。表示可以重新开始翻译。
            
//            		if (isThreeEnd % 4 == 0) {  //当检测到三个句号的时候，开始读取下一个行。
//						
//					}
            		
            		isThreeEnd  ++ ;
				}
            	mPaperContext = mPaperContext + ' ' +  tempString; 
//                System.out.println("line " + line + ": " + tempString); 显示每行的数据
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return mPaperContext;
    }


}
