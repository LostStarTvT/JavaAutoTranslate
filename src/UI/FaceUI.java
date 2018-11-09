package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//框架设置。
public class FaceUI{
	
	JFrame mJFrame = new JFrame();
		
	private MyConform myConform; //定义私有接口实现回调。
	
	private String ReadfileName  = null; //输入文件名称。
	private String OutPutFoldName = null; //输出文件夹名。
	
	private String OutPutEnglishName = "EnglisFormat.txt"; //格式化之后的英文名称
	private String OutPutChineseName = "ChineseAndEnglishFormat.txt"; //格式化之后的中文文件名称
	
	private boolean TranslateToChinese = true;  //判断需要翻译的类型  默认是翻译成中文
	private boolean TranslateToEnglish = false;
	
	public boolean getTranslateToChinese() {
		return TranslateToChinese;
	}
	
	public boolean getTranslateToEnglish() {
		return TranslateToEnglish;
	}
	
	public void setTranslateToChinese(boolean TranslateToChinese) {
		this.TranslateToChinese = TranslateToChinese;
	}
	
	public void setTranslateToEnglish(boolean TranslateToEnglish) {
		this.TranslateToEnglish = TranslateToEnglish;
	}
	public String getReadfileName() {
		return ReadfileName;
	}
	
	public String getOutPutFoldName() {
		return OutPutFoldName;
	}
	
	public String getOutPutChineseName() {
		return OutPutChineseName;
	}
	
	public String getOutPutEnglishName() {
		return OutPutEnglishName;
	}
	

	
	//初始化一个UI
	public void initUI(JFrame jf) {
	
	//frame 相当于桌子，jPanel为桌布，按钮为杯子放在桌布上。
	JPanel jpanel=new JPanel();
    jpanel.setLayout(null);
        
     Container c=jf.getContentPane();
 
        jf.setLayout(null);
        c.add(jpanel);
        
        JButton bt1,bt2,bt3;
        
        JLabel l1,l2,l3;
        JLabel label=new JLabel("选择翻译类型");
        l1 = new JLabel("");
        l2 = new JLabel("");
        l3 = new JLabel("");
        
        bt1=new JButton("请选择文件：");
        bt1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser jfc=new JFileChooser();
			
			//显示文件和目录
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
			jfc.showDialog(new JLabel(), "选择");
			File file=jfc.getSelectedFile();
			
			//file.getAbsolutePath()获取到的绝对路径。
			if(file.isDirectory()){
				System.out.println("请重新选择文件！不能是文件夹！");
			}else if(file.isFile()){
				System.out.println("文件:"+file.getAbsolutePath());
				ReadfileName = file.getAbsolutePath(); //获取到文件名，即读入文件名。
				l1.setText(ReadfileName);
			}
			
			
		}
		});
        bt2=new JButton("请选择输出文件夹：");
        bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();
				
				//显示文件和目录
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				
				//file.getAbsolutePath()获取到的绝对路径。
				if(file.isDirectory()){
					System.out.println("文件夹:"+file.getAbsolutePath());
					OutPutFoldName = file.getAbsolutePath() + "\\" ;//获取到文件夹名
					l2.setText(OutPutFoldName);
					
				}else if(file.isFile()){
					System.out.println("请选择文件夹！");
				}
				
				
			}
		});
        bt3=new JButton("提交");

        bt3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					myConform.doConfirm();
					l3.setText("提交成功！");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
 
        // 需要选择的条目
        String[] listData = new String[]{"中文->英文", "英文->中文"};

        // 创建一个下拉列表框
        final JComboBox<String> comboBox = new JComboBox<String>(listData);
        
        // 设置默认选中的条目
        comboBox.setSelectedIndex(1);
        // 添加条目选中状态改变的监听器
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	if (comboBox.getSelectedIndex() == 0) {
                		 TranslateToEnglish = true;
                		// System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
					}else {
						TranslateToChinese = true;
					//	 System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
					}
                   
                }
            }
        });
 
        l1.setText("初始文件路径");
        l2.setText("输出文件夹");
        l3.setText("");
        	
        //前俩参数设置位置， 后俩参数设置 大小。
        
        bt1.setBounds(20,10,170,20);
        l1.setBounds(200,10,800,20);
        
        bt2.setBounds(20,50,170,20);
        l2.setBounds(200,50,400,20);
        
        bt3.setBounds(20,90,100,20);
        l3.setBounds(200,90,170,20);
        
        //第四行
        label.setBounds(20,130,170,20);
        comboBox.setBounds(200,130,170,20);
        
        jpanel.add(bt1);
        jpanel.add(bt2);
        jpanel.add(bt3);
//        jpanel.add(label);
        
//        jpanel.add(comboBox);
        jpanel.add(l1);
        jpanel.add(l2);
        jpanel.add(l3);
        
        jf.setSize(500, 200);
        jpanel.setSize(500, 200);
            
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
	
	public interface MyConform{
		void doConfirm() throws Exception ;
	};
	
	
	public void registerMyConform(MyConform myConform) {
		this.myConform = myConform;
	}
}
