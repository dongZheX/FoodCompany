package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.SelectProducePlanBill;
import com.sduwh.foodcompany.entity.ProducePlan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AlterPlanDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField;
	private DateChooser dateChooser ;
	private String plan_id;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AlterPlanDialog dialog = new AlterPlanDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AlterPlanDialog(String plan_id) {
		this.plan_id = plan_id;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 251);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		

		
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
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
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
			button.addActionListener(this);
			button.setBounds(136, 172, 93, 23);
			contentPanel.add(button);
		}
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setBounds(170, 36, 122, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		String [] key = {"plan_id",plan_id};
		ArrayList<ProducePlan> plan_arr = SelectProducePlanBill.select_ProducePlan(key);
		ProducePlan myPlan = plan_arr.get(0);
		
		/*
		 * ���鵽��Ԫ�س�ʼ����textfield
		 */
		textField.setText(myPlan.getGood_id());
		textField_1.setText(myPlan.getGood_num()+"");
		Date mydate = myPlan.getDeadline();

		/*
		 * ��������ѡ����
		 */
		dateChooser = new DateChooser(mydate);
		dateChooser.setLocation(170, 122);
		dateChooser.setSize(121,25);
		contentPanel.add(dateChooser);
		
		System.out.println(plan_id);
		
		
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	String btn_name = e.getActionCommand();
	if(btn_name.equals("ȷ��")){
		com_btn_action();
	}
	
}

public void com_btn_action(){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	String [] alter_key = {
			"good_num",textField_1.getText().toString(),
			"deadline",simpleDateFormat.format(dateChooser.getDate()),
			"plan_state",null,
			"plan_id",plan_id
	};
	System.out.print(1);
	System.out.print(simpleDateFormat.format(dateChooser.getDate()));
	System.out.print(2);
	if(SelectProducePlanBill.select_state(plan_id)){
		if(SelectProducePlanBill.alter_plan(alter_key)){
			JOptionPane.showMessageDialog(this,"�޸ĳɹ�!");
			this.dispose();
		}
		
		else{
			JOptionPane.showMessageDialog(this,"�޸�ʧ��,����ϵ����Ա!");
		}
	}
	else
		JOptionPane.showMessageDialog(this, "�޸�ʧ��,�������ƻ���δȷ��״̬!");
}

}
