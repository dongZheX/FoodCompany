package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.List;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class MDISaleFrame extends JInternalFrame implements ActionListener{

	private JFrame frame;
	private  JPanel panel;
	private  JTextField id_name ;
	private JTable table;
	private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDISaleFrame window = new MDISaleFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MDISaleFrame() {
		initialize();
		setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u59D3\u540D\u6216ID\uFF1A");
		lblNewLabel.setBounds(34, 9, 119, 15);
		panel.add(lblNewLabel);
		id_name = new JTextField();
		id_name.setBounds(163, 6, 136, 21);
		panel.add(id_name);
		id_name.setColumns(0);
		
		/*ButtonGroup*/
		ButtonGroup bg  = new ButtonGroup(); 
		ButtonGroup bg_sale2 = new ButtonGroup();
		
		
		/*搜索方式单选按钮*/
		
		/*付款类型单选按钮*/
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u6309\u59D3\u540D\u641C\u7D22");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(323, 33, 124, 23);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u6309ID\u641C\u7D22");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(473, 33, 124, 23);
		panel.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		JRadioButton radioButton = new JRadioButton("\u5168\u6B3E");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(323, 5, 87, 23);
		radioButton.setSelected(true);
		panel.add(radioButton);
		bg_sale2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u9884\u5B9A");
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setBounds(473, 5, 73, 23);
		panel.add(radioButton_1);
		bg_sale2.add(radioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\u8D27\u540E\u4ED8\u6B3E");
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		rdbtnNewRadioButton_2.setBounds(622, 5, 111, 23);
		panel.add(rdbtnNewRadioButton_2);
		bg_sale2.add(rdbtnNewRadioButton_2);
		
		JButton button = new JButton("\u663E\u793A\u5546\u54C1");
		button.setBounds(30, 33, 274, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = id_name.getText();
				if(s.equals("")||s.equals(null))
					JOptionPane.showMessageDialog(panel, "请填写客户信息","提示",JOptionPane.WARNING_MESSAGE);
				else
				{
					
				}
			}
		});
		panel.add(button);
		
	
		JLabel label = new JLabel("\u8BA2\u5355\u603B\u91D1\u989D\uFF1A");
		label.setBounds(34, 383, 98, 15);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(114, 383, 103, 15);
		panel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u5143");
		label_1.setBounds(227, 383, 47, 15);
		panel.add(label_1);
		
		JButton button_1 = new JButton("\u751F\u6210\u63D0\u8D27\u5355");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(302, 421, 93, 23);
		panel.add(button_1);
	
		
		String[] year = {"2018", "2019", "2020", "2021", "2022"};
		String[] month = {"一月", "二月", "三月", "四月", "五月","六月", "七月", "八月", "九月", "十月","十一月","十二月"};
		String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
				"26","27","28","29","30","31"};
		
		JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.setBounds(258, 380, 71, 21);
		for(int i = 0 ;i < year.length;i++)
			comboBox.addItem(year[i]);
		panel.add(comboBox);
		
		JComboBox <String>comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(363, 380, 66, 21);
		for(int i = 0 ;i < month.length;i++)
			comboBox_1.addItem(month[i]);
		panel.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(473, 380, 73, 21);
		for(int i = 0 ;i < day.length;i++)
			comboBox_2.addItem(day[i]);
		panel.add(comboBox_2);
		
		JLabel label_2 = new JLabel("\u5E74");
		label_2.setBounds(339, 383, 24, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u65E5");
		label_3.setBounds(556, 383, 24, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u6708");
		label_4.setBounds(439, 383, 24, 15);
		panel.add(label_4);
		
		JLabel lblNewLabel_2 = new JLabel("\u6700\u65E9\u63D0\u8D27");
		lblNewLabel_2.setBounds(593, 383, 54, 15);
		panel.add(lblNewLabel_2);
		
		
		
		
		
		 //初始化table
		String[] table_title = {"数量", "商品编号", "商品名称", "单价"};

	    tableModel = new DefaultTableModel(table_title, 20) {
			 public boolean isCellEditable(int row, int column) {
				 	if(column != 0) 
				 		return false;
				 	return true;
				  }
		};
	    table = new JTable(tableModel);
	    table.setRowHeight(35);	  
	    table.setEditingColumn(0);
	    
	    
	    
	    //初始化scrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 74, 695, 277);
	    //将table放入scrollPane
	   
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,400));
	    scrollPane.setViewportView(table);
		panel.add(scrollPane);
	 
	   
		this.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		id_name = new JTextField();
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
