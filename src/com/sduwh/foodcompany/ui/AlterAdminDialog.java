package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.ibatis.session.SqlSession;

import com.sduwh.foodcompany.bill.SaleBll;
import com.sduwh.foodcompany.bill.SystemBll;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MybatisUtil;
import com.sduwh.foodcompany.dao.CustomerDao;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Customer;

public class AlterAdminDialog extends JDialog implements ActionListener  {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	String admin_id;


	/**
	 * Create the dialog.
	 */
	public AlterAdminDialog(String admin_id) {
		this.admin_id = admin_id;
		setBounds(100, 100, 462, 369);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(55, 42, 70, 15);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(148, 39, 163, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		label_1.setBounds(55, 82, 66, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 79, 163, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(55, 123, 70, 15);
		contentPanel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 120, 163, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u7C7B\u578B\uFF1A");
		lblNewLabel.setBounds(55, 169, 89, 15);
		contentPanel.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(148, 166, 163, 21);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(this);
		button.setBounds(151, 212, 122, 23);
		contentPanel.add(button);
		
		/*
		 * 获取现有的管理员信息
		 */
		/*
		 * 调用    private static Admin getAdminById(String adminId)
		 */
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("确认修改")){
			/*
			 * 修改管理员信息函数
			 */
			SqlSession session = MybatisUtil.getSession();
			CustomerDao dao = session.getMapper(CustomerDao.class);
			Map map = MapBuilder.buildMap("user_name", textField.getText());
			ArrayList<Customer> list = dao.findCustomer(map);
			if(list.size() ==0)
				JOptionPane.showMessageDialog(this, "管理员不存在", "错误", JOptionPane.ERROR_MESSAGE);
			String id = list.get(0).getUser_id();
			//姓名 联系方式 密码 类型
			String name = textField.getText();
			String tel = textField_1.getText();
			String psw = textField_2.getText();
			int type = Integer.parseInt(textField_3.getText());
			//String user_id, String user_name, String user_psw, String user_tel, int adm_power
			Administrators adm = new Administrators(this.admin_id, name, psw, tel, type);
			SystemBll.updateAdministrators(adm);
			JOptionPane.showMessageDialog(this, "修改成功");
			//SaleBll.updateAdmin(id, textField.getText(),  textField_1.getText(),  textField_2.getText(),  textField_3.getText());
			btn_alter_action();
		}
	}
	
	public void btn_alter_action(){
		/*
		 * 修改逻辑层
		 */
	}
}
