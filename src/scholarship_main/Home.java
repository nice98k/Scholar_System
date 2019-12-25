package scholarship_main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import Intelligent_score.Intelligent_Score_AddInfo;
import Intelligent_score.Intelligent_Score_ShowAll;
import class_info.ClassAddInfo;
import class_info.ClassShowAll;
import login_register.Login;
import scholar_compute.ComprehensiveScoreShowAll;
import student_PE.PE_Score_AddInfo;
import student_PE.PE_Score_ShowAll;
import student_course.CourseAddInfo;
import student_course.CourseShowAll;
import student_info.StudentInfoAdd;
import student_info.StudentShowAll;
import student_moral.MoralScoreAddInfo;
import student_moral.MoralScoreShowAll;
import student_score.ScoreAddInfo;
import student_score.ScoreShowAll;
import user_info.UserAddInfo;
import user_info.UserDelete;
import user_info.UserUpdate;


public class Home extends JFrame{
	
	
	
	public int id;
	JPanel cont=(JPanel) getContentPane();
	ClassLoader cl=this.getClass().getClassLoader();
	Toolkit tk=Toolkit.getDefaultToolkit();     //�õ�Ĭ�Ϲ��߰�
	JLabel jl_image=new JLabel(new ImageIcon(tk.getImage(cl.getResource("com/student/images/main.jpg"))));
	Image icon1=tk.getImage(cl.getResource("com/student/images/background1.jpg"));
	Image icon2=tk.getImage(cl.getResource("com/student/images/background2.jpg"));
	Image icon3=tk.getImage(cl.getResource("com/student/images/background3.jpg"));
	Image icon4=tk.getImage(cl.getResource("com/student/images/background4.jpg"));
	Image icon5=tk.getImage(cl.getResource("com/student/images/background5.jpg"));
	Image icon6=tk.getImage(cl.getResource("com/student/images/background6.jpg"));
	JMenuBar jmenubar=new JMenuBar();
	JMenu jmenu_student=new JMenu("ѧ������");
	JMenuItem jmenuitem_student_entering=new JMenuItem("     ¼��");
	JMenuItem jmenuitem_student_manage=new JMenuItem("     ����");
	
	JMenu jmenu_class=new JMenu("�༶����");
	JMenuItem jmenuitem_class_entering=new JMenuItem("     ¼��");
	JMenuItem jmenuitem_class_manage=new JMenuItem("     ����");
	
	JMenu jmenu_college=new JMenu("ѧԺ����");
	JMenuItem jmenuitem_college_entering=new JMenuItem("     ¼��");
	JMenuItem jmenuitem_college_manage=new JMenuItem("     ����");
	
	JMenu jmenu_course=new JMenu("�γ̹���");
	JMenuItem jmenuitem_course_entering=new JMenuItem("     ¼��");
	JMenuItem jmenuitem_course_manage=new JMenuItem("     ����");
	
	JMenu jmenu_mark=new JMenu("�����ɼ�����");
	JMenuItem jmenuitem_mark_entering=new JMenuItem("       ¼��      ");
	JMenuItem jmenuitem_mark_manage=new JMenuItem("       ����      ");
	
	JMenu jmenu_intelligent=new JMenu("�����ɼ�����");
	JMenuItem jmenuitem_intelligent_enter=new JMenuItem("       ¼��      ");
	JMenuItem jmenuitem_intelligent_manage=new JMenuItem("       ����      ");
	
	JMenu jmenu_moral=new JMenu("�����ɼ�����");
	JMenuItem jmenuitem_moral_entering=new JMenuItem("       ¼��      ");
	JMenuItem jmenuitem_moral_manage=new JMenuItem("       ����      ");
	
	JMenu jmenu_PE=new JMenu("�����ɼ�����");
	JMenuItem jmenuitem_PE_entering=new JMenuItem("       ¼��      ");
	JMenuItem jmenuitem_PE_manage=new JMenuItem("       ����      ");
	
	JMenu jmenu_Comprehensive=new JMenu("�ۺϳɼ�����");
	JMenuItem jmenuitem_Comprehensive_manage=new JMenuItem("       ����      ");
	
