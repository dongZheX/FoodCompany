package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.SelectGoodBll;
import com.sduwh.foodcompany.entity.Goods;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AlterGoodsDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private String good_id;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AlterGoodsDialog dialog = new AlterGoodsDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AlterGoodsDialog(String good_id)  {
		this.setModal(true);
		this.good_id = good_id;
		setBounds(100, 100, 450, 358);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 288);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u540D\u79F0\uFF1A");
			lblNewLabel.setBounds(67, 38, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setBounds(153, 35, 146, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u89C4\u683C\uFF1A");
		label.setBounds(67, 88, 54, 15);
		contentPanel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 85, 146, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6210\u672C\uFF1A");
		label_1.setBounds(67, 138, 54, 15);
		contentPanel.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 135, 146, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4FDD\u8D28\u671F\uFF1A");
		label_2.setBounds(67, 183, 54, 15);
		contentPanel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(153, 180, 146, 21);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(this);
		button.setBounds(134, 228, 165, 23);
		contentPanel.add(button);
		
		/*
		 * 设置初始化数据
		 */
		Goods good = SelectGoodBll.select_good("good_id",good_id).get(0);
		textField.setText(good.getGood_name());
		textField_1.setText(good.getGood_standard());
		textField_2.setText(good.getGood_cost()+"");
		textField_3.setText(good.getGood_expiration_date()+"");
	}

@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		String btn_nama = e.getActionCommand();
		if(btn_nama.equals("确认修改")){
			alter_btn_action();
		}
	
	}

	public void alter_btn_action(){
		String good_name = textField.getText();
		String good_standard = textField_1.getText();
		String good_cost = textField_2.getText();
		String good_expiration = textField_3.getText();
		try{
			Float.parseFloat(good_cost);
			
			String [] key = {
					"good_name",good_name.equals("")?null:good_name,
					"good_standard",good_standard.equals("")?null:good_standard,
					"good_cost",good_cost.equals("")?null:good_cost,
					"good_expiration_date",good_expiration.equals("")?null:good_expiration,
					"good_id",good_id
					};
			if(SelectGoodBll.alter_good_info(key)){
				JOptionPane.showMessageDialog(this, "修改成功!");
				this.dispose();
			}
			else 
				JOptionPane.showMessageDialog(this, "修改失败!");
		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "请输入正确数据");
		}
		
	}
}
