package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SelectPickUpFrame extends JInternalFrame implements ActionListener{

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectPickUpFrame frame = new SelectPickUpFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	//JSplitPane
	private JSplitPane splitPane;
	//selectPane��viewPane
	private JPanel selectPane,viewPane;
	/**
	 * Create the frame.
	 */
	public SelectPickUpFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("��ѯ�������Ϣ����");
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setMaximizable(true);	//����������󻯰�ť
	    setIconifiable(true);	//����������С����ť
	    setClosable(true);		//�������йرհ�ť
	    setResizable(true);		//���Ըı��С
	    
	    //��ʼ��selectPane��viewPane
	    selectPane = new JPanel();
	    viewPane = new JPanel();
	    //��ʼ��splitPane
	    splitPane = new JSplitPane();
	    //��splitPane��������
	    splitPane.setLeftComponent(selectPane);
	    splitPane.setRightComponent(viewPane);
	    //���÷ָ���λ��
	    splitPane.setLastDividerLocation(100);
	    this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
