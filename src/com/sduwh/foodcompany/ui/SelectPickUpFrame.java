package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.sduwh.foodcompany.bill.IdToName;
import com.sduwh.foodcompany.bill.NameToEntity;
import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.PickUp;
import com.sduwh.foodcompany.entity.User;

public class SelectPickUpFrame extends JInternalFrame implements ActionListener{


	//JSplitPane
	private JSplitPane splitPane;
	//selectPane和viewPane
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
	//scrollPane
	private JScrollPane scrollPane;
	//弹出
	JPopupMenu m_popupMenu;
	//Adm
	private Administrators administrators ;
	private SelectPickUpFrame pickthis = this;
	
	
	//字符串
    private String [] pick_up_state ={"<--请选择-->","未提货","已提货","退货销毁"};
    private String [] table_title = {"提货单编号","提货单状态","操作人编号"};
	/**
	 * Create the frame.
	 */
	public SelectPickUpFrame(Administrators user) {
		setBounds(100, 100, 450, 300);
		setTitle("查询提货单信息窗口");
		administrators = user;
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
	    
	    //在this中添加splitPane
	    this.add(splitPane);
	    //向splitPane中添加面板
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    selectPane.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
	    splitPane.setRightComponent(viewPane);
	    //设置分割线位置
	    splitPane.setLastDividerLocation(50);
	    
	    //初始化textfield
	    pick_up_id_field = new JTextField();
	    pick_up_id_field.setColumns(30);;
	    //初始化combobox
	    pick_up_state_combobox = new JComboBox(pick_up_state);
	    //初始化label
	    pick_up_id_lable = new JLabel("提货编号：");
	    pick_up_state_label = new JLabel("提货状态：");
	    //初始化only_me_checkbox
	    only_me_checkbox = new JCheckBox("只看我的");
	    //初始化select_btn
	    select_btn = new JButton("查询");
	    select_btn.addActionListener(this);
	    //将label,combobox,textfield放入selectPane
	    selectPane.add(pick_up_id_lable);
	    selectPane.add(pick_up_id_field);
	    selectPane.add(pick_up_state_label);
	    selectPane.add(pick_up_state_combobox);
	    selectPane.add(only_me_checkbox);
	    selectPane.add(select_btn);
	  	    	   
	    //初始化table
	    tableModel = new DefaultTableModel(table_title,17);
	    table = new JTable(tableModel){
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	};
	    };
	    table.setRowHeight(50);	  
	    //初始化scrollPane
	    //将table放入scrollPane
	    scrollPane = new JScrollPane(table);
	    //设置滚动条一直显示
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //设置滚动面板大小
	    scrollPane.setPreferredSize(new Dimension(900,400));
	    //将滚动面板加入viewPane
	    viewPane.add(scrollPane);
	    //弹出框实现
	    createPopupMenu();
	    //tip
	    table.addMouseMotionListener(new MouseAdapter() {
	    	 public void mouseMoved(MouseEvent e) {  
	    	        int row=table.rowAtPoint(e.getPoint());  
	    	        int col=table.columnAtPoint(e.getPoint());  
	    	        if(row>-1 && col>-1){  
	    	            Object value= IdToName.Administrators_Select(table.getValueAt(row, col).toString());
	    	            if(null!=value && !"".equals(value))  
	    	                table.setToolTipText(value.toString());//悬浮显示单元格内容  
	    	            else  
	    	                table.setToolTipText(null);//关闭提示  
	    	        }  
	    	    }  

		});

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
			public void mouseEntered(MouseEvent e) {

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

	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
		String btn_name = e.getActionCommand();
		
		if(btn_name.equals("查询")){
			//查询按钮点击事件
			select_btn_avtion();
		}
		
	
	}
	//创建弹出按钮
	private void createPopupMenu() {
		m_popupMenu = new JPopupMenu();
		        
		JMenuItem planMenItem_look = new JMenuItem();
		planMenItem_look.setText("查看详情");
		planMenItem_look.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent evt) {
		        			//该操作需要做的事
		        	int y = table.getSelectedRow();
		        	Object o = table.getModel().getValueAt(y, 0);
		        	String pick_up_id = o==null?null:o.toString();
		        	LookOrderByPickUpDialog lookOrderByPickUpDialog = new LookOrderByPickUpDialog(pick_up_id);
		        	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		        	int width = (int)screensize.getWidth();
		        	int height = (int)screensize.getHeight();
		        	lookOrderByPickUpDialog.setLocation(new Point(width*1/4, height*1/3));
		        	lookOrderByPickUpDialog.setAlwaysOnTop(true);

		        }
		});
		        
		        
		m_popupMenu.add(planMenItem_look);
		        	
		        
		      
	}
	
	private void select_btn_avtion(){
		
		
		
		int pick_up_state = PickUp.state_toInt(pick_up_state_combobox.getSelectedItem().toString());
		Object [] args ={"pick_up_id",pick_up_id_field.getText().equals("")?null:pick_up_id_field.getText(),"pick_up_state",pick_up_state==-1?null:pick_up_state};
		
		ArrayList<PickUp> pick_up_arr = NameToEntity.PickUp_select(args);
		//String[][] datas = new String[3][pick_up_arr.size()];
		DefaultTableModel dablemodel = new DefaultTableModel();
		dablemodel.setColumnIdentifiers(table_title);
		for(int i =0;i<pick_up_arr.size();i++){
			PickUp pickUp = pick_up_arr.get(i);
			dablemodel.addRow(new String[]{
					pickUp.getPick_up_id(),PickUp.state_toString(pickUp),pickUp.getAccountant_user_id()
			});			
		}
		
		table.setModel(dablemodel);
	}

}
