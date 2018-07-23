package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.comm.CheckUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.Color;

public class DateSelectDialog extends JDialog implements ActionListener {
	


	private DateChooser dateChooser;
	

	/**
	 * Create the dialog.
	 */
	public DateSelectDialog() {
		/*
		 * ����Ϊ����״̬
		 */
		this.setModal(true);
		/*
		 * ��ʼ��Dialog
		 */
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setBackground(Color.white);
		
		
		/*
		 * ��������ѡ����
		 */
		dateChooser = new DateChooser("yyyy-MM-dd");
		dateChooser.setLocation(102, 97);
		dateChooser.setSize(210,33);
		getContentPane().add(dateChooser);
		
		JLabel label = new JLabel("\u9009\u62E9\u65E5\u671F\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(169, 54, 91, 15);
		getContentPane().add(label);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(this);
		button.setBounds(149, 170, 124, 33);
		getContentPane().add(button);
		
		/*
		 * ��ȡdate
		 */
		
	}
	
	public Date get_date(){
		if(CheckUnit.dateDiff("day", dateChooser.getDate(), new Date()) < 0){
			JOptionPane.showMessageDialog(this, "ѡ�����ڲ��Ϸ�");
			return new Date();
		}
		return dateChooser.getDate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("ȷ��")){
			this.dispose();
		}
	}

}
