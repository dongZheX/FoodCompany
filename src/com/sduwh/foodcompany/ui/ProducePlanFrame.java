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
	
	//多窗体面板
	private MDIDesktopPane selectDesktop,planDesktop;
	//滚动面板
	private JScrollPane selectScrollPane,planScrollPane;
	//JTabbedPane
	private JTabbedPane tabbedPane;
	//JPane 个人信息面板
	private PersonInfoPanel personInfoPane;
	      //库存面板
	private WarehouseManagePanel warehousePane;
	      //查询面板和生产计划面板
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
		//获取用户
		Administrators user = adm;	
		setMenu();
		//初始化多窗体面板
		selectDesktop = new MDIDesktopPane();
		planDesktop = new MDIDesktopPane();
		//初始化滚动面板
		selectScrollPane = new JScrollPane();
		planScrollPane = new JScrollPane();
		//初始化个人信息、查询和生产计划面板
		personInfoPane = new PersonInfoPanel(user);
		warehousePane = new WarehouseManagePanel(user);
		selectPane = new JPanel();
		selectPane.setLayout(new BorderLayout(0,0));
		planPane = new JPanel();
		planPane.setLayout(new BorderLayout(0,0));
		//初始化JTabbedPane
		tabbedPane = new JTabbedPane();
		//向JTabbedPane中添加tab
		tabbedPane.addTab("查询", selectPane);
		tabbedPane.addTab("生产计划", planPane);
		tabbedPane.addTab("库存管理", warehousePane);
		tabbedPane.addTab("个人信息", personInfoPane);
		//初始化为选择selectPane
		tabbedPane.setSelectedIndex(0);
		
		
		//向selectPane和planPane中添加scrollPane
		selectPane.add(selectScrollPane,BorderLayout.CENTER);
		planPane.add(planScrollPane,BorderLayout.CENTER);
		//向contentpane中添加tabbedPane
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		//向scrollPane中添加多窗体;
		selectScrollPane.getViewport().add(selectDesktop);
		planScrollPane.getViewport().add(planDesktop);
		
		
		//初始化button
		selectPickUp_btn = new JButton("查询提货单");
		selectWarehouse_btn = new JButton("查询库存");
		selectProducePlan_btn = new JButton("查询生产计划");
		selectOrdered_btn = new JButton("查询订货单");
		selectCus_btn = new JButton("查询客户信息");
		selectGood_btn = new JButton("查询商品信息");
		insertPlan_btn = new JButton("创建生产计划");
		alterPlan_btn = new JButton("修改生产计划");
		//为Button注册监听信息
		selectPickUp_btn.addActionListener(this);
		selectWarehouse_btn.addActionListener(this);
		selectProducePlan_btn.addActionListener(this);
		selectOrdered_btn.addActionListener(this);
		selectCus_btn.addActionListener(this);
		selectGood_btn.addActionListener(this);
		insertPlan_btn.addActionListener(this);
		alterPlan_btn.addActionListener(this);
		
		
		//初始化toolbar
		toolbar = new JToolBar();
		toolbar_plan = new JToolBar();
		//向toolbar中添加JButton
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
		//向selectPane中添加toolbar
		selectPane.add(toolbar, BorderLayout.NORTH);
		planPane.add(toolbar_plan, BorderLayout.NORTH);
		//权限初始化
		power_init();
		
		setVisible(true);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String btn_name = ae.getActionCommand();
		
		if(btn_name.equals("查询提货单")){
			selectDesktop.add(new SelectPickUpFrame(user));
		}
		else if(btn_name.equals("查询库存")){
			selectDesktop.add(new SelectWarehouse());
		}
		else if(btn_name.equals("查询生产计划")){
			selectDesktop.add(new SelectProducePlan(user));
			
		}
		else if(btn_name.equals("查询订货单")){
			selectDesktop.add(new SelectOrderedFrame());
		}
		else if(btn_name.equals("查询客户信息")){
			selectDesktop.add( new AlterCusInfo());
		}
		else if(btn_name.equals("查询商品信息")){
			selectDesktop.add(new SelectGoodFrame());
		}
		else if(btn_name.equals("创建生产计划")){
			planDesktop.add(new InsertProducePlan(user));
		}
		else if(btn_name.equals("修改生产计划")){
			planDesktop.add(new ChangePSelectPlan(user));
		}
		JInternalFrame sf = selectDesktop.getSelectedFrame();
		try {
			//将当前窗口最大化
			sf.setMaximum(true);			
		}
		catch(Exception e){		}
		JInternalFrame sf2 = planDesktop.getSelectedFrame();
		try {
			//将当前窗口最大化
			if(!(sf2 instanceof InsertProducePlan))
				sf2.setMaximum(true);			
		}
		catch(Exception e){		}
	
	}
	private void setMenu() {
		//初始化JMenuBar
		menuBar = new JMenuBar();
		//初始化JMenu
		menuHelp = new JMenu("帮助  |");
		menuAbout = new JMenu("关于");
		menuWindow = new JMenu("窗口   |");
		menuOtherFunc = new JMenu("其他功能  |");
		//向JMenuBar中添加JMenu
		menuBar.add(menuWindow);
		
		menuBar.add(menuOtherFunc);
		menuBar.add(menuHelp);
		menuBar.add(menuAbout);
		//menu中加入item
		/*
		 * 退出登录
		 */
		JMenuItem exit_item = new JMenuItem("注销");
		exit_item.setHorizontalAlignment(SwingConstants.CENTER);
		exit_item.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//退出登录
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
		 * 关闭
		 */
		JMenuItem close_item = new JMenuItem("关闭");
		close_item.setAlignmentX(CENTER_ALIGNMENT);
		close_item.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//退出
				prethis.dispose();				
			}
		});
		menuWindow.add(close_item);
		/*
		 * 员工通讯录
		 */
		JMenuItem call_item = new JMenuItem("员工通讯录");
		call_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screensize.getWidth();
			    int height = (int)screensize.getHeight();
			    //获取员工信息
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
		JMenuItem item_help = new JMenuItem("寻求帮助");
		menuHelp.add(item_help);
		/*
		 * 联系我们
		 */
		JMenuItem item_contact_up = new JMenuItem("联系我们");
		item_contact_up.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(prethis, "email:sduwh.whspc@126.com");
			}
		});
		menuAbout.add(item_contact_up);
		//将JMenu添加进Frame
		setJMenuBar(menuBar);
				
	}
	
	//权限设置
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
