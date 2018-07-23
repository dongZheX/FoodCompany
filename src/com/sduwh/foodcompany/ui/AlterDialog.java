package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.entity.Customer;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AlterDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


	/**
	 * Create the dialog.
	 */
	public AlterDialog(String cus_user_id) {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		label.setBounds(55, 42, 70, 15);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(135, 39, 163, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BA2\u6237\u7EA7\u522B\uFF1A");
		label_1.setBounds(55, 82, 66, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 79, 163, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4FE1\u8A89\u79EF\u5206\uFF1A");
		label_2.setBounds(55, 123, 70, 15);
		contentPanel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(135, 120, 163, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u5730\u5740\uFF1A");
		lblNewLabel.setBounds(55, 169, 70, 15);
		contentPanel.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(135, 166, 163, 21);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(this);
		button.setBounds(151, 212, 122, 23);
		contentPanel.add(button);
		
		/*
		 * 获取现有的用户信息
		 */
		/*
		 * 调用    private static Customer getCustomerById(String customerId)
		 */
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("确认修改")){
			/*
			 * 修改用户信息函数
			 */
			btn_alter_action();
		}
	}
	
	public void btn_alter_action(){
		/*
		 * 修改用户信息逻辑层
		 */
	}
}
