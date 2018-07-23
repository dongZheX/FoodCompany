package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.SelectGoodBll;
import com.sduwh.foodcompany.entity.Goods;

public class SelectGoodFrame extends JInternalFrame implements ActionListener {

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectGoodFrame frame = new SelectGoodFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
//	good_id
//	good_name
//	good_standard
//	good_cost
//	good_expiration_date

	private SelectGoodFrame selectGoodFrame = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField good_id_field,good_name_field;
	//label
	private JLabel good_id_label,good_name_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	//弹出菜单
	private JPopupMenu m_popupMenu;
	
	 //字符串
    private String [] table_title = {"商品编号","名称","规格","成本","保质期"};


	/**
	 * Create the frame.
	 */
	public SelectGoodFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("查询商品窗口");
		
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
	    //设置分割线位置
	    splitPane.setDividerLocation(50);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    
	    
	    //在this中添加splitPane
	    this.add(splitPane);
	    
	    
	    //初始化textfield
	    

	    
	    good_id_field = new JTextField();
	    good_id_field.setColumns(20);
	    good_name_field = new JTextField();
	    good_name_field.setColumns(20);
	    //初始化label
	    good_id_label = new JLabel("商品号");
	    good_name_label = new JLabel("商品名");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(200, 30));
	    select_btn.addActionListener(this);
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(good_name_label);
	    selectPane.add(good_name_field);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
	    table.setRowHeight(30);	
	    
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
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("查询")){
			select_btn_action();
		}
		
	}
	
	public void select_btn_action(){
		String []key = {
				"good_id",good_id_field.getText().equals("")?null:good_id_field.getText(),
				"good_name",good_name_field.getText().equals("")?null:good_name_field.getText()};
		ArrayList<Goods> good_list = SelectGoodBll.select_good(key);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for(int i=0;i<good_list.size();i++){
			Goods goods = good_list.get(i);
			String [] datas = {
					goods.getGood_id(),
					goods.getGood_name(),
					goods.getGood_standard().equals("")?"无":goods.getGood_standard(),
					goods.getGood_cost()+" RMB",
					goods.getGood_expiration_date()+"天"
			};
			defaultTableModel.addRow(datas);
		}
		table.setModel(defaultTableModel);
	}

}
