package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
//import java.doge.qisini;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sduwh.foodcompany.comm.MDIDesktopPane;
import com.sduwh.foodcompany.entity.Administrators;

import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

public class TellerFrame  extends JFrame{

	//多窗体面板
	private JFrame frame;
	JTabbedPane tabbedPane;
	Administrators admin;
	JPanel panel;
		
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	/*默认构造函数*/
	public TellerFrame() {
		initialize();
	}
	public TellerFrame(Administrators admin) {
		this.admin = admin;
		
		/* 个人信息 */	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*Frame*/
		frame = new JFrame();
		/*panel*/
		panel = new JPanel();
		/*菜单初始化*/
		JMenuBar menuBar = new JMenuBar();
		/*tabbedPane初始化*/
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		/*Frame*/
		frame.setBounds(200, 50, 1200, 800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		panel.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		/*panel中添加tabbedPane*/
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		//菜单栏
		frame.setJMenuBar(menuBar);
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);
		JMenu aboutMenu = new JMenu("关于");
		menuBar.add(aboutMenu);
		JMenu windowMenu = new JMenu("窗口");
		menuBar.add(windowMenu);
		
		
		/**
		 * TabbedPane
		 * 
		 */
		
		/*个人信息tabbedPane*/
		PersonInfoPanel personInfoPane = new PersonInfoPanel(admin);
		tabbedPane.add("个人信息", personInfoPane);
		
		/*出纳tabbedPane*/
		tellerPane();
		
		/*会计tabbedPane*/
		accountingPane();
		
		/*销售tabbedPane*/
		SalePane();
		
		AdministorsPane();
	}
	
	/*出纳的tabbedPane*/
	private void tellerPane() {
		

		ButtonGroup bg = new ButtonGroup();
		
		JPanel receiptPane = new JPanel();
		tabbedPane.addTab("开具收据", null, receiptPane, null);
		receiptPane.setLayout(new BorderLayout(0, 0));
		
		JPanel searchPane = new JPanel();			//搜索Panel
		searchPane.setLayout(new FlowLayout());		//flowlayout类型
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JTextField searchJTF = new JTextField();	//输入框，输入搜索内容
		searchJTF.setColumns(40);
		JRadioButton IDButton = new JRadioButton("按订单ID搜索");	//radiobutton
		IDButton.setSelected(true);									//默认选中
		JRadioButton nameButton = new JRadioButton("按客户姓名搜索");
		
		bg.add(IDButton);
		bg.add(nameButton);
		searchPane.add(searchJTF);
		searchPane.add(IDButton);
		searchPane.add(nameButton);
		receiptPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		
		//JTable
		String[] title = {"订单号", "客户ID", "客户姓名", "应付金额", "付款类型"," 付款状态"};
		DefaultTableModel   tableModel = new DefaultTableModel(title, 20) {
			 public boolean isCellEditable(int row, int column) {
				 	return false;
				  }
		};
		JTable table;
		table = new JTable(tableModel);
		table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		receiptPane.add(scrollPane, BorderLayout.CENTER);
		
		//收款按钮
		JButton yes = new JButton("确认收款");
		receiptPane.add(yes, BorderLayout.SOUTH);
	}
	
