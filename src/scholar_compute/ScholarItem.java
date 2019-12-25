package scholar_compute;

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
import student_moral.MoralScoreShowAll;

public class ScholarItem extends JDialog{
	JLabel jl_title=new JLabel("奖 学 金 奖 项 输 入");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JLabel jl_scholar0=new JLabel("国家奖学金：");
	JTextField jtf_s0=new JTextField(30);
	JLabel jl_s1=new JLabel("国家励志奖学金：");
	JTextField jtf_s1=new JTextField(25);
	JLabel jl_s2=new JLabel("校一等奖学金：");
	JTextField jtf_s2=new JTextField();
	JLabel jl_s3=new JLabel("校二等奖学金：");
	JTextField jtf_s3=new JTextField();
	JLabel jl_s4=new JLabel("校三等奖学金：");
	JTextField jtf_s4=new JTextField();
	
	int s0=0;
	int s1=0;
	int s2=0;
	int s3=0;
	int s4=0;
	
	JButton jb_save=new JButton("保存");
	JButton jb_exit=new JButton("退出");
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();


    
	
	
	public ScholarItem() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(410,500);
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
		
		jl_scholar0.setFont(font);
		jl_scholar0.setBounds(new Rectangle(65,87,120,30));
		cont.add(jl_scholar0);
		
		jtf_s0.setFont(font);
		jtf_s0.setBounds(new Rectangle(186,87,150,30));
		cont.add(jtf_s0);
		
		jl_s1.setFont(font);
		jl_s1.setBounds(new Rectangle(65,145,120,30));
		cont.add(jl_s1);
		
		jtf_s1.setFont(font);
		jtf_s1.setBounds(new Rectangle(186,145,150,30));
		cont.add(jtf_s1);
		
		jl_s2.setFont(font);
		jl_s2.setBounds(new Rectangle(65,203,120,30));
		cont.add(jl_s2);
		
		jtf_s2.setFont(font);
		jtf_s2.setBounds(new Rectangle(186,203,150,30));
		cont.add(jtf_s2);
		
		jl_s3.setFont(font);
		jl_s3.setBounds(new Rectangle(65,256,120,30));
		cont.add(jl_s3);
		
		jtf_s3.setFont(font);
		jtf_s3.setBounds(new Rectangle(186,256,150,30));
		cont.add(jtf_s3);
		
		jl_s4.setFont(font);
		jl_s4.setBounds(new Rectangle(65,308,120,30));
		cont.add(jl_s4);
		
		jtf_s4.setFont(font);
		jtf_s4.setBounds(new Rectangle(186,308,150,30));
		cont.add(jtf_s4);
		
		
		jb_save.setFont(font);
		jb_save.addActionListener(new JButtonActionListener());
		jb_save.setBounds(new Rectangle(60,376,86,40));
		cont.add(jb_save);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(240,376,86,40));
		cont.add(jb_exit);
		

	}
	public static void main(String[] args) {
		new ScholarItem();
	}
	
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_save) {
				if(!jtf_s0.getText().trim().equals("")) {
					s0=Integer.valueOf(jtf_s0.getText().trim());
				}
				if(!jtf_s1.getText().trim().equals("")) {
					s1=Integer.valueOf(jtf_s1.getText().trim());
				}
				if(!jtf_s2.getText().trim().equals("")) {
					s2=Integer.valueOf(jtf_s2.getText().trim());
				}
				if(!jtf_s3.getText().trim().equals("")) {
					s3=Integer.valueOf(jtf_s3.getText().trim());
				}
				if(!jtf_s4.getText().trim().equals("")) {
					s4=Integer.valueOf(jtf_s4.getText().trim());
				}
//				System.out.println(s0+" "+s1+" "+s2+" "+s3+" "+s4);
				JOptionPane.showMessageDialog(null, "保存完成！");
				dispose();
			}
			if(source==jb_exit) {
				dispose();
			}
			
		}
		
	}
}
