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
	
	//�ര�����
	private MDIDesktopPane selectDesktop,planDesktop;
	//�������
	private JScrollPane selectScrollPane,planScrollPane;
	//JTabbedPane
	private JTabbedPane tabbedPane;
	//JPane ������Ϣ���
	private PersonInfoPanel personInfoPane;
	      //��ѯ���������ƻ����
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
		
		//��ʼ��JMenuBar
		menuBar = new JMenuBar();
		//��ʼ��JMenu
		menuHelp = new JMenu("����");
		menuAbout = new JMenu("����");
		menuWindow = new JMenu("���� ");
		//��JMenuBar�����JMenu
		menuBar.add(menuWindow);menuBar.add(menuHelp);menuBar.add(menuAbout);
		//��JMenu��ӽ�Fram
		setJMenuBar(menuBar);
		//��ʼ���ര�����
		selectDesktop = new MDIDesktopPane();
		planDesktop = new MDIDesktopPane();
		//��ʼ���������
		selectScrollPane = new JScrollPane();
		planScrollPane = new JScrollPane();
		//��ʼ��������Ϣ����ѯ�������ƻ����
		personInfoPane = new PersonInfoPanel();
		selectPane = new JPanel();
		selectPane.setLayout(new BorderLayout(0,0));
		planPane = new JPanel();
		planPane.setLayout(new BorderLayout(0,0));
		//��ʼ��JTabbedPane
		tabbedPane = new JTabbedPane();
		//��JTabbedPane�����tab
		tabbedPane.addTab("��ѯ", selectPane);
		tabbedPane.addTab("�����ƻ�", planPane);
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
		//ΪButtonע�������Ϣ
		selectPickUp_btn.addActionListener(this);
		selectWarehouse_btn.addActionListener(this);
		//��ʼ��toolbar
		toolbar = new JToolBar();
		//��toolbar�����JButton
		toolbar.add(selectPickUp_btn);
		toolbar.add(selectWarehouse_btn);
		toolbar.setFloatable(true);
		//��selectPane�����toolbar
		selectPane.add(toolbar, BorderLayout.NORTH);
		setVisible(true);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String btn_name = ae.getActionCommand();
		
		if(btn_name.equals("��ѯ�����")){
			selectDesktop.add(new SelectPickUpFrame());
		}
        JInternalFrame jf = selectDesktop.getSelectedFrame();  
		jf.show();
		try {
			//����ǰ�������
			jf.setMaximum(true);
		}
		catch(Exception e){		}
	}

}
