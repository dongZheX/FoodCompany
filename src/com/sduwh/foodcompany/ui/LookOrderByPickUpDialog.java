package com.sduwh.foodcompany.ui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;
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
	
	public LookOrderByPickUpDialog() {
		/*
		 * ��ʼ��������
		 */
		this.setVisible(true);
		this.setTitle("���������");
		this.setSize(800, 500);
		/*
		 * �����
		 */
		defaultTableModel = new DefaultTableModel(columnDefine, 6);
		table = new JTable(defaultTableModel);
		table.setRowHeight(50);
		jScrollPane = new JScrollPane(table);
		jScrollPane.setPreferredSize(new Dimension(500, 400));
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jScrollPane);
	}
	
}
