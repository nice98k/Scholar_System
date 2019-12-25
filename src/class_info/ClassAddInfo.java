package class_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data_base_dao.UserDao;




public class ClassAddInfo extends JDialog{
	JLabel jl_title;
	JLabel jl_classname,jl_collage,jl_major;
	JTextField jtf_classname;
	JComboBox jcb_collage,jcb_major;
	JButton jb_submit,jb_exit;
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,20);
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();
	public ClassAddInfo() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(550,450);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		cont.setLayout(null);
		this.setResizable(false);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title=new JLabel("班  级  录  入  信  息");
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(157,10,300,50));
		cont.add(jl_title);
		
		jl_classname=new JLabel("班级名称：");
		jl_classname.setFont(font);
		jl_classname.setBounds(new Rectangle(123,78,120,30));
		cont.add(jl_classname);
		
		jtf_classname=new JTextField();
		jtf_classname.setFont(font);
		jtf_classname.setBounds(new Rectangle(254,78,150,30));
		cont.add(jtf_classname);
		
		jl_collage=new JLabel("所属学院：");
		jl_collage.setFont(font);
		jl_collage.setBounds(new Rectangle(123,163,120,30));
		cont.add(jl_collage);
		
		jcb_collage=new JComboBox();
		jcb_collage.addItem("请选择学院");
		ShowCollage();
		jcb_collage.addActionListener(new JComboBoxActionLinster());
		jcb_collage.setFont(font);
		jcb_collage.setBounds(new Rectangle(254,163,150,30));
		cont.add(jcb_collage);
		
		jl_major=new JLabel("所属专业：");
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(123,241,120,30));
		cont.add(jl_major);
		
		jcb_major=new JComboBox();
		jcb_major.addItem("请选择专业");
		jcb_major.setFont(font);
		jcb_major.setBounds(new Rectangle(254,241,150,30));
		cont.add(jcb_major);
		
		jb_submit=new JButton("提  交");
		jb_submit.setFont(font);
		jb_submit.addActionListener(new JButtonActionLisneter());
		jb_submit.setBorder(BorderFactory.createRaisedBevelBorder());
		jb_submit.setBounds(new Rectangle(123,342,97,45));
		cont.add(jb_submit);
		
		jb_exit=new JButton("退  出");
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionLisneter());
		jb_exit.setBorder(BorderFactory.createRaisedBevelBorder());
		jb_exit.setBounds(new Rectangle(324,342,97,45));
		cont.add(jb_exit);
  }
	//学院信息
	public void ShowCollage() {
		ResultSet rs = dao.getRs("select * from student_collage");
		try {
			while(rs.next()) {
				String collage_name = rs.getString("collage_name");
				jcb_collage.addItem(collage_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//专业信息
	public void ShowMajor() {
		jcb_major.removeAllItems();
		ResultSet rs = dao.getRs("select * from student_major where collage_name='"+String.valueOf(jcb_collage.getSelectedItem())+"'");
		try {
			while(rs.next()) {
				String collage_name = rs.getString("major_name");
				jcb_major.addItem(collage_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提交按钮
	public void Sumbit() {
		String regex1="^[1-9]{1}$";
		boolean flag1=jtf_classname.getText().matches(regex1);
		if(jtf_classname.getText().length()==0||jtf_classname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入班级","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_collage.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择学院","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(!flag1) {
			JOptionPane.showMessageDialog(null, "班级只有1班-9班，并且是纯数字","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			ResultSet rs = dao.getRs("select * from student_class where major_name='"+String.valueOf(jcb_major.getSelectedItem())+"'");
			try {
				boolean isClass_name=false;
				while(rs.next()) {
					String class_name = rs.getString("class_name");
					if(class_name.equals(jtf_classname.getText())) {
						isClass_name=true;
					}
				}
				if(isClass_name) {
					JOptionPane.showMessageDialog(null, "已有该班级，请重新输入","提示",JOptionPane.INFORMATION_MESSAGE);
				}else {
						int i= dao.getUpdate("insert into student_class (class_id,collage_name,major_name,class_name) values('"+UUID.randomUUID().toString()+"','"+String.valueOf(jcb_collage.getSelectedItem())+"','"+String.valueOf(jcb_major.getSelectedItem())+"','"+jtf_classname.getText()+"')");
						if(i==1) {
							JOptionPane.showMessageDialog(null, "添加班级成功","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "添加班级失败","提示",JOptionPane.ERROR_MESSAGE);
						}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class JComboBoxActionLinster implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jcb_collage) {//选择学院触发
				ShowMajor();
			}
		}
		
	}
	class JButtonActionLisneter implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_submit) {
				Sumbit();
			}
			if(source==jb_exit) {
				dispose();
			}
			
		}
		
	}
}
