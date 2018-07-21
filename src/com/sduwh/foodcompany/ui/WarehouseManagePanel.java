package com.sduwh.foodcompany.ui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sduwh.foodcompany.entity.Administrators;
import com.sun.swingset3.demos.spinner.JSpinnerPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WarehouseManagePanel extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	//上层面板
	private JPanel up_panel;
	//下层面板
	private JPanel down_panel;
	//下层面板中的滚动绵蛮
	private JScrollPane scrollPane;
	//下层面板中的表格控件
	private JTable jTable;
	private DefaultTableModel defaultTableModel;
	//控制层内空间
	private JLabel jLabel[];
	private JTextField jTextField[];
	private String labelString[] = {"批次号:","商品名:","成品库管理员名:","生产车间管理员名:"};
	//下拉列表
	private JComboBox<String> jComboBox_state;
	private String comboBoxString[] = {"正常","售空","销毁","已过期"};
	JTextField textField_batch_id = new JTextField(20);
	//split
	private JSplitPane splitPane = new JSplitPane();
	//checkBox
	private JCheckBox zhikanwode = new JCheckBox("只看我处理的");
	//Button
	private JButton btn_select = new JButton("查询");
	private JButton btn_select_out = new JButton("查询快过期的商品");
	//弹出菜单
	private JPopupMenu m_popupMenu;
	public WarehouseManagePanel(Administrators user) {
		this.setVisible(true);
		setBounds(100, 100, 200, 250);
		this.setLayout(new BorderLayout());
		//添加面板
		up_panel = new JPanel();
		up_panel.setBorder(new TitledBorder("管理控制"));	
		up_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		down_panel = new JPanel();
		down_panel.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));
		this.add(up_panel, BorderLayout.NORTH);
		this.add(down_panel,BorderLayout.CENTER);
		splitPane.setDividerLocation(110);
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(up_panel);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(down_panel);
		
		this.add(splitPane);
		//上层面板构建
		jLabel = new JLabel[4];
		jTextField = new JTextField[4];
		for(int i = 0;i<4;i++) {
			jLabel[i] = new JLabel(labelString[i]);
			jLabel[i].setFont(new Font("宋体", Font.PLAIN, 14));
			jTextField[i] = new JTextField(13);
			jTextField[i].setFont(new Font("宋体", Font.PLAIN, 14));
			up_panel.add(jLabel[i]);
			up_panel.add(jTextField[i]);
		}
		JLabel state_label = new JLabel("库存状态");
		state_label.setFont(new Font("宋体", Font.PLAIN, 14));
		jComboBox_state = new JComboBox<>(comboBoxString);
		up_panel.add(state_label);
		up_panel.add(jComboBox_state);
		up_panel.add(zhikanwode);
		//添加按钮
		btn_select.addActionListener(this);
		up_panel.add(btn_select);
		btn_select.setPreferredSize(new Dimension(80, 30));
		btn_select_out.addActionListener(this);
		up_panel.add(btn_select_out);
		btn_select_out.setPreferredSize(new Dimension(120, 30));
		//表格设置
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(new String[] {"批次号","商品名","商品数量","生产日期","有效期","成品库管理员","计划科管理员"});
		defaultTableModel.setRowCount(100);//暂时
		
		jTable = new JTable(defaultTableModel);
		jTable.setMaximumSize(new Dimension(20, 20));
		//设置剧中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, r);	
		JScrollPane table_scroll = new JScrollPane(jTable);
		table_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table_scroll.setPreferredSize(new Dimension(900, 440));
		down_panel.add(table_scroll);
		/*
		 * jtable添加鼠标点击事件
		 */
		createPopupMenu();
		jTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				

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
				// TODO Auto-generated method stub
				
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = jTable.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            jTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            m_popupMenu.show(jTable, e.getX(), e.getY());
		        }
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//短暂测试
		String name = arg0.getActionCommand();
		if(name.equals("查询")) {
		}else if(name.equals("查询快过期的商品")){
			
		}
		
		
		
	}
	
	//创建弹出按钮
	private void createPopupMenu() {
        m_popupMenu = new JPopupMenu();
        
//        JMenuItem delMenItem_change = new JMenuItem();
//        delMenItem_change.setText("修改  ");
        //此处修改交给提货单
        JMenuItem delMenItem_add = new JMenuItem();
        delMenItem_add.setText("添加  ");
        delMenItem_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	AddWarehouseDialog addWarehouseDialog = new AddWarehouseDialog();
        		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        		int width = (int)screensize.getWidth();
        	    int height = (int)screensize.getHeight();
        	    addWarehouseDialog.setLocation(new Point(width*1/4, height*1/5));
        		addWarehouseDialog.setAlwaysOnTop(true);
        		addWarehouseDialog.show();
            }
        });
//        delMenItem_add.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                //该操作需要做的事
//            }
//        });
//        m_popupMenu.add(delMenItem_change);
        m_popupMenu.add(delMenItem_add);
	}
}
