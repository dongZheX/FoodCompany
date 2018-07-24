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

	//�ര�����
	private JFrame frame;
	JTabbedPane tabbedPane;
	Administrators admin;
	JPanel panel;
	
	JTextField searchJTF;/*������*/
	DefaultTableModel   tellerTableModel;/*���ɵı��*/
	DefaultTableModel accountantTableModel;/*��Ƶı��*/
	JTable tellerTable;	/*���ɵ�table*/
	JTable accountantTable;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	/*Ĭ�Ϲ��캯��*/
	public TellerFrame() {
		initialize();
	}
	public TellerFrame(Administrators admin) {
		this.admin = admin;
		
		/* ������Ϣ */	
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
		/*�˵���ʼ��*/
		JMenuBar menuBar = new JMenuBar();
		/*tabbedPane��ʼ��*/
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		/*Frame*/
		frame.setBounds(200, 50, 1200, 800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		panel.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		/*panel�����tabbedPane*/
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		//�˵���
		frame.setJMenuBar(menuBar);
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);
		JMenu aboutMenu = new JMenu("����");
		menuBar.add(aboutMenu);
		JMenu windowMenu = new JMenu("����");
		menuBar.add(windowMenu);
		
		
		/**
		 * TabbedPane
		 * 
		 */
		
		/*������ϢtabbedPane*/
		PersonInfoPanel personInfoPane = new PersonInfoPanel(admin);
		tabbedPane.add("������Ϣ", personInfoPane);
		
		/*����tabbedPane*/
		tellerPane();
		
		/*���tabbedPane*/
		accountingPane();
		
		/*����tabbedPane*/
		SalePane();
		
		AdministorsPane();
	}
	
	/*���ɵ�tabbedPane*/
	private void tellerPane() {
		

		ButtonGroup bg = new ButtonGroup();
		
		JPanel receiptPane = new JPanel();
		tabbedPane.addTab("�����վ�", null, receiptPane, null);
		receiptPane.setLayout(new BorderLayout(0, 0));
		
		JPanel searchPane = new JPanel();			//����Panel
		searchPane.setLayout(new FlowLayout());		//flowlayout����
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JTextField searchJTF = new JTextField();	//�����������������
		searchJTF.setColumns(40);
		
		
		JButton orderIDButton = new JButton("������ID����");
		JButton customerNameButton = new JButton("���ͻ���������");
		JButton customerIDButton = new JButton("���ͻ�ID����");
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
		/*�����ţ��ͻ��ţ��ͻ��������������ͣ�����״̬���踶��*/
		String[] title = {"������", "�ͻ�ID", "�ͻ�����", "��������"," ����״̬", "Ӧ�����"};
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
		
		//�տť
		JButton yes = new JButton("ȷ���տ�");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueReceipt();
			}
		});
		receiptPane.add(yes, BorderLayout.SOUTH);
	}
	
	/* ��Ƶ�tabbedPane*/
	private void accountingPane() {
		JPanel accountingPane = new JPanel();
		tabbedPane.addTab("�����˵�", accountingPane);
		accountingPane.setLayout(new BorderLayout(0, 0));
		
		JPanel searchPane = new JPanel();			//����Panel
		searchPane.setLayout(new FlowLayout());		//flowlayout����
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchJTF = new JTextField();	//�����������������
		searchJTF.setColumns(40);
		JButton receiptIDButton = new JButton("���վ�ID����");
		JButton orderIDButton = new JButton("������ID����");
		JButton customerIDButton = new JButton("���ͻ�ID����");
		JButton customerNameButton = new JButton("���ͻ���������");
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
		//�վ�id������id,�ͻ�id���ͻ��������տ���
		String[] title = {"�վ�ID", "����ID", "�ͻ�ID", "�ͻ�����", "�տ���"};
		accountantTableModel = new DefaultTableModel(title, 20) {
			/*��񲻿ɸ���*/
			 public boolean isCellEditable(int row, int column) {
				 	return false;
				  }
		};
		
		accountantTable = new JTable(accountantTableModel);
		accountantTable.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(accountantTable);
		accountingPane.add(scrollPane, BorderLayout.CENTER);
		
		//�տť
		JButton bill = new JButton("�����˵�");
		bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueBill();
			}
		});
		accountingPane.add(bill, BorderLayout.SOUTH);
		

	}
	
	/*���۲�tabbedPane*/
	private void SalePane()
	{
		//��ʼ��
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
					//����ǰ�������
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//����ǰ�������
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
					//����ǰ�������
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//����ǰ�������
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
					//����ǰ�������
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//����ǰ�������
					if(!(sf2 instanceof MDIcancelOrdered))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
			}
				
		});
		toolBar.add(button_cancel);
		
		/*tabbedPane�м������۲�Pane*/
		tabbedPane.addTab("\u9500\u552E\u7BA1\u7406", null, salepanel, null);
		
	}
	
	private void AdministorsPane()
	{
		JPanel Manage_panel =  new JPanel();
		tabbedPane.addTab("ϵͳ����", null, Manage_panel, null);

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
					//����ǰ�������
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//����ǰ�������
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
					//����ǰ�������
					sf.setMaximum(true);			
				}
				catch(Exception e1){		}
				JInternalFrame sf2 = MDIDesktop.getSelectedFrame();
				try {
					//����ǰ�������
					if(!(sf2 instanceof AlterAdminInfo))
						sf2.setMaximum(true);			
				}
				catch(Exception e2){		}
			}
				
		});
		toolBar.add(button_update);
	}

	/************************************************************�й���Ӧ�ķ���******************************************************************/
	/**
	 * ����bill��ť�󴥷��˷����������˵��������ж��Ƿ���Ҫ���������
	 */
	private void issueBill() {
		//�վ�id������id,�ͻ�id���ͻ��������տ���
		int row = this.accountantTable.getSelectedRow();
		String receiptID = (String)this.accountantTable.getValueAt(row, 0);
		String orderID = (String)this.accountantTable.getValueAt(row, 1);
		String customerID = (String)this.accountantTable.getValueAt(row, 2)
		String customerName = (String)this.accountantTable.getValueAt(row, 3);
		float money = Float.parseFloat((String)this.accountantTable.getValueAt(row, 4));
		ReceiptTableData receipt = new ReceiptTableData(receiptID, orderID, customerID, customerName, money);
		FinanceBll.createBill(receiptTableData, this.admin.getUser_id());
	}
	
	/*����yes��ť�󴥷��˷����������վ�*/
	private void issueReceipt() {
		/*"������", "�ͻ�ID", "�ͻ�����", "��������"," ����״̬", "Ӧ�����"*/
		int row = this.tellerTable.getSelectedRow();
		String orderedId = (String) this.tellerTable.getValueAt(row, 0);
		String customerId = (String) this.tellerTable.getValueAt(row, 1);
		String customerName = (String)this.tellerTable.getValueAt(row, 2);
		int type = Ordered.order_type_toInt((String)this.tellerTable.getValueAt(row, 3));
		int state = Ordered.order_state_toInt((String)this.tellerTable.getValueAt(row, 4));
		float sum = Float.parseFloat((String)this.tellerTable.getValueAt(row, 5));
		OrderedTableData data = new OrderedTableData(orderedId, customerId, customerName, type, state, sum);
		FinanceBll.createReceipt(data, this.admin.getUser_id());
		JOptionPane.showMessageDialog(this, "�����վݳɹ�", "Okay", JOptionPane.DEFAULT_OPTION);
	}
	
	/*������������ID���ҡ��󴥷��˷���*/
	private void searchOrderByOrderId() {
		OrderedTableData data = FinanceBll.searchOrderByOrderId(this.searchJTF.getText());
		if(data == null) {
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵĶ���������", "����", JOptionPane.ERROR_MESSAGE);
		}
		else
			this.tellerTableModel.addRow(data.toArray());
	}
	
	/*���������ͻ�ID���Һ󴥷��˷�����*/
	private void searchOrderByCustomerID() {
		OrderedTableData[] data = FinanceBll.searchOrderByCustomerId(this.searchJTF.getText());
		if(data == null) {
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵĿͻ�������", "����", JOptionPane.ERROR_MESSAGE);
		}else
			for(int i = 0; i < data.length; ++i)
				this.tellerTableModel.addRow(data[i].toArray());
	}
	
	/*���������ͻ��������Һ󴥷��˷�����*/
	private void searchOrderByCustomerName() {
		OrderedTableData[] data = FinanceBll.searchOrderByCustomerName(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵĿͻ�������", "����", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.tellerTableModel.addRow(data[i].toArray());
	}
	
	/*�����վ�ID�����վ�*/
	private void searchReceiptByReceiptId() {
		ReceiptTableData data =  FinanceBll.searchReceiptByReceiptId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵ��վݲ�����", "����", JOptionPane.ERROR_MESSAGE);
		else
			this.accountantTableModel.addRow(data.toArray());
	}
	
	/*���ݶ���ID�����վ�*/
	private void searchReceiptByOrderId() {
		ReceiptTableData data = FinanceBll.searchReceiptByOrderId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "�����ҵĶ���������", "����", JOptionPane.ERROR_MESSAGE);
		else
			this.accountantTableModel.addRow(data.toArray());
	}
	
	/*�����û�ID�����վ�*/
	private void searchReceiptByCumstomerId() {
		ReceiptTableData[] data =  FinanceBll.searchReceiptByCustomerId(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵĿͻ�������", "����", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.accountantTableModel.addRow(data[i].toArray());
	}
	
	/*�����û����������վ�*/
	private void searchReceiptByCustomerName() {
		ReceiptTableData[] data =  FinanceBll.searchReceiptByCustomerName(this.searchJTF.getText());
		if(data == null)
			JOptionPane.showMessageDialog(this, "��Ҫ���ҵĿͻ�������", "����", JOptionPane.ERROR_MESSAGE);
		else
			for(int i = 0; i < data.length; ++i)
				this.accountantTableModel.addRow(data[i].toArray());
	}
}
