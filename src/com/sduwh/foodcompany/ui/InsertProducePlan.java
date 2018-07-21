package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PRIVATE_MEMBER;
import javax.swing.JButton;

public class InsertProducePlan extends JInternalFrame implements ActionListener {

	//table&tablemodel
	private JTable table;
	private DefaultTableModel tableModel;
	//table_title&table_values
	private String [] table_title = {"列名","输入值"};
	private Object[][] table_values =
    {
		new Object[]{"商品名" ,""},
		new Object[]{"商品数量",""},
		new Object[]{"需要日期",""},
		  };
	
	//scrollPane
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public InsertProducePlan() {
		setBounds(100, 100, 450, 300);
		
		setTitle("创建生产计划窗口");
		
		this.setLayout(new BorderLayout());
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
		//talbe初始化
		tableModel = new DefaultTableModel(table_values, table_title);
		table = new JTable(tableModel);
		//初始化scrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700, 100));
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		this.add(new Button("test"),BorderLayout.SOUTH);
		
		//为table添加双击监听事件
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			int clickCount = e.getClickCount();
			//if (clickCount == 2) {
			System.out.println("2");
			new DateSelectDialog().show(true);
			}
			//}
		});
		
		this.setVisible(true);
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
