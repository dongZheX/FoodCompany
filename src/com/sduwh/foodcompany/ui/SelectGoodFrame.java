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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
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

import com.sduwh.foodcompany.bill.SelectGoodBll;
import com.sduwh.foodcompany.entity.Goods;

public class SelectGoodFrame extends JInternalFrame implements ActionListener {


	private SelectGoodFrame selectGoodFrame = this;
	
	
	//scrollPane
	private JScrollPane scrollPane_main;
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
	    splitPane.setPreferredSize(new Dimension(900, 350));
	    
	    //初始化scrollPane
	    scrollPane = new JScrollPane(splitPane);
	    //在this中添加scrollPane
	    this.add(scrollPane);
	    
	    
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
	    scrollPane.setPreferredSize(new Dimension(890,350));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane);
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
		
		//创建右击菜单
		createPopupMenu();
	}
	
	//创建弹出按钮
	private void createPopupMenu() {
		m_popupMenu = new JPopupMenu();
		        
		JMenuItem goodMenItem_alter = new JMenuItem();
		goodMenItem_alter.setText("修改");
		goodMenItem_alter.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent evt) {
		        			//该操作需要做的事
		        	/*
		        	 * 获取右键位置
		        	 */
		        	int y = table.getSelectedRow();
		        	Object o = table.getModel().getValueAt(y, 0);
		        	String good_id = o==null?null:o.toString();
		        	/*
		        	 * 初始化AlterGoodDialog
		        	 */
		        	AlterGoodsDialog alterGoodsDialog = new AlterGoodsDialog(good_id);
		        	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		        	int width = (int)screensize.getWidth();
		        	int height = (int)screensize.getHeight();
		        	alterGoodsDialog.setLocation(new Point(width*1/4, height*1/3));
		        	alterGoodsDialog.setAlwaysOnTop(true);
		        	alterGoodsDialog.show();
		        	
		        	/*
		        	 * 再执行一次查询
		        	 */
		        	select_btn_action();

		        }
		});
		m_popupMenu.add(goodMenItem_alter);
	}

}
