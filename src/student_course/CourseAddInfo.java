package student_course;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CourseAddInfo extends JDialog{
	JPanel cont=(JPanel) getContentPane();
	JLabel jl_title=new JLabel("课  程  录  入");
	JLabel jl_course_name=new JLabel("课程名称：");
	JLabel jl_major=new JLabel("所属专业：");
	JLabel jl_course_score=new JLabel("课程学分：");
	JTextField jtf_course_name=new JTextField();
	JTextField jtf_course_score=new JTextField();
	JComboBox jcb_major=new JComboBox();
	JButton jb_sumbit=new JButton("提交");
	JButton jb_exit=new JButton("退出");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	UserDao dao=new UserDao();
	public CourseAddInfo() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(132,10,300,30));
		cont.add(jl_title);
		
		jl_course_name.setFont(font);
		jl_course_name.setBounds(new Rectangle(54,77,120,30));
		cont.add(jl_course_name);
		
		jtf_course_name.setFont(font);
		jtf_course_name.setBounds(new Rectangle(176,77,150,30));
		cont.add(jtf_course_name);
		
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(54,146,120,30));
		cont.add(jl_major);
		
		ShowAllMajor();
		jcb_major.setFont(font);
		jcb_major.setBounds(new Rectangle(176,146,150,30));
		cont.add(jcb_major);
		
		jl_course_score.setFont(font);
		jl_course_score.setBounds(new Rectangle(54,214,120,30));
		cont.add(jl_course_score);
		
		jtf_course_score.setFont(font);
		jtf_course_score.setBounds(new Rectangle(176,214,150,30));
		cont.add(jtf_course_score);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonActionListener());
		jb_sumbit.setBounds(new Rectangle(67,287,78,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(234,287,78,40));
		cont.add(jb_exit);
	}
	//显示所有专业的信息
	public void ShowAllMajor() {
		jcb_major.addItem("请选择");
		ResultSet rs = dao.getRs("select * from student_major");
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
	//点击提交按钮
	public void AddFindCourse() {
		String regex1="([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
		boolean flag1=jtf_course_score.getText().matches(regex1);
		if(jtf_course_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入课程名称","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_major.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择专业","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jtf_course_score.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入学分","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(!flag1) {
			JOptionPane.showMessageDialog(null, "学分只能输入数字","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			ResultSet rs = dao.getRs("select * from student_course where major_name='"+String.valueOf(jcb_major.getSelectedItem())+"'");
			boolean iscourse_name=false;
			try {
				while(rs.next()) {
					String course_name = rs.getString("course_name");
					if(jtf_course_name.getText().trim().equals(course_name)) {
						iscourse_name=true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(iscourse_name) {
				JOptionPane.showMessageDialog(null, "课程名称已存在","提示",JOptionPane.INFORMATION_MESSAGE);
			}else {
				int i = dao.getUpdate("insert into student_course (course_id,major_name,course_name,course_score) values ('"+UUID.randomUUID().toString()+"','"+String.valueOf(jcb_major.getSelectedItem())+"','"+jtf_course_name.getText().trim()+"','"+Double.parseDouble(jtf_course_score.getText().trim())+"')");
				if(i==1) {
					JOptionPane.showMessageDialog(null, "课程添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "课程添加失败","提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				AddFindCourse();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
