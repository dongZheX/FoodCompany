package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.entity.Warehouse;

public class SelectWarehouse extends JInternalFrame implements ActionListener {

	
	//scrollPane
	private JScrollPane scrollPane_main;
	// JSplitPane
	private JSplitPane splitPane;
	// selectPane��viewPane
	private JPanel selectPane, viewPane;
	// textfield
	private JTextField batch_id_field, good_id_field, warehouse_user_id_field, workshop_user_id_field;
	// combobox
	private JComboBox<String> good_state_combobox;
	// label
	private JLabel batch_id_label, good_id_label, warehouse_user_id_label, workshop_user_id_label, good_state_label;
	// button
	private JButton select_btn;
	// table
	private JTable table;
	private DefaultTableModel tableModel;
	// scrollPane
	private JScrollPane scrollPane;
	// ��������
	private String batch_id, good_name, warehouse_username, workshop_username, good_state;
	// �ַ���
	private String[] good_states = { "<--��ѡ��-->","����", "�ۿ�", "�ѹ���", "������" };
	private String[] table_title = { "����", "��Ʒ��", "��Ʒ����", "��������", "��Ч��", "��Ʒ�������Ա", "�������������Ա", "��Ʒ״̬" };
	/**
	 * Create the frame.
	 */
	public SelectWarehouse() {
		setBounds(100, 100, 450, 300);

		setTitle("��ѯ�����Ϣ����");

		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximizable(true); // ����������󻯰�ť
		setIconifiable(true); // ����������С����ť
		setClosable(true); // �������йرհ�ť
		setResizable(true); // ���Ըı��С

		// ��ʼ��selectPane��viewPane
		selectPane = new JPanel();
		viewPane = new JPanel();

		

		// ��ʼ��splitPane
		splitPane = new JSplitPane();
		// ���÷ָ���λ��
		splitPane.setDividerLocation(100);
		// ��splitPane��������
		splitPane.setLeftComponent(selectPane);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		splitPane.setRightComponent(viewPane);
		splitPane.setPreferredSize(new Dimension(900, 350));
		
		//��ʼ��scrollPane
		scrollPane = new JScrollPane(splitPane);
		// ��this�����scrollPane
		this.add(scrollPane);

		// ��ʼ��textfield
		batch_id_field = new JTextField();
		batch_id_field.setColumns(20);
		good_id_field = new JTextField();
		good_id_field.setColumns(20);
		warehouse_user_id_field = new JTextField();
		warehouse_user_id_field.setColumns(20);
		workshop_user_id_field = new JTextField();
		workshop_user_id_field.setColumns(20);
		// ��ʼ��combobox
		good_state_combobox = new JComboBox(good_states);
		// ��ʼ��label
		batch_id_label = new JLabel("���ţ�");
		good_id_label = new JLabel("��Ʒ���ƣ�");
		warehouse_user_id_label = new JLabel("��Ʒ�������Ա��ţ�");
		workshop_user_id_label = new JLabel("�������������Ա��ţ�");
		good_state_label = new JLabel("��Ʒ״̬��");
		// ��ʼ��select_btn
		select_btn = new JButton("��ѯ");
		select_btn.setPreferredSize(new Dimension(230, 30));
		select_btn.addActionListener(this);
		// ��label,combobox,textfield����selectPane
		selectPane.add(batch_id_label);
		selectPane.add(batch_id_field);
		selectPane.add(good_id_label);
		selectPane.add(good_id_field);
		selectPane.add(warehouse_user_id_label);
		selectPane.add(warehouse_user_id_field);
		selectPane.add(workshop_user_id_label);
		selectPane.add(workshop_user_id_field);
		selectPane.add(good_state_label);
		selectPane.add(good_state_combobox);
		selectPane.add(select_btn);
		
		// ��ʼ��table
		tableModel = new DefaultTableModel(table_title, 17);
		table = new JTable(tableModel) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
		table.setRowHeight(50);
		// ��ʼ��scrollPane
		// ��table����scrollPane
		scrollPane = new JScrollPane(table);
		// ���ù�����һֱ��ʾ
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// ���ù�������С
		scrollPane.setPreferredSize(new Dimension(890, 350));
		// ������������viewPane
		viewPane.add(scrollPane);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		batch_id = batch_id_field.getText();
		good_name = good_id_field.getText();
		warehouse_username = warehouse_user_id_field.getText();
		workshop_username = workshop_user_id_field.getText();
		good_state = good_state_combobox.getSelectedItem().toString();
		/*
		 * ���ϲ�ѯ��Ϣ
		 */
		if(command.equals("��ѯ")) {
			String good_id = WarehouseService.findIdByGoodName(good_name);
			String warehouse_id = WarehouseService.findIdByAdminName(warehouse_username);	
			String workshop_id = WarehouseService.findIdByAdminName(workshop_username);
			int good_state_1 = Warehouse.stateToNum(good_state);
			ArrayList<Warehouse> data = WarehouseService.getWarehouseList(
					"batch_id",batch_id.equals("")?null:batch_id,
					"good_id",good_name.equals("")?null:good_id,
					"warehouse_user_id",warehouse_id.equals("")?null:warehouse_id,
					"workshop_user_id",workshop_id.equals("")?null:workshop_id,
					"good_state",good_state_1==-1?null:good_state_1
			);
			DefaultTableModel defaultTableModel = WarehouseService.getTableModelForWareHouse(data, table_title);
			table.setModel(defaultTableModel);
		}
	}

}
