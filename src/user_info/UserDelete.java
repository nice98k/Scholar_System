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
import javax.swing.JTextField;

import data_base_dao.UserDao;

public class UserDelete extends JDialog{
	JLabel jl_title=new JLabel("删    除    用    户");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_username=new JLabel("用户名：");	JTextField jtf_username=new JTextField();
	JButton jb_delete=new JButton("删 除");
	JButton jb_exit=new JButton("退 出");
	JPanel cont=(JPanel) getContentPane();
	UserDao dao=new UserDao();
	public UserDelete() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(113,10,300,30));
		cont.add(jl_title);
		
		jl_username.setFont(font);
		jl_username.setBounds(new Rectangle(65,103,120,30));
		cont.add(jl_username);
		
		jtf_username.setFont(font);
		jtf_username.setBounds(new Rectangle(185,103,150,30));
		cont.add(jtf_username);
		
		jb_delete.setFont(font);
		jb_delete.addActionListener(new JButtonActionListener());
		jb_delete.setBounds(new Rectangle(55,197,75,36));
		cont.add(jb_delete);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(275,197,75,36));
		cont.add(jb_exit);
	}
	//点击删除按钮
	public void DeleteStu() {
		ResultSet rs = dao.getRs("select * from student_user");
		boolean isDelete=false;
		try {
			while(rs.next()) {
				String student_name = rs.getString("student_name");
				if(student_name.equals(jtf_username.getText().trim())) {
					isDelete=true;//有该用户名
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isDelete) {
			//可以删除
			int i = JOptionPane.showConfirmDialog(null, "您确定要删除吗？","提示",JOptionPane.YES_NO_OPTION);
			if(i==0) {
				int update = dao.getUpdate("delete from student_user where student_name='"+jtf_username.getText().trim()+"'");
				if(update==1) {
					JOptionPane.showMessageDialog(null, "用户名删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
					jtf_username.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "用户名删除失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			//没有该用户名
			JOptionPane.showMessageDialog(null, "没有找到你要删除的用户名，请重新输入","提示",JOptionPane.INFORMATION_MESSAGE);
			jtf_username.setText("");
		}
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_delete) {
				DeleteStu();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
