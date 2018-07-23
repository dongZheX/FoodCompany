package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class SelectOrderedFrame extends JInternalFrame implements ActionListener {

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectOrderedFrame frame = new SelectOrderedFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	private SelectOrderedFrame selectOrderedFrame = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField order_id_field,good_id_field,cus_user_id_field,sale_user_id_field;
	//combobox
	private JComboBox<String> order_type_combobox,order_state_combobox;
	//label
	private JLabel order_id_label,good_id_label,cus_user_id_label,sale_user_id_label,order_type_label,order_state_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	//checkbox
	private JCheckBox only_me_checkbox;
	//弹出菜单
	private JPopupMenu m_popupMenu;
	
	 //字符串
//    order_id
//    good_id
//    cus_user_id
//    sale_user_id
//    order_unit_price
//    order_num
//    order_type
//    order_date
//    pick_up_time_start
//    pick_up_time_end
//    order_state
	
    private String [] good_state ={"<-请选择->","未确认","已投入生产","入库","取消"};
    //private String [] order_type = {"<-请选择->","现货(先付)","现货(后付)","预定(先付)","预定()"}
    private String [] table_title = {"订货单号","商品编号","客户编号","销售人员编号","单价","数量","订单类型","订单日期","最早提货日期","最晚提货日期","订单状态"};

	/**
	 * Create the frame.
	 */


	public SelectOrderedFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("查询订货单窗口");
		
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
	    

	    
	    order_id_field = new JTextField();
	    order_id_field.setColumns(25);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(25);
	    cus_user_id_field = new JTextField();
	    cus_user_id_field.setColumns(25);
	    sale_user_id_field = new JTextField();
	    sale_user_id_field.setColumns(25);
	    //初始化combobox
	    order_type_combobox = new JComboBox();
	    order_state_combobox = new JComboBox ();
	    //初始化label
	    order_id_label = new JLabel("订货单号");
	    good_id_label = new JLabel("商品编号");
	    cus_user_id_label = new JLabel("客户编号");
	    sale_user_id_label = new JLabel("销售人员编号");
	    order_type_label = new JLabel("订单类型");
	    order_state_label = new JLabel("订单状态");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(200, 30));
	    select_btn.addActionListener(this);
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(order_id_label);
	    selectPane.add(order_id_field);
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(cus_user_id_label);
	    selectPane.add(cus_user_id_field);
	    selectPane.add(sale_user_id_label);
	    selectPane.add(sale_user_id_field);
	    selectPane.add(order_type_label);
	    selectPane.add(order_type_combobox);
	    selectPane.add(order_state_label);
	    selectPane.add(order_state_combobox);
	    selectPane.add(only_me_checkbox);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel);
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
		
	}

}
