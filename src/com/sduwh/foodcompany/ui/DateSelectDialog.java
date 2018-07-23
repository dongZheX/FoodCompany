package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.Color;

public class DateSelectDialog extends JDialog implements ActionListener {
	


	private Date date;
	

	/**
	 * Create the dialog.
	 */
	public DateSelectDialog() {
		/*
		 * 设置为阻塞状态
		 */
		this.setModal(true);
		/*
		 * 初始化Dialog
		 */
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		/*
		 * 设置日期选择器
		 */
		DateChooser dateChooser = new DateChooser("yyyy-MM-dd");
		dateChooser.setLocation(203, 90);
		dateChooser.setSize(157,33);
		getContentPane().add(dateChooser);
		
		JLabel label = new JLabel("\u9009\u62E9\u65E5\u671F\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(59, 96, 91, 15);
		getContentPane().add(label);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(this);
		button.setBounds(149, 170, 124, 33);
		getContentPane().add(button);
		
		/*
		 * 获取date
		 */
		this.date = dateChooser.getDate();
	}
	
	public Date get_date(){
		return date;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("确定")){
			this.dispose();
		}
	}

}
