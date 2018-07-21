package com.sduwh.foodcompany.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.sduwh.foodcompany.comm.MDIDesktopPane;

import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class ProducePlanFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	
	//JMenuBar
	private JMenuBar menuBar;
	//JMenu
	private JMenu menuHelp,menuAbout,menuWindow;
	//JMenuItem
	
	//多窗体面板
	private MDIDesktopPane selectDesktop,planDesktop;
	//滚动面板
	private JScrollPane selectScrollPane,planScrollPane;
	//JTabbedPane
	private JTabbedPane tabbedPane;
	//JPane 个人信息面板
	private PersonInfoPanel personInfoPane;
	      //查询面板和生产计划面板
	private JPanel selectPane,planPane;
	//toolbar
	private JToolBar toolbar;
	//button
	private JButton selectPickUp_btn,selectWarehouse_btn;
	



	/**
	 * Create the frame.
	 */
	public ProducePlanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//初始化JMenuBar
		menuBar = new JMenuBar();
		//初始化JMenu
		menuHelp = new JMenu("帮助");
		menuAbout = new JMenu("关于");
		menuWindow = new JMenu("窗口 ");
		//向JMenuBar中添加JMenu
		menuBar.add(menuWindow);menuBar.add(menuHelp);menuBar.add(menuAbout);
		//将JMenu添加进Fram
		setJMenuBar(menuBar);
		//初始化多窗体面板
		selectDesktop = new MDIDesktopPane();
		planDesktop = new MDIDesktopPane();
		//初始化滚动面板
		selectScrollPane = new JScrollPane();
		planScrollPane = new JScrollPane();
		//初始化个人信息、查询和生产计划面板
		personInfoPane = new PersonInfoPanel();
		selectPane = new JPanel();
		selectPane.setLayout(new BorderLayout(0,0));
		planPane = new JPanel();
		planPane.setLayout(new BorderLayout(0,0));
		//初始化JTabbedPane
		tabbedPane = new JTabbedPane();
		//向JTabbedPane中添加tab
		tabbedPane.addTab("查询", selectPane);
		tabbedPane.addTab("生产计划", planPane);
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
		//为Button注册监听信息
		selectPickUp_btn.addActionListener(this);
		selectWarehouse_btn.addActionListener(this);
		//初始化toolbar
		toolbar = new JToolBar();
		//向toolbar中添加JButton
		toolbar.add(selectPickUp_btn);
		toolbar.add(selectWarehouse_btn);
		toolbar.setFloatable(true);
		//向selectPane中添加toolbar
		selectPane.add(toolbar, BorderLayout.NORTH);
		setVisible(true);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String btn_name = ae.getActionCommand();
		
		if(btn_name.equals("查询提货单")){
			selectDesktop.add(new SelectPickUpFrame());
		}
        JInternalFrame jf = selectDesktop.getSelectedFrame();  
		jf.show();
		try {
			//将当前窗口最大化
			jf.setMaximum(true);
		}
		catch(Exception e){		}
	}

}
