package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
	//textfield
	private JTextField pick_up_id_field;
	//combobox
	private JComboBox<String> pick_up_state_combobox;
	//label
	private JLabel pick_up_id_lable,pick_up_state_label;
	//checkbox
	private JCheckBox only_me_checkbox;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	
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
	    //字符串
	    String [] pick_up_state ={"未提货","已提货","退货销毁"};
	    String [] table_title = {"提货单编号","提货单状态","操作人编号"};
	    //在this中添加splitPane
	    this.add(splitPane);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    //设置分割线位置
	    splitPane.setLastDividerLocation(50);
	    
	    //初始化textfield
	    pick_up_id_field = new JTextField();
	    pick_up_id_field.setColumns(30);;
	    //初始化combobox
	    pick_up_state_combobox = new JComboBox(pick_up_state);
	    //初始化label
	    pick_up_id_lable = new JLabel("提货编号：");
	    pick_up_state_label = new JLabel("提货状态：");
	    //初始化only_me_checkbox
	    only_me_checkbox = new JCheckBox("只看我的");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(pick_up_id_lable);
	    selectPane.add(pick_up_id_field);
	    selectPane.add(pick_up_state_label);
	    selectPane.add(pick_up_state_combobox);
	    selectPane.add(only_me_checkbox);
	    selectPane.add(select_btn);
	    
	    //初始化table
	    Object[][] tableData =
	    	  {
	    	    new Object[]{"李清照" , 29 , "女"},
	    	    new Object[]{"苏格拉底", 56 , "男"},
	    	    new Object[]{"李白", 35 , "男"},
	    	    new Object[]{"弄玉", 18 , "女"},
	    	    new Object[]{"虎头" , 2 , "男"}
	    	  };
	   tableModel = new DefaultTableModel(tableData,table_title);
	   table = new JTable(tableModel);
	   table.setRowHeight(50);
	   
	   //将table放入viewPane
	   viewPane.add(table);
	    this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
