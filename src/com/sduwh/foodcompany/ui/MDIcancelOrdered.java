package com.sduwh.foodcompany.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.IdToName;
import com.sduwh.foodcompany.bill.SaleBll;
import com.sduwh.foodcompany.bill.SelectOrderedBll;
import com.sduwh.foodcompany.bill.SystemBll;
import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.entity.Ordered;

public class MDIcancelOrdered extends JInternalFrame implements ActionListener{



	
	private MDIcancelOrdered selectOrderedFrame = this;
	
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
	//弹出菜单
	private JPopupMenu m_popupMenu;
	
	 //字符串
    private String [] order_state ={"<-请选择->","未确认","已投入生产","入库","取消"};
    private String [] order_type = {"<-请选择->","现货(先付)","现货(后付)","预定(先付)","预定()"};
    private String [] table_title = {"订货单号","商品编号","客户编号","销售人员编号","单价","数量","订单类型","订单日期","最早提货日期","最晚提货日期","订单状态"};
    private JLabel label;
    private JTextField textField;

	/**
	 * Create the frame.
	 */


	public MDIcancelOrdered() {
		setBounds(100, 100, 1139, 449);
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
	    getContentPane().add(splitPane);
	    
	    
	    //初始化textfield 
	    order_id_field = new JTextField();
	    order_id_field.setColumns(20);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(15);
	    cus_user_id_field = new JTextField();
	    cus_user_id_field.setColumns(20);
	    sale_user_id_field = new JTextField();
	    sale_user_id_field.setColumns(20);
	    //初始化combobox
	    order_type_combobox = new JComboBox(order_type);
	    order_state_combobox = new JComboBox (order_state);
	    //初始化label
	    order_id_label = new JLabel("订货单号");
	    good_id_label = new JLabel("商品名");
	    cus_user_id_label = new JLabel("客户编号");
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
	    
	    label = new JLabel("\u5BA2\u6237\u59D3\u540D");
	    selectPane.add(label);
	    
	    textField = new JTextField();
	    selectPane.add(textField);
	    textField.setColumns(15);
	    selectPane.add(cus_user_id_label);
	    selectPane.add(cus_user_id_field);
	    sale_user_id_label = new JLabel("销售人员名");
	    selectPane.add(sale_user_id_label);
	    selectPane.add(sale_user_id_field);
	    order_type_label = new JLabel("订单类型");
	    selectPane.add(order_type_label);
	    selectPane.add(order_type_combobox);
	    selectPane.add(order_state_label);
	    selectPane.add(order_state_combobox);
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
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane = new JScrollPane(table);
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(1100,350));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("查询")){
			btn_select_action();
		}
	}
	
	public void btn_select_action(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String order_id = order_id_field.getText();
		String good_id = WarehouseService.findIdByGoodName(good_id_field.getText());
		System.out.println("good_id+"+good_id);
		String cus_user_id = cus_user_id_field.getText();
		String sale_user_id = WarehouseService.findIdByAdminName(sale_user_id_field.getText());
		String order_type_str = order_type_combobox.getSelectedItem().toString();
		String order_state_str = order_state_combobox.getSelectedItem().toString();
		int order_type_int = Ordered.order_type_toInt(order_type_str);
		int order_state_int = Ordered.order_state_toInt(order_state_str);
		String [] key = {
				"order_id",order_id.equals("")?null:order_id,
				"good_id",good_id.equals("")?null:good_id,
				"cus_user_id",cus_user_id.equals("")?null:cus_user_id,
				"sale_user_id",sale_user_id.equals("")?null:sale_user_id,
				"order_type",order_type_int == -1?null:order_type_int+"",
				"order_state",order_state_int == -1?null:order_state_int+""
				};
		ArrayList<Ordered> order_list = SelectOrderedBll.select_ordered(key);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for (int i = 0; i < order_list.size(); i++){
			Ordered ordered = order_list.get(i);
			String []datas = {
					ordered.getOrder_id(),
					IdToName.Goods_select(ordered.getGood_id()),
					ordered.getCus_user_id(),
					ordered.getSale_user_id(),
					ordered.getOrder_unit_price()+"",
					ordered.getOrder_num()+"",
					simpleDateFormat.format(ordered.getOrder_date()),
					simpleDateFormat.format(ordered.getPick_up_time_start()),
					simpleDateFormat.format(ordered.getPick_up_time_end()),
					Ordered.order_type_toStr(ordered.getOrder_type()),
					Ordered.order_state_toStr(ordered.getOrder_state())
			};
			defaultTableModel.addRow(datas);
		}
		table.setModel(defaultTableModel);
		
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
	        JMenuItem MenItem_alter1 = new JMenuItem();
	        JMenuItem MenItem_alter2 = new JMenuItem();
	        //添加 修改 弹窗item
	        MenItem_alter1.setText("退货 ");
	        MenItem_alter2.setText("退订该订单所有商品 ");
	        //添加监听器
	        MenItem_alter1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //该操作需要做的事
	            	/*
	            	 * 获取选中的order_id
	            	 * 暂时注释掉,因为没实际查询时会报错
	            	 */
	            	int row = table.getRowCount();
	            	String order_id = table.getModel().getValueAt(row, 0).toString();
	            	String good_name = "";
	            	String good_id = WarehouseService.findIdByGoodName(good_name);
	            	
	            	/*
	            	 * 创建修改用户信息dialog
	            	 */
	            	SaleBll.cancelOrder(order_id, good_id);
	            	
	            	//这是一个测试字段
	            	
	            	
	            	
	            	AlterDialog alterDialog = new AlterDialog(order_id);
	        	    alterDialog.setLocation(new Point(width*1/4, height*1/5));
	        		alterDialog.setAlwaysOnTop(true);
	        		alterDialog.show();	
	            }
	        });
	        
	        MenItem_alter2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	int row = table.getRowCount();
	            	String order_id = table.getModel().getValueAt(row, 0).toString();
	            	
	            	/*
	            	 * 创建修改用户信息dialog
	            	 */
	            	SaleBll.cancelOrder(order_id, null);
	            }
	          });
	        //添加弹窗item
	        m_popupMenu.add(MenItem_alter1);
	        m_popupMenu.add(MenItem_alter2);
		}
}
