package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.NameToEntity;
import com.sduwh.foodcompany.entity.Ordered;
import com.sduwh.foodcompany.entity.PickUp;
/*
 * create by dongzhe 2018/7/22
 */
public class LookOrderByPickUpDialog extends JDialog{
	//������
	private JScrollPane jScrollPane;
	//���
	private JTable table;
	private DefaultTableModel defaultTableModel;
	//��ǰthis
	private LookOrderByPickUpDialog lookOrderByPickUpDialog = this;
	//��ͷ
	private String[] columnDefine = {"��Ʒ���","��Ʒ����","��Ʒ����","�����������","�����������","�ͻ���"};
	
	public LookOrderByPickUpDialog(String pick_up_id) {
		/*
		 * ��ʼ��������
		 */
		this.setVisible(true);
		this.setTitle("���������");
		this.setSize(800, 500);
		/*
		 * �����
		 */
		
		table = new JTable();
		table.setRowHeight(50);
		jScrollPane = new JScrollPane(table);
		jScrollPane.setPreferredSize(new Dimension(500, 400));
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jScrollPane);
		
		/*
		 * ����������
		 */
		ArrayList<Ordered> ordered_arr = NameToEntity.PickUp_select_Ordered(pick_up_id);
		System.out.println(ordered_arr);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(columnDefine);
		for(int i = 0;i<ordered_arr.size();i++){
			Ordered ordered = ordered_arr.get(i);
			defaultTableModel.addRow(new String[]{
					ordered.getGood_id()
			});
		}
	}
	
}
