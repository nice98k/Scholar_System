package login_register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;

import data_base_dao.UserDao;

public class Register extends JFrame{
	
	JLabel jl_student_name,jl_student_password,jl_student_repassword;
	JLabel jl_image;
	JTextField jtf_student_name;
	JPasswordField jpf_student_password,jpf_student_repassword;
	JButton jb_submit,jb_exit;
	Container cont=getContentPane();
	ClassLoader cl=this.getClass().getClassLoader();
	Toolkit tk=Toolkit.getDefaultToolkit();
	Image image=tk.getImage(cl.getResource("com/student/images/register.jpg"));
	Login login;
	
	//构造方法
	public Register() {
		super("注册");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(400, 150, 500, 450));
//		this.setBounds(400, 150, 500, 450);
//		this.setUndecorated(true);//去除默认标题栏  setUndecorated(boolean undecorated) 禁用或启用此 frame 的装饰。是Frame 类的一个成员方法。
		init();
		this.setResizable(false);
		this.setVisible(true);
	}
	public void init() {
		jl_student_name=new JLabel("用户名:");
		jl_student_name.setBounds(new Rectangle(83, 87, 100, 25));
		jl_student_name.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_name);
		jtf_student_name=new JTextField();
		jtf_student_name.setBounds(new Rectangle(257,87,170,30));
		jtf_student_name.setFont(new Font("Dianlg",Font.PLAIN,13));
		jtf_student_name.setToolTipText("用户名必须是3-6位");
		cont.add(jtf_student_name);
		//密码
		jl_student_password=new JLabel("密码:");
		jl_student_password.setBounds(new Rectangle(83, 165, 100, 25));
		jl_student_password.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_password);
		jpf_student_password=new JPasswordField();
		jpf_student_password.setBounds(new Rectangle(257, 165, 170, 30));
		jpf_student_password.setFont(new Font("Dianlg",Font.PLAIN,13));
		jpf_student_password.setToolTipText("密码必须是字母和数字6,到16位");
		cont.add(jpf_student_password);
		//确认密码
		jl_student_repassword=new JLabel("确认密码:");
		jl_student_repassword.setBounds(new Rectangle(83, 238, 100, 25));
		jl_student_repassword.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_repassword);
		jpf_student_repassword=new JPasswordField();
		jpf_student_repassword.setBounds(new Rectangle(257, 232, 170, 30));
		jpf_student_repassword.setFont(new Font("Dianlg",Font.PLAIN,13));
		jpf_student_repassword.setToolTipText("两次密码必须一致");
		cont.add(jpf_student_repassword);
		
		//注册按钮
		jb_submit=new JButton("注册");
		jb_submit.setBounds(new Rectangle(97,317,89,45));
		jb_submit.setFont(new Font("Dianlg",Font.PLAIN,20));
		jb_submit.setContentAreaFilled(false);//将按钮变透明
		jb_submit.setBorder(null);
		jb_submit.addActionListener(new JButtonListAction());
		cont.add(jb_submit);
		
		//关闭
		jb_exit=new JButton("已有账号,点击登陆");
		jb_exit.setBounds(new Rectangle(257,317,200,45));
		jb_exit.setFont(new Font("Dianlg",Font.PLAIN,20));
		jb_exit.setContentAreaFilled(false);//将按钮变透明
		jb_exit.setBorder(null);
		jb_exit.addActionListener(new JButtonListAction());
		cont.add(jb_exit);
		
		//背景图片
		jl_image=new JLabel(new ImageIcon(image));
		jl_image.setBounds(new Rectangle(0, 0, getWidth(), getHeight()));
		cont.add(jl_image);
	}
	
	
	class JButtonListAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb_submit) {
				UserDao dao=new UserDao();
				String jtf_value=jtf_student_name.getText();
				String jpf_value=jpf_student_password.getText();
				String rejpf_value=jpf_student_repassword.getText();
				String regex1="^[a-z0-9A-Z\\u4e00-\\u9fa5]{3,6}$";//用户名是3-6位
		        boolean flag1=jtf_value.matches(regex1);
		        String regex2="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$"; //密码必须是字母和数字6,到16位
		        boolean flag2=jpf_value.matches(regex2);
		        if(!flag1) {
		        	 JOptionPane.showMessageDialog(null,"用户名必须是3-6位","提示信息",JOptionPane.WARNING_MESSAGE);
		        	 jtf_student_name.setText("");
		        }else if(!flag2) {
		        	JOptionPane.showMessageDialog(null,"密码必须是字母和数字6,到16位","提示信息",JOptionPane.WARNING_MESSAGE);
		        	jpf_student_password.setText("");
		        }else if(!rejpf_value.equals(jpf_value)){
		        	JOptionPane.showMessageDialog(null,"两次密码不一致，请重新输入","提示信息",JOptionPane.WARNING_MESSAGE);
		        	jpf_student_repassword.setText("");
		        }else {
		        	//判断是否已有该用户名
		        	boolean isName=dao.RepeatName(jtf_value); 
		        	if(isName) {
		        		//有重名
		        		JOptionPane.showMessageDialog(null,"已有该用户名，请重新输入","提示信息",JOptionPane.ERROR_MESSAGE);
		        		jtf_student_name.setText("");
		        	}else {
		        		//没有
		        		boolean isRegister=dao.register(jtf_value,jpf_value);
			        	if(isRegister) {
			        		//成功
				        	int i=JOptionPane.showConfirmDialog(null,"注册成功，是否登陆","提示信息",JOptionPane.YES_NO_OPTION);
				        	//0表示点击了是   1点击了否
				        	if(i==0) {
				        		login=new Login();
				        		dispose();   //dispose()即释放所有本机屏幕资源，这些 Component 的资源将被破坏，它们使用的所有内存都将返回到操作系统
				        	} 
			        	}else {
			        		//失败
				        	JOptionPane.showMessageDialog(null,"注册失败","提示信息",JOptionPane.ERROR_MESSAGE);
			        	}
		        	}

		        }
				
			}
			
			if(e.getSource()==jb_exit) {
				 login=new Login();
				 dispose();
			}
		}
		
	}
}
