package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class SelectOrderedFrame extends JInternalFrame implements ActionListener {

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectOrderedFrame frame = new SelectOrderedFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	private SelectOrderedFrame selectOrderedFrame = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField order_id_field,good_id_field,cus_user_id_field,sale_user_id_field;
	//combobox
	private JComboBox<String> order_type_combobox,order_state_combobox;
	//label
	private JLabel order_id_label,good_id_label,cus_user_id_label,sale_user_id_label,order_type_label,order_state_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	//checkbox
	private JCheckBox only_me_checkbox;
	//�����˵�
	private JPopupMenu m_popupMenu;
	
	 //�ַ���
//    order_id
//    good_id
//    cus_user_id
//    sale_user_id
//    order_unit_price
//    order_num
//    order_type
//    order_date
//    pick_up_time_start
//    pick_up_time_end
//    order_state
	
    private String [] good_state ={"<-��ѡ��->","δȷ��","��Ͷ������","���","ȡ��"};
    //private String [] order_type = {"<-��ѡ��->","�ֻ�(�ȸ�)","�ֻ�(��)","Ԥ��(�ȸ�)","Ԥ��()"}
    private String [] table_title = {"��������","��Ʒ���","�ͻ����","������Ա���","����","����","��������","��������","�����������","�����������","����״̬"};

	/**
	 * Create the frame.
	 */


	public SelectOrderedFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("��ѯ����������");
		
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
	    splitPane.setDividerLocation(100);
	    //��splitPane��������
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    
	    
	    //��this�����splitPane
	    this.add(splitPane);
	    
	    //��ʼ��only_me_checkbox
	    only_me_checkbox = new JCheckBox("ֻ���ҵ�");
	    
	    //��ʼ��textfield
	    

	    
	    order_id_field = new JTextField();
	    order_id_field.setColumns(25);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(25);
	    cus_user_id_field = new JTextField();
	    cus_user_id_field.setColumns(25);
	    sale_user_id_field = new JTextField();
	    sale_user_id_field.setColumns(25);
	    //��ʼ��combobox
	    order_type_combobox = new JComboBox();
	    order_state_combobox = new JComboBox ();
	    //��ʼ��label
	    order_id_label = new JLabel("��������");
	    good_id_label = new JLabel("��Ʒ���");
	    cus_user_id_label = new JLabel("�ͻ����");
	    sale_user_id_label = new JLabel("������Ա���");
	    order_type_label = new JLabel("��������");
	    order_state_label = new JLabel("����״̬");
	    //��ʼ��select_btn
	    select_btn = new JButton("��ѯ");
	    select_btn.setPreferredSize(new Dimension(200, 30));
	    select_btn.addActionListener(this);
	    //��label,combobox,textfield����selectPane
	    selectPane.add(order_id_label);
	    selectPane.add(order_id_field);
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(cus_user_id_label);
	    selectPane.add(cus_user_id_field);
	    selectPane.add(sale_user_id_label);
	    selectPane.add(sale_user_id_field);
	    selectPane.add(order_type_label);
	    selectPane.add(order_type_combobox);
	    selectPane.add(order_state_label);
	    selectPane.add(order_state_combobox);
	    selectPane.add(only_me_checkbox);
	    selectPane.add(select_btn);
	  	    	   
	    //��ʼ��table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel);
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
		
	}

}
