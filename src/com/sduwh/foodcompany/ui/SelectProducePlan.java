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
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.PickUp;
import com.sduwh.foodcompany.entity.ProducePlan;

public class SelectProducePlan extends JInternalFrame implements ActionListener {


	

	private SelectProducePlan selectProducePlan = this;
	
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
	
	 //字符串
    private String [] good_state ={"<-请选择->","未确认","已投入生产","入库","取消"};
    private String [] table_title = {"生产计划号","商品号","商品数量","需要日期","计划状态","生产计划科操作人员"};
	/**
	 * Create the frame.
	 */
	public SelectProducePlan(Administrators user) {
		setBounds(100, 100, 450, 300);
		setTitle("查询生产计划窗口");
		
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
	    good_id_label = new JLabel("商品编号：");
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
	    
	    this.setVisible(true);
	    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String btn_name = e.getActionCommand();
		if(btn_name.equals("查询")){
			//调用查询函数
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
