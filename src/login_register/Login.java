package login_register;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URI;
import java.net.URL;

import javax.swing.*;

import data_base_dao.UserDao;
import scholarship_main.Home;
import user_info.UserUpdate;

public class Login extends JFrame{
	JPanel cont=(JPanel) getContentPane();
	ClassLoader cl=this.getClass().getClassLoader();
	Toolkit tk=Toolkit.getDefaultToolkit();
	Image image=tk.getImage(cl.getResource("com\\student\\images\\Login.jpg"));
	JLabel jl_Text1,jl_Text2,jl_name,jl_password;
	JLabel jl_image=new JLabel(new ImageIcon(image));
	JTextField jtf_name;
	JPasswordField jpf_password;
	JButton jb_login,jb_register;
	JB_ActionLinst jbaction=new JB_ActionLinst();
	JRadioButton jrb1_student=new JRadioButton("学生",true);
	JRadioButton jrb2_teacher=new JRadioButton("老师");
	ButtonGroup bg=new ButtonGroup();
	JPanel jp=new JPanel(new BorderLayout());
	String isAdmin="学生";
	
	int id=0;
	
	public static  String name;//将用户名传递到UserUpdate用来修改密码
	
	public Login() {
		init();
	}
	public void init() {
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 150, 500, 333);
		this.setResizable(false);
		
		//头部标题
		jl_Text1=new JLabel("欢迎登录奖学金评定系统");
		jl_Text1.setBounds(new Rectangle(82, 11, 400, 30));
		jl_Text1.setFont(new Font("楷体",Font.TYPE1_FONT,30));
		jl_Text1.setForeground(Color.WHITE);
		cont.add(jl_Text1);
//		jl_Text2=new JLabel("管    理    系    统");
//		jl_Text2.setBounds(new Rectangle(159, 58, 189, 30));
//		jl_Text2.setFont(new Font("Dialog",Font.PLAIN,25));
//		jl_Text2.setForeground(Color.BLUE);
//		cont.add(jl_Text2);
		//用户名
		jl_name=new JLabel("用户名:");
		jl_name.setForeground(Color.YELLOW);
		jl_name.setBounds(new Rectangle(83, 82, 100, 25));
		jl_name.setFont(new Font("楷体",Font.TYPE1_FONT,20));
		cont.add(jl_name);
		jtf_name=new JTextField();
		jtf_name.setBounds(new Rectangle(185, 82, 212, 25));
		jtf_name.setFont(new Font("Dialog",Font.PLAIN,18));
		cont.add(jtf_name);
		//密码
		jl_password=new JLabel("密   码:");
		jl_password.setForeground(Color.YELLOW);
		jl_password.setBounds(new Rectangle(83,130,100,25));
		jl_password.setFont(new Font("楷体",Font.TYPE1_FONT,20));
		cont.add(jl_password);
		jpf_password=new JPasswordField();
		jpf_password.setBounds(new Rectangle(185,130,212,25));
		jpf_password.setFont(new Font("Dialog",Font.PLAIN,18));
		cont.add(jpf_password);
		//登录注册按钮
		jb_login=new JButton("登    录");
		jb_login.setForeground(Color.YELLOW);

		jb_login.setBounds(new Rectangle(103,212,120,43));
		jb_login.setFont(new Font("楷体",Font.TYPE1_FONT,25));
		jb_login.setBorder(BorderFactory.createRaisedBevelBorder());
		jb_login.setFont(new Font("Dialog", Font.PLAIN, 13));
		jb_login.addActionListener(jbaction);
		jb_login.setContentAreaFilled(false);
		jb_login.setBorder(null);
		cont.add(jb_login);
		
		
		jb_register=new JButton("注    册");
		jb_register.setForeground(Color.YELLOW);
		
		jb_register.setFont(new Font("楷体",Font.TYPE1_FONT,25));
		jb_register.setBounds(new Rectangle(312,212,100,43));
		jb_register.setBorder(BorderFactory.createRaisedBevelBorder());
		jb_register.setFont(new Font("Dialog", Font.PLAIN, 13));
		jb_register.addActionListener(jbaction);
		jb_register.setContentAreaFilled(false);
		jb_register.setBorder(null);
		cont.add(jb_register);
		//单选按钮
		jrb1_student.setBounds(new Rectangle(156,145,100,100));
		bg.add(jrb1_student);
		jrb1_student.setFont(new Font("楷体",Font.TYPE1_FONT,20));
		jrb1_student.setContentAreaFilled(false);
		jrb1_student.setForeground(Color.RED);
		jrb1_student.addItemListener(new JrbioButtion());
		cont.add(jrb1_student);
		
		jrb2_teacher.setBounds(new Rectangle(267,145,100,100));
		bg.add(jrb2_teacher);
		jrb2_teacher.setFont(new Font("楷体",Font.TYPE1_FONT,20));
		jrb2_teacher.setContentAreaFilled(false);
		jrb2_teacher.setForeground(Color.RED);
		jrb2_teacher.addItemListener(new JrbioButtion());
		cont.add(jrb2_teacher);
		//背景图片
		jl_image.setBounds(new Rectangle(0,0,getWidth(),getHeight()));
		cont.add(jl_image);
		this.setVisible(true);
	}
	public void SqlServer(String sql) {
		UserDao dao=new UserDao();
		name = jtf_name.getText();
			String password=jpf_password.getText();
			//判断文本框中的内容是否为空
			if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名","提示信息",JOptionPane.WARNING_MESSAGE);
			}else if(password.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入密码","提示信息",JOptionPane.WARNING_MESSAGE);
			}else {
				boolean isLogin=dao.loginUser(name,password,sql);
				System.out.println(sql);
				//登录成功
				Home home=new Home(id);
//				isLogin=true;
//				if(isLogin) {
//					Home home=new Home(id);
//					dispose();
//				}else {
//					JOptionPane.showMessageDialog(null, "用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
//				}
			}
	}
	
	class JB_ActionLinst implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String sql=null;
			if(e.getSource()==jb_login) {
				if(isAdmin.trim().equals("学生".trim())) {
					id=2;
					sql="select * from student_admin where admin_name=? and admin_password=?";
					SqlServer(sql);
				}else if(isAdmin.trim().equals("老师".trim())) {
					id=1;
					sql="select * from student_admin where admin_name=? and admin_password=?";
					SqlServer(sql);
				}
			}
			//注册
			if (e.getSource()==jb_register) {
				Register register=new Register();
				dispose();
			}

		}

	}
	class JrbioButtion implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(jrb1_student.isSelected()) {
				isAdmin="学生";
			}
			if(jrb2_teacher.isSelected()) {
				isAdmin="老师";
			}
		}
		
	}
}


