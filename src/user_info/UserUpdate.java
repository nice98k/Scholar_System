package user_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data_base_dao.UserDao;
import login_register.Login;

public class UserUpdate extends JDialog{
	JLabel jl_title=new JLabel("修  改  密  码");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,19);
	JLabel jl_username=new JLabel("用户名：");
	JTextField jtf_username=new JTextField();
	JLabel jl_oldpassword=new JLabel("旧密码：");
	JPasswordField jpf_oldpassword=new JPasswordField();
	JLabel jl_password=new JLabel("新密码：");
	JPasswordField jpf_password=new JPasswordField();
	JLabel jl_repassword=new JLabel("确认密码：");
	JPasswordField jpf_repassword=new JPasswordField();
	JButton jb_update=new JButton("修 改");
	JButton jb_exit=new JButton("退 出");
	JPanel cont=(JPanel) getContentPane();
	UserDao dao=new UserDao();
	public UserUpdate() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480,500);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(156,10,300,30));
		cont.add(jl_title);
		
		jl_username.setFont(font);
		jl_username.setBounds(new Rectangle(88,87,120,30));
		cont.add(jl_username);
		
		jtf_username.setText(Login.name);
		jtf_username.setEditable(false);
		jtf_username.setFont(font);
		jtf_username.setBounds(new Rectangle(208,87,150,30));
		cont.add(jtf_username);
		
		jl_oldpassword.setFont(font);
		jl_oldpassword.setBounds(new Rectangle(88,165,120,30));
		cont.add(jl_oldpassword);
		
		jpf_oldpassword.setFont(font);
		jpf_oldpassword.setBounds(new Rectangle(208,165,150,30));
		cont.add(jpf_oldpassword);
		
		jl_password.setFont(font);
		jl_password.setBounds(new Rectangle(88,244,120,30));
		cont.add(jl_password);
		
		jpf_password.setFont(font);
		jpf_password.setBounds(new Rectangle(208,244,150,30));
		cont.add(jpf_password);
		
		jl_repassword.setFont(font);
		jl_repassword.setBounds(new Rectangle(70,313,120,30));
		cont.add(jl_repassword);
		
		jpf_repassword.setFont(font);
		jpf_repassword.setBounds(new Rectangle(208,313,150,30));
		cont.add(jpf_repassword);
		
		jb_update.setFont(font);
		jb_update.addActionListener(new JButtonActionListener());
		jb_update.setBounds(new Rectangle(77,395,84,38));
		cont.add(jb_update);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(297,395,84,38));
		cont.add(jb_exit);
	}
	//点击修改按钮
	public void UpdatePassword() {
		ResultSet rs = dao.getRs("select * from student_user where student_name='"+Login.name+"'");
		boolean isOld=false;
		try {
			while(rs.next()) {
				String student_password = rs.getString("student_password");
				if(student_password.equals(jpf_oldpassword.getText().trim())){
					isOld=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isOld) {
			String regex1="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$"; //密码必须是字母和数字6,到16位
			boolean flag1=jpf_password.getText().matches(regex1);
			if(!flag1) {
				JOptionPane.showMessageDialog(null,"密码必须是字母和数字6,到16位","提示信息",JOptionPane.WARNING_MESSAGE);
	        	jpf_password.setText("");
			}else if(!jpf_password.getText().trim().equals(jpf_repassword.getText().trim())) {
				JOptionPane.showMessageDialog(null,"两次密码不一致，请重新输入","提示信息",JOptionPane.WARNING_MESSAGE);
	        	jpf_repassword.setText("");
			}else {
				int i=dao.getUpdate("update student_user set student_password='"+jpf_password.getText().trim()+"' where student_name='"+Login.name+"'");
				if(i==1) {
					JOptionPane.showMessageDialog(null, "修改密码成功","提示",JOptionPane.INFORMATION_MESSAGE);
					jpf_oldpassword.setText("");
					jpf_password.setText("");
					jpf_repassword.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "修改密码错误","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "密码错误，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
			jpf_oldpassword.setText("");
		}
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_update) {
				UpdatePassword();
			}
			if(source==jb_exit) {
				dispose();			
			}
		}
		
	}
}
