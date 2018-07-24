package com.sduwh.foodcompany.ui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.handler.MessageContext;
import com.sduwh.foodcompany.bill.PersonalInfoBll;
import com.sduwh.foodcompany.comm.CheckUnit;
import com.sduwh.foodcompany.comm.MyListener;
import com.sduwh.foodcompany.entity.Administrators;
import com.sduwh.foodcompany.entity.User;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
/*
 * create by dongzhe
 */
public class PersonInfoPanel extends JPanel implements ActionListener{
	//用户名文本框
	private JTextField textField_user_id;
	//用户姓名文本框
	private JTextField textField_user_name;
	//电话标签
	private JLabel label_tel;
	//电话输入框
	private JTextField textField_tel;
	//身份标签
	private JLabel label__admin_power;
	//身份输入框
	private JTextField textField_admin_power;
	private JLabel label;
	private JLabel label_1;
	//按钮
	JButton btn_edit;
	JButton button_save;
	//逻辑
	private String user_id,user_tel;
	private PersonInfoPanel panel =this;
	
	/**
	 * Create the panel.
	 */
	public PersonInfoPanel(Administrators user) {
		//初始设置
		setSize(800,700);
		setLayout(null);
		user_id = user.getUser_id();
		user_tel = user.getUser_tel();
		/*
		 * 标签和输入框的样式设置
		 */
		JLabel label_user_id = new JLabel("用户名:");
		label_user_id.setBounds(139, 208, 107, 19);
		label_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		label_user_id.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_user_id);
		label_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		//用户名输入框
		textField_user_id = new JTextField();
		textField_user_id.setBounds(257, 203, 146, 29);
		textField_user_id.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_user_id.setEditable(false);
		textField_user_id.setEnabled(false);
		add(textField_user_id);
		textField_user_id.setColumns(10);
		
		//姓名标签
		JLabel label_user_name = new JLabel("姓名：");
		label_user_name.setBounds(433, 208, 96, 19);
		label_user_name.setFont(new Font("宋体", Font.PLAIN, 16));
		label_user_name.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_user_name);
		//姓名输入框
		textField_user_name = new JTextField();
		textField_user_name.setBounds(539, 203, 179, 29);
		textField_user_name.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_user_name.setEnabled(false);
		textField_user_name.setEditable(false);
		textField_user_name.setColumns(10);
		add(textField_user_name);
		
		//电话标签
		label_tel = new JLabel("电话：");
		label_tel.setBounds(152, 305, 107, 19);
		label_tel.setHorizontalAlignment(SwingConstants.CENTER);
		label_tel.setFont(new Font("宋体", Font.PLAIN, 16));
		add(label_tel);
		//电话输入框
		textField_tel = new JTextField();
		textField_tel.setBounds(257, 300, 146, 29);
		textField_tel.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_tel.setEnabled(false);
		textField_tel.setEditable(false);
		textField_tel.setColumns(10);
		add(textField_tel);
		
		//身份标签
		label__admin_power = new JLabel("身份：");
		label__admin_power.setBounds(444, 305, 73, 19);
		label__admin_power.setHorizontalAlignment(SwingConstants.CENTER);
		label__admin_power.setFont(new Font("宋体", Font.PLAIN, 16));
		add(label__admin_power);
		//身份输入框
		textField_admin_power = new JTextField();
		textField_admin_power.setBounds(539, 300, 179, 29);
		textField_admin_power.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_admin_power.setEnabled(false);
		textField_admin_power.setEditable(false);
		textField_admin_power.setColumns(10);
		add(textField_admin_power);
		
		/*
		 * 添加按钮
		 */
		btn_edit = new JButton("编辑");
		btn_edit.setFont(new Font("黑体", Font.BOLD, 14));
		btn_edit.setBounds(285, 388, 107, 40);
		btn_edit.addActionListener(this);
		add(btn_edit);
		button_save = new JButton("保存");
		button_save.setFont(new Font("黑体", Font.BOLD, 14));
		button_save.setForeground(Color.BLACK);
		button_save.setBounds(503, 388, 96, 40);
		button_save.addActionListener(this);
		button_save.setEnabled(false);
		add(button_save);
		
		label = new JLabel("\u4E2A \u4EBA \u4FE1 \u606F");
		label.setFont(new Font("隶书", Font.BOLD, 45));
		label.setBounds(323, 79, 398, 40);
		add(label);
		/*
		 * 添加标签 并添加点击事件
		 */
		label_1 = new JLabel("<html><u>修改密码</u></html>");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(723, 10, 107, 29);
		add(label_1);
		//添加鼠标点击事件
		label_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				ChangePWD changePWD = new ChangePWD(user_id);
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screensize.getWidth();
			    int height = (int)screensize.getHeight();
			    changePWD.setLocation(new Point(width*1/4, height*1/5));
			    changePWD.setAlwaysOnTop(true);
				changePWD.show();
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
				
			}
		});
		//设置文本框内容
		if(user!=null) {
			textField_admin_power.setText(CheckUnit.getPowerOfAdmin(user.getAdm_power()));
			textField_tel.setText(user.getUser_tel());
			textField_user_id.setText(user.getUser_id());
			textField_user_name.setText(user.getUser_name());
		}
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = e.getActionCommand();
		if(name.equals("编辑")) {
			textField_tel.setEnabled(true);
			textField_tel.setEditable(true);
			button_save.setEnabled(true);
			btn_edit.setText("取消");;		
			
		}
		if(name.equals("取消")) {
			textField_tel.setEnabled(false);
			textField_tel.setEditable(false);
			textField_tel.setText(user_tel);
			button_save.setEnabled(false);
			btn_edit.setText("编辑");	
			
		}
		if(name.equals("保存")) {
			
			String new_tel = textField_tel.getText().toString();
			if(!CheckUnit.isMobileNumber(new_tel)) {
				JOptionPane.showMessageDialog(this, "不是合格的电话号码,请重新输入");
			}
			else {
				//修改电话号码逻辑
				boolean state = PersonalInfoBll.reSetUserTel(user_id,new_tel);
				if(state) {
					JOptionPane.showMessageDialog(this, "修改成功");
					btn_edit.setEnabled(true);
					btn_edit.setEnabled(true);
					button_save.setEnabled(false);
					textField_tel.setEnabled(false);
					textField_tel.setEditable(false);
					btn_edit.setText("编辑");
				}else {
					JOptionPane.showMessageDialog(this, "修改失败，异常002");
					
				}
			}
		}
		
	}
}
