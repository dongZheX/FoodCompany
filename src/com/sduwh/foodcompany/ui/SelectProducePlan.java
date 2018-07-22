package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.entity.Administrators;

public class SelectProducePlan extends JInternalFrame {


	

	private SelectProducePlan selectProducePlan = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField plan_id_field,good_id_field,planer_user_id_field;
	//combobox
	private JComboBox<String> plan_state_combobox;
	//label
	private JLabel plan_id_label,good_id_label,good_numlabel,deadline_label,plan_state_label,planer_user_id_label;
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
	/**
	 * Create the frame.
	 */
	public SelectProducePlan(Administrators user) {
		setBounds(100, 100, 450, 300);
		setTitle("��ѯ�����ƻ�����");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//����������󻯰�ť
	    setIconifiable(true);	//����������С����ť
	    setClosable(true);		//�������йرհ�ť
	    setResizable(true);		//���Ըı��С
	    
	    //��ʼ��selectPane��viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	   
	    //�ַ���
	    String [] good_state ={"δȷ��","��Ͷ������","���","ȡ��"};
	    String [] table_title = {"�����ƻ���","��Ʒ��","��Ʒ����","��Ҫ����","�ƻ�״̬","�����ƻ��Ʋ�����Ա"};
	    
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
	    plan_id_field = new JTextField();
	    plan_id_field.setColumns(25);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(25);
	    planer_user_id_field = new JTextField();
	    planer_user_id_field.setColumns(25);
	    //��ʼ��combobox
	    plan_state_combobox = new JComboBox(good_state);
	    //plan_state_combobox.setSize(new Dimension(00, 30));
	    //��ʼ��label
	    plan_id_label = new JLabel("�ƻ��ţ�");
	    good_id_label = new JLabel("��Ʒ��ţ�");
	    plan_state_label = new JLabel("�ƻ�״̬��");
	    planer_user_id_label = new JLabel("�����ƻ��Ʋ�����Ա��ţ�");
	    //��ʼ��select_btn
	    select_btn = new JButton("��ѯ");
	    select_btn.setPreferredSize(new Dimension(500, 30));
	    //��label,combobox,textfield����selectPane
	    selectPane.add(plan_id_label);
	    selectPane.add(plan_id_field);
	    selectPane.add(good_id_label);
	    selectPane.add(good_id_field);
	    selectPane.add(planer_user_id_label);
	    selectPane.add(planer_user_id_field);
	    selectPane.add(plan_state_label);
	    selectPane.add(plan_state_combobox);
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
	
	
	 
}
