package login_register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;

import data_base_dao.UserDao;

public class Register extends JFrame{
	
	JLabel jl_student_name,jl_student_password,jl_student_repassword;
	JLabel jl_image;
	JTextField jtf_student_name;
	JPasswordField jpf_student_password,jpf_student_repassword;
	JButton jb_submit,jb_exit;
	Container cont=getContentPane();
	ClassLoader cl=this.getClass().getClassLoader();
	Toolkit tk=Toolkit.getDefaultToolkit();
	Image image=tk.getImage(cl.getResource("com/student/images/register.jpg"));
	Login login;
	
	//���췽��
	public Register() {
		super("ע��");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(400, 150, 500, 450));
//		this.setBounds(400, 150, 500, 450);
//		this.setUndecorated(true);//ȥ��Ĭ�ϱ�����  setUndecorated(boolean undecorated) ���û����ô� frame ��װ�Ρ���Frame ���һ����Ա������
		init();
		this.setResizable(false);
		this.setVisible(true);
	}
	public void init() {
		jl_student_name=new JLabel("�û���:");
		jl_student_name.setBounds(new Rectangle(83, 87, 100, 25));
		jl_student_name.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_name);
		jtf_student_name=new JTextField();
		jtf_student_name.setBounds(new Rectangle(257,87,170,30));
		jtf_student_name.setFont(new Font("Dianlg",Font.PLAIN,13));
		jtf_student_name.setToolTipText("�û���������3-6λ");
		cont.add(jtf_student_name);
		//����
		jl_student_password=new JLabel("����:");
		jl_student_password.setBounds(new Rectangle(83, 165, 100, 25));
		jl_student_password.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_password);
		jpf_student_password=new JPasswordField();
		jpf_student_password.setBounds(new Rectangle(257, 165, 170, 30));
		jpf_student_password.setFont(new Font("Dianlg",Font.PLAIN,13));
		jpf_student_password.setToolTipText("�����������ĸ������6,��16λ");
		cont.add(jpf_student_password);
		//ȷ������
		jl_student_repassword=new JLabel("ȷ������:");
		jl_student_repassword.setBounds(new Rectangle(83, 238, 100, 25));
		jl_student_repassword.setFont(new Font("Dianlg",Font.PLAIN,20));
		cont.add(jl_student_repassword);
		jpf_student_repassword=new JPasswordField();
		jpf_student_repassword.setBounds(new Rectangle(257, 232, 170, 30));
		jpf_student_repassword.setFont(new Font("Dianlg",Font.PLAIN,13));
		jpf_student_repassword.setToolTipText("�����������һ��");
		cont.add(jpf_student_repassword);
		
		//ע�ᰴť
		jb_submit=new JButton("ע��");
		jb_submit.setBounds(new Rectangle(97,317,89,45));
		jb_submit.setFont(new Font("Dianlg",Font.PLAIN,20));
		jb_submit.setContentAreaFilled(false);//����ť��͸��
		jb_submit.setBorder(null);
		jb_submit.addActionListener(new JButtonListAction());
		cont.add(jb_submit);
		
		//�ر�
		jb_exit=new JButton("�����˺�,�����½");
		jb_exit.setBounds(new Rectangle(257,317,200,45));
		jb_exit.setFont(new Font("Dianlg",Font.PLAIN,20));
		jb_exit.setContentAreaFilled(false);//����ť��͸��
		jb_exit.setBorder(null);
		jb_exit.addActionListener(new JButtonListAction());
		cont.add(jb_exit);
		
		//����ͼƬ
		jl_image=new JLabel(new ImageIcon(image));
		jl_image.setBounds(new Rectangle(0, 0, getWidth(), getHeight()));
		cont.add(jl_image);
	}
	
	
	class JButtonListAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb_submit) {
				UserDao dao=new UserDao();
				String jtf_value=jtf_student_name.getText();
				String jpf_value=jpf_student_password.getText();
				String rejpf_value=jpf_student_repassword.getText();
				String regex1="^[a-z0-9A-Z\\u4e00-\\u9fa5]{3,6}$";//�û�����3-6λ
		        boolean flag1=jtf_value.matches(regex1);
		        String regex2="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$"; //�����������ĸ������6,��16λ
		        boolean flag2=jpf_value.matches(regex2);
		        if(!flag1) {
		        	 JOptionPane.showMessageDialog(null,"�û���������3-6λ","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		        	 jtf_student_name.setText("");
		        }else if(!flag2) {
		        	JOptionPane.showMessageDialog(null,"�����������ĸ������6,��16λ","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		        	jpf_student_password.setText("");
		        }else if(!rejpf_value.equals(jpf_value)){
		        	JOptionPane.showMessageDialog(null,"�������벻һ�£�����������","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		        	jpf_student_repassword.setText("");
		        }else {
		        	//�ж��Ƿ����и��û���
		        	boolean isName=dao.RepeatName(jtf_value); 
		        	if(isName) {
		        		//������
		        		JOptionPane.showMessageDialog(null,"���и��û���������������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
		        		jtf_student_name.setText("");
		        	}else {
		        		//û��
		        		boolean isRegister=dao.register(jtf_value,jpf_value);
			        	if(isRegister) {
			        		//�ɹ�
				        	int i=JOptionPane.showConfirmDialog(null,"ע��ɹ����Ƿ��½","��ʾ��Ϣ",JOptionPane.YES_NO_OPTION);
				        	//0��ʾ�������   1����˷�
				        	if(i==0) {
				        		login=new Login();
				        		dispose();   //dispose()���ͷ����б�����Ļ��Դ����Щ Component ����Դ�����ƻ�������ʹ�õ������ڴ涼�����ص�����ϵͳ
				        	} 
			        	}else {
			        		//ʧ��
				        	JOptionPane.showMessageDialog(null,"ע��ʧ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
			        	}
		        	}

		        }
				
			}
			
			if(e.getSource()==jb_exit) {
				 login=new Login();
				 dispose();
			}
		}
		
	}
}
