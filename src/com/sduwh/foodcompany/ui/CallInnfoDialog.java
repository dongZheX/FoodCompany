package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.sduwh.foodcompany.comm.KMP;
import com.sduwh.foodcompany.entity.Administrators;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CallInnfoDialog extends JDialog implements ActionListener{
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
		private JTextField textField_name;
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
			getContentPane().add(jScrollPane);
			
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));
			getContentPane().add(panel, BorderLayout.NORTH);
			
			JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
			panel.add(lblNewLabel);
			
			textField_name = new JTextField();
			panel.add(textField_name);
			textField_name.setColumns(20);
			
			JButton btnNewButton_search = new JButton("\u641C\u7D22");
			panel.add(btnNewButton_search);
			btnNewButton_search.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DefaultTableModel defaultTableModel = new DefaultTableModel();
			defaultTableModel.setColumnIdentifiers(columnDefine);
			if(users!=null) {
				for(int i =0;i<users.size();i++) {
					Administrators administrators = users.get(i);
					if(KMP.kmp(administrators.getUser_name(), textField_name.getText())!=-1||textField_name.getText().equals("")) {
						defaultTableModel.addRow(new String[] {
								administrators.getUser_id(),administrators.getUser_name(),administrators.getUser_tel(),CheckUnit.getPowerOfAdmin(administrators.getAdm_power())
						});
					}
				}
			}
			table.setModel(defaultTableModel);
		}
}
