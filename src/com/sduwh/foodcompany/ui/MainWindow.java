package com.sduwh.foodcompany.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MD5;
import com.sduwh.foodcompany.comm.MapBuilder;
import com.sduwh.foodcompany.dao.AdministratorsDao;
import com.sduwh.foodcompany.dao.CustomerDao;
import com.sduwh.foodcompany.dao.DaoFactory;
import com.sduwh.foodcompany.entity.Administrators;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class MainWindow {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField  textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
	    {
			//BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u767B\u5F55");
		frame.setBackground(new Color(245, 255, 250));
		frame.setBounds(100, 100, 548, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ȡ�ļ��е�ǰλ��  ��ťͼ��
		File file = new File(""); 
		String absolute_path = file.getAbsolutePath();
		System.out.println(absolute_path);
		
		
		//��ʾ����Ļ�м� -----------------------------------------------------
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = frame.getHeight();
		int width = frame.getWidth();
		frame.setLocation(screenWidth-width/2, screenHeight-height/2);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setForeground(UIManager.getColor("Button.darkShadow"));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
		);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(246, 12, 1, 1);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u5A01\u6D77\u98DF\u54C1\u5382");
		label.setBackground(UIManager.getColor("Button.darkShadow"));
		label.setForeground(UIManager.getColor("Button.disabledForeground"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("������κ", Font.PLAIN, 18));
		String path_interesting = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\interesting.jpg"; 
		label.setIcon(new ImageIcon(path_interesting));
		label.setBounds(10, 12, 154, 59);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setForeground(UIManager.getColor("Button.darkShadow"));
		label_1.setBounds(151, 149, 59, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setForeground(UIManager.getColor("Button.darkShadow"));
		label_2.setBounds(151, 180, 59, 15);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(212, 146, 113, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(212, 177, 113, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//�����б� --��¼����
		JComboBox<String>comboBox = new JComboBox();
		comboBox.setBounds(212, 115, 138, 21);
		panel.add(comboBox);
		String[] combobox_item = {"ϵͳ����Ա", "��Ʒ�����Ա","���","����","�����������Ա","�����ƻ�����Ա","����"};
		int combobox_num = combobox_item.length;
		for(int i = 0; i < combobox_num; i++)
			comboBox.addItem(combobox_item[i]);
		
		
		JLabel label_3 = new JLabel("\u8EAB\u4EFD\u7C7B\u578B\uFF1A");
		label_3.setForeground(UIManager.getColor("Button.darkShadow"));
		label_3.setBounds(131, 115, 71, 21);
		panel.add(label_3);
		frame.getContentPane().setLayout(groupLayout);
		
		
		//��¼��ť
		String path_login = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\login.png";
		MyButton btnNewButton = new MyButton(path_login, path_login, path_login);
		btnNewButton.setForeground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.setBackground(new Color(240, 255, 240));
		
		
		//��¼
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String id = new String();
				String psw = new String();
				
				id = textField.getText();
				psw = String.valueOf(textField_1.getPassword());
				System.out.println("�������룺" + psw);
				
				
				//��Ϣ����ȷ
				if(id.equals("") || id.equals(null) || psw.equals("") || psw.equals(null))
					JOptionPane.showMessageDialog(null, "��¼��Ϣ��������������", "����������", JOptionPane.ERROR_MESSAGE);
				
				else if(id.length()!= 11)
					JOptionPane.showMessageDialog(null, "�˺ų�����������������", "����������", JOptionPane.ERROR_MESSAGE);
				
				
				//��Ϣ��ȷ
				else{
					Map map = MapBuilder.buildMap("user_id",id);
					AdministratorsDao dao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
					String correct = dao.findAdministrators(map).get(0).getUser_psw();
					
					//correct ����ȷ����
					System.out.print("��ȷ���ܺ����룺");
					CheckUnit.print(correct);
					
					System.out.print("md5���ܺ����룺");
					CheckUnit.print(MD5.getMD5(psw));
					
					System.out.print("�����Ƿ���ȣ�");
					CheckUnit.print(correct.equals(MD5.getMD5(psw)));
					
					int correct_power = dao.findAdministrators(map).get(0).getAdm_power();	
					
					if(!correct.equals(MD5.getMD5(psw)) ||comboBox.getSelectedIndex()+2 != correct_power )
						JOptionPane.showMessageDialog(null, "�˺Ų����ڻ��������", "����������", JOptionPane.ERROR_MESSAGE);
					else{
						//�������
						if(comboBox.getSelectedIndex() == 1 || comboBox.getSelectedIndex() == 4 || comboBox.getSelectedIndex() == 5 )
						{
							//��ʵ��
							new ProducePlanFrame(dao.findAdministrators(map).get(0));
							frame.setVisible(false);
						}
						//�����������
						else if(comboBox.getSelectedIndex()== 0 || comboBox.getSelectedIndex()== 2 || comboBox.getSelectedIndex()== 3|| comboBox.getSelectedIndex() == 6)
						{
							new TellerFrame(dao.findAdministrators(map).get(0));
							frame.setVisible(false);
						}
						
					}
				}
				
				
			}
		});
		
		String path_bqb = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\interesting.jpg";
		btnNewButton.setIcon(new ImageIcon(path_bqb));
		btnNewButton.setBounds(335, 225, 71, 72);
		panel.add(btnNewButton);
	
		//��ʾ����
		JButton btnNewButton_1 = new JButton("\u67E5\u770B");
		btnNewButton_1.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton_1.setForeground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			        String cmd = arg0.getActionCommand();
			        if(cmd.equals("�鿴")){
			            textField_1.setEchoChar('\0');//���д��������ʾ����
			            btnNewButton_1.setText("����");
			        }else{
			        	textField_1.setEchoChar('*');//���д�����������
			            btnNewButton_1.setText("�鿴");
			    }
			}
		});
		btnNewButton_1.setBounds(335, 176, 57, 23);
		panel.add(btnNewButton_1);
		
		
	}
}
