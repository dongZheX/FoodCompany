package com.sduwh.foodcompany.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.entity.Administrators;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
/*
 * create by dongzhe
 */
public class PersonInfoPanel extends JPanel {
	private JTextField textField_user_id;
	private JTextField textField_user_name;
	private JLabel label_tel;
	private JTextField textField_tel;
	private JLabel label__admin_power;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PersonInfoPanel() {
		//初始设置
		setSize(800,700);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{222, 112, 192, 111, 192, 0};
		gridBagLayout.rowHeights = new int[]{0, 84, 131, 34, 59, 34, 50, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label_user_id = new JLabel("用户名:");
		label_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		label_user_id.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_user_id = new GridBagConstraints();
		gbc_label_user_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_user_id.insets = new Insets(0, 0, 5, 5);
		gbc_label_user_id.gridx = 1;
		gbc_label_user_id.gridy = 3;
		add(label_user_id, gbc_label_user_id);
		label_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		
		textField_user_id = new JTextField();
		textField_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_user_id.setEditable(false);
		textField_user_id.setEnabled(false);
		GridBagConstraints gbc_textField_user_id = new GridBagConstraints();
		gbc_textField_user_id.fill = GridBagConstraints.BOTH;
		gbc_textField_user_id.insets = new Insets(0, 0, 5, 5);
		gbc_textField_user_id.gridx = 2;
		gbc_textField_user_id.gridy = 3;
		add(textField_user_id, gbc_textField_user_id);
		textField_user_id.setColumns(10);
		
		JLabel label_user_name = new JLabel("姓名：");
		label_user_name.setFont(new Font("宋体", Font.PLAIN, 16));
		label_user_name.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_user_name = new GridBagConstraints();
		gbc_label_user_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_user_name.insets = new Insets(0, 0, 5, 5);
		gbc_label_user_name.gridx = 3;
		gbc_label_user_name.gridy = 3;
		add(label_user_name, gbc_label_user_name);
		
		textField_user_name = new JTextField();
		textField_user_name.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_user_name.setEnabled(false);
		textField_user_name.setEditable(false);
		textField_user_name.setColumns(10);
		GridBagConstraints gbc_textField_user_name = new GridBagConstraints();
		gbc_textField_user_name.fill = GridBagConstraints.BOTH;
		gbc_textField_user_name.insets = new Insets(0, 0, 5, 0);
		gbc_textField_user_name.gridx = 4;
		gbc_textField_user_name.gridy = 3;
		add(textField_user_name, gbc_textField_user_name);
		
		label_tel = new JLabel("电话：");
		label_tel.setHorizontalAlignment(SwingConstants.CENTER);
		label_tel.setFont(new Font("宋体", Font.PLAIN, 16));
		GridBagConstraints gbc_label_tel = new GridBagConstraints();
		gbc_label_tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_tel.insets = new Insets(0, 0, 5, 5);
		gbc_label_tel.gridx = 1;
		gbc_label_tel.gridy = 5;
		add(label_tel, gbc_label_tel);
		
		textField_tel = new JTextField();
		textField_tel.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_tel.setEnabled(false);
		textField_tel.setEditable(false);
		textField_tel.setColumns(10);
		GridBagConstraints gbc_textField_tel = new GridBagConstraints();
		gbc_textField_tel.fill = GridBagConstraints.BOTH;
		gbc_textField_tel.insets = new Insets(0, 0, 5, 5);
		gbc_textField_tel.gridx = 2;
		gbc_textField_tel.gridy = 5;
		add(textField_tel, gbc_textField_tel);
		
		label__admin_power = new JLabel("身份：");
		label__admin_power.setHorizontalAlignment(SwingConstants.CENTER);
		label__admin_power.setFont(new Font("宋体", Font.PLAIN, 16));
		GridBagConstraints gbc_label__admin_power = new GridBagConstraints();
		gbc_label__admin_power.fill = GridBagConstraints.HORIZONTAL;
		gbc_label__admin_power.insets = new Insets(0, 0, 5, 5);
		gbc_label__admin_power.gridx = 3;
		gbc_label__admin_power.gridy = 5;
		add(label__admin_power, gbc_label__admin_power);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		
		JButton btn_edit = new JButton("\u7F16\u8F91");
		GridBagConstraints gbc_btn_edit = new GridBagConstraints();
		gbc_btn_edit.fill = GridBagConstraints.BOTH;
		gbc_btn_edit.insets = new Insets(0, 0, 0, 5);
		gbc_btn_edit.gridx = 1;
		gbc_btn_edit.gridy = 7;
		add(btn_edit, gbc_btn_edit);
		
		JButton button_edit = new JButton("\u4FDD\u5B58");
		button_edit.setEnabled(false);
		GridBagConstraints gbc_button_edit = new GridBagConstraints();
		gbc_button_edit.fill = GridBagConstraints.BOTH;
		gbc_button_edit.insets = new Insets(0, 0, 0, 5);
		gbc_button_edit.gridx = 3;
		gbc_button_edit.gridy = 7;
		add(button_edit, gbc_button_edit);
		DefaultTableModel defaultTableModel = new DefaultTableModel(4,2);
		setVisible(true);
		
		
		//设置边框
	}
}
