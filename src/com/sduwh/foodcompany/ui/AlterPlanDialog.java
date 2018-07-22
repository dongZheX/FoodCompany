package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class AlterPlanDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlterPlanDialog dialog = new AlterPlanDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlterPlanDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 251);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		/*
		 * 设置日期选择器
		 */
		DateChooser dateChooser = new DateChooser("yyyy-MM-dd");
		dateChooser.setLocation(170, 122);
		dateChooser.setSize(121,25);
		contentPanel.add(dateChooser);

		
		{
			JLabel label = new JLabel("");
			label.setBounds(55, 36, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u53F7\uFF1A");
			label.setBounds(50, 36, 70, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u6570\u91CF\uFF1A");
			label.setBounds(50, 79, 72, 15);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(170, 76, 121, 21);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u9700\u8981\u65E5\u671F\uFF1A");
			label.setBounds(50, 122, 70, 15);
			contentPanel.add(label);
		}
		{
			JButton button = new JButton("\u786E\u5B9A");
			button.setBounds(136, 172, 93, 23);
			contentPanel.add(button);
		}
		{
			textField = new JTextField();
			textField.setBounds(170, 36, 122, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
	}

}
