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
	private JTextField id_name_textField;
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
		lblNewLabel.setBounds(34, 9, 111, 15);
		panel.add(lblNewLabel);
		id_name_textField = new JTextField();
		id_name_textField.setBounds(132, 6, 136, 21);
		panel.add(id_name_textField);
		id_name_textField.setColumns(0);
		
		/*ButtonGroup*/
		ButtonGroup bg  = new ButtonGroup(); 
		ButtonGroup bg_sale2 = new ButtonGroup();
		
		
		/*搜索方式单选按钮*/
		
		/*付款类型单选按钮*/
		JRadioButton button_name = new JRadioButton("\u6309\u59D3\u540D\u641C\u7D22");
		button_name.setBackground(Color.WHITE);
		button_name.setBounds(290, 5, 111, 23);
		button_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(button_name);
		bg.add(button_name);
		button_name.setSelected(true);
		
		JRadioButton rbutton_id = new JRadioButton("\u6309ID\u641C\u7D22");
		rbutton_id.setBackground(Color.WHITE);
		rbutton_id.setBounds(403, 5, 103, 23);
		panel.add(rbutton_id);
		bg.add(rbutton_id);
		
		//JButton现货
		JRadioButton rbutton_1 = new JRadioButton("\u73B0\u8D27");
		rbutton_1.setBackground(Color.WHITE);
		rbutton_1.setBounds(542, 5, 71, 23);
		rbutton_1.setSelected(true);
		panel.add(rbutton_1);
		bg_sale2.add(rbutton_1);
		
		//JButton现货后付
		JRadioButton rbutton_2 = new JRadioButton("\u73B0\u8D27\uFF08\u540E\u4ED8\uFF09");
		rbutton_2.setBackground(Color.WHITE);
		rbutton_2.setBounds(615, 5, 97, 23);
		panel.add(rbutton_2);
		bg_sale2.add(rbutton_2);
		
		//JButton预定
		JRadioButton rbutton_3 = new JRadioButton("\u9884\u5B9A");
		rbutton_3.setBackground(Color.WHITE);
		rbutton_3.setBounds(732, 5, 79, 23);
		panel.add(rbutton_3);
		
		//JButton预定后付
		JRadioButton r_button4 = new JRadioButton("\u9884\u5B9A\uFF08\u540E\u4ED8\uFF09");
		r_button4.setBackground(Color.WHITE);
		r_button4.setBounds(813, 5, 97, 23);
		panel.add(r_button4);
		bg_sale2.add(r_button4);
		
		//查询商品
		JButton button_search = new JButton("\u663E\u793A\u5546\u54C1");
		button_search.setBounds(940, 5, 124, 23);
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = id_name.getText();
				if(s.equals("")||s.equals(null))
					JOptionPane.showMessageDialog(panel, "请填写客户信息","提示",JOptionPane.WARNING_MESSAGE);
				else
				{
					
				}
			}
		});
		panel.add(button_search);
		
	
		JLabel label = new JLabel("\u8BA2\u5355\u603B\u91D1\u989D\uFF1A");
		label.setBounds(144, 383, 98, 15);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(240, 383, 103, 15);
		panel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u5143");
		label_1.setBounds(353, 383, 47, 15);
		panel.add(label_1);
		
		//生成提货单
		JButton button_createOrdered = new JButton("\u751F\u6210\u63D0\u8D27\u5355");
		button_createOrdered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_createOrdered.setBounds(898, 373, 124, 35);
		panel.add(button_createOrdered);
	
		//选择最早提货日期
		String[] year = {"2018", "2019", "2020", "2021", "2022"};
		String[] month = {"一月", "二月", "三月", "四月", "五月","六月", "七月", "八月", "九月", "十月","十一月","十二月"};
		String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
				"26","27","28","29","30","31"};
		
		JComboBox <String>comboBox_year = new JComboBox<String>();
		comboBox_year.setBounds(507, 380, 71, 21);
		for(int i = 0 ;i < year.length;i++)
			comboBox_year.addItem(year[i]);
		panel.add(comboBox_year);
		
		JComboBox <String>comboBox_month = new JComboBox<String>();
		comboBox_month.setBounds(622, 380, 66, 21);
		for(int i = 0 ;i < month.length;i++)
			comboBox_month.addItem(month[i]);
		panel.add(comboBox_month);
		
		JComboBox<String> comboBox_day = new JComboBox<String>();
		comboBox_day.setBounds(740, 380, 73, 21);
		for(int i = 0 ;i < day.length;i++)
			comboBox_day.addItem(day[i]);
		panel.add(comboBox_day);
		
		JLabel label_2 = new JLabel("\u5E74");
		label_2.setBounds(588, 383, 24, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u65E5");
		label_3.setBounds(829, 383, 24, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u6708");
		label_4.setBounds(698, 383, 24, 15);
		panel.add(label_4);
		
		
		
		
		
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
		scrollPane.setBounds(27, 74, 1003, 277);
	    //将table放入scrollPane
	   
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,400));
	    scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		JLabel label_5 = new JLabel("|");
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(512, 9, 24, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u6700\u65E9\u63D0\u8D27\u65E5\u671F");
		label_6.setBounds(397, 383, 98, 15);
		panel.add(label_6);
		
		
	 
	   
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
