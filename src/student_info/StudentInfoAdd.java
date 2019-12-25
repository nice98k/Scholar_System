package student_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data_base_dao.UserDao;

public class StudentInfoAdd extends JDialog{
	JLabel jl_number,jl_name,jl_email,jl_collage,jl_major,jl_class;
	JTextField jtf_number,jtf_name,jtf_email;
	JButton jb_submit,jb_exit;
	JComboBox jcb_collage=new JComboBox();
	JComboBox jcb_major=new JComboBox();
	JComboBox jcb_class=new JComboBox();
	JButton jl_sumbit;
	JLabel jl_tilte=new JLabel("添  加  学  生  信  息");
	JPanel cont= (JPanel) getContentPane();
	UserDao dao=new UserDao();
	UserDao conn=new UserDao();
	Font font=new Font("微软黑体",Font.PLAIN,18);
	public StudentInfoAdd() {
		cont.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,495);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);//让窗口居中
		this.setResizable(false);
		this.setModal(true);
		cont.setBackground(Color.WHITE);
		init();
		this.setVisible(true);
	}
	public void init(){
		jl_tilte.setFont(new Font("微软",Font.BOLD,25));
		jl_tilte.setBounds(new Rectangle(124,28,240,30));
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
		
		
		jcb_collage.addItem("请选择学院");
		ShowCollageName();
		jcb_collage.setFont(font);
		jcb_collage.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jcb_collage.setBounds(new Rectangle(123,254,120,25));
		cont.add(jcb_collage);
		
		jl_major=new JLabel("所 属 专 业:");
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(255,254,150,25));
		cont.add(jl_major);
		
		jcb_major.addItem("请选择专业");
		jcb_major.setFont(font);
		jcb_major.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jcb_major.setBounds(new Rectangle(355,254,120,25));
		cont.add(jcb_major);
		
		jl_class=new JLabel("所 属 班 级:");
		jl_class.setFont(font);
		jl_class.setBounds(new Rectangle(23,311,150,25));
		cont.add(jl_class);
		
		jcb_class.addItem("请选择班级");
		jcb_class.setFont(font);
		jcb_class.setBounds(new Rectangle(125,311,120,25));
		cont.add(jcb_class);
		
		jb_submit=new JButton("提 交");
		jb_submit.setFont(font);
		jb_submit.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jb_submit.setBounds(new Rectangle(155,376,80,40));
		cont.add(jb_submit);
		
		jb_exit=new JButton("退 出");
		jb_exit.setFont(font);
		jb_exit.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jb_exit.setBounds(new Rectangle(301,376,80,40));
		cont.add(jb_exit);
	}
//	public static void main(String[] args) {
//		new StudentInfoAdd();
//	}
	//获取专业的所有信息给下拉框
	public void ShowMajorName() {
		jcb_major.removeAllItems();//每次切换都要删除上次的内容
		jcb_major.addItem("请选择专业");
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
	//获取学院的所有信息给下拉框
	public void ShowCollageName() {
		jcb_major.removeAllItems();//每次切换都要删除上次的内容
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
	//获取班级的所有信息给下拉框
	public void ShowClassName() {
		jcb_class.removeAllItems();
		jcb_class.addItem("请选择班级");
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
	
	//提交
	public void AddAllStudent() {
		String number = jtf_number.getText();//学号
		String name = jtf_name.getText();//姓名
		String email = jtf_email.getText();//邮箱
		String collage = String.valueOf(jcb_collage.getSelectedItem());//学院
		String major = String.valueOf(jcb_major.getSelectedItem());//专业
		String stu_class = String.valueOf(jcb_class.getSelectedItem());//班级
		String regex1="^[0-9]{10,12}$";
		boolean flag1=number.matches(regex1);
		String regex2="^[a-z0-9A-Z\\u4e00-\\u9fa5]{2,6}$";
		boolean flag2=name.matches(regex2);
		String regex3="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean flag3=email.matches(regex3);
		if(!flag1) {
			JOptionPane.showMessageDialog(this, "学号必须是数字,10~12位","警告",JOptionPane.WARNING_MESSAGE);
			jtf_number.setText("");
		}else if(!flag2) {
			JOptionPane.showMessageDialog(this, "姓名必须是中文,字母,数字,至少2-6位","警告",JOptionPane.WARNING_MESSAGE);
			jtf_name.setText("");
		}else if(!flag3) {
			JOptionPane.showMessageDialog(this, "邮箱格式不正确","警告",JOptionPane.WARNING_MESSAGE);
			jtf_email.setText("");
		}else if(jcb_collage.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "请选择所属学院","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_major.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "请选择所属专业","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_class.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "请选择所属班级","提示",JOptionPane.INFORMATION_MESSAGE);
		}else{
			//判断学号是否重名
			boolean isNumber=false;
			ResultSet rs1 = dao.getRs("select * from student_stu where student_number='"+number+"'");
			//判断姓名是否重名
			boolean isName=false;
			ResultSet rs2 = dao.getRs("select * from student_stu where student_name='"+name+"'");
			try {
				if(rs1.next()) {
					isNumber=true;
				}
				if(rs2.next()) {
					isName=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(isNumber) {
				JOptionPane.showMessageDialog(this, "已有该学号,请重新输入","提示",JOptionPane.WARNING_MESSAGE);
				jtf_number.setText("");
			}else if(isName){
				JOptionPane.showMessageDialog(this, "已有该学生姓名,请重新输入","提示",JOptionPane.WARNING_MESSAGE);
				jtf_name.setText("");
			}else {
				int i = dao.getUpdate(
						"insert into student_stu (student_id,student_number,student_name,student_email,student_collage,student_major,student_class) values ('"
								+ UUID.randomUUID().toString() + "','" + number + "','" + name + "','" + email + "','"
								+ collage + "','" + major + "','" + stu_class + "')");
				if (i == 1) {
					JOptionPane.showMessageDialog(this, "学生信息录入成功！", "提 示", JOptionPane.INFORMATION_MESSAGE);
					jtf_number.setText("");
					jtf_name.setText("");
					jtf_email.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "学生信息录入失败！", "提 示", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		}
	
	}
	
	class StudentInfoAdd_collage_actionAdapter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jcb_collage) {//当我点击学院时触发
				ShowMajorName();
			}
			if(source==jcb_major) {//当我点击专业时触发
				ShowClassName();
			}
			if(source==jb_submit) {//当我点击提交时触发
				AddAllStudent();
			}
			if(source==jb_exit) {//当我点击退出时触发
				dispose();
			}
		}
	}
}



