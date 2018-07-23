package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.InsertProducePlanBill;
import com.sduwh.foodcompany.entity.Goods;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddGoodIdDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	
	/*
	 * ����
	 */
	private String good_id;
	private String good_name;

	/**
	 * Create the dialog.
	 */
	public AddGoodIdDialog() {
		/*
		 * ���ؼ���
		 * ��dialog��Ϊ����״̬
		 * ֻ�е�dialog���н������������ټ���ִ��
		 * JDialog�Ƿ�����״̬�ĶԻ���,JOptionPane�������Ի���,���JOptiongPane������������ֵ
		 */
		this.setModal(true);
		/*
		 * ��ʼ��dialog����
		 */
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
		
		JButton button = new JButton("��ѯ");
		button.addActionListener(this);
		button.setBounds(308, 43, 69, 23);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u7F16\u53F7");
		label_1.setBounds(46, 119, 54, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 116, 156, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("ȷ��");
		button_1.addActionListener(this);
		button_1.setBounds(171, 170, 93, 23);
		contentPanel.add(button_1);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("��ѯ")){
			get_good_id();
		}
		
		else if(btn_name.equals("ȷ��")){
			this.dispose();
		}
		
	}
	
	
	public void get_good_id(){
		good_name = textField.getText();
		good_id = InsertProducePlanBill.select_good_id(good_name);
		if(good_id.equals("error"))
			JOptionPane.showMessageDialog(this, "δ��ѯ����Ʒ");
		else
			textField_1.setText(good_id);
	}
	
	public String comfrim(){
		return good_id;
	}
	
}
