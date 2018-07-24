package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.PersonalInfoBll;
import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.entity.Administrators;

public class CallInnfoDialog extends JDialog{
	//滚动框
		private JScrollPane jScrollPane;
		//表格
		private JTable table;
		private DefaultTableModel defaultTableModel;
		//当前this
		private CallInnfoDialog callInnfoDialog = this;
		//表头
		private String[] columnDefine = {"员工号","员工姓名","员工电话","员工身份"};
		private ArrayList<Administrators> users;
		public CallInnfoDialog(ArrayList<Administrators> administrators) {
			/*
			 * 初始化弹出框
			 */
			this.setVisible(true);
			this.setTitle("员工通讯录");
			this.setSize(800, 500);
			
			users = administrators;
			initTable();
			
		}
		private void initTable() {
			/*
			 * 表格处理
			 */
			defaultTableModel = new DefaultTableModel();
			defaultTableModel.setColumnIdentifiers(columnDefine);
			if(users!=null) {
				for(int i =0;i<users.size();i++) {
					Administrators administrators = users.get(i);
					defaultTableModel.addRow(new String[] {
						administrators.getUser_id(),administrators.getUser_name(),administrators.getUser_tel(),CheckUnit.getPowerOfAdmin(administrators.getAdm_power())
					});
				}
			}
			table = new JTable(defaultTableModel){
		    	@Override
		    	public boolean isCellEditable(int row, int column) {
		    		// TODO Auto-generated method stub
		    		return false;
		    	}
		    };
			table.setRowHeight(50);
			// tcr.setHorizontalAlignment(JLabel.CENTER);
			jScrollPane = new JScrollPane(table);
			jScrollPane.setPreferredSize(new Dimension(500, 400));
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.add(jScrollPane);
		}
}
