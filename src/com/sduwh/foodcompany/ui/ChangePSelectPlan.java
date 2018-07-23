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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import com.sduwh.foodcompany.bill.SelectProducePlanBill;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.ProducePlan;

public class ChangePSelectPlan extends JInternalFrame implements ActionListener {


	
	
	private ChangePSelectPlan selectProducePlan = this;
	
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
	private Administrators muser;
	
	//�ַ���
    private String [] good_state ={"δȷ��","��Ͷ������","���","ȡ��"};
    private String [] table_title = {"�����ƻ���","��Ʒ��","��Ʒ����","��Ҫ����","�ƻ�״̬","�����ƻ��Ʋ�����Ա"};
	/**
	 * Create the frame.
	 */
	public ChangePSelectPlan(Administrators user) {
		setBounds(100, 100, 450, 300);
		setTitle("�޸Ŀ����Ϣ����");
		muser = user; 
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
	    select_btn.addActionListener(this);
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
	    
	    
	    createPopupMenu();
	    
	    table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
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
	}
	  //����������ť
		private void createPopupMenu() {
	        m_popupMenu = new JPopupMenu();
	        if(muser.getAdm_power()==7) {
	        	JMenuItem planMenItem_alter = new JMenuItem();
	        	planMenItem_alter.setText("�޸�");
	        	planMenItem_alter.addActionListener(new java.awt.event.ActionListener() {
	        		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        			//�ò�����Ҫ������
	        			int row = table.getSelectedRow();
	        			String plan_id = table.getModel().getValueAt(row, 0).toString();
	        			AlterPlanDialog alterPlanDialog = new AlterPlanDialog(plan_id);
	        			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        			int width = (int)screensize.getWidth();
	        			int height = (int)screensize.getHeight();
	        			alterPlanDialog.setLocation(new Point(width*1/4, height*1/3));
	        			alterPlanDialog.setAlwaysOnTop(true);
	        	    	alterPlanDialog.show();
	        	    	
	        		}
	        	});
	        
	        	JMenuItem planMenuItem_del = new JMenuItem("ȡ��");
	        	planMenuItem_del.addActionListener(new ActionListener() {
				
	        		@Override
	        		public void actionPerformed(ActionEvent e) {
	        			// TODO Auto-generated method stub
	        			int row = table.getSelectedRow();
	        			String plan_id = table.getModel().getValueAt(row, 0).toString();
//	        			new JOptionPane().showMessageDialog(selectProducePlan, "ȷ��ȡ��?");
	        			JOptionPane sure_dialog;
	        			sure_dialog = new JOptionPane();
	        			int i = sure_dialog.showConfirmDialog(selectProducePlan, "ȷ��ȡ��?","��ʾ",JOptionPane.YES_NO_OPTION);
	        			if(i == 0){
	        				if(SelectProducePlanBill.select_state(plan_id)){
	        					if(SelectProducePlanBill.cancel_plan(plan_id)){
	        						JOptionPane.showMessageDialog(selectProducePlan,"ȡ���ɹ�!");
	        					}
	        					else
	        						JOptionPane.showMessageDialog(selectProducePlan,"ȡ��ʧ��,����ϵ����Ա!");	
	        				}
	        				else 
	        					JOptionPane.showMessageDialog(selectProducePlan,"ȡ��ʧ��,�������ƻ�Ϊ����ȡ��״̬!");
	        			}
					
	        		}
	        	});
	        	m_popupMenu.add(planMenItem_alter);
	        	m_popupMenu.add(planMenuItem_del);
	        }else if(muser.getAdm_power()==6) {
	        	
	        	JMenuItem planMenItem_confirm = new JMenuItem();
	        	planMenItem_confirm.setText("ȷ��");
	        	planMenItem_confirm.addActionListener(new java.awt.event.ActionListener() {
	        		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        			//�ò�����Ҫ������
	        			
	        			int result = new JOptionPane().showConfirmDialog(selectProducePlan, "��ȷ��Ҫȷ����");
	        			if(result == JOptionPane.YES_OPTION) {
	        				
	        			}else {
	        				
	        			}
	        		}
	        	});
	        
	        	JMenuItem planMenuItem_cancel = new JMenuItem("ȡ��");
	        	planMenuItem_cancel.addActionListener(new ActionListener() {
				
	        		@Override
	        		public void actionPerformed(ActionEvent e) {
	        			// TODO Auto-generated method stub
	        			int result = new JOptionPane().showConfirmDialog(selectProducePlan, "��ȷ��Ҫȡ����");
	        			if(result == JOptionPane.YES_OPTION) {
	        				
	        			}else {
	        				
	        			}
	        			
	        		}
	        	});
	        	m_popupMenu.add(planMenItem_confirm);
	        	m_popupMenu.add(planMenuItem_cancel);
	        }
	      
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("��ѯ")){
			select_btn_action();
		}
		
	}
	
	public void select_btn_action() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int plan_state_int = ProducePlan.plan_state_toInt(plan_state_combobox.getSelectedItem().toString());
		String [] select_key = {
				"plan_id",plan_id_field.getText().equals("")?null:plan_id_field.getText().toString(),
				"good_id",good_id_field.getText().equals("")?null:good_id_field.getText().toString(),
				"planer_user_id",planer_user_id_field.getText().equals("")?null:planer_user_id_field.getText().toString(),
				"plan_state",plan_state_int == -1?null:plan_state_int+""
				};
		ArrayList<ProducePlan> planArr = SelectProducePlanBill.select_ProducePlan(select_key);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for(int i =0;i<planArr.size();i++){
			ProducePlan plan = planArr.get(i);
			defaultTableModel.addRow(new String[]{
					plan.getPlan_id(),
					plan.getGood_id(),
					plan.getGood_num()+"",
					simpleDateFormat.format(plan.getDeadline()),
					ProducePlan.plan_state_toString(plan.getPlan_state()),
					plan.getPlaner_user_id()
			});			
		}
		
		table.setModel(defaultTableModel);
		
	}

}
