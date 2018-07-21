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
	//�ϲ����
	private JPanel up_panel;
	//�²����
	private JPanel down_panel;
	//�²�����еĹ�������
	private JScrollPane scrollPane;
	//�²�����еı��ؼ�
	private JTable jTable;
	private DefaultTableModel defaultTableModel;
	//���Ʋ��ڿռ�
	private JLabel jLabel[];
	private JTextField jTextField[];
	private String labelString[] = {"���κ�:","��Ʒ��:","��Ʒ�����Ա��:","�����������Ա��:"};
	//�����б�
	private JComboBox<String> jComboBox_state;
	private String comboBoxString[] = {"����","�ۿ�","����","�ѹ���"};
	JTextField textField_batch_id = new JTextField(20);
	//split
	private JSplitPane splitPane = new JSplitPane();
	//checkBox
	private JCheckBox zhikanwode = new JCheckBox("ֻ���Ҵ����");
	//Button
	private JButton btn_select = new JButton("��ѯ");
	private JButton btn_select_out = new JButton("��ѯ����ڵ���Ʒ");
	//�����˵�
	private JPopupMenu m_popupMenu;
	public WarehouseManagePanel(Administrators user) {
		this.setVisible(true);
		setBounds(100, 100, 200, 250);
		this.setLayout(new BorderLayout());
		//������
		up_panel = new JPanel();
		up_panel.setBorder(new TitledBorder("�������"));	
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
		//�ϲ���幹��
		jLabel = new JLabel[4];
		jTextField = new JTextField[4];
		for(int i = 0;i<4;i++) {
			jLabel[i] = new JLabel(labelString[i]);
			jLabel[i].setFont(new Font("����", Font.PLAIN, 14));
			jTextField[i] = new JTextField(13);
			jTextField[i].setFont(new Font("����", Font.PLAIN, 14));
			up_panel.add(jLabel[i]);
			up_panel.add(jTextField[i]);
		}
		JLabel state_label = new JLabel("���״̬");
		state_label.setFont(new Font("����", Font.PLAIN, 14));
		jComboBox_state = new JComboBox<>(comboBoxString);
		up_panel.add(state_label);
		up_panel.add(jComboBox_state);
		up_panel.add(zhikanwode);
		//��Ӱ�ť
		btn_select.addActionListener(this);
		up_panel.add(btn_select);
		btn_select.setPreferredSize(new Dimension(80, 30));
		btn_select_out.addActionListener(this);
		up_panel.add(btn_select_out);
		btn_select_out.setPreferredSize(new Dimension(120, 30));
		//�������
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.setColumnIdentifiers(new String[] {"���κ�","��Ʒ��","��Ʒ����","��������","��Ч��","��Ʒ�����Ա","�ƻ��ƹ���Ա"});
		defaultTableModel.setRowCount(100);//��ʱ
		
		jTable = new JTable(defaultTableModel);
		jTable.setMaximumSize(new Dimension(20, 20));
		//���þ���
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, r);	
		JScrollPane table_scroll = new JScrollPane(jTable);
		table_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table_scroll.setPreferredSize(new Dimension(900, 550));
		down_panel.add(table_scroll);
		/*
		 * jtable���������¼�
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
		            //ͨ�����λ���ҵ����Ϊ����е���
		            int focusedRowIndex = jTable.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //�������ѡ����Ϊ��ǰ�Ҽ��������
		            jTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //�����˵�
		            m_popupMenu.show(jTable, e.getX(), e.getY());
		        }
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//���ݲ���
		String name = arg0.getActionCommand();
		if(name.equals("��ѯ")) {
		}else if(name.equals("��ѯ����ڵ���Ʒ")){
			
		}
		
		
		
	}
	
	//����������ť
	private void createPopupMenu() {
        m_popupMenu = new JPopupMenu();
        
//        JMenuItem delMenItem_change = new JMenuItem();
//        delMenItem_change.setText("�޸�  ");
        //�˴��޸Ľ��������
        JMenuItem delMenItem_add = new JMenuItem();
        delMenItem_add.setText("���  ");
        delMenItem_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //�ò�����Ҫ������
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
//                //�ò�����Ҫ������
//            }
//        });
//        m_popupMenu.add(delMenItem_change);
        m_popupMenu.add(delMenItem_add);
	}
}
