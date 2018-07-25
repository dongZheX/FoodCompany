package com.sduwh.foodcompany.ui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.plaf.windows.WindowsClassicLookAndFeelAddons;

import com.sduwh.foodcompany.bill.WarehouseService;
import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.comm.MyListener;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.Warehouse;
import com.sun.swingset3.demos.spinner.JSpinnerPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private String comboBoxString[] = {"<--请选择-->","正常","售空","销毁","已过期"};
	private String columndefine[] =  {"批次号","商品名","商品数量","生产日期","有效期","成品库管理员","成品库管理员","状态"};
	//split
	private JSplitPane splitPane = new JSplitPane();
	//checkBox
	private JCheckBox zhikanwode = new JCheckBox("只看我处理的");
	//Button
	private JButton btn_select = new JButton("查询");
	private JButton btn_select_out = new JButton("查询快过期的商品");
	private JButton btn_select_out_a = new JButton("查询过期的商品");
	private WarehouseManagePanel sManagePanel = this;
	//弹出菜单
	private JPopupMenu m_popupMenu;
	//输入数据
	private String batch_id,good_name,warehouse_username,workshop_username,good_state;
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
		zhikanwode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(zhikanwode.isSelected()) {
					jTextField[2].setEditable(false);
					jTextField[2].setText(user.getUser_id());
				}
				else {
					jTextField[2].setEditable(true);
				}
			}
		});
		up_panel.add(zhikanwode);
		//添加按钮
		up_panel.add(btn_select);
		btn_select.setPreferredSize(new Dimension(80, 30));
		up_panel.add(btn_select_out);
		btn_select_out.setPreferredSize(new Dimension(120, 30));
		btn_select_out_a.setPreferredSize(new Dimension(120, 30));
		up_panel.add(btn_select_out_a);
		btn_select.addActionListener(this);
		btn_select_out.addActionListener(this);
		btn_select_out_a.addActionListener(this);
		//表格设置
		DefaultTableModel defaultTableModel = new DefaultTableModel(columndefine, 22);
		jTable = new JTable(defaultTableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		jTable.setMaximumSize(new Dimension(20, 20));	
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
		//短暂测试
		// TODO Auto-generated method stub
		/*
		 * 获取输入框内容 
		 */
		String command = arg0.getActionCommand();
		batch_id = jTextField[0].getText();
		good_name = jTextField[1].getText();
		warehouse_username = jTextField[2].getText();
		workshop_username = jTextField[3].getText();
		good_state = jComboBox_state.getSelectedItem().toString();
		/*
		 * 联合查询信息
		 */
		if(command.equals("查询")) {
			String good_id = WarehouseService.findIdByGoodName(good_name);
			String warehouse_id = WarehouseService.findIdByAdminName(warehouse_username);	
			String workshop_id = WarehouseService.findIdByAdminName(workshop_username);
			int good_state_1 = Warehouse.stateToNum(good_state);
			ArrayList<Warehouse> data = WarehouseService.getWarehouseList(
					"batch_id",batch_id.equals("")?null:batch_id,
					"good_id",good_name.equals("")?null:good_id,
					"warehouse_user_id",warehouse_id.equals("")?null:warehouse_id,
					"workshop_user_id",workshop_id.equals("")?null:workshop_id,
					"good_state",good_state_1==-1?null:good_state_1
			);
			DefaultTableModel defaultTableModel = WarehouseService.getTableModelForWareHouse(data, columndefine);
			jTable.setModel(defaultTableModel);
		}else if(command.equals("查询快过期的商品")) {
			/*
			 * 查询快过期商品
			 */
			ArrayList<Warehouse> data = WarehouseService.findWareHouseOutOfDateAllMore();
			CheckUnit.print(data);
			DefaultTableModel defaultTableModel = WarehouseService.getTableModelForWareHouse(data, columndefine);
			jTable.setModel(defaultTableModel);
		}else if(command.equals("查询过期的商品")) {
			/*
			 * 查询过期商品
			 */
			ArrayList<Warehouse> data = WarehouseService.getOutofDateWarehouse();
			CheckUnit.print(data);
			DefaultTableModel defaultTableModel = WarehouseService.getTableModelForWareHouse(data, columndefine);
			jTable.setModel(defaultTableModel);
		}
		

	}
	
	//创建弹出按钮
	private void createPopupMenu() {
		//获取屏幕
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
	    int height = (int)screensize.getHeight();
        m_popupMenu = new JPopupMenu();
        //弹出菜单设置
        JMenuItem MenItem_change = new JMenuItem();
        MenItem_change.setText("提货  ");
      
        JMenuItem MenItem_add = new JMenuItem();
        MenItem_add.setText("添加  ");
        JMenuItem MenItem_copy = new JMenuItem();
        MenItem_copy.setText("复制  ");
        JMenuItem MenItem_destroy = new JMenuItem();
        MenItem_destroy.setText("销毁  ");
        //添加监听器
        MenItem_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	Map map = new HashMap<>();
            	AddWarehouseDialog addWarehouseDialog = new AddWarehouseDialog();
        	    addWarehouseDialog.setLocation(new Point(width*1/4, height*1/5));
        		addWarehouseDialog.setAlwaysOnTop(true);
        		addWarehouseDialog.show();
        		
        		
            }
        });
        /*
         * 提货点击事件
         */
        MenItem_change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//PickUpDialog pickUpDialog = new PickUpDialog();
			
				String batch_id ,good_id,good_num_temp;
				int good_num;
				try {
					//获取选中行的信息
					int y = jTable.getSelectedRow();
					//过期体或限制
					if(!jTable.getModel().getValueAt(y,7).toString().equals("正常")) {
						JOptionPane.showMessageDialog(sManagePanel, "已过期或者售空或销毁不能提货");
						return;
					}
					batch_id = jTable.getModel().getValueAt(y,0).toString();
					good_id = jTable.getModel().getValueAt(y,1).toString();
					good_num_temp = jTable.getModel().getValueAt(y,2).toString();
					good_num = Integer.parseInt(good_num_temp);
					/*
					 * 父窗口取值
					 */
					Map map = new HashMap<>();
					PickUpDialog pickUpDialog = new PickUpDialog(good_id, batch_id, good_num,new MyListener() {
						
						@Override
						public void getResult(Object...num) {
							map.put("num", num[0]);
							map.put("state", num[1]);
							jTable.getModel().setValueAt(map.get("num"), y, 2);
							jTable.getModel().setValueAt(map.get("state"), y, 7);
						}
					});					
	        	    pickUpDialog.setLocation(new Point(width*1/4, height*1/5));
	        	    pickUpDialog.setLocationRelativeTo(null);
	        	    pickUpDialog.setAlwaysOnTop(true);
//	        	    CheckUnit.print("sda");
	        	    pickUpDialog.setVisible(true);
	        	    pickUpDialog.show();
	        	    
				}catch (Exception e5) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(sManagePanel, "出错003");
				}
				
			}
		});
        /*
         * 复制点击事件
         */
        MenItem_copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x = jTable.getSelectedColumn();
				int y = jTable.getSelectedRow();
				String content = null;
				try {
					content = jTable.getModel().getValueAt(y, x).toString();
					StringSelection ss = new StringSelection(content);
					Clipboard clipboard =getToolkit().getSystemClipboard();
					clipboard.setContents(ss, null);
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(sManagePanel, "请您选中");
				}
				
				
			}
		});
        /*
         * 摧毁点击事件
         */
        MenItem_destroy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int y = jTable.getSelectedRow();
				int result = JOptionPane.showConfirmDialog(sManagePanel, "您确定要销毁吗");
				if(result==JOptionPane.YES_OPTION) {
					try {
						// 过期体或限制
						String batch_id = jTable.getModel().getValueAt(y, 0).toString();
						if (WarehouseService.destoryWarehouse(batch_id)) {
							JOptionPane.showMessageDialog(sManagePanel, "销毁成功");
							jTable.getModel().setValueAt("已销毁", y, 7);
						}
					} catch (NullPointerException e6) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(sManagePanel, "别对空行操作，乖");
					}
				}
				
			}
		});
        m_popupMenu.add(MenItem_change);
        m_popupMenu.add(MenItem_add);
        m_popupMenu.add(MenItem_copy);
        m_popupMenu.add(MenItem_destroy);
	}
}
