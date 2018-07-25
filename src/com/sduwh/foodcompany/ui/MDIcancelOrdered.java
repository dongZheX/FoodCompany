package com.sduwh.foodcompany.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.IdToName;
import com.sduwh.foodcompany.bill.SaleBll;
import com.sduwh.foodcompany.bill.SelectOrderedBll;
import com.sduwh.foodcompany.bill.SystemBll;
import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.entity.Ordered;

public class MDIcancelOrdered extends JInternalFrame implements ActionListener{



	
	private MDIcancelOrdered selectOrderedFrame = this;
	
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
	//�����˵�
	private JPopupMenu m_popupMenu;
	
	 //�ַ���
    private String [] order_state ={"<-��ѡ��->","δȷ��","��Ͷ������","���","ȡ��"};
    private String [] order_type = {"<-��ѡ��->","�ֻ�(�ȸ�)","�ֻ�(��)","Ԥ��(�ȸ�)","Ԥ��()"};
    private String [] table_title = {"��������","��Ʒ���","�ͻ����","������Ա���","����","����","��������","��������","�����������","�����������","����״̬"};
    private JLabel label;
    private JTextField textField;

	/**
	 * Create the frame.
	 */


	public MDIcancelOrdered() {
		setBounds(100, 100, 1139, 449);
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
	    getContentPane().add(splitPane);
	    
	    
	    //��ʼ��textfield 
	    order_id_field = new JTextField();
	    order_id_field.setColumns(20);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(15);
	    cus_user_id_field = new JTextField();
	    cus_user_id_field.setColumns(20);
	    sale_user_id_field = new JTextField();
	    sale_user_id_field.setColumns(20);
	    //��ʼ��combobox
	    order_type_combobox = new JComboBox(order_type);
	    order_state_combobox = new JComboBox (order_state);
	    //��ʼ��label
	    order_id_label = new JLabel("��������");
	    good_id_label = new JLabel("��Ʒ��");
	    cus_user_id_label = new JLabel("�ͻ����");
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
	    
	    label = new JLabel("\u5BA2\u6237\u59D3\u540D");
	    selectPane.add(label);
	    
	    textField = new JTextField();
	    selectPane.add(textField);
	    textField.setColumns(15);
	    selectPane.add(cus_user_id_label);
	    selectPane.add(cus_user_id_field);
	    sale_user_id_label = new JLabel("������Ա��");
	    selectPane.add(sale_user_id_label);
	    selectPane.add(sale_user_id_field);
	    order_type_label = new JLabel("��������");
	    selectPane.add(order_type_label);
	    selectPane.add(order_type_combobox);
	    selectPane.add(order_state_label);
	    selectPane.add(order_state_combobox);
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
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    //��ʼ��scrollPane
	    //��table����scrollPane
	    scrollPane = new JScrollPane(table);
	    //���ù�����һֱ��ʾ
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //���ù�������С
	    scrollPane.setPreferredSize(new Dimension(1100,350));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("��ѯ")){
			btn_select_action();
		}
	}
	
	public void btn_select_action(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String order_id = order_id_field.getText();
		String good_id = WarehouseService.findIdByGoodName(good_id_field.getText());
		System.out.println("good_id+"+good_id);
		String cus_user_id = cus_user_id_field.getText();
		String sale_user_id = WarehouseService.findIdByAdminName(sale_user_id_field.getText());
		String order_type_str = order_type_combobox.getSelectedItem().toString();
		String order_state_str = order_state_combobox.getSelectedItem().toString();
		int order_type_int = Ordered.order_type_toInt(order_type_str);
		int order_state_int = Ordered.order_state_toInt(order_state_str);
		String [] key = {
				"order_id",order_id.equals("")?null:order_id,
				"good_id",good_id.equals("")?null:good_id,
				"cus_user_id",cus_user_id.equals("")?null:cus_user_id,
				"sale_user_id",sale_user_id.equals("")?null:sale_user_id,
				"order_type",order_type_int == -1?null:order_type_int+"",
				"order_state",order_state_int == -1?null:order_state_int+""
				};
		ArrayList<Ordered> order_list = SelectOrderedBll.select_ordered(key);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for (int i = 0; i < order_list.size(); i++){
			Ordered ordered = order_list.get(i);
			String []datas = {
					ordered.getOrder_id(),
					IdToName.Goods_select(ordered.getGood_id()),
					ordered.getCus_user_id(),
					ordered.getSale_user_id(),
					ordered.getOrder_unit_price()+"",
					ordered.getOrder_num()+"",
					simpleDateFormat.format(ordered.getOrder_date()),
					simpleDateFormat.format(ordered.getPick_up_time_start()),
					simpleDateFormat.format(ordered.getPick_up_time_end()),
					Ordered.order_type_toStr(ordered.getOrder_type()),
					Ordered.order_state_toStr(ordered.getOrder_state())
			};
			defaultTableModel.addRow(datas);
		}
		table.setModel(defaultTableModel);
		
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
	        JMenuItem MenItem_alter1 = new JMenuItem();
	        JMenuItem MenItem_alter2 = new JMenuItem();
	        //��� �޸� ����item
	        MenItem_alter1.setText("�˻� ");
	        MenItem_alter2.setText("�˶��ö���������Ʒ ");
	        //��Ӽ�����
	        MenItem_alter1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //�ò�����Ҫ������
	            	/*
	            	 * ��ȡѡ�е�order_id
	            	 * ��ʱע�͵�,��Ϊûʵ�ʲ�ѯʱ�ᱨ��
	            	 */
	            	int row = table.getRowCount();
	            	String order_id = table.getModel().getValueAt(row, 0).toString();
	            	String good_name = "";
	            	String good_id = WarehouseService.findIdByGoodName(good_name);
	            	
	            	/*
	            	 * �����޸��û���Ϣdialog
	            	 */
	            	SaleBll.cancelOrder(order_id, good_id);
	            	
	            	//����һ�������ֶ�
	            	
	            	
	            	
	            	AlterDialog alterDialog = new AlterDialog(order_id);
	        	    alterDialog.setLocation(new Point(width*1/4, height*1/5));
	        		alterDialog.setAlwaysOnTop(true);
	        		alterDialog.show();	
	            }
	        });
	        
	        MenItem_alter2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	int row = table.getRowCount();
	            	String order_id = table.getModel().getValueAt(row, 0).toString();
	            	
	            	/*
	            	 * �����޸��û���Ϣdialog
	            	 */
	            	SaleBll.cancelOrder(order_id, null);
	            }
	          });
	        //��ӵ���item
	        m_popupMenu.add(MenItem_alter1);
	        m_popupMenu.add(MenItem_alter2);
		}
}
