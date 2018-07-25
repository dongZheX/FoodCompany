package com.sduwh.foodcompany.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NetworkChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.ibatis.scripting.ScriptingException;

import com.sduwh.foodcompany.bill.IdToName;
import com.sduwh.foodcompany.bill.SelectBillBll;
import com.sduwh.foodcompany.entity.Bill;
import com.sduwh.foodcompany.entity.Ordered;

import java.awt.Font;

public class SelectBillFrame extends JInternalFrame implements ActionListener{

	
	private SelectBillFrame selectBillFrame = this;
	
	
	//scrollPane
	private JScrollPane scrollPane_main;
	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
	private JPanel selectPane,viewPane;
	//combobox
	private JComboBox<String> select_year_combocox,select_month_combobox,select_day_combocox;
	//label
	private JLabel select_tip_label;
	//button
	private JButton select_btn;
	//table
	private JTable table;
	private DefaultTableModel tableModel;
	//scrollPane
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	//底部label
	private JLabel tip_label;
	
	 //字符串
    private ArrayList<String> year = new ArrayList<>();
    private String [] month = {"<-请选择->","1", "2", "3", "4", "5","6", "7", "8", "9", "10","11","12"};
    private String [] day = {"<-请选择->","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
			"26","27","28","29","30","31"};
    private String table_title [] = {"账单编号","时间","金额(元)","收据编号","会计编号"};
	/**
	 * Create the frame.
	 */
	public SelectBillFrame() {
		setBounds(100, 100, 817, 531);
		setTitle("查询账单窗口");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//标题栏有最大化按钮
	    setIconifiable(true);	//标题栏有最小化按钮
	    setClosable(true);		//标题栏有关闭按钮
	    setResizable(true);		//可以改变大小
	    
	    //初始化selectPane和viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	   
	    //初始化年份字符串
	    int year_start = 1998;
	    Calendar calendar = Calendar.getInstance();//获取calendar的实例
	    Date today = new Date();
	    calendar.setTime(today);
	    int this_year = calendar.get(Calendar.YEAR);
	    year.add("<-请选择->");
	    for(int year_temp = year_start; year_temp <= this_year; year_temp++){
	    	year.add(year_temp+"");
	    }
	    String [] year_arr = year.toArray(new String [year.size()]);
	    
	    //初始化splitPane
	    splitPane = new JSplitPane();
	    //设置分割线位置
	    splitPane.setDividerLocation(50);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    splitPane.setPreferredSize(new Dimension(900, 350));
	    
	    //初始化scrollPane
	    scrollPane = new JScrollPane(splitPane);  
	    //在this中添加scrollPane
	    getContentPane().add(scrollPane);
	    
	    
	    //初始化combobox
	    select_year_combocox = new JComboBox(year_arr);
	    select_year_combocox.setPreferredSize(new Dimension(130, 30));
	    select_month_combobox = new JComboBox (month);
	    select_day_combocox = new JComboBox(day);
	    //初始化label
	    select_tip_label = new JLabel("查询日期");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.setPreferredSize(new Dimension(200, 30));
	    select_btn.addActionListener(this);
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(select_tip_label);
	    selectPane.add(select_year_combocox);
	    selectPane.add(select_month_combobox);
	    selectPane.add(select_day_combocox);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    viewPane.setLayout(null);
	    table = new JTable(tableModel) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
	    table.setRowHeight(30);	  
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane_1 = new JScrollPane(table);
	    scrollPane_1.setBounds(4, 5, 890, 350);
	    //设置滚动条一直显示
	    scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane_1.setPreferredSize(new Dimension(890,350));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane_1);
	    //初始化底部label
	    tip_label = new JLabel("今日入账： 100 RMB,出账： 50 RMB,净赚： t50 RMB");
	    tip_label.setFont(new Font("宋体", Font.PLAIN, 19));
	    tip_label.setBounds(100, 368, 666, 35);
	    //加入底部label
	    //viewPane.add(in_label);
	    //viewPane.add(out_label);
	    viewPane.add(tip_label);
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
	    
	    /*
	     * 控制combobox选择权限
	     */
	    select_month_combobox.setEnabled(false);
	    select_day_combocox.setEnabled(false);
	    select_year_combocox.addActionListener(this);
	    select_month_combobox.addActionListener(this);
	    select_day_combocox.addActionListener(this);
	    this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String btn_name = e.getActionCommand();
		if(btn_name.equals("查询")){
			select_btn_action();
		}
		if(e.getSource()==select_year_combocox){
			if(select_year_combocox.getSelectedIndex()==0){
				select_month_combobox.setEnabled(false);
				select_month_combobox.setSelectedIndex(0);
				select_day_combocox.setEnabled(false);
				select_day_combocox.setSelectedIndex(0);
			}
			else {
				select_month_combobox.setEnabled(true);
			}
				
			
		}
		if(e.getSource() == select_month_combobox){
			if(select_month_combobox.getSelectedIndex() == 0){
				select_day_combocox.setEnabled(false);
				select_day_combocox.setSelectedIndex(0);
			}
			else
				select_day_combocox.setEnabled(true);
		}
		
	}
	
	public void select_btn_action(){
		String year = select_year_combocox.getSelectedItem().toString();
		String month = select_month_combobox.getSelectedItem().toString();
		String day = select_day_combocox.getSelectedItem().toString();
		ArrayList<Bill>  bill_list = SelectBillBll.select_bill_at_date(
				year.equals("<-请选择->")?null:year, 
				month.equals("<-请选择->")?null:month, 
				day.equals("<-请选择->")?null:day
				);
//		if(bill_list.size() == 0) {
//			JOptionPane.showMessageDialog(this, "未查询到账单信息");
//			tip_label.setText("");
//		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(table_title);
		for (int i = 0; i < bill_list.size(); i++){
			Bill bill = bill_list.get(i);
			String []datas = {
					bill.getBill_id(),
					simpleDateFormat.format(bill.getBill_date()),
					bill.getBill_money()+"",
					bill.getReceipt_id(),
					bill.getAcountant_user_id()
			};
			defaultTableModel.addRow(datas);
		}
		table.setModel(defaultTableModel);
		
		/*
		 * 计算出入账和总金额
		 */
		float in_money = SelectBillBll.in(bill_list);
		float out_money = SelectBillBll.out(bill_list);
		float tol = in_money + out_money;
		String tols = String.format("%.2f", tol);
		String temp;
		if(select_day_combocox.getSelectedIndex()!=0)
			temp = "本日";
		else if(select_month_combobox.getSelectedIndex()!=0)
			temp = "本月";
		else if(select_year_combocox.getSelectedIndex()!=0)
			temp = "今年";
		else 
			temp = "本公司";
		tip_label.setText(temp + "入账： "+in_money+" RMB,出账： "+out_money+" RMB,净赚： "+tols+" RMB");
	}


}
