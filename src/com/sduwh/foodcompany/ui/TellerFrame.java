package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
//import java.doge.qisini;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sduwh.foodcompany.entity.Administrators;

import javax.swing.JMenu;
import java.awt.BorderLayout;
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

public class TellerFrame  extends JFrame{

	private JFrame frame;
	JTabbedPane tabbedPane;
	Administrators admin;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try
	    {
	        //设置本属性将改变窗口边框样式定义
	        //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TellerFrame window = new TellerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public TellerFrame() {
		initialize();
	}
	public TellerFrame(Administrators admin) {
		this.admin = admin;
		
		/**
		 * 个人信息
		 */
			
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 932, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		PersonInfoPanel personInfoPane = new PersonInfoPanel(admin);
		tabbedPane.add("个人信息", personInfoPane);
		
		
		//菜单栏
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);
		JMenu aboutMenu = new JMenu("关于");
		menuBar.add(aboutMenu);
		JMenu windowMenu = new JMenu("窗口");
		menuBar.add(windowMenu);
		
		tellerPane();
		accountingPane();
	}
	
	/**
	 * 出纳的pane
	 */
	private void tellerPane() {
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u9500\u552E\u7BA1\u7406", null, panel, null);
		panel.setLayout(null);
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
		ButtonGroup bg = new ButtonGroup();
		bg.add(IDButton);
		bg.add(nameButton);
		searchPane.add(searchJTF);
		searchPane.add(IDButton);
		searchPane.add(nameButton);
		receiptPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		
		//JTable
		String[] title = {"订单号", "客户ID", "客户姓名", "应付金额", "付款类型"};
		DefaultTableModel tableModel = new DefaultTableModel(title, 20);
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
	
	/**
	 * 会计的pane
	 */
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
		ButtonGroup bg = new ButtonGroup();
		bg.add(IDButton);
		bg.add(nameButton);
		searchPane.add(searchJTF);
		searchPane.add(IDButton);
		searchPane.add(nameButton);
		accountingPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		//JTable
		String[] title = {"订单号", "客户ID", "客户姓名", "收款金额", "付款类型"};
		DefaultTableModel tableModel = new DefaultTableModel(title, 20);
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
	
	/**
	 * 单击bill按钮后触发此方法，开具账单，并且判断是否需要开具提货单
	 */
	public void issueBill() {
		
	}

}
