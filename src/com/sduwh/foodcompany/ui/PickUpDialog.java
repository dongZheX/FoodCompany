package com.sduwh.foodcompany.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MyListener;

import javax.swing.JButton;
/*
 * create by dongzhe
 */
public class PickUpDialog extends JDialog implements ActionListener{
	//���������
	private JTextField textField_good_id;
	private JTextField textField_batch_id;
	private JTextField textField_num;
	//������ť
	private JButton button_pick_up;
	//String
	private String good_id1,batch_id1;
	private int good_num;
	private MyListener mlistener;
	public PickUpDialog(String good_id,String batch_id,int num,MyListener listener) {
		//���ڳ�ʼ��
		super();
		setTitle("\u63D0\u8D27");
		getContentPane().setLayout(null);
		setSize(500,500);
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		good_id1 = good_id;
		batch_id1 = batch_id;
		good_num = num;
		mlistener = listener;
		/*
		 * �ؼ���ʼ��
		 */
		JLabel label = new JLabel("\u5546\u54C1\u7F16\u53F7:");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(76, 72, 81, 40);
		getContentPane().add(label);
		
		textField_good_id = new JTextField();
		textField_good_id.setEditable(false);
		textField_good_id.setFont(new Font("����", Font.PLAIN, 14));
		textField_good_id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_good_id.setBounds(167, 77, 184, 30);
		getContentPane().add(textField_good_id);
		textField_good_id.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6279\u6B21\u53F7:");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(76, 126, 81, 40);
		getContentPane().add(label_1);
		
		textField_batch_id = new JTextField();
		textField_batch_id.setEditable(false);
		textField_batch_id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_batch_id.setFont(new Font("����", Font.PLAIN, 14));
		textField_batch_id.setColumns(10);
		textField_batch_id.setBounds(167, 131, 184, 30);
		getContentPane().add(textField_batch_id);
	
		JLabel label_2 = new JLabel("\u63D0\u8D27\u6570\u91CF:");
		label_2.setFont(new Font("����", Font.PLAIN, 14));
		label_2.setBounds(76, 182, 81, 40);
		getContentPane().add(label_2);
		
		textField_num = new JTextField();
		textField_num.setHorizontalAlignment(SwingConstants.CENTER);
		textField_num.setFont(new Font("����", Font.PLAIN, 14));
		textField_num.setColumns(10);
		textField_num.setBounds(167, 192, 184, 30);
		getContentPane().add(textField_num);
		//������ť
		button_pick_up = new JButton("\u63D0\u8D27");
		button_pick_up.setBounds(167, 263, 103, 40);
		getContentPane().add(button_pick_up);
		button_pick_up.addActionListener(this);
		//��ʼ�������
		if(good_id!=null||batch_id!=null){
			textField_good_id.setText(good_id);
			textField_batch_id.setText(batch_id);
		}
		//this.setVisible(true);
		//this.setModal(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("���")) {
			if(textField_num.getText().equals("0")|textField_num.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "���������������");
			}else {
				try {
					int getNum = Integer.parseInt(textField_num.getText());
					if(getNum>good_num) {
						JOptionPane.showMessageDialog(this, "�������㣬������Ʒ��ʣ"+good_num);
					}
					else {
						int result = JOptionPane.showConfirmDialog(this, "��ȷ�������");
						if(result==JOptionPane.YES_OPTION) {
						   //����߼�
							if(WarehouseService.pickUpBatch(batch_id1, getNum))
							{
								JOptionPane.showMessageDialog(this, "����ɹ�");
								mlistener.getResult(good_num-getNum,good_num-getNum==0?"�ۿ�":"����");
								this.dispose();
							}
							else {
								JOptionPane.showMessageDialog(this, "�쳣");
							}
						}else {
							//ȡ��
							//do nothing							
						}
						
					}
				}catch (NumberFormatException e5) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "����������ȷ����������");
				}
			}
			
		}
	}
}
