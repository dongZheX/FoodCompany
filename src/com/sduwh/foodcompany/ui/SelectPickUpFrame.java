package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class SelectPickUpFrame extends JInternalFrame implements ActionListener{

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectPickUpFrame frame = new SelectPickUpFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField pick_up_id_field;
	//combobox
	private JComboBox<String> pick_up_state_combobox;
	//label
	private JLabel pick_up_id_lable,pick_up_state_label;
	//checkbox
	private JCheckBox only_me_checkbox;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the frame.
	 */
	public SelectPickUpFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("��ѯ�������Ϣ����");
		
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
	    //�ַ���
	    String [] pick_up_state ={"δ���","�����","�˻�����"};
	    String [] table_title = {"��������","�����״̬","�����˱��"};
	    //��this�����splitPane
	    this.add(splitPane);
	    //��splitPane��������
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    //���÷ָ���λ��
	    splitPane.setLastDividerLocation(50);
	    
	    //��ʼ��textfield
	    pick_up_id_field = new JTextField();
	    pick_up_id_field.setColumns(30);;
	    //��ʼ��combobox
	    pick_up_state_combobox = new JComboBox(pick_up_state);
	    //��ʼ��label
	    pick_up_id_lable = new JLabel("�����ţ�");
	    pick_up_state_label = new JLabel("���״̬��");
	    //��ʼ��only_me_checkbox
	    only_me_checkbox = new JCheckBox("ֻ���ҵ�");
	    //��ʼ��select_btn
	    select_btn = new JButton("��ѯ");
	    //��label,combobox,textfield����selectPane
	    selectPane.add(pick_up_id_lable);
	    selectPane.add(pick_up_id_field);
	    selectPane.add(pick_up_state_label);
	    selectPane.add(pick_up_state_combobox);
	    selectPane.add(only_me_checkbox);
	    selectPane.add(select_btn);
	    
	    //��ʼ��table
	    Object[][] tableData =
	    	  {
	    	    new Object[]{"������" , 29 , "Ů"},
	    	    new Object[]{"�ո�����", 56 , "��"},
	    	    new Object[]{"���", 35 , "��"},
	    	    new Object[]{"Ū��", 18 , "Ů"},
	    	    new Object[]{"��ͷ" , 2 , "��"}
	    	  };
	   tableModel = new DefaultTableModel(tableData,table_title);
	   table = new JTable(tableModel);
	   table.setRowHeight(50);
	   
	   //��table����viewPane
	   viewPane.add(table);
	    this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
