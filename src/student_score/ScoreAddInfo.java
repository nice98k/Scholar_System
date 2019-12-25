package student_score;

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

public class ScoreAddInfo extends JDialog{
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_title=new JLabel("学  生  成  绩  添  加");
	JLabel jl_score_name=new JLabel("学生姓名：");
	JTextField jtf_score_name=new JTextField();
	JLabel jl_course_name=new JLabel("课程名称：");
	JComboBox jcb_course_name=new JComboBox();
	JLabel jl_score=new JLabel("学生成绩：");
	JTextField jtf_score=new JTextField();
	JButton jb_sumbit=new JButton("提  交");
	JButton jb_exit=new JButton("退  出");
	JPanel cont=(JPanel) getContentPane();
	UserDao dao=new UserDao();
	public static void main(String [] args) {
		new ScoreAddInfo();
	}
	public ScoreAddInfo() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,400);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(100,10,300,30));
		cont.add(jl_title);
		
		jl_score_name.setFont(font);
		jl_score_name.setBounds(new Rectangle(112,82,120,30));
		cont.add(jl_score_name);
		
		jtf_score_name.setFont(font);
		jtf_score_name.setBounds(new Rectangle(235,82,150,30));
		cont.add(jtf_score_name);
		
		jl_course_name.setFont(font);
		jl_course_name.setBounds(new Rectangle(112,144,120,30));
		cont.add(jl_course_name);
		
		ShowAllCourse();
		jcb_course_name.setFont(font);
		jcb_course_name.setBounds(new Rectangle(235,144,150,30));
		cont.add(jcb_course_name);
		
		jl_score.setFont(font);
		jl_score.setBounds(new Rectangle(112,215,120,30));
		cont.add(jl_score);
		
		jtf_score.setFont(font);
		jtf_score.setBounds(new Rectangle(235,215,150,30));
		cont.add(jtf_score);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonActionListener());
		jb_sumbit.setBounds(new Rectangle(98,298,84,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(321,298,84,40));
		cont.add(jb_exit);
	}
//	public static void main(String[] args) {
//		new ScoreAddInfo();
//	}
	//显示所有课程
	public void ShowAllCourse() {
		jcb_course_name.addItem("请选择课程");
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
	//点击提交
	public void Sumbitscore() {
		String regex1="^[1-9][0-9]*(\\.[0-9]{1})?$";
		boolean flag1=jtf_score.getText().matches(regex1);
		if(jtf_score_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入要添加的学生","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_course_name.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择课程","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(!flag1) {
			JOptionPane.showMessageDialog(null, "分数必须是数字,小数点后面只能输入一位整数","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			ResultSet rs = dao.getRs("select * from student_stu");
			boolean isName = false;// 判读是否有该学生姓名如果有就让你添加
			try {
				while (rs.next()) {
					String student_name = rs.getString("student_name");
					if (student_name.equals(jtf_score_name.getText())) {
						isName = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isName) {
				boolean isStuName=false;
				rs = dao.getRs("select * from student_score where course_name='"+String.valueOf(jcb_course_name.getSelectedItem())+"'");
				try {
					while(rs.next()) {
						String student_name = rs.getString("student_name");
						if(student_name.equals(jtf_score_name.getText().trim())) {
							isStuName=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isStuName) {
					JOptionPane.showMessageDialog(null, "已有该学生成绩，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int update = dao
							.getUpdate("insert into student_score (student_name,course_name,score) values('" + jtf_score_name.getText() + "','"
									+ String.valueOf(jcb_course_name.getSelectedItem()) + "','" + jtf_score.getText()
									+ "')");
					if (update == 1) {
						rs = dao.getRs("select * from student_study_score");
						boolean isExist = false;// 判读是否有该学生姓名如果有就让你添加
						try {
							while (rs.next()) {
								String student_name = rs.getString("student_name");
								if (student_name.equals(jtf_score_name.getText())) {
									isExist = true;
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(isExist) {
							double course_credit = 0;
							double credit_score = 0;
							double credit=0;
							double course_cur=0;
							try {
								rs=dao.getRs("select * from student_course where course_name='"+String.valueOf(jcb_course_name.getSelectedItem()).toString().trim()+"'");
								
								while(rs.next()) {
									course_credit=Double.valueOf(rs.getString("course_score").trim().toString());
								}
								
								rs=dao.getRs("select * from student_study_score where student_name='"+jtf_score_name.getText()+"'");
								while(rs.next()) {
									credit_score=Double.valueOf(rs.getString("creditxscore").trim().toString());
									credit=Double.valueOf(rs.getString("credit").trim().toString());
									course_cur=Double.valueOf(rs.getString("course_sum").trim().toString());
								}
								
								
							} catch (NumberFormatException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							credit_score=credit_score+course_credit*Double.valueOf(jtf_score.getText().trim());
							credit=credit+course_credit;
							course_cur=course_cur+1.0;
							System.out.println(credit_score+"  "+credit+"   "+course_cur);
							int res = dao.getUpdate("update student_study_score set creditxscore="+credit_score+",credit="+credit+",course_sum="+course_cur+" where student_name='"+jtf_score_name.getText()+"'");
							if(res==1) {
								JOptionPane.showMessageDialog(null, "平均成绩录入成功","提示",JOptionPane.INFORMATION_MESSAGE);
//								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "平均成绩录入失败","提示",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else {
							double course_credit=0;
							String s_id = null;
							try {
								rs=dao.getRs("select * from student_course where course_name='"+String.valueOf(jcb_course_name.getSelectedItem()).toString().trim()+"'");
								while(rs.next()) {
									course_credit=Double.valueOf(rs.getString("course_score").toString().trim());
								}
						        System.out.println("success!");

								rs=dao.getRs("select * from student_stu where student_name='"+jtf_score_name.getText().trim()+"'");
								while(rs.next()) {
									s_id=rs.getString("student_number").toString().trim();
								}
								
								System.out.println(s_id);
							} catch (NumberFormatException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(course_credit+"  "+s_id);
							int res = dao.getUpdate("insert into student_study_score (student_number,student_name,creditxscore,credit,course_sum) values('" +
									s_id+"','"+jtf_score_name.getText()+"',"+course_credit*Double.valueOf(jtf_score.getText().trim())+","+course_credit+","+1+")");
							if(res==1) {
								JOptionPane.showMessageDialog(null, "添加成绩成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "添加成绩失败", "提示", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "没有找到该学生，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE);
				jtf_score_name.setText("");
			}
		}
		
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				Sumbitscore();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
