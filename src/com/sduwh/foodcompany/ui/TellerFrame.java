package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
//import java.doge.qisini;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sduwh.foodcompany.bill.FinanceBll;
import com.sduwh.foodcompany.bill.OrderedTableData;
import com.sduwh.foodcompany.bill.ReceiptTableData;
import com.sduwh.foodcompany.comm.MDIDesktopPane;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Ordered;

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
	
	JTextField searchJTF;/*搜索框*/
	DefaultTableModel   tellerTableModel;/*出纳的表格*/
	DefaultTableModel accountantTableModel;/*会计的表格*/
	JTable tellerTable;	/*出纳的table*/
	JTable accountantTable;
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
		
		
		JButton orderIDButton = new JButton("按订单ID搜索");
		JButton customerNameButton = new JButton("按客户姓名搜索");
		JButton customerIDButton = new JButton("按客户ID搜索");
		orderIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchOrderByOrderId();
			}
		});
		customerIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchOrderByCustomerID();
			}
		});
		customerNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchOrderByCustomerName();
			}
		});
		
		searchPane.add(searchJTF);
		searchPane.add(orderIDButton);
		searchPane.add(customerIDButton);
		searchPane.add(customerNameButton);
		receiptPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		
		//JTable
		/*订单号，客户号，客户姓名，订单类型，订单状态，需付款*/
		String[] title = {"订单号", "客户ID", "客户姓名", "付款类型"," 付款状态", "应付金额"};
		tellerTableModel = new DefaultTableModel(title, 20) {
			 public boolean isCellEditable(int row, int column) {
				 return false;
			 }
		};
		
		tellerTable = new JTable(tellerTableModel);
		tellerTable.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tellerTable);
		receiptPane.add(scrollPane, BorderLayout.CENTER);
		
		//收款按钮
		JButton yes = new JButton("确认收款");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueReceipt();
			}
		});
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
		searchJTF = new JTextField();	//输入框，输入搜索内容
		searchJTF.setColumns(40);
		JButton receiptIDButton = new JButton("按收据ID搜索");
		JButton orderIDButton = new JButton("按订单ID搜索");
		JButton customerIDButton = new JButton("按客户ID搜索");
		JButton customerNameButton = new JButton("按客户姓名搜索");
		receiptIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchReceiptByReceiptId();
			}
		});
		orderIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchReceiptByOrderId();
			}
		});
		customerIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchReceiptByCumstomerId();
			}
		});
		customerNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchReceiptByCustomerName();
			}
		});
		
		searchPane.add(searchJTF);
		searchPane.add(receiptIDButton);
		searchPane.add(orderIDButton);
		searchPane.add(customerNameButton);
		accountingPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		//JTable
		//收据id，订单id,客户id，客户姓名，收款金额
		String[] title = {"收据ID", "订单ID", "客户ID", "客户姓名", "收款金额"};
		accountantTableModel = new DefaultTableModel(title, 20) {
			/*表格不可更改*/
			 public boolean isCellEditable(int row, int column) {
				 	return false;
				  }
		};
		
		accountantTable = new JTable(accountantTableModel);
		accountantTable.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(accountantTable);
		accountingPane.add(scrollPane, BorderLayout.CENTER);
		
		//收款按钮
		JButton bill = new JButton("开具账单");
		bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueBill();
			}
		});
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
		
		JButton button_cancel = new JButton("\u9000\u8D27");
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDIDesktop.add(new MDIcancelOrdered());
				JInternalFrame sf = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//将当前窗口最大化
					if(!(sf2 instanceof MDIcancelOrdered))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
			}
				
		});
		toolBar.add(button_cancel);
		
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

	/************************************************************有关响应的方法******************************************************************/
	/**
	 * 单击bill按钮后触发此方法，开具账单，并且判断是否需要开具提货单
	 */
	private void issueBill() {
		//收据id，订单id,客户id，客户姓名，收款金额
		int row = this.accountantTable.getSelectedRow();
		String receiptID = (String)this.accountantTable.getValueAt(row, 0);
		String orderID = (String)this.accountantTable.getValueAt(row, 1);
		String customerID = (String)this.accountantTable.getValueAt(row, 2)
		String customerName = (String)this.accountantTable.getValueAt(row, 3);
		float money = Float.parseFloat((String)this.accountantTable.getValueAt(row, 4));
		ReceiptTableData receipt = new ReceiptTableData(receiptID, orderID, customerID, customerName, money);
		FinanceBll.createBill(receiptTableData, this.admin.getUser_id());
	}
	
	/*单击yes按钮后触发此方法，开具收据*/
	private void issueReceipt() {
		/*"订单号", "客户ID", "客户姓名", "付款类型"," 付款状态", "应付金额"*/
		int row = this.tellerTable.getSelectedRow();
		String orderedId = (String) this.tellerTable.getValueAt(row, 0);
		String customerId = (String) this.tellerTable.getValueAt(row, 1);
		String customerName = (String)this.tellerTable.getValueAt(row, 2);
		int type = Ordered.order_type_toInt((String)this.tellerTable.getValueAt(row, 3));
		int state = Ordered.order_state_toInt((String)this.tellerTable.getValueAt(row, 4));
		float sum = Float.parseFloat((String)this.tellerTable.getValueAt(row, 5));
		OrderedTableData data = new OrderedTableData(orderedId, customerId, customerName, type, state, sum);
		FinanceBll.createReceipt(data, this.admin.getUser_id());
		JOptionPane.showMessageDialog(this, "生成收据成功", "Okay", JOptionPane.DEFAULT_OPTION);
	}
	
	/*单击“按订单ID查找”后触发此方法*/
	private void searchOrderByOrderId() {
		OrderedTableData data = FinanceBll.searchOrderByOrderId(this.searchJTF.getText());
		if(data == null) {
			JOptionPane.showMessageDialog(this, "您要查找的订单不存在", "错误", JOptionPane.ERROR_MESSAGE);
		}
		else
			this.tellerTableModel.addRow(data.toArray());
	}
	
	/*单击“按客户ID查找后触发此方法”*/
	private void searchOrderByCustomerID() {
		OrderedTableData[] data = FinanceBll.searchOrderByCustomerId(this.searchJTF.getText());
		if(data == null) {
			JOptionPane.showMessageDialog(this, "您要查找的客户不存在", "错误", JOptionPane.ERROR_MESSAGE);
		}else
			for(int i = 0; i < data.length; ++i)
				this.tellerTableModel.addRow(data[i].toArray());
	}
	
	/*单击“按客户姓名查找后触发此方法”*/
	private void searchOrderByCustomerName() {
		OrderedTableData[] data = FinanceBll.searchOrderByCustomerName(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "您要查找的客户不存在", "错误", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.tellerTableModel.addRow(data[i].toArray());
	}
	
	/*根据收据ID查找收据*/
	private void searchReceiptByReceiptId() {
		ReceiptTableData data =  FinanceBll.searchReceiptByReceiptId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "您要查找的收据不存在", "错误", JOptionPane.ERROR_MESSAGE);
		else
			this.accountantTableModel.addRow(data.toArray());
	}
	
	/*根据订单ID查找收据*/
	private void searchReceiptByOrderId() {
		ReceiptTableData data = FinanceBll.searchReceiptByOrderId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "您查找的订单不存在", "错误", JOptionPane.ERROR_MESSAGE);
		else
			this.accountantTableModel.addRow(data.toArray());
	}
	
	/*根据用户ID查找收据*/
	private void searchReceiptByCumstomerId() {
		ReceiptTableData[] data =  FinanceBll.searchReceiptByCustomerId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "您要查找的客户不存在", "错误", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.accountantTableModel.addRow(data[i].toArray());
	}
	
	/*根据用户姓名查找收据*/
	private void searchReceiptByCustomerName() {
		ReceiptTableData[] data =  FinanceBll.searchReceiptByCustomerName(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "您要查找的客户不存在", "错误", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.accountantTableModel.addRow(data[i].toArray());
	}
}
