package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.sduwh.foodcompany.comm.DefaultTableModelIsEditable;

import javax.swing.JButton;

public class InsertProducePlan extends JInternalFrame implements ActionListener {

	//table&tablemodel
	private JTable table;
	private DefaultTableModel tableModel;
	private InsertProducePlan lethis = this;
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
		setBounds(100, 100, 615, 350);
		
		setTitle("创建生产计划窗口");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    //setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    
	    
	    
		//talbe初始化
		tableModel = new DefaultTableModel(table_values, table_title);
		getContentPane().setLayout(null);
		
		table = new JTable(tableModel);
		table.setRowHeight(70);
		table.setEditingColumn(1);
		table.setEditingRow(1);
		//初始化scrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 599, 256);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700, 100));
		getContentPane().add(scrollPane);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setBounds(146, 266, 306, 32);
		getContentPane().add(button);
		
		
		//为table添加双击监听事件
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int RowIndex = table.rowAtPoint(e.getPoint());
				int CulumnIndex = table.columnAtPoint(e.getPoint());
				if(RowIndex == 2 && CulumnIndex == 1){
					DateSelectDialog addselectDialog = new DateSelectDialog();
	        		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        		int width = (int)screensize.getWidth();
	        	    int height = (int)screensize.getHeight();
	        	    addselectDialog.setLocation(new Point(width*1/8, height*1/3));
	        	    addselectDialog.setAlwaysOnTop(true);
	        	    addselectDialog.show();
					
				}
				else if(RowIndex == 0 && CulumnIndex == 1){
					AddGoodIdDialog addgoodDialog = new AddGoodIdDialog();
	        		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        		//Point p = lethis.getLocation();
	        		int width = (int)screensize.getWidth();
	        	    int height = (int)screensize.getHeight();
	        	    addgoodDialog.setLocation(new Point(width*1/8, height*1/3));
	        	    addgoodDialog.setAlwaysOnTop(true);
	        	    addgoodDialog.show();
				}
				
			}
		});
		
		
		
		
		this.setVisible(true);
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
