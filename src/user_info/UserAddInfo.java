package user_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data_base_dao.UserDao;

public class UserAddInfo extends JDialog{
	JLabel jl_title=new JLabel("添  加  用  户");
	Font f=new Font("微软",Font.PLAIN,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_username=new JLabel("用户名：");
	JTextField jtf_username=new JTextField();
	JLabel jl_password=new JLabel("密    码：");
	JPasswordField jpf_password=new JPasswordField();
	JLabel jl_repasswrod=new JLabel("确认密码：");
	JPasswordField jpf_repassword=new JPasswordField();
	JButton jb_sumbit=new JButton("确 认");
	JButton jb_exit=new JButton("退 出");
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();
	public UserAddInfo() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480,400);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(176,10,300,30));
		cont.add(jl_title);
		
		jl_username.setFont(font);
		jl_username.setBounds(new Rectangle(102,87,120,30));
		cont.add(jl_username);
		
		jtf_username.setFont(font);
		jtf_username.setBounds(new Rectangle(213,87,150,30));
		cont.add(jtf_username);
		
		jl_password.setFont(font);
		jl_password.setBounds(new Rectangle(102,155,120,30));
		cont.add(jl_password);
		
		jpf_password.setFont(font);
		jpf_password.setBounds(new Rectangle(213,155,150,30));
		cont.add(jpf_password);
		
		jl_repasswrod.setFont(font);
		jl_repasswrod.setBounds(new Rectangle(89,213,120,30));
		cont.add(jl_repasswrod);
		
		jpf_repassword.setFont(font);
		jpf_repassword.setBounds(new Rectangle(213,213,150,30));
		cont.add(jpf_repassword);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonActionListener());
		jb_sumbit.setBounds(new Rectangle(84,296,83,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(304,296,83,40));
		cont.add(jb_exit);
	}
	//点击确认按钮
	public void RegistSumbit() {
		String jtf_value=jtf_username.getText();
		String jpf_value=jpf_password.getText();
		String rejpf_value=jpf_repassword.getText();
		String regex1="^[a-z0-9A-Z\\u4e00-\\u9fa5]{3,6}$";//用户名是3-6位
        boolean flag1=jtf_value.matches(regex1);
        String regex2="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$"; //密码必须是字母和数字6,到16位
        boolean flag2=jpf_value.matches(regex2);
        if(!flag1) {
        	 JOptionPane.showMessageDialog(null,"用户名必须是3-6位","提示信息",JOptionPane.WARNING_MESSAGE);
        	 jtf_username.setText("");
        }else if(!flag2) {
        	JOptionPane.showMessageDialog(null,"密码必须是字母和数字6,到16位","提示信息",JOptionPane.WARNING_MESSAGE);
        	jpf_password.setText("");
        }else if(!rejpf_value.equals(jpf_value)){
        	JOptionPane.showMessageDialog(null,"两次密码不一致，请重新输入","提示信息",JOptionPane.WARNING_MESSAGE);
        	jpf_repassword.setText("");
        }else {
			ResultSet rs = dao.getRs("select * from student_user");
			boolean isReName = false;
			try {
				while (rs.next()) {
					String student_name = rs.getString("student_name");
					if (student_name.equals(jtf_username.getText().trim())) {
						isReName = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isReName) {
				JOptionPane.showMessageDialog(null, "已有该用户名，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE);
				jtf_username.setText("");
			} else {
				int update = dao.getUpdate("insert into student_user () values('" + UUID.randomUUID().toString() + "','"
						+ jtf_username.getText().trim() + "','" + jpf_password.getText().trim() + "')");
				if (update == 1) {
					JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "注册失败", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
        }
		
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				RegistSumbit();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
