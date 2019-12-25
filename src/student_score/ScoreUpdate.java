package student_score;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data_base_dao.UserDao;

public class ScoreUpdate extends JDialog{
	JLabel jl_title=new JLabel("学  生  成  绩  修  改");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_student_name=new JLabel("学生名称：");
	JTextField jtf_student_name=new JTextField();
	JLabel jl_course_name=new JLabel("课程名称：");
	JComboBox jcb_course_name=new JComboBox();
	JLabel jl_score_name=new JLabel("学生成绩：");
	JTextField jtf_score_name=new JTextField();
	JButton jb_update=new JButton("修改");
	JButton jb_exit=new JButton("退出");
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();
	String c_name;
	String s_name;
	double pre_score=0;
	boolean isUpdate;//判断是否修改
	public ScoreUpdate() {
		
	}
	public ScoreUpdate(String cur_name,String cur_course) {
		this.s_name=cur_name;
		this.c_name=cur_course;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(430,400);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(102,10,300,30));
		cont.add(jl_title);
		
		jl_student_name.setFont(font);
		jl_student_name.setBounds(new Rectangle(65,87,120,30));
		cont.add(jl_student_name);
		
		jtf_student_name.setFont(font);
		jtf_student_name.setBounds(new Rectangle(186,87,150,30));
		cont.add(jtf_student_name);
		
		jl_course_name.setFont(font);
		jl_course_name.setBounds(new Rectangle(65,145,120,30));
		cont.add(jl_course_name);
		
		ShowAllCourse();
		jcb_course_name.setFont(font);
		jcb_course_name.setBounds(new Rectangle(186,145,150,30));
		cont.add(jcb_course_name);
		
		jl_score_name.setFont(font);
		jl_score_name.setBounds(new Rectangle(65,203,120,30));
		cont.add(jl_score_name);
		
		jtf_score_name.setFont(font);
		jtf_score_name.setBounds(new Rectangle(186,203,150,30));
		cont.add(jtf_score_name);
		
		jb_update.setFont(font);
		jb_update.addActionListener(new JButtonActionListener());
		jb_update.setBounds(new Rectangle(77,276,86,40));
		cont.add(jb_update);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(265,276,86,40));
		cont.add(jb_exit);
		
		ReturnScore();
	}
	//显示课程信息
	public void ShowAllCourse() {
		ResultSet rs = dao.getRs("select * from student_course");
		try {
			while(rs.next()) {
				String course_name = rs.getString("course_name");
				jcb_course_name.addItem(course_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据id回显成绩信息
	public void ReturnScore() {
		System.out.println(s_name);
		ResultSet rs = dao.getRs("select * from student_score where student_name='"+s_name+"' and course_name='"+c_name+"'");
		try {
			while(rs.next()) {
				String student_name = rs.getString("student_name");
				String course_name = rs.getString("course_name");
				String score = rs.getString("score");
				pre_score=Double.valueOf(score.trim());
				jtf_student_name.setText(student_name);
				jcb_course_name.setSelectedItem(course_name);
				jtf_score_name.setText(score);
				jtf_student_name.setEditable(false);
				jcb_course_name.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		new ScoreUpdate();
	}
	//点击修改按钮
	public void UpdateScore() {
		//在不修改学生姓名时
//		if(c_name.equals(jtf_student_name.getText())) {
//			Update();
//		}else {
//			ResultSet rs = dao.getRs("select * from student_score where course_name='"
//					+ String.valueOf(jcb_course_name.getSelectedItem()) + "'");
//			boolean isName=false;
//			try {
//				while (rs.next()) {
//					String student_name = rs.getString("student_name");
//					if(student_name.equals(jtf_student_name.getText().trim())) {
//						isName=true;
//					}
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(isName) {
//				JOptionPane.showMessageDialog(null, "该课程已有该学生","提示",JOptionPane.INFORMATION_MESSAGE);
//			}else {
		Update();
//			}
//		}
		
	}
	//修改
	public void Update() {
		double sxc=0;
		int c_sum=0;
		double cur_score=Double.valueOf(jtf_score_name.getText().trim());
		int cur_course_credit=0;
		ResultSet rs=null;
		try {
			rs=dao.getRs("select * from student_course where course_name='"+jcb_course_name.getSelectedItem().toString().trim()+"'");
			while(rs.next()) {
				cur_course_credit=rs.getInt("course_score");
			}
			rs=dao.getRs("select * from student_study_score where student_name='"+s_name+"'");
			while(rs.next()) {
				sxc=rs.getDouble("creditxscore");
				c_sum=rs.getInt("course_sum");
			}
//			rs=dao.getRs("select * from student_score where stud");
			System.out.println(cur_course_credit+"  "+sxc+"  "+c_sum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sxc=sxc-(pre_score-cur_score)*cur_course_credit;
		
		
		int res=dao.getUpdate("update student_study_score set creditxscore="+sxc+" where student_name='"+s_name+"'");
		
		int update = dao.getUpdate("update student_score set score='"+jtf_score_name.getText()+"' where student_name='"+s_name+"' and course_name='"+c_name+"'");
		 
		if(update==1&&res==1) {
			isUpdate=true;
			JOptionPane.showMessageDialog(null, "该项成绩修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "该项成绩修改失败","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_update) {
				UpdateScore();
			}
			if(source==jb_exit) {
				dispose();
			}
			
		}
		
	}
}
