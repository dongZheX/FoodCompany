package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SelectPickUpFrame extends JInternalFrame implements ActionListener{

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectPickUpFrame frame = new SelectPickUpFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	/**
	 * Create the frame.
	 */
	public SelectPickUpFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("查询提货单信息窗口");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
	    
	    //初始化selectPane和viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	    //初始化splitPane
	    splitPane = new JSplitPane();
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setRightComponent(viewPane);
	    //设置分割线位置
	    splitPane.setLastDividerLocation(100);
	    this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