	JMenu jmenu_user=new JMenu("�û�����");
	JMenuItem jmenuitem_add_user=new JMenuItem("����û�");
	JMenuItem jmenuitem_delete_user=new JMenuItem("ɾ���û�");
	JMenuItem jmenuitem_update_password=new JMenuItem("�޸�����");
	
	JMenu jmenu_about=new JMenu("����");
	JMenuItem jmenuitem_about_me=new JMenuItem("��������");
	
	JMenu jmenu_system=new JMenu("ϵͳ");
	JMenuItem jmenuitem_background=new JMenuItem("��������");
	JMenuItem jmenuitem_restart=new JMenuItem("��������");
	JMenuItem jmenuitem_exit=new JMenuItem("�˳�");

	public Home(int t) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 30, 1000, 600);
		// ���ò˵���
		this.id=t;
		this.setJMenuBar(jmenubar);
		init();
		this.setVisible(true);
	}
	public void init() {
		jmenubar.add(jmenu_student);
		jmenu_student.add(jmenuitem_student_entering);
		jmenuitem_student_entering.addActionListener(new JMenuitemListEvent());
		System.out.println(this.id);
		if(this.id==1) {
			jmenu_student.addSeparator();//�ָ���
			jmenu_student.add(jmenuitem_student_manage);
			jmenuitem_student_manage.addActionListener(new JMenuitemListEvent());
		}
			
		
		
//		jmenubar.add(jmenu_class);
//		jmenu_class.add(jmenuitem_class_entering);
//		jmenuitem_class_entering.addActionListener(new JMenuitemListEvent());
//		if(this.id==1) {
//			jmenu_class.addSeparator();
//			jmenu_class.add(jmenuitem_class_manage);
//			jmenuitem_class_manage.addActionListener(new JMenuitemListEvent());
//		}
		

		
		
		
		
		
		jmenubar.add(jmenu_course);
		jmenu_course.add(jmenuitem_course_entering);
		jmenuitem_course_entering.addActionListener(new JMenuitemListEvent());
		if(this.id==1) {
			jmenu_course.addSeparator();
			jmenu_course.add(jmenuitem_course_manage);
			jmenuitem_course_manage.addActionListener(new JMenuitemListEvent());
		}
		
		
		jmenubar.add(jmenu_mark);
		jmenu_mark.add(jmenuitem_mark_entering);
		jmenuitem_mark_entering.addActionListener(new JMenuitemListEvent());
		if(this.id==1) {
			jmenu_mark.addSeparator();
			jmenu_mark.add(jmenuitem_mark_manage);
			jmenuitem_mark_manage.addActionListener(new JMenuitemListEvent());
		}
		
		jmenubar.add(jmenu_intelligent);
		jmenu_intelligent.add(jmenuitem_intelligent_enter);
		jmenuitem_intelligent_enter.addActionListener(new JMenuitemListEvent());
		if(this.id==1) {
			jmenu_intelligent.addSeparator();
			jmenu_intelligent.add(jmenuitem_intelligent_manage);
			jmenuitem_intelligent_manage.addActionListener(new JMenuitemListEvent());
		}
		
		
		
		jmenubar.add(jmenu_moral);
		jmenu_moral.add(jmenuitem_moral_entering);
		jmenuitem_moral_entering.addActionListener(new JMenuitemListEvent());
		if(this.id==1) {
			jmenu_moral.addSeparator();
			jmenu_moral.add(jmenuitem_moral_manage);
			jmenuitem_moral_manage.addActionListener(new JMenuitemListEvent());
		}
		
		jmenubar.add(jmenu_PE);
		jmenubar.add(jmenu_Comprehensive);
		jmenu_PE.add(jmenuitem_PE_entering);
		jmenuitem_PE_entering.addActionListener(new JMenuitemListEvent());
		if(this.id==1) {
			jmenu_PE.addSeparator();
			jmenu_PE.add(jmenuitem_PE_manage);
			jmenuitem_PE_manage.addActionListener(new JMenuitemListEvent());
			
//			jmenu_Comprehensive.addSeparator();
			jmenu_Comprehensive.add(jmenuitem_Comprehensive_manage);
			jmenuitem_Comprehensive_manage.addActionListener(new JMenuitemListEvent());
			
			
		}
		
		
		
		
		if(this.id==1) {
			jmenubar.add(jmenu_user);
			jmenu_user.add(jmenuitem_add_user);
			jmenuitem_add_user.addActionListener(new JMenuitemListEvent());
			jmenu_user.add(jmenuitem_delete_user);
			jmenuitem_delete_user.addActionListener(new JMenuitemListEvent());
			jmenu_user.add(jmenuitem_update_password);
			jmenuitem_update_password.addActionListener(new JMenuitemListEvent());
		}
		
		
		
		jmenubar.add(jmenu_about);
		jmenu_about.add(jmenuitem_about_me);
		jmenuitem_about_me.addActionListener(new JMenuitemListEvent());
		
		jmenubar.add(jmenu_system);
		jmenu_system.add(jmenuitem_background);
		jmenuitem_background.addActionListener(new JMenuitemListEvent());
		jmenu_system.add(jmenuitem_restart);
		jmenuitem_restart.addActionListener(new JMenuitemListEvent());
		jmenu_system.add(jmenuitem_exit);
		jmenuitem_exit.addActionListener(new JMenuitemListEvent());
		//����ͼƬ
		jl_image.setBounds(new Rectangle(100,20,1366,768));
		cont.add(jl_image);
	}
	
	class JMenuitemListEvent implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jmenuitem_about_me) {
				About about=new About();//����
			}
			if(source==jmenuitem_restart) {
				Login login=new Login();//����
				dispose();
			}
			if(source==jmenuitem_exit) {
				System.exit(0);//�˳�
			}
			if(source==jmenuitem_background) {
				BackgroundPrcture back=new BackgroundPrcture();		//��������
			}
			if(source==jmenuitem_student_entering) {
				StudentInfoAdd add=new StudentInfoAdd(); //���ѧ����Ϣ
			}
			if(source==jmenuitem_student_manage) {
				StudentShowAll showall=new StudentShowAll();//����ѧ����Ϣ
			}
			if(source==jmenuitem_class_entering) {
				ClassAddInfo add=new ClassAddInfo();//��Ӱ༶��Ϣ
			}
			if(source==jmenuitem_class_manage) {//����༶��Ϣ
				ClassShowAll showall=new ClassShowAll();
			}
			if(source==jmenuitem_course_entering) {
				CourseAddInfo add=new CourseAddInfo();
			}
			if(source==jmenuitem_course_manage) {
				CourseShowAll showall=new CourseShowAll();
			}
			if(source==jmenuitem_mark_entering) {
				ScoreAddInfo add=new ScoreAddInfo();
			}
			if(source==jmenuitem_mark_manage) {
				ScoreShowAll add=new ScoreShowAll();
			}
			
			if(source==jmenuitem_intelligent_enter) {
				Intelligent_Score_AddInfo add=new Intelligent_Score_AddInfo();
			}
			if(source==jmenuitem_intelligent_manage) {
				Intelligent_Score_ShowAll add=new Intelligent_Score_ShowAll();
			}
			
			
			if(source==jmenuitem_Comprehensive_manage) {
				ComprehensiveScoreShowAll add=new ComprehensiveScoreShowAll();
			}
			
			
			
			
			if(source==jmenuitem_moral_entering) {
				MoralScoreAddInfo add=new MoralScoreAddInfo();
			}
			if(source==jmenuitem_moral_manage) {
				MoralScoreShowAll add=new MoralScoreShowAll();
			}
			if(source==jmenuitem_add_user) {
				UserAddInfo add=new UserAddInfo();
			}
			
			if(source==jmenuitem_PE_entering) {
				PE_Score_AddInfo add=new PE_Score_AddInfo();
			}
			if(source==jmenuitem_PE_manage) {
				PE_Score_ShowAll add=new PE_Score_ShowAll();
			}
			
			if(source==jmenuitem_delete_user) {
				UserDelete delete=new UserDelete();
			}
			if(source==jmenuitem_update_password) {
				UserUpdate update=new UserUpdate();
			}
		}
		
	}
	//��������
	class BackgroundPrcture extends JDialog{

		private final int back_kind = 6;
		private Font f = new Font("΢���ź�",Font.PLAIN,15);
		private JPanel p = new JPanel();
		public BackgroundPrcture(){
			 setTitle("��������");//���ô������
			 Image img=Toolkit.getDefaultToolkit().getImage("title.png");//����ͼ��
			 setIconImage(img);
		     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		     setModal(true);//����Ϊģ̬����
		     setSize(650,390);
		     setResizable(false);
		     setLocationRelativeTo(null);
		     
		     //��ӱ���ͼƬ
		     ImageIcon background[] = new ImageIcon[back_kind];
		     background[0] = new ImageIcon(icon1);
		     background[1] = new ImageIcon(icon2);
		     background[2] = new ImageIcon(icon3);
		     background[3] = new ImageIcon(icon4);
		     background[4] = new ImageIcon(icon5);
		     background[5] = new ImageIcon(icon6);

		     background[0].setImage(background[0].getImage().getScaledInstance(200,110,Image.SCALE_FAST));//����
		     background[1].setImage(background[1].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[2].setImage(background[2].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[3].setImage(background[3].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[4].setImage(background[4].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[5].setImage(background[5].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     
		     JLabel Back_label[] = new JLabel[back_kind];
		     JButton choose[] = new JButton[back_kind];
		     for(int i = 0;i < back_kind;i++)
		     {
		    	 Back_label[i] = new JLabel(background[i],SwingConstants.LEFT);
		    	 Back_label[i].setFont(f);
		    	 Back_label[i].setHorizontalTextPosition(SwingConstants.CENTER);
		    	 Back_label[i].setVerticalTextPosition(SwingConstants.BOTTOM);
		    	 
		    	 choose[i] = new JButton("ѡ��");
		    	 choose[i].setFont(f);
		    	 p.add(choose[i]);
		    	 p.add(Back_label[i]);
		     }
		     
		     this.add(p,BorderLayout.CENTER);
		     p.setBackground(Color.white);
		     p.setLayout(null);
		     
		     
		     
		     Back_label[0].setBounds(10, 0, 200, 120);//ģ̬����ͼƬ��λ��
		     choose[0].setBounds(70, 140, 80, 25);//ģ̬���ڰ�ť��λ��
		     Back_label[1].setBounds(220, 0, 200, 120);
		     choose[1].setBounds(280, 140, 80, 25);
		     Back_label[2].setBounds(430, 0, 200, 120);
		     choose[2].setBounds(490, 140, 80, 25);
		     Back_label[3].setBounds(10, 180, 200, 120);
		     choose[3].setBounds(70, 320, 80, 25);
		     Back_label[4].setBounds(220, 180, 200, 120);
		     choose[4].setBounds(280, 320, 80, 25);
		     Back_label[5].setBounds(430, 180, 200, 120);
		     choose[5].setBounds(490, 320, 80, 25);
		     
		     for(int i = 0;i < back_kind;i++)
		     {
		    	 choose[i].addActionListener(new ActionListener(){
		         	public void actionPerformed(ActionEvent e){
		        		if(e.getSource() == choose[0])
		        		{
		        			background[0] = new ImageIcon(icon1);
		        			background[0].setImage(background[0].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[0]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        		else if(e.getSource() == choose[1])
		        		{
		       		     	background[1] = new ImageIcon(icon2);
		        			background[1].setImage(background[1].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[1]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        		else if(e.getSource() == choose[2])
		        		{
		        			background[2] = new ImageIcon(icon3);
		        			background[2].setImage(background[2].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[2]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        		else if(e.getSource() == choose[3])
		        		{
		        			background[3] = new ImageIcon(icon4);
		        			background[3].setImage(background[3].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[3]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        		else if(e.getSource() == choose[4])
		        		{
		        			background[4] = new ImageIcon(icon5);
		        			background[4].setImage(background[4].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[4]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        		else if(e.getSource() == choose[5])
		        		{
		        			background[5] = new ImageIcon(icon6);
		        			background[5].setImage(background[5].getImage().getScaledInstance(1366,768,Image.SCALE_SMOOTH));
		        			jl_image.setIcon(background[5]);
		        			//JOptionPane.showMessageDialog(null,"���ĳɹ�!\n");
		        		}
		        	}
		        });
		     }
		     setVisible(true);
		}
	}


}