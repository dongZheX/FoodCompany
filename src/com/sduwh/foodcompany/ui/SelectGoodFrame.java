package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.SelectGoodBll;
import com.sduwh.foodcompany.entity.Goods;

public class SelectGoodFrame extends JInternalFrame implements ActionListener {

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectGoodFrame frame = new SelectGoodFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
//	good_id
//	good_name
//	good_standard
//	good_cost
//	good_expiration_date

	private SelectGoodFrame selectGoodFrame = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField good_id_field,good_name_field;
	//label
	private JLabel good_id_label,good_name_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	//�����˵�
	private JPopupMenu m_popupMenu;
	
	 //�ַ���
    private String [] table_title = {"��Ʒ���","����","���","�ɱ�","������"};


	/**
	 * Create the frame.
	 */
	public SelectGoodFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("��ѯ��Ʒ����");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//����������󻯰�ť
	    setIconifiable(true);	//����������С����ť
	    setClosable(true);		//�������йرհ�ť
	    setResizable(true);		//���Ըı��С
	    
	    //��ʼ��selectPane��viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	   
	   
	    
	    //��ʼ��splitPane
	    splitPane = new JSplitPane();
	    //���÷ָ���λ��
	    splitPane.setDividerLocation(50);
	    //��splitPane��������
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    
	    
	    //��this�����splitPane
	    this.add(splitPane);
	    
	    
	    //��ʼ��textfield
	    

	    
	    good_id_field = new JTextField();
	    good_id_field.setColumns(20);
	    good_name_field = new JTextField();
	    good_name_field.setColumns(20);
	    //��ʼ��label
	    good_id_label = new JLabel("��Ʒ��");
	    good_name_label = new JLabel("��Ʒ��");
	    //��ʼ��select_btn
	    select_btn = new JButton("��ѯ");
	    select_btn.setPreferredSize(new Dimension(200, 30));
	    select_btn.addActionListener(this);
	    //��label,combobox,textfield����selectPane
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(good_name_label);
	    selectPane.add(good_name_field);
	    selectPane.add(select_btn);
	  	    	   
	    //��ʼ��table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
	    table.setRowHeight(30);	
	    
	    //��ʼ��scrollPane
	    //��table����scrollPane
	    scrollPane = new JScrollPane(table);
	    //���ù�����һֱ��ʾ
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //���ù�������С
	    scrollPane.setPreferredSize(new Dimension(900,350));
	    //������������viewPane
	    viewPane.add(scrollPane);
	    this.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("��ѯ")){
			select_btn_action();
		}
		
	}
	
	public void select_btn_action(){
		String []key = {
				"good_id",good_id_field.getText().equals("")?null:good_id_field.getText(),
				"good_name",good_name_field.getText().equals("")?null:good_name_field.getText()};
		ArrayList<Goods> good_list = SelectGoodBll.select_good(key);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for(int i=0;i<good_list.size();i++){
			Goods goods = good_list.get(i);
			String [] datas = {
					goods.getGood_id(),
					goods.getGood_name(),
					goods.getGood_standard().equals("")?"��":goods.getGood_standard(),
					goods.getGood_cost()+" RMB",
					goods.getGood_expiration_date()+"��"
			};
			defaultTableModel.addRow(datas);
		}
		table.setModel(defaultTableModel);
	}

}
