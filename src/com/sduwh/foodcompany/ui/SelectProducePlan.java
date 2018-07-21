package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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

public class SelectProducePlan extends JInternalFrame {


	


	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField plan_id_field,good_id_field,planer_user_id_field;
	//combobox
	private JComboBox<String> plan_state_combobox;
	//label
	private JLabel plan_id_label,good_id_label,good_numlabel,deadline_label,plan_state_label,planer_user_id_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	//checkbox
	private JCheckBox only_me_checkbox;
	/**
	 * Create the frame.
	 */
	public SelectProducePlan() {
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
	    String [] good_state ={"未确认","已投入生产","入库","取消"};
	    String [] table_title = {"生产计划号","商品号","商品数量","需要日期","计划状态","生产计划科操作人员"};
	    
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
	    
	    //初始化only_me_checkbox
	    only_me_checkbox = new JCheckBox("只看我的");
	    
	    //初始化textfield
	    plan_id_field = new JTextField();
	    plan_id_field.setColumns(25);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(25);
	    planer_user_id_field = new JTextField();
	    planer_user_id_field.setColumns(25);
	    //初始化combobox
	    plan_state_combobox = new JComboBox(good_state);
	    //plan_state_combobox.setSize(new Dimension(00, 30));
	    //初始化label
	    plan_id_label = new JLabel("计划号：");
	    good_id_label = new JLabel("商品编号：");
	    plan_state_label = new JLabel("计划状态：");
	    planer_user_id_label = new JLabel("生产计划科操作人员编号：");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(500, 30));
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(plan_id_label);
	    selectPane.add(plan_id_field);
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(planer_user_id_label);
	    selectPane.add(planer_user_id_field);
	    selectPane.add(plan_state_label);
	    selectPane.add(plan_state_combobox);
	    selectPane.add(only_me_checkbox);
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

}
