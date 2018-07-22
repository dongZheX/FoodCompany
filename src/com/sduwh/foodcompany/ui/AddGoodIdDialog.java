package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddGoodIdDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the dialog.
	 */
	public AddGoodIdDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
		label.setBounds(46, 47, 54, 15);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(137, 44, 156, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(308, 43, 69, 23);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u7F16\u53F7");
		label_1.setBounds(46, 119, 54, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 116, 156, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u786E\u5B9A");
		button_1.setBounds(171, 170, 93, 23);
		contentPanel.add(button_1);
	}
}
