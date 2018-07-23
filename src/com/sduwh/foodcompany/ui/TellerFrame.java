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

	//�ര�����
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
		JRadioButton IDButton = new JRadioButton("������ID����");	//radiobutton
		IDButton.setSelected(true);									//Ĭ��ѡ��
		JRadioButton nameButton = new JRadioButton("���ͻ���������");
		
		bg.add(IDButton);
		bg.add(nameButton);
		searchPane.add(searchJTF);
		searchPane.add(IDButton);
		searchPane.add(nameButton);
		receiptPane.add(searchPane, BorderLayout.NORTH);
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new GridLayout());
		
		
		//JTable
		String[] title = {"������", "�ͻ�ID", "�ͻ�����", "Ӧ�����", "��������"," ����״̬"};
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
		
		//�տť
		JButton yes = new JButton("ȷ���տ�");
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
		JTextField searchJTF = new JTextField();	//�����������������
		searchJTF.setColumns(40);
		JRadioButton IDButton = new JRadioButton("���ͻ�ID����");	//radiobutton
		IDButton.setSelected(true);									//Ĭ��ѡ��
		JRadioButton nameButton = new JRadioButton("���ͻ���������");
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
		String[] title = {"������", "�ͻ�ID", "�ͻ�����", "�տ���", "��������"};
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
		
		//�տť
		JButton bill = new JButton("�����˵�");
		bill.addActionListener((ActionListener)EventHandler.create(ActionListener.class, this, "issueBill"));
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
	
	/**
	 * ����bill��ť�󴥷��˷����������˵��������ж��Ƿ���Ҫ���������
	 */
	public void issueBill() {
		
	}
}
