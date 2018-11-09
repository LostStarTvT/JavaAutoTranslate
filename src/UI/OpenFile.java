package UI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OpenFile extends JFrame implements ActionListener {
	/**
	 *  这个是文件选择的ui
	 */
	private static final long serialVersionUID = 1L;
	JButton open=null;
	public OpenFile(){
		open=new JButton("请选择文件：");
		this.add(open);
		this.setBounds(400, 200, 100, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc=new JFileChooser();
		
		//显示文件和目录
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();
		
		//file.getAbsolutePath()获取到的绝对路径。
		if(file.isDirectory()){
			System.out.println("文件夹:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("文件:"+file.getAbsolutePath());
		}
		
		//jfc.getSelectedFile().getName() 获取到文件的名称、文件名。
		System.out.println(jfc.getSelectedFile().getName());
		
	}
}