	/* 会计的tabbedPane*/
	private void accountingPane() {
		JPanel accountingPane = new JPanel();
		tabbedPane.addTab("开具账单", accountingPane);
		accountingPane.setLayout(new BorderLayout(0, 0));
		
		JPanel searchPane = new JPanel();			//搜索Panel
		searchPane.setLayout(new FlowLayout());		//flowlayout类型
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JTextField searchJTF = new JTextField();	//输入框，输入搜索内容
		searchJTF.setColumns(40);
		JRadioButton IDButton = new JRadioButton("按客户ID搜索");	//radiobutton
		IDButton.setSelected(true);									//默认选中
		JRadioButton nameButton = new JRadioButton("按客户姓名搜索");
		ButtonGroup bg_sale1 = new ButtonGroup();
		bg_sale1.add(IDButton);
		bg_sale1.add(nameButton);
		searchPane.add(searchJTF);
		searchPane.add(IDButton);
		searchPane.add(nameButton);
		accountingPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		//JTable
		String[] title = {"订单号", "客户ID", "客户姓名", "收款金额", "付款类型"};
		DefaultTableModel tableModel = new DefaultTableModel(title, 20) {
			 public boolean isCellEditable(int row, int column) {
				 	return false;
				  }
		};
		JTable table;
		table = new JTable(tableModel);
		table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		accountingPane.add(scrollPane, BorderLayout.CENTER);
		
		//收款按钮
		JButton bill = new JButton("开具账单");
		bill.addActionListener((ActionListener)EventHandler.create(ActionListener.class, this, "issueBill"));
		accountingPane.add(bill, BorderLayout.SOUTH);
		

	}
	
	/*销售部tabbedPane*/
	private void SalePane()
	{
		//初始化
		JPanel salepanel = new JPanel();
		//panel.add(salepanel);
		JScrollPane selectScrollPane = new JScrollPane();
		MDIDesktopPane MDIDesktop = new MDIDesktopPane();
		
		//salepanel
		salepanel.setToolTipText("");
		salepanel.setLayout(null);

		//scrollpane
		selectScrollPane.setBounds(0, 0, 1100, 600);
		salepanel.add(selectScrollPane);
		
		JToolBar toolBar = new JToolBar();
		selectScrollPane.setColumnHeaderView(toolBar);
		selectScrollPane.setViewportView(MDIDesktop);
		
		
		JButton button_sale = new JButton("\u9500\u552E");
		button_sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDIDesktop.add(new MDISaleFrame());
				JInternalFrame sf = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					if(!(sf2 instanceof MDISaleFrame))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
				
			}
		});
		toolBar.add(button_sale);
		
		JButton button_customer = new JButton("\u5BA2\u6237\u4FE1\u606F");
		button_customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDIDesktop.add(new AlterCusInfo());
				JInternalFrame sf = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					if(!(sf2 instanceof AlterCusInfo))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
			}
				
		});
		toolBar.add(button_customer);
		
		/*tabbedPane中加入销售部Pane*/
		tabbedPane.addTab("\u9500\u552E\u7BA1\u7406", null, salepanel, null);
		
	}
	
	private void AdministorsPane()
	{
		JPanel Manage_panel =  new JPanel();
		tabbedPane.addTab("系统管理", null, Manage_panel, null);

		//panel.add(salepanel);
		JScrollPane selectScrollPane = new JScrollPane();
		MDIDesktopPane MDIDesktop = new MDIDesktopPane();
		
		//salepanel
		Manage_panel.setToolTipText("");
		Manage_panel.setLayout(null);

		//scrollpane
		selectScrollPane.setBounds(0, 0, 1100, 600);
		Manage_panel.add(selectScrollPane);
		
		JToolBar toolBar = new JToolBar();
		selectScrollPane.setColumnHeaderView(toolBar);
		selectScrollPane.setViewportView(MDIDesktop);
		
		
		JButton button_create = new JButton("\u521B\u5EFA\u7BA1\u7406\u5458");
		button_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDIDesktop.add(new MDICreateUser());
				JInternalFrame sf = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					if(!(sf2 instanceof MDICreateUser))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
				
			}
		});
		toolBar.add(button_create);
		
		JButton button_update = new JButton("\u4FEE\u6539\u7BA1\u7406\u5458");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDIDesktop.add(new AlterAdminInfo());
				JInternalFrame sf = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					if(!(sf2 instanceof AlterAdminInfo))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
			}
				
		});
		toolBar.add(button_update);
		
		
	}
	
	/**
	 * 单击bill按钮后触发此方法，开具账单，并且判断是否需要开具提货单
	 */
	public void issueBill() {
		
	}
}
