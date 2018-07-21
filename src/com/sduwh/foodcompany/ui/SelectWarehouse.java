package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SelectWarehouse extends JInternalFrame implements ActionListener {

	

	
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField batch_id_field,good_id_field,warehouse_user_id_field,workshop_user_id_field;
	//combobox
	private JComboBox<String> good_state_combobox;
	//label
	private JLabel batch_id_label,good_id_label,warehouse_user_id_label,workshop_user_id_label,good_state_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
		
	/**
	 * Create the frame.
	 */
	public SelectWarehouse() {
		setBounds(100, 100, 450, 300);
		
		
		setBounds(100, 100, 450, 300);
		setTitle("查询库存信息窗口");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
	    
	    //初始化selectPane和viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	   
	    //字符串
	    String [] good_state ={"正常","已售空","已过期","已销毁"};
	    String [] table_title = {"批号","商品号","生产日期","有效期","商品余量","成品库操作人员","生产车间操作人员","商品状态"};
	    
	    //初始化splitPane
	    splitPane = new JSplitPane();
	    //设置分割线位置
	    splitPane.setDividerLocation(100);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    
	    
	    //在this中添加splitPane
	    this.add(splitPane);
	    
	    
	    
	    //初始化textfield
	    batch_id_field = new JTextField();
	    batch_id_field.setColumns(20);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(20);
	    warehouse_user_id_field = new JTextField();
	    warehouse_user_id_field.setColumns(20);
	    workshop_user_id_field = new JTextField();
	    workshop_user_id_field.setColumns(20);
	    //初始化combobox
	    good_state_combobox = new JComboBox(good_state);
	    //初始化label
	    batch_id_label = new JLabel("批号：");
	    good_id_label = new JLabel("商品编号：");
	    warehouse_user_id_label = new JLabel("成品库操作人员编号：");
	    workshop_user_id_label = new JLabel("生产车间操作人员编号：");
	    good_state_label = new JLabel("商品状态：");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(230, 30));
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(batch_id_label);
	    selectPane.add(batch_id_field);
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(warehouse_user_id_label);
	    selectPane.add(warehouse_user_id_field);
	    selectPane.add(workshop_user_id_label);
	    selectPane.add(workshop_user_id_field);
	    selectPane.add(good_state_label);
	    selectPane.add(good_state_combobox);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel);
	    table.setRowHeight(50);	  
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane = new JScrollPane(table);
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,350));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane);
	    this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
