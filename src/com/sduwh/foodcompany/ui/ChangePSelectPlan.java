package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.sduwh.foodcompany.bill.IdToName;
import com.sduwh.foodcompany.bill.SelectProducePlanBll;
import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.ProducePlan;

public class ChangePSelectPlan extends JInternalFrame implements ActionListener {


	private Administrators user;
	
	private ChangePSelectPlan selectProducePlan = this;
	
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
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
	//弹出菜单
	private JPopupMenu m_popupMenu;
	private Administrators muser;
	
	//字符串
    private String [] good_state ={"<-请选择->","未确认","已投入生产","入库","取消"};
    private String [] table_title = {"生产计划号","商品号","商品数量","需要日期","计划状态","生产计划科操作人员"};
	/**
	 * Create the frame.
	 */
	public ChangePSelectPlan(Administrators user) {
		this.user = user;
		setBounds(100, 100, 450, 300);
		setTitle("修改生产计划信息窗口");
		muser = user; 
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
	    
	    //初始化selectPane和viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	   
	    
	    
	    //初始化splitPane
	    splitPane = new JSplitPane();
	    //设置分割线位置
	    splitPane.setDividerLocation(100);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    
	    
	    //在this中添加splitPane
	    this.add(splitPane);
	    
	    //初始化only_me_checkbox
	    only_me_checkbox = new JCheckBox("只看我的");
	    only_me_checkbox.addActionListener(this);
	    //初始化textfield
	    plan_id_field = new JTextField();
	    plan_id_field.setColumns(25);
	    good_id_field = new JTextField();
	    good_id_field.setColumns(25);
	    planer_user_id_field = new JTextField();
	    planer_user_id_field.setColumns(25);
	    //初始化combobox
	    plan_state_combobox = new JComboBox(good_state);
	    //plan_state_combobox.setSize(new Dimension(00, 30));
	    //初始化label
	    plan_id_label = new JLabel("计划号：");
	    good_id_label = new JLabel("商品名：");
	    plan_state_label = new JLabel("计划状态：");
	    planer_user_id_label = new JLabel("生产计划科操作人员编号：");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(500, 30));
	    select_btn.addActionListener(this);
	    //将label,combobox,textfield放入selectPane
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
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel);
	    table.setRowHeight(30);	  
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane = new JScrollPane(table);
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,350));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane);
	    
	    //tip
	    table.addMouseMotionListener(new MouseAdapter() {
	    	 public void mouseMoved(MouseEvent e) {  
	    	        int row=table.rowAtPoint(e.getPoint());  
	    	        int col=table.columnAtPoint(e.getPoint());  
	    	        if(row>-1 && col>-1){  
	    	        	Object object = table.getValueAt(row, col);
	    	        	if(object==null)return;
	    	            Object value= IdToName.Administrators_Select(object.toString());
	    	            if(null!=value && !"".equals(value))  
	    	                table.setToolTipText(value.toString());//悬浮显示单元格内容  
	    	            else  
	    	                table.setToolTipText(null);//关闭提示  
	    	        }  
	    	    }  

		});
	    
	    
	    
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
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = table.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            m_popupMenu.show(table, e.getX(), e.getY());
		        }
				
			}
		});
	    
	    
	    this.setVisible(true);
	}
	  //创建弹出按钮
		private void createPopupMenu() {
	        m_popupMenu = new JPopupMenu();
	        if(muser.getAdm_power()==Administrators.PRODUCE_PLAN_ADMIN_NUM || muser.getAdm_power() == Administrators.BEST) {
	        	JMenuItem planMenItem_alter = new JMenuItem();
	        	planMenItem_alter.setText("修改");
	        	planMenItem_alter.addActionListener(new java.awt.event.ActionListener() {
	        		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        			//该操作需要做的事
	        			int row = table.getSelectedRow();
	        			String plan_id = table.getModel().getValueAt(row, 0).toString();
	        			AlterPlanDialog alterPlanDialog = new AlterPlanDialog(plan_id);
	        			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        			int width = (int)screensize.getWidth();
	        			int height = (int)screensize.getHeight();
	        			alterPlanDialog.setLocation(new Point(width*1/4, height*1/3));
	        			alterPlanDialog.setAlwaysOnTop(true);
	        	    	alterPlanDialog.show();
	        	    	/*
						 * 再执行一次查询
						 */
						select_btn_action();
	        	    	
	        		}
	        	});
	        
	        	JMenuItem planMenuItem_del = new JMenuItem("取消");
	        	planMenuItem_del.addActionListener(new ActionListener() {
				
	        		@Override
	        		public void actionPerformed(ActionEvent e) {
	        			// TODO Auto-generated method stub
	        			int row = table.getSelectedRow();
	        			String plan_id = table.getModel().getValueAt(row, 0).toString();
//	        			new JOptionPane().showMessageDialog(selectProducePlan, "确定取消?");
	        			JOptionPane sure_dialog;
	        			sure_dialog = new JOptionPane();
	        			int i = sure_dialog.showConfirmDialog(selectProducePlan, "确定取消?","提示",JOptionPane.YES_NO_OPTION);
	        			if(i == 0){
	        				if(SelectProducePlanBll.select_state(plan_id)){
	        					if(SelectProducePlanBll.cancel_plan(plan_id)){
	        						JOptionPane.showMessageDialog(selectProducePlan,"取消成功!");
	        						/*
	        						 * 再执行一次查询
	        						 */
	        						select_btn_action();
	        					}
	        					else
	        						JOptionPane.showMessageDialog(selectProducePlan,"取消失败,请联系管理员!");	
	        				}
	        				else 
	        					JOptionPane.showMessageDialog(selectProducePlan,"取消失败,该生产计划为不可取消状态或已为取消状态!");
	        			}
					
	        		}
	        	});
	        	m_popupMenu.add(planMenItem_alter);
	        	m_popupMenu.add(planMenuItem_del);
	        } if(muser.getAdm_power()==Administrators.PRODUCE_ADMIN_NUM || muser.getAdm_power() == Administrators.BEST) {
	        	
	        	JMenuItem planMenItem_confirm = new JMenuItem();
	        	planMenItem_confirm.setText("确认");
	        	planMenItem_confirm.addActionListener(new java.awt.event.ActionListener() {
	        		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        			//该操作需要做的事
	        			int row = table.getSelectedRow();
        				int result = new JOptionPane().showConfirmDialog(selectProducePlan, "您确定要确定吗？");
        				if(result == JOptionPane.YES_OPTION) {
        					Object object = table.getModel().getValueAt(row, 4);
        					if(object==null)
        						return;
        					String state = object.toString();
        					if(!state.equals("未确认")) {
        						JOptionPane.showMessageDialog(selectProducePlan,"此订单已经确认过");
        					}
        					else {
        						SelectProducePlanBll.confirm_plan(table.getModel().getValueAt(row, 0).toString());
        						JOptionPane.showMessageDialog(selectProducePlan,"确认成功");
        						table.getModel().setValueAt("已投入生产", row, 4);
        					}
        				}
	        		}
	        	});
	        
	        	JMenuItem planMenuItem_cancel = new JMenuItem("终止");
	        	planMenuItem_cancel.addActionListener(new ActionListener() {
				
	        		@Override
	        		public void actionPerformed(ActionEvent e) {
	        			// TODO Auto-generated method stub
	        			try {
	        				int row = table.getSelectedRow();
	        				int result = new JOptionPane().showConfirmDialog(selectProducePlan, "您确定要终止吗？");
	        				if(result == JOptionPane.YES_OPTION) {
	        					Object object = table.getModel().getValueAt(row, 4);
	        					if(object==null)
	        						return;
	        					String state = object.toString();
	        					if(state.equals("入库")||state.equals("取消")) {
	        						JOptionPane.showMessageDialog(selectProducePlan,"此订单已经不能终止");
	        					}
	        					else {
	        						SelectProducePlanBll.cancel_plan(table.getModel().getValueAt(row, 0).toString());
	        						JOptionPane.showMessageDialog(selectProducePlan,"终止成功");
	        						table.getModel().setValueAt("取消", row, 4);
	        					}
	        					
	        				}else {
	        					
	        				}
	        			}catch (Exception e5) {
							// TODO: handle exception
	        				JOptionPane.showMessageDialog(selectProducePlan,"不要空操作!");
						}
	        		}
	        	});
	        	JMenuItem planMenuItem_cometo = new JMenuItem("入库");
	        	planMenuItem_cometo.addActionListener(new ActionListener() {
				
	        		@Override
	        		public void actionPerformed(ActionEvent e) {
	        			// TODO Auto-generated method stub
	        			try {
	        				int row = table.getSelectedRow();
	        				int result = new JOptionPane().showConfirmDialog(selectProducePlan, "您确定要入库吗？");
	        				if(result == JOptionPane.YES_OPTION) {
	        					Object object = table.getModel().getValueAt(row, 4);
	        					if(object==null)
	        						return;
	        					String state = object.toString();
	        					if(state.equals("取消")||state.equals("未确认")) {
	        						JOptionPane.showMessageDialog(selectProducePlan,"此订单还不能入库");
	        					}
	        					else if(state.equals("入库")) {
	        						JOptionPane.showMessageDialog(selectProducePlan,"此订单已入库");
	        					}
	        					else {
	        						SelectProducePlanBll.cometo_plan(table.getModel().getValueAt(row, 0).toString());
	        						JOptionPane.showMessageDialog(selectProducePlan,"入库成功");
	        						table.getModel().setValueAt("入库", row, 4);
	        					}
	        					
	        				}else {
	        					
	        				}
	        			}catch (Exception e5) {
							// TODO: handle exception
	        				JOptionPane.showMessageDialog(selectProducePlan,"不要空操作!");
						}
	        		}
	        	});
	        	m_popupMenu.add(planMenItem_confirm);
	        	m_popupMenu.add(planMenuItem_cancel);
	        	m_popupMenu.add(planMenuItem_cometo);
	        }
	      
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("查询")){
			select_btn_action();
		}
		else if(btn_name.equals("只看我的")){
			if(only_me_checkbox.isSelected()){
				planer_user_id_field.setEditable(false);
				planer_user_id_field.setText(user.getUser_id());
			}
			else{
				planer_user_id_field.setEditable(true);
				planer_user_id_field.setText("");
			}
		}
		
	}
	
	public void select_btn_action() {
		String good_id = WarehouseService.findIdByGoodName(good_id_field.getText());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int plan_state_int = ProducePlan.plan_state_toInt(plan_state_combobox.getSelectedItem().toString());
		String [] select_key = {
				"plan_id",plan_id_field.getText().equals("")?null:plan_id_field.getText().toString(),
				"good_id",good_id.equals("")?null:good_id,
				"planer_user_id",planer_user_id_field.getText().equals("")?null:planer_user_id_field.getText().toString(),
				"plan_state",plan_state_int == -1?null:plan_state_int+""
				};
		ArrayList<ProducePlan> planArr = SelectProducePlanBll.select_ProducePlan(select_key);
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
