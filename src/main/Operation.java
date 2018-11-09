package main;

import javax.swing.JFrame;

import com.lsj.trans.LANG;

import com.lsj.trans.factory.TFactory;
import com.lsj.trans.factory.TranslatorFactory;

import UI.FaceUI;
import util.ReadText;
import util.WriteText;



//本程序能够自动的读取直接从论文上复制下来的数据，连接成行，之后调用谷歌翻译接口，直接的进行翻译输出。
// 论文直接复制会导致一行行的。 翻译也不准确。
public class Operation {

	public static void main(String[] args) throws Exception {
		
		FaceUI  mFaceUI= new FaceUI(); //生成界面对象。
		JFrame jf = new JFrame("自动翻译软件");
		mFaceUI.initUI(jf);
			
		//接口回调。  在点击提交按钮时候，会自动调用这个回调方法。
		FaceUI.MyConform myConform = new FaceUI.MyConform() {
			@Override
			public void doConfirm() throws Exception {
				//点击确认时会调用的方法。	
				fileOperation( mFaceUI); //调用文件翻译方法。。
				
			}
		};
				
		//注册接口回调。
		mFaceUI.registerMyConform(myConform);
		
	}
	
	//文件翻译方法。
	private static void fileOperation(FaceUI  mFaceUI) throws Exception {
		
		//1.输入路径。
		String ReadfileName = mFaceUI.getReadfileName(); //需要翻译的文件路径。
		System.out.println(ReadfileName);
		
		//2.输出路径。
		//2.1输出英文文档路径
		String WritefileName = mFaceUI.getOutPutFoldName() + mFaceUI.getOutPutEnglishName();  
		//2.2输出中文文档路径
		String AfterTransWritefileName = mFaceUI.getOutPutFoldName() + mFaceUI.getOutPutChineseName(); 
		
		//3.定义临时数据存储变量。
		String mPaperContext = ""; //存储读取到的数据，即经过拼接后的英文文档数据。
		String mAfterTransPaperContext = ""; //存储翻译过后的数据
		
		//4.读取文件到内存。
		mPaperContext = ReadText.readFileByLines(ReadfileName,0); //需要指定从第几行进行读取。
		System.out.println(mPaperContext); //输出翻译数据
		
		//5.进行翻译。
		TFactory factory = new TranslatorFactory();
		mAfterTransPaperContext = factory.get("google").trans(LANG.EN, LANG.ZH, mPaperContext);
		
		//System.out.println(AfterTransWritefileName); //输出翻译数据
		System.out.println(AfterTransWritefileName);   //输出翻译后的文档文件夹名称 路径。
		
		//6.写入文档 所有的文件都写入到同一个文件中去。
		
		//6.1将英文文档写入文本。
		mPaperContext = mPaperContext + "\r\n"; //加上一行空行。
		WriteText.WriteStringToFile(AfterTransWritefileName, mPaperContext);
		//6.2将中文文档写入文本。
		mAfterTransPaperContext = mAfterTransPaperContext + "\r\n";
		WriteText.WriteStringToFile(AfterTransWritefileName, mAfterTransPaperContext);	
	}
	
}
