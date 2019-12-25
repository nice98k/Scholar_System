package student_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class StudentInfoAdd extends JDialog{
	JLabel jl_number,jl_name,jl_email,jl_collage,jl_major,jl_class;
	JTextField jtf_number,jtf_name,jtf_email;
	JButton jb_submit,jb_exit;
	JComboBox jcb_collage=new JComboBox();
	JComboBox jcb_major=new JComboBox();
	JComboBox jcb_class=new JComboBox();
	JButton jl_sumbit;
	JLabel jl_tilte=new JLabel("��  ��  ѧ  ��  ��  Ϣ");
	JPanel cont= (JPanel) getContentPane();
	UserDao dao=new UserDao();
	UserDao conn=new UserDao();
	Font font=new Font("΢�����",Font.PLAIN,18);
	public StudentInfoAdd() {
		cont.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,495);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);//�ô��ھ���
		this.setResizable(false);
		this.setModal(true);
		cont.setBackground(Color.WHITE);
		init();
		this.setVisible(true);
	}
	public void init(){
		jl_tilte.setFont(new Font("΢��",Font.BOLD,25));
		jl_tilte.setBounds(new Rectangle(124,28,240,30));
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
		
		
		jcb_collage.addItem("��ѡ��ѧԺ");
		ShowCollageName();
		jcb_collage.setFont(font);
		jcb_collage.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jcb_collage.setBounds(new Rectangle(123,254,120,25));
		cont.add(jcb_collage);
		
		jl_major=new JLabel("�� �� ר ҵ:");
		jl_major.setFont(font);
		jl_major.setBounds(new Rectangle(255,254,150,25));
		cont.add(jl_major);
		
		jcb_major.addItem("��ѡ��רҵ");
		jcb_major.setFont(font);
		jcb_major.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jcb_major.setBounds(new Rectangle(355,254,120,25));
		cont.add(jcb_major);
		
		jl_class=new JLabel("�� �� �� ��:");
		jl_class.setFont(font);
		jl_class.setBounds(new Rectangle(23,311,150,25));
		cont.add(jl_class);
		
		jcb_class.addItem("��ѡ��༶");
		jcb_class.setFont(font);
		jcb_class.setBounds(new Rectangle(125,311,120,25));
		cont.add(jcb_class);
		
		jb_submit=new JButton("�� ��");
		jb_submit.setFont(font);
		jb_submit.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jb_submit.setBounds(new Rectangle(155,376,80,40));
		cont.add(jb_submit);
		
		jb_exit=new JButton("�� ��");
		jb_exit.setFont(font);
		jb_exit.addActionListener(new StudentInfoAdd_collage_actionAdapter());
		jb_exit.setBounds(new Rectangle(301,376,80,40));
		cont.add(jb_exit);
	}
//	public static void main(String[] args) {
//		new StudentInfoAdd();
//	}
	//��ȡרҵ��������Ϣ��������
	public void ShowMajorName() {
		jcb_major.removeAllItems();//ÿ���л���Ҫɾ���ϴε�����
		jcb_major.addItem("��ѡ��רҵ");
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
	//��ȡѧԺ��������Ϣ��������
	public void ShowCollageName() {
		jcb_major.removeAllItems();//ÿ���л���Ҫɾ���ϴε�����
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
	//��ȡ�༶��������Ϣ��������
	public void ShowClassName() {
		jcb_class.removeAllItems();
		jcb_class.addItem("��ѡ��༶");
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
	
	//�ύ
	public void AddAllStudent() {
		String number = jtf_number.getText();//ѧ��
		String name = jtf_name.getText();//����
		String email = jtf_email.getText();//����
		String collage = String.valueOf(jcb_collage.getSelectedItem());//ѧԺ
		String major = String.valueOf(jcb_major.getSelectedItem());//רҵ
		String stu_class = String.valueOf(jcb_class.getSelectedItem());//�༶
		String regex1="^[0-9]{10,12}$";
		boolean flag1=number.matches(regex1);
		String regex2="^[a-z0-9A-Z\\u4e00-\\u9fa5]{2,6}$";
		boolean flag2=name.matches(regex2);
		String regex3="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean flag3=email.matches(regex3);
		if(!flag1) {
			JOptionPane.showMessageDialog(this, "ѧ�ű���������,10~12λ","����",JOptionPane.WARNING_MESSAGE);
			jtf_number.setText("");
		}else if(!flag2) {
			JOptionPane.showMessageDialog(this, "��������������,��ĸ,����,����2-6λ","����",JOptionPane.WARNING_MESSAGE);
			jtf_name.setText("");
		}else if(!flag3) {
			JOptionPane.showMessageDialog(this, "�����ʽ����ȷ","����",JOptionPane.WARNING_MESSAGE);
			jtf_email.setText("");
		}else if(jcb_collage.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "��ѡ������ѧԺ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_major.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "��ѡ������רҵ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else if(jcb_class.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "��ѡ�������༶","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			//�ж�ѧ���Ƿ�����
			boolean isNumber=false;
			ResultSet rs1 = dao.getRs("select * from student_stu where student_number='"+number+"'");
			//�ж������Ƿ�����
			boolean isName=false;
			ResultSet rs2 = dao.getRs("select * from student_stu where student_name='"+name+"'");
			try {
				if(rs1.next()) {
					isNumber=true;
				}
				if(rs2.next()) {
					isName=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(isNumber) {
				JOptionPane.showMessageDialog(this, "���и�ѧ��,����������","��ʾ",JOptionPane.WARNING_MESSAGE);
				jtf_number.setText("");
			}else if(isName){
				JOptionPane.showMessageDialog(this, "���и�ѧ������,����������","��ʾ",JOptionPane.WARNING_MESSAGE);
				jtf_name.setText("");
			}else {
				int i = dao.getUpdate(
						"insert into student_stu (student_id,student_number,student_name,student_email,student_collage,student_major,student_class) values ('"
								+ UUID.randomUUID().toString() + "','" + number + "','" + name + "','" + email + "','"
								+ collage + "','" + major + "','" + stu_class + "')");
				if (i == 1) {
					JOptionPane.showMessageDialog(this, "ѧ����Ϣ¼��ɹ���", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
					jtf_number.setText("");
					jtf_name.setText("");
					jtf_email.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "ѧ����Ϣ¼��ʧ�ܣ�", "�� ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		}
	
	}
	
	class StudentInfoAdd_collage_actionAdapter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jcb_collage) {//���ҵ��ѧԺʱ����
				ShowMajorName();
			}
			if(source==jcb_major) {//���ҵ��רҵʱ����
				ShowClassName();
			}
			if(source==jb_submit) {//���ҵ���ύʱ����
				AddAllStudent();
			}
			if(source==jb_exit) {//���ҵ���˳�ʱ����
				dispose();
			}
		}
	}
}



