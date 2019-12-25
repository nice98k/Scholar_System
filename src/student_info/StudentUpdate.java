package student_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.*;

import data_base_dao.UserDao;

public class StudentUpdate extends JDialog{
	JLabel jl_number,jl_name,jl_email,jl_collage,jl_major,jl_class;
	JTextField jtf_number,jtf_name,jtf_email;
	JButton jb_submit,jb_exit;
	JComboBox jcb_collage=new JComboBox();
	JComboBox jcb_major=new JComboBox();
	JComboBox jcb_class=new JComboBox();
	JButton jl_sumbit;
	JLabel jl_tilte=new JLabel("修 改  学  生  信  息");
	JPanel cont= (JPanel) getContentPane();
	UserDao dao=new UserDao();
	UserDao conn=new UserDao();
	Font font=new Font("微软黑体",Font.PLAIN,18);
	String find;
	String Tablename;
	public static boolean isUpdate;//判断是否修改成功
	private String student_id;
	public StudentUpdate() {
		
	}
	public StudentUpdate(String find,String Tablename) {
		this.Tablename=Tablename;
		this.find=find;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);
		init();
		Echo();
		this.setVisible(true);
	}
	public void init() {
		jl_tilte.setFont(new Font("微软",Font.BOLD,25));
		jl_tilte.setBounds(new Rectangle(154,28,240,30));
		cont.add(jl_tilte);
		
		jl_number=new JLabel("学 生 学 号:");
		jl_number.setFont(font);
		jl_number.setBounds(new Rectangle(83,89,150,25));
		cont.add(jl_number);
		jtf_number=new JTextField();
		jtf_number.setFont(font);
		jtf_number.setBounds(new Rectangle(223,89,150,25));
		cont.add(jtf_number);
		
		jl_name=new JLabel("学 生 姓 名:");
		jl_name.setFont(font);
		jl_name.setBounds(new Rectangle(83,146,150,25));
		cont.add(jl_name);
		jtf_name=new JTextField();
		jtf_name.setFont(font);
		jtf_name.setBounds(new Rectangle(223,146,150,25));
		cont.add(jtf_name);
		
		jl_email=new JLabel("学 生 邮 箱:");
		jl_email.setFont(font);
		jl_email.setBounds(new Rectangle(83,198,150,25));
		cont.add(jl_email);
		jtf_email=new JTextField();
		jtf_email.setFont(font);
		jtf_email.setBounds(new Rectangle(223,198,230,25));
		cont.add(jtf_email);
		
		jl_collage=new JLabel("所 属 学 院:");
		jl_collage.setFont(font);
		jl_collage.setBounds(new Rectangle(23,254,150,25));
		cont.add(jl_collage);
		
		ShowCollageName();
		jcb_collage.setFont(font);
		jcb_collage.addActionListener(new StudentUpdateAction());
		jcb_collage.setBounds(new Rectangle(123,254,120,25));
		cont.add(jcb_collage);
		
		jl_major=new JLabel("所 属 专 业:");
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(255,254,150,25));
		cont.add(jl_major);
		
		jcb_major.setFont(font);
		jcb_major.addActionListener(new StudentUpdateAction());
		jcb_major.setBounds(new Rectangle(355,254,120,25));
		cont.add(jcb_major);
		
		jl_class=new JLabel("所 属 班 级:");
		jl_class.setFont(font);
		jl_class.setBounds(new Rectangle(23,311,150,25));
		cont.add(jl_class);
		
		jcb_class.setFont(font);
		jcb_class.setBounds(new Rectangle(125,311,120,25));
		cont.add(jcb_class);
		
		jb_submit=new JButton("修 改");
		jb_submit.setFont(font);
		jb_submit.addActionListener(new StudentUpdateJButton());
		jb_submit.setBounds(new Rectangle(155,376,80,40));
		cont.add(jb_submit);
		
		jb_exit=new JButton("退 出");
		jb_exit.setFont(font);
		jb_exit.addActionListener(new StudentUpdateJButton());
		jb_exit.setBounds(new Rectangle(301,376,80,40));
		cont.add(jb_exit);
	}
	//将修改好的数据保存到数据库
	public void UpdateStudent() {
		String number = jtf_number.getText();//学号
		String name = jtf_name.getText();//姓名
		String email = jtf_email.getText();//邮箱
		String collage = String.valueOf(jcb_collage.getSelectedItem());//学院
		String major = String.valueOf(jcb_major.getSelectedItem());//专业
		String stu_class = String.valueOf(jcb_class.getSelectedItem());//班级
		String regex1="^[0-9]{1,4}$";
		boolean flag1=number.matches(regex1);
		String regex2="^[a-z0-9A-Z\\u4e00-\\u9fa5]{2,6}$";
		boolean flag2=name.matches(regex2);
		String regex3="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean flag3=email.matches(regex3);
		if(!flag1) {
			JOptionPane.showMessageDialog(this, "学号必须是数字,不能超过4位","警告",JOptionPane.WARNING_MESSAGE);
			jtf_number.setText("");
		}else if(!flag2) {
			JOptionPane.showMessageDialog(this, "姓名必须是中文,字母,数字,至少2-6位","警告",JOptionPane.WARNING_MESSAGE);
			jtf_name.setText("");
		}else if(!flag3) {
			JOptionPane.showMessageDialog(this, "邮箱格式不正确","警告",JOptionPane.WARNING_MESSAGE);
			jtf_email.setText("");
		}else{
			//不修改学号的情况下	当我表格中的学号跟我文本域中的学号一致是，说明是我原来的学号，可以不用修改
			if(find.equals(jtf_number.getText().trim())) {
				Update();
			}else {
				//修改学号的情况下
				boolean isNumber=false;
				ResultSet rs = dao.getRs("select * from student_stu");
				try {
					while(rs.next()) {
						if(number.equals(rs.getString("student_number").trim())) {
							isNumber=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isNumber) {
					JOptionPane.showMessageDialog(null, "已有该学号请重新输入","提示",JOptionPane.INFORMATION_MESSAGE);
				}else {
					Update();
				}
			}
			
		}
	}
	public void Update() {
		int i = dao.getUpdate("update student_stu set student_number='"+jtf_number.getText().trim()+"',student_name='"+jtf_name.getText().trim()+"',student_email='"+jtf_email.getText().trim()+"',student_collage='"+String.valueOf(jcb_collage.getSelectedItem())+"',student_major='"+String.valueOf(jcb_major.getSelectedItem())+"',student_class='"+String.valueOf(jcb_class.getSelectedItem())+"' where student_id='"+student_id+"'");
		if (i == 1) {
			JOptionPane.showMessageDialog(this, "学生修改成功！", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isUpdate=true;
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "学生修改失败！", "提 示", JOptionPane.ERROR_MESSAGE);
		}
	}
	//根据学号查询信息回显到页面上
	public void Echo() {
		ResultSet rs = dao.getRs("select * from student_stu where student_number='"+find+"'");
		try {
			while(rs.next()) {
				student_id = rs.getString("student_id");
				jtf_number.setText(find);
				jtf_name.setText(rs.getString("student_name"));
				jtf_email.setText(rs.getString("student_email"));
				jcb_collage.setSelectedItem(rs.getString("student_collage"));
				jcb_major.setSelectedItem(rs.getString("student_major"));
				jcb_class.setSelectedItem(rs.getString("student_class"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取学院的所有信息给下拉框
	public void ShowCollageName() {
		ResultSet rs = conn.getRs("select * from student_collage");
		try {
			while(rs.next()) {
				String collage = rs.getString("collage_name");
				jcb_collage.addItem(collage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取专业的所有信息给下拉框
	public void ShowMajorName() {
		jcb_major.removeAllItems();//每次切换都要删除上次的内容
		ResultSet rs = conn.getRs("select * from student_major where collage_name='"+String.valueOf(jcb_collage.getSelectedItem())+"'");
		try {
			while(rs.next()) {
				String major_name = rs.getString("major_name");
				jcb_major.addItem(major_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取班级的所有信息给下拉框
		public void ShowClassName() {
			jcb_class.removeAllItems();
			ResultSet rs = dao.getRs("select * from student_class where major_name='"+String.valueOf(jcb_major.getSelectedItem())+"'");
			try {
				while(rs.next()) {
					String class_name = rs.getString("class_name");
					jcb_class.addItem(class_name);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	class StudentUpdateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jcb_collage) {//选择学院触发
				ShowMajorName();
			}
			if(source==jcb_major) {//选择专业触发
				ShowClassName();
			}
		}
		
	}
	class StudentUpdateJButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_submit) {//提交时触发
				UpdateStudent();
			}
			if(source==jb_exit) {//退出
				dispose();
			}
		}
		
	}
}
