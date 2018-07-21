package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWarehouseDialog extends JDialog implements ActionListener{

	/*
	 * 声明控件
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_good_id;
	private JTextField textField_workshop_id;
	private JTextField textField_warehouse_id;
	private JTextField textFied_good_num;



	/**
	 * Create the dialog.
	 */
	public AddWarehouseDialog() {
		setTitle("\u6DFB\u52A0\u65B0\u5E93\u5B58");
		/*
		 * 初始化界面
		 */
		setBounds(100, 100, 465, 481);
		getContentPane().setLayout(null);
		setBackground(Color.white);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		/*
		 * 设置日期选择器
		 */
		DateChooser dateChooser_1 = new DateChooser("yyyy-MM-dd");
		dateChooser_1.setLocation(220, 135);
		dateChooser_1.setSize(151,25);
		getContentPane().add(dateChooser_1);
		
		DateChooser dateChooser_2 = new DateChooser("yyyy-MM-dd");
		dateChooser_2.setBounds(220, 185, 151, 25);
		getContentPane().add(dateChooser_2);
		/*设置输入控件*/
		textField_good_id = new JTextField();
		textField_good_id.setBounds(220, 37, 151, 25);
		getContentPane().add(textField_good_id);
		textField_good_id.setColumns(10);
		
		JLabel label = new JLabel("\u751F\u4EA7\u65E5\u671F\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(84, 135, 70, 25);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6709\u6548\u671F\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 14));
		label_1.setBounds(84, 185, 70, 25);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6210\u54C1\u5E93\u7BA1\u7406\u5458\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 14));
		label_2.setBounds(73, 235, 104, 25);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u7F16\u53F7\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 14));
		label_3.setBounds(84, 37, 70, 25);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u751F\u4EA7\u8F66\u95F4\u7BA1\u7406\u5458\uFF1A");
		label_4.setFont(new Font("黑体", Font.PLAIN, 14));
		label_4.setBounds(64, 281, 113, 25);
		getContentPane().add(label_4);
		
		textField_workshop_id = new JTextField();
		textField_workshop_id.setColumns(10);
		textField_workshop_id.setBounds(220, 281, 151, 25);
		getContentPane().add(textField_workshop_id);
		
		textField_warehouse_id = new JTextField();
		textField_warehouse_id.setColumns(10);
		textField_warehouse_id.setBounds(220, 235, 151, 25);
		getContentPane().add(textField_warehouse_id);
		
		JLabel label_5 = new JLabel("\u5546\u54C1\u6570\u91CF\uFF1A");
		label_5.setFont(new Font("黑体", Font.PLAIN, 14));
		label_5.setBounds(84, 92, 70, 25);
		getContentPane().add(label_5);
		
		textFied_good_num = new JTextField();
		textFied_good_num.setColumns(10);
		textFied_good_num.setBounds(220, 92, 151, 25);
		getContentPane().add(textFied_good_num);
		/*
		 * 设置按钮
		 */
		JButton button = new JButton("\u6DFB\u52A0");
		button.setBounds(168, 337, 93, 34);
		getContentPane().add(button);
		button.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
