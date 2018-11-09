package xpdf;

import java.io.File;
import java.io.IOException;

public class XpdfToText {
	//pdf 名
	private File pdffile;
	
	//转换器所在的文件夹路径
	private String CONVERTOR_STORED_PATH = "D:\\xpdftools\\bin64\\";
	
	//转换器的名称 
	private String CONVERTOR_NAME = "pdftotext";
	
	// 构造函数，参数为pdf文件的路径  
    public XpdfToText(String pdffile) throws IOException {  
        this.pdffile = new File(pdffile);  
    } 	
	
    //将pdf转为文本文档，参数为目标文件的路径
    public void toTextFile(String targertfile) throws IOException {
		toTextFile(targertfile,false);
	}
    
    //将pdf转为文本文档，参数为目标文件的路径
    //参数二 为true 表示使用pdf中的布局
    public void toTextFile(String targertfile,boolean isLayout) throws IOException{
			String [] cmd  = getCmd(new File(targertfile),isLayout);
			System.out.print(cmd[0] + cmd[1] + cmd[2] + cmd[3]+ cmd[4]+ cmd[5]+ cmd[6]+ cmd[7]);
			Runtime.getRuntime().exec(cmd);
	}
    
    //获取pdf转换器的路径
    public String getCONVERTOR_STORED_PATH() {
    	return CONVERTOR_STORED_PATH;
    }
    
    //设置pdf转换器的路径
    public void setCONVERTOR_STORED_PATH(String path) {
    	if (!path.trim().endsWith("\\"))  
    	{
    		path = path.trim() + "\\";  
    	}
    	this.CONVERTOR_STORED_PATH = path;
    }
    
    
    
    //解析命令行参数
	private String[] getCmd(File targetfile, boolean isLayout) {
		// TODO Auto-generated method stub
		// 命令字符  
		String command = CONVERTOR_STORED_PATH + CONVERTOR_NAME;
		// PDF文件的绝对路径 
		String source_absolutePath = pdffile.getAbsolutePath(); 
		// 输出文本文件的绝对路径
		String target_absolutePath = targetfile.getAbsolutePath();
		// 保持原来的layout  
		String layout = "-layout";
		// 设置编码方式  
		String encoding = "-enc";  
        String character = "UTF-8";
        
        // 设置不打印任何消息和错误  
        String mistake = "-q"; 
        
        // 页面之间不加入分页  
        String nopagebrk = "-nopgbrk";
     // 如果isLayout为false，则设置不保持原来的layout  
        if (!isLayout)  
            layout = "";
        
        return new String[] { command, layout, encoding, character, mistake,nopagebrk, source_absolutePath, target_absolutePath };
	}

    
}
