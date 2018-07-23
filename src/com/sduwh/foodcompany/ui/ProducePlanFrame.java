package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.bill.PersonalInfoBll;
import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.comm.MDIDesktopPane;
import com.sduwh.foodcompany.entity.Administrators;

import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ProducePlanFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	
	//JMenuBar
	private JMenuBar menuBar;
	//JMenu
	private JMenu menuHelp,menuAbout,menuWindow,menuOtherFunc;
	//JMenuItem
	
	//�ര�����
	private MDIDesktopPane selectDesktop,planDesktop;
	//�������
	private JScrollPane selectScrollPane,planScrollPane;
	//JTabbedPane
	private JTabbedPane tabbedPane;
	//JPane ������Ϣ���
	private PersonInfoPanel personInfoPane;
	      //������
	private WarehouseManagePanel warehousePane;
	      //��ѯ���������ƻ����
	private JPanel selectPane,planPane;
	//toolbar
	private JToolBar toolbar;
	private JToolBar toolbar_plan;
	//button
	private JButton selectPickUp_btn,selectWarehouse_btn,selectProducePlan_btn,selectOrdered_btn,selectCus_btn,selectGood_btn;
	private JButton insertPlan_btn,alterPlan_btn;
	private Administrators user;
	//
	private ProducePlanFrame prethis = this;


	/**
	 * Create the frame.
	 */
	public ProducePlanFrame(Administrators adm) {
		user = adm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		//��ȡ�û�
		Administrators user = adm;	
		setMenu();
		//��ʼ���ര�����
		selectDesktop = new MDIDesktopPane();
		planDesktop = new MDIDesktopPane();
		//��ʼ���������
		selectScrollPane = new JScrollPane();
		planScrollPane = new JScrollPane();
		//��ʼ��������Ϣ����ѯ�������ƻ����
		personInfoPane = new PersonInfoPanel(user);
		warehousePane = new WarehouseManagePanel(user);
		selectPane = new JPanel();
		selectPane.setLayout(new BorderLayout(0,0));
		planPane = new JPanel();
		planPane.setLayout(new BorderLayout(0,0));
		//��ʼ��JTabbedPane
		tabbedPane = new JTabbedPane();
		//��JTabbedPane�����tab
		tabbedPane.addTab("��ѯ", selectPane);
		tabbedPane.addTab("�����ƻ�", planPane);
		tabbedPane.addTab("������", warehousePane);
		tabbedPane.addTab("������Ϣ", personInfoPane);
		//��ʼ��Ϊѡ��selectPane
		tabbedPane.setSelectedIndex(0);
		
		
		//��selectPane��planPane�����scrollPane
		selectPane.add(selectScrollPane,BorderLayout.CENTER);
		planPane.add(planScrollPane,BorderLayout.CENTER);
		//��contentpane�����tabbedPane
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		//��scrollPane����Ӷര��;
		selectScrollPane.getViewport().add(selectDesktop);
		planScrollPane.getViewport().add(planDesktop);
		
		
		//��ʼ��button
		selectPickUp_btn = new JButton("��ѯ�����");
		selectWarehouse_btn = new JButton("��ѯ���");
		selectProducePlan_btn = new JButton("��ѯ�����ƻ�");
		selectOrdered_btn = new JButton("��ѯ������");
		selectCus_btn = new JButton("��ѯ�ͻ���Ϣ");
		selectGood_btn = new JButton("��ѯ��Ʒ��Ϣ");
		insertPlan_btn = new JButton("���������ƻ�");
		alterPlan_btn = new JButton("�޸������ƻ�");
		//ΪButtonע�������Ϣ
		selectPickUp_btn.addActionListener(this);
		selectWarehouse_btn.addActionListener(this);
		selectProducePlan_btn.addActionListener(this);
		selectOrdered_btn.addActionListener(this);
		selectCus_btn.addActionListener(this);
		selectGood_btn.addActionListener(this);
		insertPlan_btn.addActionListener(this);
		alterPlan_btn.addActionListener(this);
		
		
		//��ʼ��toolbar
		toolbar = new JToolBar();
		toolbar_plan = new JToolBar();
		//��toolbar�����JButton
		toolbar.add(selectPickUp_btn);
		toolbar.add(selectWarehouse_btn);
		toolbar.add(selectProducePlan_btn);
		toolbar.add(selectOrdered_btn);
		toolbar.add(selectCus_btn);
		toolbar.add(selectGood_btn);
		toolbar.setFloatable(true);
		toolbar_plan.add(insertPlan_btn);
		toolbar_plan.add(alterPlan_btn);
		toolbar_plan.setFloatable(true);
		//��selectPane�����toolbar
		selectPane.add(toolbar, BorderLayout.NORTH);
		planPane.add(toolbar_plan, BorderLayout.NORTH);
		//Ȩ�޳�ʼ��
		power_init();
		
		setVisible(true);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String btn_name = ae.getActionCommand();
		
		if(btn_name.equals("��ѯ�����")){
			selectDesktop.add(new SelectPickUpFrame(user));
		}
		else if(btn_name.equals("��ѯ���")){
			selectDesktop.add(new SelectWarehouse());
		}
		else if(btn_name.equals("��ѯ�����ƻ�")){
			selectDesktop.add(new SelectProducePlan(user));
			
		}
		else if(btn_name.equals("��ѯ������")){
			selectDesktop.add(new SelectOrderedFrame());
		}
		else if(btn_name.equals("��ѯ�ͻ���Ϣ")){
			selectDesktop.add( new AlterCusInfo());
		}
		else if(btn_name.equals("��ѯ��Ʒ��Ϣ")){
			selectDesktop.add(new SelectGoodFrame());
		}
		else if(btn_name.equals("���������ƻ�")){
			planDesktop.add(new InsertProducePlan(user));
		}
		else if(btn_name.equals("�޸������ƻ�")){
			planDesktop.add(new ChangePSelectPlan(user));
		}
		JInternalFrame sf = selectDesktop.getSelectedFrame();
		try {
			//����ǰ�������
			sf.setMaximum(true);			
		}
		catch(Exception e){		}
		JInternalFrame sf2 = planDesktop.getSelectedFrame();
		try {
			//����ǰ�������
			if(!(sf2 instanceof InsertProducePlan))
				sf2.setMaximum(true);			
		}
		catch(Exception e){		}
	
	}
	private void setMenu() {
		//��ʼ��JMenuBar
		menuBar = new JMenuBar();
		//��ʼ��JMenu
		menuHelp = new JMenu("����  |");
		menuAbout = new JMenu("����");
		menuWindow = new JMenu("����   |");
		menuOtherFunc = new JMenu("��������  |");
		//��JMenuBar�����JMenu
		menuBar.add(menuWindow);
		
		menuBar.add(menuOtherFunc);
		menuBar.add(menuHelp);
		menuBar.add(menuAbout);
		//menu�м���item
		/*
		 * �˳���¼
		 */
		JMenuItem exit_item = new JMenuItem("ע��");
		exit_item.setHorizontalAlignment(SwingConstants.CENTER);
		exit_item.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�˳���¼
				try
			    {
			        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			    }
			    catch(Exception e4)
			    {
			        //TODO exception
			    }
				MainWindow mains = new MainWindow();
				mains.frame.setVisible(true);				
				prethis.dispose();
								
			}
		});
		menuWindow.add(exit_item);
		menuWindow.addSeparator();
		/*
		 * �ر�
		 */
		JMenuItem close_item = new JMenuItem("�ر�");
		close_item.setAlignmentX(CENTER_ALIGNMENT);
		close_item.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�˳�
				prethis.dispose();				
			}
		});
		menuWindow.add(close_item);
		/*
		 * Ա��ͨѶ¼
		 */
		JMenuItem call_item = new JMenuItem("Ա��ͨѶ¼");
		call_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screensize.getWidth();
			    int height = (int)screensize.getHeight();
			    //��ȡԱ����Ϣ
				CallInnfoDialog callInnfoDialog = new CallInnfoDialog(PersonalInfoBll.getAdminList());
				callInnfoDialog.setLocation(new Point(width*1/4, height*1/5));
				callInnfoDialog.setAlwaysOnTop(true);
				callInnfoDialog.show();
			}
		});
		menuOtherFunc.add(call_item);
		/*
		 * Table
		 */
		JMenuItem item_help = new JMenuItem("Ѱ�����");
		menuHelp.add(item_help);
		/*
		 * ��ϵ����
		 */
		JMenuItem item_contact_up = new JMenuItem("��ϵ����");
		item_contact_up.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(prethis, "email:sduwh.whspc@126.com");
			}
		});
		menuAbout.add(item_contact_up);
		//��JMenu��ӽ�Frame
		setJMenuBar(menuBar);
				
	}
	
	//Ȩ������
	public void power_init(){
		int adm_power = user.getAdm_power();
		switch (adm_power) {
		case Administrators.PRODUCE_ADMIN_NUM:
			tabbedPane.setEnabledAt(2, false);
			insertPlan_btn.setEnabled(false);
			break;
		case Administrators.PRODUCE_PLAN_ADMIN_NUM:
			tabbedPane.setEnabledAt(2, false);
			break;
		case Administrators.WAREHOUSE_ADMIN_NUM:
			tabbedPane.setEnabledAt(1, false);
			break;
		default:
			break;
		}
	}

}
