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
	        //���ñ����Խ��ı䴰�ڱ߿���ʽ����
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
		 * ������Ϣ
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
		tabbedPane.add("������Ϣ", personInfoPane);
		
		
		//�˵���
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);
		JMenu aboutMenu = new JMenu("����");
		menuBar.add(aboutMenu);
		JMenu windowMenu = new JMenu("����");
		menuBar.add(windowMenu);
		
		tellerPane();
		accountingPane();
	}
	
	/**
	 * ���ɵ�pane
	 */
	private void tellerPane() {
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u9500\u552E\u7BA1\u7406", null, panel, null);
		panel.setLayout(null);
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
		String[] title = {"������", "�ͻ�ID", "�ͻ�����", "Ӧ�����", "��������"};
		DefaultTableModel tableModel = new DefaultTableModel(title, 20);
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
	
	/**
	 * ��Ƶ�pane
	 */
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
		String[] title = {"������", "�ͻ�ID", "�ͻ�����", "�տ���", "��������"};
		DefaultTableModel tableModel = new DefaultTableModel(title, 20);
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
	
	/**
	 * ����bill��ť�󴥷��˷����������˵��������ж��Ƿ���Ҫ���������
	 */
	public void issueBill() {
		
	}

}
