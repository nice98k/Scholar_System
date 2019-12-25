package student_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.*;

import data_base_dao.UserDao;

public class StudentUpdate extends JDialog{
	JLabel jl_number,jl_name,jl_email,jl_collage,jl_major,jl_class;
	JTextField jtf_number,jtf_name,jtf_email;
	JButton jb_submit,jb_exit;
	JComboBox jcb_collage=new JComboBox();
	JComboBox jcb_major=new JComboBox();
	JComboBox jcb_class=new JComboBox();
	JButton jl_sumbit;
	JLabel jl_tilte=new JLabel("�� ��  ѧ  ��  ��  Ϣ");
	JPanel cont= (JPanel) getContentPane();
	UserDao dao=new UserDao();
	UserDao conn=new UserDao();
	Font font=new Font("΢�����",Font.PLAIN,18);
	String find;
	String Tablename;
	public static boolean isUpdate;//�ж��Ƿ��޸ĳɹ�
	private String student_id;
	public StudentUpdate() {
		
	}
	public StudentUpdate(String find,String Tablename) {
		this.Tablename=Tablename;
		this.find=find;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);
		init();
		Echo();
		this.setVisible(true);
	}
	public void init() {
		jl_tilte.setFont(new Font("΢��",Font.BOLD,25));
		jl_tilte.setBounds(new Rectangle(154,28,240,30));
		cont.add(jl_tilte);
		
		jl_number=new JLabel("ѧ �� ѧ ��:");
		jl_number.setFont(font);
		jl_number.setBounds(new Rectangle(83,89,150,25));
		cont.add(jl_number);
		jtf_number=new JTextField();
		jtf_number.setFont(font);
		jtf_number.setBounds(new Rectangle(223,89,150,25));
		cont.add(jtf_number);
		
		jl_name=new JLabel("ѧ �� �� ��:");
		jl_name.setFont(font);
		jl_name.setBounds(new Rectangle(83,146,150,25));
		cont.add(jl_name);
		jtf_name=new JTextField();
		jtf_name.setFont(font);
		jtf_name.setBounds(new Rectangle(223,146,150,25));
		cont.add(jtf_name);
		
		jl_email=new JLabel("ѧ �� �� ��:");
		jl_email.setFont(font);
		jl_email.setBounds(new Rectangle(83,198,150,25));
		cont.add(jl_email);
		jtf_email=new JTextField();
		jtf_email.setFont(font);
		jtf_email.setBounds(new Rectangle(223,198,230,25));
		cont.add(jtf_email);
		
		jl_collage=new JLabel("�� �� ѧ Ժ:");
		jl_collage.setFont(font);
		jl_collage.setBounds(new Rectangle(23,254,150,25));
		cont.add(jl_collage);
		
		ShowCollageName();
		jcb_collage.setFont(font);
		jcb_collage.addActionListener(new StudentUpdateAction());
		jcb_collage.setBounds(new Rectangle(123,254,120,25));
		cont.add(jcb_collage);
		
		jl_major=new JLabel("�� �� ר ҵ:");
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(255,254,150,25));
		cont.add(jl_major);
		
		jcb_major.setFont(font);
		jcb_major.addActionListener(new StudentUpdateAction());
		jcb_major.setBounds(new Rectangle(355,254,120,25));
		cont.add(jcb_major);
		
		jl_class=new JLabel("�� �� �� ��:");
		jl_class.setFont(font);
		jl_class.setBounds(new Rectangle(23,311,150,25));
		cont.add(jl_class);
		
		jcb_class.setFont(font);
		jcb_class.setBounds(new Rectangle(125,311,120,25));
		cont.add(jcb_class);
		
		jb_submit=new JButton("�� ��");
		jb_submit.setFont(font);
		jb_submit.addActionListener(new StudentUpdateJButton());
		jb_submit.setBounds(new Rectangle(155,376,80,40));
		cont.add(jb_submit);
		
		jb_exit=new JButton("�� ��");
		jb_exit.setFont(font);
		jb_exit.addActionListener(new StudentUpdateJButton());
		jb_exit.setBounds(new Rectangle(301,376,80,40));
		cont.add(jb_exit);
	}
	//���޸ĺõ����ݱ��浽���ݿ�
	public void UpdateStudent() {
		String number = jtf_number.getText();//ѧ��
		String name = jtf_name.getText();//����
		String email = jtf_email.getText();//����
		String collage = String.valueOf(jcb_collage.getSelectedItem());//ѧԺ
		String major = String.valueOf(jcb_major.getSelectedItem());//רҵ
		String stu_class = String.valueOf(jcb_class.getSelectedItem());//�༶
		String regex1="^[0-9]{1,4}$";
		boolean flag1=number.matches(regex1);
		String regex2="^[a-z0-9A-Z\\u4e00-\\u9fa5]{2,6}$";
		boolean flag2=name.matches(regex2);
		String regex3="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean flag3=email.matches(regex3);
		if(!flag1) {
			JOptionPane.showMessageDialog(this, "ѧ�ű���������,���ܳ���4λ","����",JOptionPane.WARNING_MESSAGE);
			jtf_number.setText("");
		}else if(!flag2) {
			JOptionPane.showMessageDialog(this, "��������������,��ĸ,����,����2-6λ","����",JOptionPane.WARNING_MESSAGE);
			jtf_name.setText("");
		}else if(!flag3) {
			JOptionPane.showMessageDialog(this, "�����ʽ����ȷ","����",JOptionPane.WARNING_MESSAGE);
			jtf_email.setText("");
		}else{
			//���޸�ѧ�ŵ������	���ұ���е�ѧ�Ÿ����ı����е�ѧ��һ���ǣ�˵������ԭ����ѧ�ţ����Բ����޸�
			if(find.equals(jtf_number.getText().trim())) {
				Update();
			}else {
				//�޸�ѧ�ŵ������
				boolean isNumber=false;
				ResultSet rs = dao.getRs("select * from student_stu");
				try {
					while(rs.next()) {
						if(number.equals(rs.getString("student_number").trim())) {
							isNumber=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isNumber) {
					JOptionPane.showMessageDialog(null, "���и�ѧ������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else {
					Update();
				}
			}
			
		}
	}
	public void Update() {
		int i = dao.getUpdate("update student_stu set student_number='"+jtf_number.getText().trim()+"',student_name='"+jtf_name.getText().trim()+"',student_email='"+jtf_email.getText().trim()+"',student_collage='"+String.valueOf(jcb_collage.getSelectedItem())+"',student_major='"+String.valueOf(jcb_major.getSelectedItem())+"',student_class='"+String.valueOf(jcb_class.getSelectedItem())+"' where student_id='"+student_id+"'");
		if (i == 1) {
			JOptionPane.showMessageDialog(this, "ѧ���޸ĳɹ���", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
			isUpdate=true;
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "ѧ���޸�ʧ�ܣ�", "�� ʾ", JOptionPane.ERROR_MESSAGE);
		}
	}
	//����ѧ�Ų�ѯ��Ϣ���Ե�ҳ����
	public void Echo() {
		ResultSet rs = dao.getRs("select * from student_stu where student_number='"+find+"'");
		try {
			while(rs.next()) {
				student_id = rs.getString("student_id");
				jtf_number.setText(find);
				jtf_name.setText(rs.getString("student_name"));
				jtf_email.setText(rs.getString("student_email"));
				jcb_collage.setSelectedItem(rs.getString("student_collage"));
				jcb_major.setSelectedItem(rs.getString("student_major"));
				jcb_class.setSelectedItem(rs.getString("student_class"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ȡѧԺ��������Ϣ��������
	public void ShowCollageName() {
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
	//��ȡרҵ��������Ϣ��������
	public void ShowMajorName() {
		jcb_major.removeAllItems();//ÿ���л���Ҫɾ���ϴε�����
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
	//��ȡ�༶��������Ϣ��������
		public void ShowClassName() {
			jcb_class.removeAllItems();
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
	class StudentUpdateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jcb_collage) {//ѡ��ѧԺ����
				ShowMajorName();
			}
			if(source==jcb_major) {//ѡ��רҵ����
				ShowClassName();
			}
		}
		
	}
	class StudentUpdateJButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_submit) {//�ύʱ����
				UpdateStudent();
			}
			if(source==jb_exit) {//�˳�
				dispose();
			}
		}
		
	}
}
