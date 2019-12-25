package user_info;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data_base_dao.UserDao;

public class UserAddInfo extends JDialog{
	JLabel jl_title=new JLabel("��  ��  ��  ��");
	Font f=new Font("΢��",Font.PLAIN,25);
	Font font=new Font("΢��",Font.PLAIN,18);
	JLabel jl_username=new JLabel("�û�����");
	JTextField jtf_username=new JTextField();
	JLabel jl_password=new JLabel("��    �룺");
	JPasswordField jpf_password=new JPasswordField();
	JLabel jl_repasswrod=new JLabel("ȷ�����룺");
	JPasswordField jpf_repassword=new JPasswordField();
	JButton jb_sumbit=new JButton("ȷ ��");
	JButton jb_exit=new JButton("�� ��");
	UserDao dao=new UserDao();
	JPanel cont=(JPanel) getContentPane();
	public UserAddInfo() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480,400);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		jl_title.setBounds(new Rectangle(176,10,300,30));
		cont.add(jl_title);
		
		jl_username.setFont(font);
		jl_username.setBounds(new Rectangle(102,87,120,30));
		cont.add(jl_username);
		
		jtf_username.setFont(font);
		jtf_username.setBounds(new Rectangle(213,87,150,30));
		cont.add(jtf_username);
		
		jl_password.setFont(font);
		jl_password.setBounds(new Rectangle(102,155,120,30));
		cont.add(jl_password);
		
		jpf_password.setFont(font);
		jpf_password.setBounds(new Rectangle(213,155,150,30));
		cont.add(jpf_password);
		
		jl_repasswrod.setFont(font);
		jl_repasswrod.setBounds(new Rectangle(89,213,120,30));
		cont.add(jl_repasswrod);
		
		jpf_repassword.setFont(font);
		jpf_repassword.setBounds(new Rectangle(213,213,150,30));
		cont.add(jpf_repassword);
		
		jb_sumbit.setFont(font);
		jb_sumbit.addActionListener(new JButtonActionListener());
		jb_sumbit.setBounds(new Rectangle(84,296,83,40));
		cont.add(jb_sumbit);
		
		jb_exit.setFont(font);
		jb_exit.addActionListener(new JButtonActionListener());
		jb_exit.setBounds(new Rectangle(304,296,83,40));
		cont.add(jb_exit);
	}
	//���ȷ�ϰ�ť
	public void RegistSumbit() {
		String jtf_value=jtf_username.getText();
		String jpf_value=jpf_password.getText();
		String rejpf_value=jpf_repassword.getText();
		String regex1="^[a-z0-9A-Z\\u4e00-\\u9fa5]{3,6}$";//�û�����3-6λ
        boolean flag1=jtf_value.matches(regex1);
        String regex2="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$"; //�����������ĸ������6,��16λ
        boolean flag2=jpf_value.matches(regex2);
        if(!flag1) {
        	 JOptionPane.showMessageDialog(null,"�û���������3-6λ","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
        	 jtf_username.setText("");
        }else if(!flag2) {
        	JOptionPane.showMessageDialog(null,"�����������ĸ������6,��16λ","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
        	jpf_password.setText("");
        }else if(!rejpf_value.equals(jpf_value)){
        	JOptionPane.showMessageDialog(null,"�������벻һ�£�����������","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
        	jpf_repassword.setText("");
        }else {
			ResultSet rs = dao.getRs("select * from student_user");
			boolean isReName = false;
			try {
				while (rs.next()) {
					String student_name = rs.getString("student_name");
					if (student_name.equals(jtf_username.getText().trim())) {
						isReName = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isReName) {
				JOptionPane.showMessageDialog(null, "���и��û���������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				jtf_username.setText("");
			} else {
				int update = dao.getUpdate("insert into student_user () values('" + UUID.randomUUID().toString() + "','"
						+ jtf_username.getText().trim() + "','" + jpf_password.getText().trim() + "')");
				if (update == 1) {
					JOptionPane.showMessageDialog(null, "ע��ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "ע��ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
        }
		
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_sumbit) {
				RegistSumbit();
			}
			if(source==jb_exit) {
				dispose();
			}
		}
		
	}
}
