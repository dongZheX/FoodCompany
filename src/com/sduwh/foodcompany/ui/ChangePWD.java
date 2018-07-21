package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.PersonalInfoBll;



public class ChangePWD extends JDialog implements ActionListener{

	/*
	 * 控件声明
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id_c;
	private JPasswordField textField_old_pass_c;
	private JPasswordField textField_new_pass_c;
	private JPasswordField textField_new_con_pass_c;
	private JButton button_c_changpass;



	/**
	 * Create the dialog.
	 */
	public ChangePWD() {
		/*
		 * 控件初始化
		 */
		setBounds(100, 100, 480, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label_c_id = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_c_id.setFont(new Font("宋体", Font.PLAIN, 14));
		label_c_id.setBounds(91, 51, 66, 28);
		contentPanel.add(label_c_id);
		
		textField_id_c = new JTextField();
		textField_id_c.setBounds(166, 52, 169, 28);
		contentPanel.add(textField_id_c);
		textField_id_c.setColumns(10);
		
		JLabel label_old_pass = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
		label_old_pass.setFont(new Font("宋体", Font.PLAIN, 14));
		label_old_pass.setBounds(91, 107, 77, 16);
		contentPanel.add(label_old_pass);
		
		textField_old_pass_c = new JPasswordField();
		textField_old_pass_c.setColumns(10);
		textField_old_pass_c.setBounds(166, 102, 169, 28);
		contentPanel.add(textField_old_pass_c);
		
		JLabel label_new_pass = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_new_pass.setFont(new Font("宋体", Font.PLAIN, 14));
		label_new_pass.setBounds(91, 154, 77, 16);
		contentPanel.add(label_new_pass);
		
		JLabel label__new_con_pass_c = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label__new_con_pass_c.setFont(new Font("宋体", Font.PLAIN, 14));
		label__new_con_pass_c.setBounds(79, 200, 77, 16);
		contentPanel.add(label__new_con_pass_c);
		
		textField_new_pass_c = new JPasswordField();
		textField_new_pass_c.setColumns(10);
		textField_new_pass_c.setBounds(166, 152, 169, 28);
		contentPanel.add(textField_new_pass_c);
		
		textField_new_con_pass_c = new JPasswordField();
		textField_new_con_pass_c.setColumns(10);
		textField_new_con_pass_c.setBounds(166, 195, 169, 28);
		contentPanel.add(textField_new_con_pass_c);
		/*
		 * 按钮初始化
		 */
		button_c_changpass = new JButton("\u4FEE\u6539");
		button_c_changpass.setFont(new Font("黑体", Font.BOLD, 15));
		button_c_changpass.setBounds(175, 252, 93, 33);
		contentPanel.add(button_c_changpass);
		button_c_changpass.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String user_id = textField_id_c.getText().toString();
		String old_pass = textField_old_pass_c.getPassword().toString();
		String new_pass = textField_new_pass_c.getPassword().toString();
		String new_pass_con =textField_new_con_pass_c.getPassword().toString();
		/*
		 * 判断用户输入是否有问题
		 */
		if(user_id.equals("")||user_id==null) {
			JOptionPane.showMessageDialog(this, "请输入用户名");
			return;
		}
		if(old_pass.equals("")||old_pass==null) {
			JOptionPane.showMessageDialog(this, "请输入旧密码");
			return;
		}
		if(new_pass.equals("")||new_pass==null) {
			JOptionPane.showMessageDialog(this, "请输入新密码");
			return;
		}
		if(new_pass_con.equals("")||new_pass_con==null) {
			JOptionPane.showMessageDialog(this, "请输入确认密码");
			return;
		}
		/*
		 * 判断用户输入的两次密码是否一致
		 */
		if(!new_pass.equals(new_pass_con)) {
			JOptionPane.showMessageDialog(this, "请确保输入的两次密码一致");
			return;
		}else {
			//调用修改密码逻辑
			boolean state = PersonalInfoBll.reSetPwd(user_id, old_pass, new_pass);
			if(state) {
				JOptionPane.showMessageDialog(this, "修改成功");
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "修改失败，旧密码不正确");
			}
		}
 	}

}
