package com.sduwh.foodcompany.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sduwh.foodcompany.bill.AdministratorsTableData;
import com.sduwh.foodcompany.bill.SystemBll;
import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.entity.Administrators;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class MDICreateUser extends JInternalFrame implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JComboBox<String>comboBox;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDICreateUser window = new MDICreateUser();
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
	public MDICreateUser() {
		setTitle("\u65B0\u589E\u7BA1\u7406\u5458");
		initialize();
		setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
		panel.setLayout(null);
		
		
		comboBox.setBounds(402, 31, 156, 32);
		panel.add(comboBox);
		String[] combobox_item = {"系统管理员", "成品库管理员","会计","出纳","生产车间管理员","生产计划管理员","销售"};
		int combobox_num = combobox_item.length;
		for(int i = 0; i < combobox_num; i++)
			comboBox.addItem(combobox_item[i]);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u7C7B\u578B:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(204, 40, 127, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7BA1\u7406\u5458\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(204, 89, 127, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(204, 140, 127, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(204, 249, 127, 15);
		panel.add(lblNewLabel_3);
		
		JLabel label = new JLabel("\u518D\u6B21\u8F93\u5165\u5BC6\u7801:");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(204, 195, 127, 15);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(402, 81, 156, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(402, 132, 156, 32);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(402, 187, 156, 32);
		panel.add(passwordField_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(402, 241, 156, 32);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u65B0\u589E\u7BA1\u7406\u5458");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psw, repsw, name, tell;
				name = textField.getText();
				tell = textField_1.getText();
				psw = String.valueOf(passwordField.getPassword());
				repsw = String.valueOf(passwordField_1.getPassword());
				if(name.equals("") || tell.equals("")||psw.equals("")||repsw.equals(""))
					JOptionPane.showMessageDialog(null, "信息输入不完整", "【出错啦】", JOptionPane.ERROR_MESSAGE);
				else if(!psw.equals(repsw))
					JOptionPane.showMessageDialog(null, "密码输入不一致，请重试", "【出错啦】", JOptionPane.ERROR_MESSAGE);
				else
				{
					//传递参数
					String temp = (String)comboBox.getSelectedItem();
					int type = Administrators.administrators_string_toInt(temp);
					
					//AdministratorsTableData data = new AdministratorsTableData(name, psw, tel, type);
					SystemBll.createAdministrators(name, psw, tell, temp);
					
					JOptionPane.showMessageDialog(null, "创建成功");
				}
			}
		});
		button.setBounds(271, 302, 109, 37);
		panel.add(button);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		comboBox = new JComboBox<String>();
		this.setContentPane(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
