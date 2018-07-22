package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.sduwh.foodcompany.entity.Administrators;

public class SelectPickUpFrame extends JInternalFrame implements ActionListener{


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
	//scrollPane
	private JScrollPane scrollPane;
	//弹出
	JPopupMenu m_popupMenu;
	//Adm
	private Administrators administrators ;
	private SelectPickUpFrame pickthis = this;
	/**
	 * Create the frame.
	 */
	public SelectPickUpFrame(Administrators user) {
		setBounds(100, 100, 450, 300);
		setTitle("查询提货单信息窗口");
		administrators = user;
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
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel);
	    table.setRowHeight(50);	  
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane = new JScrollPane(table);
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,400));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane);
	    //弹出框实现
	    createPopupMenu();
	    table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
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
	
	}
	//创建弹出按钮
			private void createPopupMenu() {
		        m_popupMenu = new JPopupMenu();
		        
		        	JMenuItem planMenItem_look = new JMenuItem();
		        	planMenItem_look.setText("查看详情");
		        	planMenItem_look.addActionListener(new java.awt.event.ActionListener() {
		        		public void actionPerformed(java.awt.event.ActionEvent evt) {
		        			//该操作需要做的事
		        			LookOrderByPickUpDialog lookOrderByPickUpDialog = new LookOrderByPickUpDialog();
		        			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		        			int width = (int)screensize.getWidth();
		        			int height = (int)screensize.getHeight();
		        			lookOrderByPickUpDialog.setLocation(new Point(width*1/4, height*1/3));
		        			lookOrderByPickUpDialog.setAlwaysOnTop(true);

		        		}
		        	});
		        
		        
		        	m_popupMenu.add(planMenItem_look);
		        	
		        
		      
			}

}
