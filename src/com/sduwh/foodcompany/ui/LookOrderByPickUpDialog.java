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
	//滚动框
	private JScrollPane jScrollPane;
	//表格
	private JTable table;
	private DefaultTableModel defaultTableModel;
	//当前this
	private LookOrderByPickUpDialog lookOrderByPickUpDialog = this;
	//表头
	private String[] columnDefine = {"商品编号","商品名称","商品数量","最早提货日期","最晚提货日期","客户号"};
	
	public LookOrderByPickUpDialog() {
		/*
		 * 初始化弹出框
		 */
		this.setVisible(true);
		this.setTitle("提货单详情");
		this.setSize(800, 500);
		/*
		 * 表格处理
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
