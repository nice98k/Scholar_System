package student_moral;

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

public class MoralScoreAddInfo extends JDialog{
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_title=new JLabel("学  生 德 育 成  绩  添  加");
	JLabel jl_student_name=new JLabel("学生姓名：");
	JTextField jtf_student_name=new JTextField();
	JLabel jl_moral_name=new JLabel("加分项名称：");
	JTextField jcb_moral_score_name=new JTextField();
	JLabel jl_score=new JLabel("加分数值：");
	JTextField jtf_moral_score=new JTextField();
	JButton jb_sumbit=new JButton("提  交");
	JButton jb_exit=new JButton("退  出");
	JPanel cont=(JPanel) getContentPane();
	UserDao dao=new UserDao();
	public static void main(String[] args) {
		new MoralScoreAddInfo();
	}
	public MoralScoreAddInfo() {     // 设置框体
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
		
		jl_student_name.setFont(font);
		jl_student_name.setBounds(new Rectangle(112,82,120,30));
		cont.add(jl_student_name);
		
		jtf_student_name.setFont(font);
		jtf_student_name.setBounds(new Rectangle(235,82,150,30));
		cont.add(jtf_student_name);
		
		jl_moral_name.setFont(font);
		jl_moral_name.setBounds(new Rectangle(112,144,120,30));
		cont.add(jl_moral_name);
		
//		ShowAllCourse();
		jcb_moral_score_name.setFont(font);
		jcb_moral_score_name.setBounds(new Rectangle(235,144,150,30));
		cont.add(jcb_moral_score_name);
		
		jl_score.setFont(font);
		jl_score.setBounds(new Rectangle(112,215,120,30));
		cont.add(jl_score);
		
		jtf_moral_score.setFont(font);
		jtf_moral_score.setBounds(new Rectangle(235,215,150,30));
		cont.add(jtf_moral_score);
		
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
//		new MoralScoreAddInfo();
//	}
	//显示所有课程
//	public void ShowAllCourse() {
//		jcb_moral_score_name.addItem("请选择课程");
//		ResultSet rs = dao.getRs("select * from student_course");
//		try {
//			while(rs.next()) {
//				String course_name = rs.getString("course_name");
//				jcb_moral_score_name.addItem(course_name);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//点击提交
	public void Sumbitscore() {
		String regex1="^[1-9][0-9]*(\\.[0-9]{1})?$";
		boolean flag1=jtf_moral_score.getText().matches(regex1);
		if(jtf_student_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入要添加的学生","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_moral_score_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入相应的智育成绩项名称","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(!flag1) {
			JOptionPane.showMessageDialog(null, "分数必须是数字,小数点后面只能输入一位整数","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			ResultSet rs = dao.getRs("select * from student_stu");
			boolean isName = false;// 判读是否有该学生姓名如果有就让你添加
			try {
				while (rs.next()) {
					String student_name = rs.getString("student_name");
					if (student_name.equals(jtf_student_name.getText())) {
						isName = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isName) {
				boolean isMoral=false;     // 标记是否已有该奖项
				rs = dao.getRs("select * from student_moral where student_name='"+String.valueOf(jtf_student_name.getText().trim())+"'");  // and moral_name='"+jcb_moral_score_name.getText().trim()+"'
				try {
					while(rs.next()) {
						String student_moralname = rs.getString("moral_name");
						if(student_moralname.equals(jcb_moral_score_name.getText().trim())) {
							isMoral=true;
						} 
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isMoral) {
					JOptionPane.showMessageDialog(null, "已有该学生成绩，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					String temp="";
					String temp_name=null;
					rs = dao.getRs("select * from student_stu where student_name='"+String.valueOf(jtf_student_name.getText().trim())+"'");
					
					try {
						while(rs.next()) {
							temp=rs.getString("student_number");
							temp_name=rs.getString("student_name");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					int update = dao
							.getUpdate("insert into student_moral (student_number,student_name,moral_name,moral_score) values('" +temp.trim()+ "','" + jtf_student_name.getText().trim() + "','"
									+ String.valueOf(jcb_moral_score_name.getText()).trim() + "','" + jtf_moral_score.getText().trim()
									+ "')");
					
					rs=dao.getRs("select * from student_moral_score where student_name='"+String.valueOf(jtf_student_name.getText().trim())+"'");
					boolean flag=false;
					int insert_flag=0;
					int cur_num=0;
					double cur_sum=0;
					
					
					try {
						rs=dao.getRs("select * from student_moral_score where student_name='"+String.valueOf(jtf_student_name.getText().trim())+"'");
//						System.out.println(jtf_student_name.getText().trim()+"   "+temp);
						while(rs.next()) {
							if(temp_name.equals(rs.getString("student_name"))) {
								cur_num=Integer.valueOf(rs.getString("total_num").toString().trim());
								cur_sum=Double.valueOf(rs.getString("sum").toString().trim());
								flag=true;
//								System.out.println(flag);
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(flag) {    //如果原表中存在该同学信息
						cur_num=cur_num+1;
						cur_sum=cur_sum+Double.valueOf(jtf_moral_score.getText().trim());
						int res = dao.getUpdate("update student_moral_score set total_num="+cur_num+",sum="+cur_sum+" where student_name='"+jtf_student_name.getText().trim()+"'");
						if(res==1) {
							JOptionPane.showMessageDialog(null, "德育成绩录入成功","提示",JOptionPane.INFORMATION_MESSAGE);
//							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "德育成绩录入失败","提示",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						cur_num=1;
						cur_sum=Double.valueOf(jtf_moral_score.getText().trim());
						int res = dao.getUpdate("insert into student_moral_score (student_number,student_name,total_num,sum) values('" +
								temp+"','"+jtf_student_name.getText().trim()+"',"+cur_num+","+cur_sum+")");
						if(res==1) {
							JOptionPane.showMessageDialog(null, "德育成绩录入成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "德育成绩录入失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					if (update == 1) {
						JOptionPane.showMessageDialog(null, "添加成绩成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "添加成绩失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "没有找到该学生，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE);
				jtf_student_name.setText("");
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
