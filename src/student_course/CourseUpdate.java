package student_course;

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

public class CourseUpdate extends JDialog{
	JLabel jl_title=new JLabel("修  改  课  程  信  息");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_course_name=new JLabel("课程名称：");
	JTextField jtf_course_name=new JTextField();
	JLabel jl_major=new JLabel("专业名称：");
	JComboBox jcb_major=new JComboBox();
	JLabel jl_course_score=new JLabel("课程学分：");
	JTextField jtf_course_score=new JTextField();
	JButton jb_sumbit=new JButton("修 改");
	JButton jb_exit=new JButton("退  出");
	UserDao dao=new UserDao();
	String valueRow;
	String valueRowName;
	JPanel cont=(JPanel) getContentPane();
	static boolean isUpdate;//判断是否修改
	public CourseUpdate() {
		
	}
	public CourseUpdate(String valueRow,String valueRowName) {
		this.valueRow=valueRow;
		this.valueRowName=valueRowName;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setModal(true);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(113,10,300,30));
		cont.add(jl_title);
		
		jl_course_name.setFont(font);
		jl_course_name.setBounds(new Rectangle(76,87,120,30));
		cont.add(jl_course_name);
		
		jtf_course_name.setFont(font);
		jtf_course_name.setBounds(new Rectangle(201,87,150,30));
		cont.add(jtf_course_name);
		
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(76,146,120,30));
		cont.add(jl_major);
		
		ShowAllMajor();
		jcb_major.setFont(font);
		jcb_major.setBounds(new Rectangle(201,146,150,30));
		cont.add(jcb_major);
		
		jl_course_score.setFont(font);
		jl_course_score.setBounds(new Rectangle(76,202,120,30));
		cont.add(jl_course_score);
		
		jtf_course_score.setFont(font);
		jtf_course_score.setBounds(new Rectangle(201,202,150,30));
		cont.add(jtf_course_score);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonActionListener());
		jb_sumbit.setBounds(new Rectangle(89,287,86,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(271,287,86,40));
		cont.add(jb_exit);

		ReturnCourse();
	}
	//显示所有专业的信息
	public void ShowAllMajor() {
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
	//回显课程信息
	public void ReturnCourse() {
		ResultSet rs = dao.getRs("select * from student_course where course_id='"+valueRow+"'");
		try {
			while(rs.next()) {
				String course_name = rs.getString("course_name");
				String major_name = rs.getString("major_name");
				String course_score = rs.getString("course_score");
				jtf_course_name.setText(course_name);
				jcb_major.setSelectedItem(major_name);
				jtf_course_score.setText(course_score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UpdateCourse() {
		int update = dao.getUpdate("update student_course set course_name='"+jtf_course_name.getText().trim()+"',major_name='"+String.valueOf(jcb_major.getSelectedItem())+"',course_score='"+jtf_course_score.getText().trim()+"' where course_id='"+valueRow+"'");
		if(update==1) {
			JOptionPane.showMessageDialog(null, "修改课程成功","提示",JOptionPane.INFORMATION_MESSAGE);
			isUpdate=true;
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "修改课程失败","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void SumbitUpdate() {
		//没有修改课程姓名的情况下
		if(valueRowName.equals(jtf_course_name.getText().trim())) {
			UpdateCourse();
		}else {
			ResultSet rs = dao.getRs("select * from student_course where major_name='"+String.valueOf(jcb_major.getSelectedItem())+"'");
			boolean isCourse_name=false;
			try {
				while(rs.next()) {
					String course_name = rs.getString("course_name");
					if(course_name.equals(jtf_course_name.getText())) {
						isCourse_name=true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isCourse_name) {
				JOptionPane.showMessageDialog(null, "课程已存在，请重新输入","提示",JOptionPane.INFORMATION_MESSAGE);
			}else {
				UpdateCourse();
			}
		}
		
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				SumbitUpdate();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
