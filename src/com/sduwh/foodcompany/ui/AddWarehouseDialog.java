package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MyListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

public class AddWarehouseDialog extends JDialog implements ActionListener{

	/*
	 * 声明控件
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_good_name;
	private JTextField textField_workshop_id;
	private JTextField textField_warehouse_id;
	private JTextField textFied_good_num;
	JButton button;
	private String good_name,good_num,good_PD,good_GP,warehouse_user_id,workshop_user_id;
	private DateChooser dateChooser_1;
	private static Calendar calendar = Calendar.getInstance();
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
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setBackground(Color.WHITE);
		/*
		 * 设置日期选择器
		 */
		dateChooser_1 = new DateChooser("yyyy-MM-dd");
		dateChooser_1.setLocation(220, 145);
		dateChooser_1.setSize(151,25);
		getContentPane().add(dateChooser_1);
		/*设置输入控件*/
		textField_good_name = new JTextField();
		textField_good_name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_good_name.setBounds(220, 49, 151, 25);
		getContentPane().add(textField_good_name);
		textField_good_name.setColumns(10);
		
		JLabel label = new JLabel("\u751F\u4EA7\u65E5\u671F\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(84, 145, 70, 25);
		getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u6210\u54C1\u5E93\u7BA1\u7406\u5458\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 14));
		label_2.setBounds(64, 189, 104, 25);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u540D\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 14));
		label_3.setBounds(95, 49, 70, 25);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u751F\u4EA7\u8F66\u95F4\u7BA1\u7406\u5458\uFF1A");
		label_4.setFont(new Font("黑体", Font.PLAIN, 14));
		label_4.setBounds(64, 237, 113, 25);
		getContentPane().add(label_4);
		
		textField_workshop_id = new JTextField();
		textField_workshop_id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_workshop_id.setColumns(10);
		textField_workshop_id.setBounds(220, 237, 151, 25);
		getContentPane().add(textField_workshop_id);
		
		textField_warehouse_id = new JTextField();
		textField_warehouse_id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_warehouse_id.setColumns(10);
		textField_warehouse_id.setBounds(220, 189, 151, 25);
		getContentPane().add(textField_warehouse_id);
		
		JLabel label_5 = new JLabel("\u5546\u54C1\u6570\u91CF\uFF1A");
		label_5.setFont(new Font("黑体", Font.PLAIN, 14));
		label_5.setBounds(84, 92, 70, 25);
		getContentPane().add(label_5);
		
		textFied_good_num = new JTextField();
		textFied_good_num.setHorizontalAlignment(SwingConstants.CENTER);
		textFied_good_num.setColumns(10);
		textFied_good_num.setBounds(220, 92, 151, 25);
		getContentPane().add(textFied_good_num);
		/*
		 * 设置按钮
		 */
		button = new JButton("\u6DFB\u52A0");
		button.setBounds(153, 287, 93, 34);
		getContentPane().add(button);
		button.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//商品名的输入判断
		good_name = textField_good_name.getText();
		String good_id;

		if (good_name.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入商品名");
			return;
		} else {
			good_id = WarehouseService.findIdByGoodName(good_name);
			if (good_id.equals("")) {
				JOptionPane.showMessageDialog(this, "我们没有这个商品");
				return;
			}
		}
		/*
		 * 商品数量输入
		 */
		good_num = textFied_good_num.getText();
		int good_num_true;
		try {
			if(good_num.equals(""))
			{
				JOptionPane.showMessageDialog(this, "请输入商品数量");
				return;
			}
			good_num_true = Integer.parseInt(good_num);
		}catch(NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "商品数量请输入数字");
			return;
		}
		/*
		 * 获取日期
		 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		good_PD = simpleDateFormat.format(dateChooser_1.getDate());
		calendar.add(Calendar.DATE, WarehouseService.findYXByGoodId(good_id));
		Date good_GP_d = calendar.getTime();		
		good_GP = simpleDateFormat.format(good_GP_d);
		if(CheckUnit.dateDiff("day", dateChooser_1.getDate(), new Date())>0) {
			JOptionPane.showMessageDialog(this, "输入日期不合法");
			return;
		}
		/*
		 * 获取人
		 */
		workshop_user_id = WarehouseService.findIdByAdminName(textField_workshop_id.getText());
		if(workshop_user_id.equals("")) {JOptionPane.showMessageDialog(this, "查无此人");return;}
		warehouse_user_id = WarehouseService.findIdByAdminName(textField_warehouse_id.getText());
		if(warehouse_user_id.equals("")) {JOptionPane.showMessageDialog(this, "查无此人");return;}
		/*
		 * 获取成功后
		 */
		WarehouseService.insertWarehouse(
					"good_id",good_id,
					"good_PD",good_PD,
					"good_GP",good_GP,
					"good_num",good_num_true,
					"warehouse_user_id",warehouse_user_id,
					"workshop_user_id",workshop_user_id,
					"good_state","1"
				);
		JOptionPane.showMessageDialog(this, "添加成功,刷新查看");
		this.dispose();
	}
}
