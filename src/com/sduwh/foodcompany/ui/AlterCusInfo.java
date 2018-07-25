package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.CustomerAlterData;
import com.sduwh.foodcompany.bill.SaleBll;
import com.sduwh.foodcompany.entity.Customer;

public class AlterCusInfo extends JInternalFrame{


	//创建自身对象
	private AlterCusInfo alterCusInfo = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField user_id_field,user_name_field;
	//label
	private JLabel user_id_lable,user_name_lable;
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
	//table_title
	private String [] table_title = {"客户编号","客户姓名","联系方式","客户等级","信誉积分","客户地址"};

	/**
	 * Create the frame.
	 */
	public AlterCusInfo() {
		/*
		 * 初始化界面
		 */
		setBounds(100, 100, 827, 651);
		setTitle("修改客户信息窗口");
		
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
	    getContentPane().add(splitPane);
	    
	    //初始化textfield
	    user_id_field = new JTextField();
	    user_id_field.setColumns(25);
	    user_name_field = new JTextField();
	    user_name_field.setColumns(25);
	    //初始化label
	    user_id_lable = new JLabel("客户编号");
	    user_name_lable = new JLabel("客户名称");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(150, 30));
	    select_btn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String user_id = user_id_field.getText();
	    		String user_name = user_name_field.getText();
	    		CustomerAlterData[] data = SaleBll.searchCustomer(user_id, user_name);
	    		DefaultTableModel model = new DefaultTableModel();
	    		model.setColumnIdentifiers(table_title);
	    		for(int i = 0; i < data.length; ++i)
	    			model.addRow(data[i].getArray());
	    		table.setModel(model);
	    	}
	    });
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(user_id_lable);
	    selectPane.add(user_id_field);
	    selectPane.add(user_name_lable);
	    selectPane.add(user_name_field);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
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
	    //创建弹窗
	    createPopupMenu();
	    //注册鼠标右击table事件
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
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = table.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            m_popupMenu.show(table, e.getX(), e.getY());
		        }
			}
		});
	    
	    this.setVisible(true);

	}

	//创建弹窗
	private void createPopupMenu() {
		//获取屏幕
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
	    int height = (int)screensize.getHeight();
        m_popupMenu = new JPopupMenu();
        /*
         * 弹窗菜单设置
         */
        JMenuItem MenItem_alter = new JMenuItem();
        //添加 修改 弹窗item
        MenItem_alter.setText("修改 ");
        //添加监听器
        MenItem_alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	/*
            	 * 获取选中的cus_user_id
            	 * 暂时注释掉,因为没实际查询时会报错
            	 */
            	//int row = table.getRowCount();
            	//String cus_user_id = table.getModel().getValueAt(row, 0).toString();
            	/*
            	 * 创建修改用户信息dialog
            	 */
            	
            	
            	//这是一个测试字段
            	String cus_user_id = "";
            	//
            	
            	
            	AlterDialog alterDialog = new AlterDialog(cus_user_id);
        	    alterDialog.setLocation(new Point(width*1/4, height*1/5));
        		alterDialog.setAlwaysOnTop(true);
        		alterDialog.show();	
            }
        });
        //添加弹窗item
        m_popupMenu.add(MenItem_alter);
	}

}
