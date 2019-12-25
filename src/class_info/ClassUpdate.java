package class_info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import data_base_dao.UserDao;



public class ClassUpdate extends JDialog{
	JLabel jl_title=new JLabel("修  改   班  级  信  息");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_class_name=new JLabel("班级名称：");
	JTextField jtf_class_name=new JTextField();
	JLabel jl_collage_name=new JLabel("学院名称：");
	JComboBox jcb_collage_name=new JComboBox();
	JLabel jl_major_name=new JLabel("专业名称：");
	JComboBox jcb_major_name=new JComboBox();
	JButton jb_sumbit=new JButton("修改");
	JButton jb_exit=new JButton("退出");
	String valueRow;
	JPanel cont=(JPanel) getContentPane();
	UserDao dao=new UserDao();
	boolean isUpdate;//判断是否修改
	String valueRowClass;
	public ClassUpdate() {
	
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(82,10,300,30));
		cont.add(jl_title);
		
		jl_class_name.setFont(font);
		jl_class_name.setBounds(new Rectangle(54,96,120,30));
		cont.add(jl_class_name);
		
		jtf_class_name.setFont(font);
		jtf_class_name.setBounds(new Rectangle(162,96,150,30));
		cont.add(jtf_class_name);
		
		jl_collage_name.setFont(font);
		jl_collage_name.setBounds(new Rectangle(54,156,120,30));
		cont.add(jl_collage_name);
		
		ShowAllCollage();
		jcb_collage_name.setFont(font);
		jcb_collage_name.addActionListener(new JButtonAcitonListener());
		jcb_collage_name.setBounds(new Rectangle(162,156,150,30));
		cont.add(jcb_collage_name);
		
		jl_major_name.setFont(font);
		jl_major_name.setBounds(new Rectangle(54,212,120,30));
		cont.add(jl_major_name);
		
		jcb_major_name.setFont(font);
		jcb_major_name.setBounds(new Rectangle(162,212,150,30));
		cont.add(jcb_major_name);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonAcitonListener());
		jb_sumbit.setBounds(new Rectangle(65,288,78,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonAcitonListener());
		jb_exit.setBounds(new Rectangle(235,288,78,40));
		cont.add(jb_exit);
		Echo();
	}
	public ClassUpdate(String valueRow,String valueRowClass) {
		this.valueRowClass=valueRowClass;
		this.valueRow=valueRow;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setModal(true);
		init();
		this.setVisible(true);
	}
	//根据id回显班级信息
	public void Echo() {
		ResultSet rs = dao.getRs("select * from student_class where class_id='"+valueRow+"'");
		try {
			while(rs.next()) {
				String class_name = rs.getString("class_name");
				String collage_name = rs.getString("collage_name");
				String major_name = rs.getString("major_name");
				jtf_class_name.setText(class_name);
				jcb_collage_name.setSelectedItem(collage_name);
				jcb_major_name.setSelectedItem(major_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//显示学院信息
	public void ShowAllCollage() {
		ResultSet rs = dao.getRs("select * from student_collage");
		try {
			while(rs.next()) {
				String collage_name = rs.getString("collage_name");
				jcb_collage_name.addItem(collage_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//显示专业信息
	public void ShowAllMajor() {
		jcb_major_name.removeAllItems();
		ResultSet rs = dao.getRs("select * from student_major where collage_name='"+String.valueOf(jcb_collage_name.getSelectedItem())+"'");
		try {
			while(rs.next()) {
				String collage_name = rs.getString("major_name");
				jcb_major_name.addItem(collage_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//点击提交按钮
	public void ClassSumbit() {
		//不修改班级时
		if(valueRowClass.equals(jtf_class_name.getText())) {
			ClassUpdate();
		}else {
			ResultSet rs = dao.getRs("select * from student_class where major_name='"
					+ String.valueOf(jcb_major_name.getSelectedItem()) + "'");
			boolean isClassName = false;
			try {
				while (rs.next()) {
					String class_name = rs.getString("class_name");
					if (class_name.equals(jtf_class_name.getText().trim())) {
						isClassName = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isClassName) {
				JOptionPane.showMessageDialog(null, "已有该班级，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				ClassUpdate();
			}
		}
		
		
	}
	public void ClassUpdate() {
		int update = dao.getUpdate(
				"update student_class set collage_name='" + String.valueOf(jcb_collage_name.getSelectedItem())
						+ "',major_name='" + String.valueOf(jcb_major_name.getSelectedItem()) + "',class_name='"
						+ jtf_class_name.getText() + "' where class_id='"+valueRow+"'");
		if (update == 1) {
			JOptionPane.showMessageDialog(null, "修改班级成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			isUpdate=true;
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "修改班级失败", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}
	public static void main(String[] args) {
		new ClassUpdate();
	}
	class JButtonAcitonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				ClassSumbit();
			}
			if(source==jb_exit) {
				dispose();
			}
			if(source==jcb_collage_name) {
				ShowAllMajor();
			}
		}
		
	}
}
