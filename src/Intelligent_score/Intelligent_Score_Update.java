package Intelligent_score;

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

public class Intelligent_Score_Update extends JDialog{
	JLabel jl_title=new JLabel("学  生 智 育 成  绩  修  改");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_student_name=new JLabel("学生名称：");
	JTextField jtf_student_name=new JTextField();
	JLabel jl_moral_name=new JLabel("加分项名称：");
	JTextField moral_name=new JTextField();
	JLabel jl_moral_score=new JLabel("分值大小：");
	JTextField jtf_moral_score=new JTextField();
	JButton jb_update=new JButton("修改");
	JButton jb_exit=new JButton("退出");
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();
	String valueName;
	String valueId;
	boolean isUpdate;//判断是否修改

	public Intelligent_Score_Update(String s_name,double score) {
		Delete_final(s_name,score);
	}
	public void Delete_final(String s_name,double score) {
		int cur_total=0;
		double cur_sum=0;
		ResultSet rs=null;
		try {
			rs=dao.getRs("select * from student_intelligent_score where student_name='"+s_name+"'");
			while(rs.next()) {
				cur_total=rs.getInt("total_num");
				cur_sum=rs.getDouble("sum");
	        }
			cur_total=cur_total-1;
			cur_sum=cur_sum-score;
			int res=dao.getUpdate("update student_intelligent_score set total_num="+cur_total+",sum="+cur_sum+" where student_name='"+s_name+"'");
			if(res==1) {
				JOptionPane.showMessageDialog(null, "该项智育成绩删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "该项智育成绩删除失败！","提示",JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Intelligent_Score_Update(String valueId,String valueName) {
		this.valueId=valueId;
		this.valueName=valueName;
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
		jl_title.setBounds(new Rectangle(82,10,300,30));
		cont.add(jl_title);
		
		jl_student_name.setFont(font);
		jl_student_name.setBounds(new Rectangle(65,87,120,30));
		cont.add(jl_student_name);
		
		jtf_student_name.setFont(font);
		jtf_student_name.setBounds(new Rectangle(186,87,150,30));
		cont.add(jtf_student_name);
		
		jl_moral_name.setFont(font);
		jl_moral_name.setBounds(new Rectangle(65,145,120,30));
		cont.add(jl_moral_name);
		
//		ShowAllCourse();
		moral_name.setFont(font);
		moral_name.setBounds(new Rectangle(186,145,150,30));
		cont.add(moral_name);
		
		jl_moral_score.setFont(font);
		jl_moral_score.setBounds(new Rectangle(65,203,120,30));
		cont.add(jl_moral_score);
		
		jtf_moral_score.setFont(font);
		jtf_moral_score.setBounds(new Rectangle(186,203,150,30));
		cont.add(jtf_moral_score);
		
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
	public void Update_final(String s_name,String score_name,double score) {
		System.out.println(123);
		ResultSet  rs=null;
		double cur_sum=0;
		int cur_num=0;
		try {
			rs=dao.getRs("select * from student_intelligent_score where student_name='"+s_name+"'");
			while(rs.next()) {
				cur_num=rs.getInt("total_num");
				cur_sum=rs.getDouble("sum");
			}
			cur_sum=cur_sum-(score-Double.valueOf(jtf_moral_score.getText().trim()));
			System.out.println(cur_sum);
			int res=dao.getUpdate("update student_intelligent_score set sum="+cur_sum+" where student_name='"+s_name+"'");
			if(res==1) {
				JOptionPane.showMessageDialog(null, "该项智育成绩修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "该项智育成绩修改失败！","提示",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据id回显成绩信息
	public void ReturnScore() {
		ResultSet rs = dao.getRs("select * from student_intelligent where student_name='"+valueId+"' and intelligent_name='"+valueName+"'");
		try {
			while(rs.next()) {
				String temp_student_name = rs.getString("student_name");
				String temp_intelligent_name = rs.getString("intelligent_name");
				String temp_score = rs.getString("intelligent_score");
				jtf_student_name.setText(temp_student_name);
				jtf_student_name.setEditable(false);
				moral_name.setText(temp_intelligent_name);
				moral_name.setEditable(false);
				jtf_moral_score.setText(temp_score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//点击修改按钮
	public void UpdateScore() {
		Update();
		//在不修改学生姓名时
//		if(valueId.equals(jtf_student_name.getText())) {
//			Update();
//		}else {
//			ResultSet rs = dao.getRs("select * from student_moral where moral_name='"
//					+ String.valueOf(moral_name.getText().trim()) + "'");
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
//				Update();
//			}
//		}
		
	}
	//修改
	public void Update() {
		double pre_score=0;
		try {
			ResultSet rs=dao.getRs("select * from student_intelligent where student_name='"+valueId+"' and intelligent_name='"+valueName+"'");
		
			while(rs.next()) {
				pre_score=rs.getDouble("intelligent_score");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int update = dao.getUpdate("update student_intelligent set intelligent_score='"+jtf_moral_score.getText().trim()+"' where student_name='"+valueId+"' and intelligent_name='"+String.valueOf(moral_name.getText().trim())+"'");
		 Update_final(valueId,moral_name.getText().trim(),pre_score);
		 if(update==1) {
			isUpdate=true;
			JOptionPane.showMessageDialog(null, "德育信息修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "德育信息修改失败","提示",JOptionPane.INFORMATION_MESSAGE);
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
