package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.List;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.bill.GoodsTableData;
import com.sduwh.foodcompany.bill.SaleBll;

import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class MDISaleFrame extends JInternalFrame implements ActionListener{

	private JFrame frame;
	private  JPanel panel;
	private  JTextField id_name ;
	private JTextField id_name_textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel label_sumMoney;
	GoodsTableData[] data; /*�������Ʒ�ı��*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDISaleFrame window = new MDISaleFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MDISaleFrame() {
		initialize();
		setMaximizable(true);	//����������󻯰�ť
	    setIconifiable(true);	//����������С����ť
	    setClosable(true);		//�������йرհ�ť
	    setResizable(true);		//���Ըı��С
		panel.setLayout(null);
		
<<<<<<< HEAD
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u540D\u79F0\u6216ID");
		lblNewLabel.setBounds(34, 9, 111, 15);
=======
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u59D3\u540D\u6216ID\uFF1A");
		lblNewLabel.setBounds(10, 9, 111, 15);
>>>>>>> 01ff217766b7646fef9beaf02ab4a569f5509e4e
		panel.add(lblNewLabel);
		id_name_textField = new JTextField();
		id_name_textField.setBounds(104, 6, 124, 21);
		panel.add(id_name_textField);
		id_name_textField.setColumns(0);
		
		/*ButtonGroup*/
		ButtonGroup bg  = new ButtonGroup(); 
		ButtonGroup bg_sale2 = new ButtonGroup();
		
		
		/*������ʽ��ѡ��ť*/
		
		/*�������͵�ѡ��ť*/
		JRadioButton button_name = new JRadioButton("\u6309\u540D\u79F0\u641C\u7D22");
		button_name.setBackground(Color.WHITE);
		button_name.setBounds(234, 5, 111, 23);
		button_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(button_name);
		bg.add(button_name);
		button_name.setSelected(true);
		
		JRadioButton rbutton_id = new JRadioButton("��ID����");
		rbutton_id.setBackground(Color.WHITE);
		rbutton_id.setBounds(353, 5, 103, 23);
		panel.add(rbutton_id);
		bg.add(rbutton_id);
		
		//JButton�ֻ�
		JRadioButton rbutton_1 = new JRadioButton("�ֻ�(�ȸ�)");
		rbutton_1.setBackground(Color.WHITE);
		rbutton_1.setBounds(472, 5, 71, 23);
		rbutton_1.setSelected(true);
		panel.add(rbutton_1);
		bg_sale2.add(rbutton_1);
		
		//JButton�ֻ���
		JRadioButton rbutton_2 = new JRadioButton("�ֻ�(��)");
		rbutton_2.setBackground(Color.WHITE);
		rbutton_2.setBounds(554, 5, 97, 23);
		panel.add(rbutton_2);
		bg_sale2.add(rbutton_2);
		
		//JButtonԤ��
		JRadioButton rbutton_3 = new JRadioButton("Ԥ��(�ȸ�)");
		rbutton_3.setBackground(Color.WHITE);
		rbutton_3.setBounds(678, 5, 79, 23);
		panel.add(rbutton_3);
		
		//JButtonԤ����
<<<<<<< HEAD
		JRadioButton rbutton_4 = new JRadioButton("Ԥ��(��)");
		rbutton_4.setBackground(Color.WHITE);
		rbutton_4.setBounds(813, 5, 97, 23);
		panel.add(rbutton_4);
		bg_sale2.add(rbutton_4);
		
		//��ѯ��Ʒ
		JButton button_search = new JButton("��ʾ��Ʒ");
		button_search.setBounds(940, 5, 124, 23);
=======
		JRadioButton r_button4 = new JRadioButton("\u9884\u5B9A\uFF08\u540E\u4ED8\uFF09");
		r_button4.setBackground(Color.WHITE);
		r_button4.setBounds(776, 5, 97, 23);
		panel.add(r_button4);
		bg_sale2.add(r_button4);
		
		//��ѯ��Ʒ
		JButton button_search = new JButton("\u663E\u793A\u5546\u54C1");
		button_search.setBounds(914, 5, 124, 23);
>>>>>>> 01ff217766b7646fef9beaf02ab4a569f5509e4e
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = id_name.getText();
				if(s.equals("")||s.equals(null))
					JOptionPane.showMessageDialog(panel, "����д�ͻ���Ϣ","��ʾ",JOptionPane.WARNING_MESSAGE);
				else
				{
					data = SaleBll.getGoods();
					for(int i = 0; i < data.length; ++i)
						tableModel.addRow(data[i].toArray());
				}
			}
		});
		panel.add(button_search);
		
	
		JLabel label = new JLabel("�����ܽ��");
		label.setBounds(144, 383, 98, 15);
		panel.add(label);
		
		label_sumMoney = new JLabel("");
		label_sumMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		label_sumMoney.setBounds(240, 383, 103, 15);
		panel.add(label_sumMoney);
		
		JLabel label_1 = new JLabel("Ԫ");
		label_1.setBounds(353, 383, 47, 15);
		panel.add(label_1);
		
		//���ɶ���
		JButton button_createOrdered = new JButton("���ɶ���");
		button_createOrdered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*��ȡ�µ���ʽ*/
				String Type = null;
				if(rbutton_1.isSelected())	Type = rbutton_1.getText();
				if(rbutton_2.isSelected())	Type = rbutton_2.getText();
				if(rbutton_3.isSelected())	Type = rbutton_3.getText();
				if(rbutton_4.isSelected())	Type = rbutton_4.getText();
				
				int goodNum = 0;
				for(int i = 0; i < data.length; ++i) {
					int num = Integer.parseInt((String)table.getValueAt(i, 0));
					if(num != 0) {
						++goodNum;
					}
				}
				
				/*��Ҫ�������Ʒ����һ��������*/
				GoodsTableData[] goodsData = new GoodsTableData[goodNum];
				goodNum = 0;
				for(int i = 0; i < data.length; ++i) {
					int num = Integer.parseInt((String)table.getValueAt(i, 0));
					if(num != 0) {
						goodsData[goodNum++] = data[i];
					}
				}
				
				/*private String order_id;
				private String good_id;
				private String cus_user_id;
				private String sale_user_id;
				private float order_unit_price;
				private int order_num;
				private int order_type;
				private Date order_date;
				private Date pick_up_time_start;
				private Date pick_up_time_end ;
				private int order_state;*/
				
				/*private int needNumber;
				private int nowNumber;
				private String goodsID;
				private String goodsName;
				private float value;*/
				/*����ز�������ȥ*/
				//goodsData, cus_user_id, sale_user_id, order_type, order_date, pick_up_time_start, pick_up_time_end, order_state
				
			}
		});
		button_createOrdered.setBounds(898, 373, 124, 35);
		panel.add(button_createOrdered);
	
		//ѡ�������������
		String[] year = {"2018", "2019", "2020", "2021", "2022"};
		String[] month = {"һ��", "����", "����", "����", "����","����", "����", "����", "����", "ʮ��","ʮһ��","ʮ����"};
		String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
				"26","27","28","29","30","31"};
		
		JComboBox <String>comboBox_year = new JComboBox<String>();
		comboBox_year.setBounds(507, 380, 71, 21);
		for(int i = 0 ;i < year.length;i++)
			comboBox_year.addItem(year[i]);
		panel.add(comboBox_year);
		
		JComboBox <String>comboBox_month = new JComboBox<String>();
		comboBox_month.setBounds(622, 380, 66, 21);
		for(int i = 0 ;i < month.length;i++)
			comboBox_month.addItem(month[i]);
		panel.add(comboBox_month);
		
		JComboBox<String> comboBox_day = new JComboBox<String>();
		comboBox_day.setBounds(740, 380, 73, 21);
		for(int i = 0 ;i < day.length;i++)
			comboBox_day.addItem(day[i]);
		panel.add(comboBox_day);
		
		JLabel label_2 = new JLabel("��");
		label_2.setBounds(588, 383, 24, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("��");
		label_3.setBounds(829, 383, 24, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("��");
		label_4.setBounds(698, 383, 24, 15);
		panel.add(label_4);
		
		
		
		
		
		 //��ʼ��table
		String[] table_title = {"����", "��Ʒ���", "��Ʒ����", "����", "�ֻ�����"};

	    tableModel = new DefaultTableModel(table_title, 20) {
			 public boolean isCellEditable(int row, int column) {
				 	if(column != 0) 
				 		return false;
				 	return true;
				  }
		};
		
	    table = new JTable(tableModel);
	    table.setRowHeight(35);
	    table.setEditingColumn(0);
	    
	    
	    
	    //��ʼ��scrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 74, 1003, 277);
	    //��table����scrollPane
	   
	    //���ù�����һֱ��ʾ
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //���ù�������С
	    scrollPane.setPreferredSize(new Dimension(900,400));
	    scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		JLabel label_5 = new JLabel("|");
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(462, 9, 24, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("�����������");
		label_6.setBounds(397, 383, 98, 15);
		panel.add(label_6);
		
		
	 
	   
		this.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		id_name = new JTextField();
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
