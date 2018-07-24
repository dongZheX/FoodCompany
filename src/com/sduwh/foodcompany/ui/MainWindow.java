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
		
		//获取文件夹当前位置  按钮图标
		File file = new File(""); 
		String absolute_path = file.getAbsolutePath();
		//System.out.println(absolute_path);
		
		
		//显示在屏幕中间 -----------------------------------------------------
		Toolkit kit = Toolkit.getDefaultToolkit(); 		// 定义工具包
		Dimension screenSize = kit.getScreenSize(); 	// 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; 			// 获取屏幕的宽
		int screenHeight = screenSize.height/2; 		// 获取屏幕的高
		int height = frame.getHeight();
		int width = frame.getWidth();
		frame.setLocation(screenWidth-width/2, screenHeight-height/2);
		
		//panel
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
		
		//Label
		JLabel label = new JLabel("\u5A01\u6D77\u98DF\u54C1\u5382");
		label.setBackground(UIManager.getColor("Button.darkShadow"));
		label.setForeground(UIManager.getColor("Button.disabledForeground"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("华文新魏", Font.PLAIN, 18));
		String path_interesting = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\interesting.jpg"; 
		label.setIcon(new ImageIcon(path_interesting));
		label.setBounds(10, 12, 154, 59);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setForeground(UIManager.getColor("Button.darkShadow"));
		label_1.setBounds(151, 129, 59, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setForeground(UIManager.getColor("Button.darkShadow"));
		label_2.setBounds(151, 157, 59, 15);
		panel.add(label_2);
		
		//ID框
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(212, 126, 113, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		//密码框
		textField_1 = new JPasswordField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(212, 154, 113, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		/*String[] combobox_item = {"系统管理员", "成品库管理员","会计","出纳","生产车间管理员","生产计划管理员","销售","万能账号"};
		int combobox_num = combobox_item.length;
		for(int i = 0; i < combobox_num; i++)
			comboBox.addItem(combobox_item[i]);*/
		frame.getContentPane().setLayout(groupLayout);
		
		
		//登录按钮
		String path_login = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\login.png";
		MyButton btnNewButton = new MyButton(path_login, path_login, path_login);
		btnNewButton.setForeground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.setBackground(new Color(240, 255, 240));
		
		
		//登录
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String id = new String();
				String psw = new String();
				
				id = textField.getText();
				psw = String.valueOf(textField_1.getPassword());
				System.out.println("输入密码：" + psw);
				
				
				//信息不完整
				if(id.equals("") || id.equals(null) || psw.equals("") || psw.equals(null))
					JOptionPane.showMessageDialog(null, "登录信息不完整，请重试", "【出错啦】", JOptionPane.ERROR_MESSAGE);
				
				else if(id.length()!= 11)
					JOptionPane.showMessageDialog(null, "账号长度有误，请重新输入", "【出错啦】", JOptionPane.ERROR_MESSAGE);
				
				
				//信息完整
				else{
					Map map = MapBuilder.buildMap("user_id",id);
					AdministratorsDao dao = (AdministratorsDao)DaoFactory.createDao(DaoFactory.DAO_ADMINISTRATORS);
					String correct = dao.findAdministrators(map).get(0).getUser_psw();
					Administrators a = dao.findAdministrators(map).get(0);
					int power = a.getAdm_power();
					int correct_power = a.getAdm_power();
					
					//correct 是正确密码
					/*System.out.print("正确加密后密码：");
					CheckUnit.print(correct);
					
					//System.out.print("md5加密后密码：");
					CheckUnit.print(MD5.getMD5(psw));
					
					System.out.print("密码是否相等：");
					CheckUnit.print(correct.equals(MD5.getMD5(psw)));*/
					
					//信息不正确
					if(!correct.equals(MD5.getMD5(psw)) )
						JOptionPane.showMessageDialog(null, "账号不存在或密码错误", "【出错啦】", JOptionPane.ERROR_MESSAGE);
					
					//信息正确
					else{
						//生产相关
						if(power == 3 || power == 6 || power == 7)
							new ProducePlanFrame(a);//传实体
						
						//财务销售相关
						else if(power == 2 || power == 4 || power == 5|| power == 8)
							new TellerFrame(a);
						
						else if(power == 9)
						{
							new ProducePlanFrame(a);				
							new TellerFrame(a);	
						}
						frame.setVisible(false);
					}
				}
				
				
			}
		});
		
		//登录箭头
		String path_bqb = absolute_path + "\\src\\com\\sduwh\\foodcompany\\ui\\interesting.jpg";
		btnNewButton.setIcon(new ImageIcon(path_bqb));
		btnNewButton.setBounds(335, 225, 71, 72);
		panel.add(btnNewButton);
	
		//显示/隐藏密码按钮
		JButton btnNewButton_1 = new JButton("\u67E5\u770B");
		btnNewButton_1.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton_1.setForeground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			        String cmd = arg0.getActionCommand();
			        if(cmd.equals("查看")){
			            textField_1.setEchoChar('\0');//这行代码可以显示明文
			            btnNewButton_1.setText("隐藏");
			        }else{
			        	textField_1.setEchoChar('*');//这行代码用于隐藏
			            btnNewButton_1.setText("查看");
			    }
			}
		});
		btnNewButton_1.setBounds(335, 153, 57, 23);
		panel.add(btnNewButton_1);
		
		
	}
}
