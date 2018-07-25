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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.CustomerAlterData;
import com.sduwh.foodcompany.bill.SaleBll;
import com.sduwh.foodcompany.entity.Customer;

public class AlterCusInfo extends JInternalFrame{


	//�����������
	private AlterCusInfo alterCusInfo = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	//textfield
	private JTextField user_id_field,user_name_field;
	//label
	private JLabel user_id_lable,user_name_lable;
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
	//table_title
	private String [] table_title = {"�ͻ����","�ͻ�����","��ϵ��ʽ","�ͻ��ȼ�","��������","�ͻ���ַ"};

	/**
	 * Create the frame.
	 */
	public AlterCusInfo() {
		/*
		 * ��ʼ������
		 */
		setBounds(100, 100, 827, 651);
		setTitle("�޸Ŀͻ���Ϣ����");
		
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
	    getContentPane().add(splitPane);
	    
	    //��ʼ��textfield
	    user_id_field = new JTextField();
	    user_id_field.setColumns(25);
	    user_name_field = new JTextField();
	    user_name_field.setColumns(25);
	    //��ʼ��label
	    user_id_lable = new JLabel("�ͻ����");
	    user_name_lable = new JLabel("�ͻ�����");
	    //��ʼ��select_btn
	    select_btn = new JButton("��ѯ");
	    select_btn.setPreferredSize(new Dimension(150, 30));
	    select_btn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String user_id = user_id_field.getText();
	    		String user_name = user_name_field.getText();
	    		CustomerAlterData[] data = SaleBll.searchCustomer(user_id, user_name);
	    		DefaultTableModel model = new DefaultTableModel();
	    		model.setColumnIdentifiers(table_title);
	    		for(int i = 0; i < data.length; ++i)
	    			model.addRow(data[i].getArray());
	    		table.setModel(model);
	    	}
	    });
	    //��label,combobox,textfield����selectPane
	    selectPane.add(user_id_lable);
	    selectPane.add(user_id_field);
	    selectPane.add(user_name_lable);
	    selectPane.add(user_name_field);
	    selectPane.add(select_btn);
	  	    	   
	    //��ʼ��table
	    tableModel = new DefaultTableModel(table_title,17) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
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
	    //��������
	    createPopupMenu();
	    //ע������һ�table�¼�
	    table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
		            //ͨ�����λ���ҵ����Ϊ����е���
		            int focusedRowIndex = table.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //�������ѡ����Ϊ��ǰ�Ҽ��������
		            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //�����˵�
		            m_popupMenu.show(table, e.getX(), e.getY());
		        }
			}
		});
	    
	    this.setVisible(true);

	}

	//��������
	private void createPopupMenu() {
		//��ȡ��Ļ
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
	    int height = (int)screensize.getHeight();
        m_popupMenu = new JPopupMenu();
        /*
         * �����˵�����
         */
        JMenuItem MenItem_alter = new JMenuItem();
        //��� �޸� ����item
        MenItem_alter.setText("�޸� ");
        //��Ӽ�����
        MenItem_alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //�ò�����Ҫ������
            	/*
            	 * ��ȡѡ�е�cus_user_id
            	 * ��ʱע�͵�,��Ϊûʵ�ʲ�ѯʱ�ᱨ��
            	 */
            	//int row = table.getRowCount();
            	//String cus_user_id = table.getModel().getValueAt(row, 0).toString();
            	/*
            	 * �����޸��û���Ϣdialog
            	 */
            	
            	
            	//����һ�������ֶ�
            	String cus_user_id = "";
            	//
            	
            	
            	AlterDialog alterDialog = new AlterDialog(cus_user_id);
        	    alterDialog.setLocation(new Point(width*1/4, height*1/5));
        		alterDialog.setAlwaysOnTop(true);
        		alterDialog.show();	
            }
        });
        //��ӵ���item
        m_popupMenu.add(MenItem_alter);
	}

}
