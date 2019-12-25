package student_score;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import data_base_dao.UserDao;

public class ScoreShowAll extends JDialog{
	JLabel jl_title=new JLabel("��   ��   ��   ��");
	Font f=new Font("΢��",Font.BOLD,25);
	Font font=new Font("΢��",Font.PLAIN,18);
	JButton jb_print=new JButton("�� ӡ");
	JButton jb_sort=new JButton("�� ��");
	JButton jb_avg=new JButton("ƽ����");
	JButton jb_fail=new JButton("������");
	JButton jb_update=new JButton("�޸�");
	JButton jb_delete=new JButton("ɾ��");
	JButton jb_return=new JButton("����");
	JPanel jp=new JPanel();
	JTable table;
	DefaultTableModel model;
	JScrollPane jsp=new JScrollPane();
	UserDao dao=new UserDao();
	String sql;
	int count=0;
	private int row;
	private String valueName;
	private String valueId;
	private String course=null;
	public ScoreShowAll() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(510,650);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new XYLayout());
		this.setResizable(false);
		init();
		this.setVisible(true);
	}
	public void init() {
		jl_title.setFont(f);
		this.getContentPane().add(jl_title,new XYConstraints(154, 10, 300, 30));
		
		jp.setLayout(new XYLayout());
		jp.setBorder(BorderFactory.createEtchedBorder());
		
		jb_print.setFont(font);
		jb_print.addActionListener(new JButtonActionListener());
		jp.add(jb_print,new XYConstraints(10, 15, 88, 37));
		
		jb_sort.setFont(font);
		jb_sort.addActionListener(new JButtonActionListener());
		jp.add(jb_sort,new XYConstraints(134, 15, 88, 37));
		
		jb_avg.setFont(font);
		jb_avg.addActionListener(new JButtonActionListener());
		jp.add(jb_avg,new XYConstraints(260, 15, 88, 37));
		
		jb_fail.setFont(font);
		jb_fail.addActionListener(new JButtonActionListener());
		jp.add(jb_fail,new XYConstraints(372, 15, 88, 37));
		this.getContentPane().add(jp,new XYConstraints(10, 56, 480, 75));
		
		sql="select * from student_score";
		UpdateTable();
		jsp.getViewport().add(table);
		this.getContentPane().add(jsp,new XYConstraints(10, 148, 480, 387));
		
		jb_update.setFont(font);
		jb_update.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_update,new XYConstraints(56, 555, 86, 41));
		
		jb_delete.setFont(font);
		jb_delete.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_delete,new XYConstraints(201, 555, 86, 41));
		
		jb_return.setFont(font);
		jb_return.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_return,new XYConstraints(344, 555, 86, 41));
	}
	public void UpdateTable() {
		Vector colum=new Vector();
		
		colum.add("ѧ��ѧ��");
		colum.add("ѧ������");
		colum.add("�γ�����");
		colum.add("�γ�ѧ��");
		colum.add("�γ̷���");
		Vector row=new Vector();
		ResultSet rs = dao.getRs(sql);
		try {
			while(rs.next()) {
				Vector value=new Vector();
				
				ResultSet rs1 = dao.getRs("select * from student_stu where student_name='"+rs.getString("student_name")+"'");
				while(rs1.next()) {
					value.add(rs1.getString("student_number"));
				}
				value.add(rs.getString("student_name"));
				value.add(rs.getString("course_name"));
				ResultSet rs2 = dao.getRs("select * from student_course where course_name='"+rs.getString("course_name")+"'");
				while(rs2.next())
				{
					value.add(rs2.getString("course_score"));
				}
				

//				value.add(rs.getString("course_score"));
				value.add(rs.getString("score"));
				row.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model=new DefaultTableModel(row,colum) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		table=new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		jsp.getViewport().add(table);
		
	}
	//�������ť
	public void score_order(){
		count++;
		if(count%2==1) {//�Գɼ�����
			jb_sort.setText("�� ��");
			sql = "select * from student_score order by score asc";
			UpdateTable();
		}else {//�Գɼ�����
			jb_sort.setText("�� ��");
			sql = "select * from student_score order by score desc";
			UpdateTable();
		}
		
	}
	//���ƽ����
	public void score_avg() {
		float f_average = 0;//ƽ����
			//����ƽ����	//С��ƽ����
		int averageBig = 0, averageSmall = 0;
		try {
			// ���ƽ����
			ResultSet rs_average = dao.getRs("select avg(score) as scoreAverage from student_score");
			while (rs_average.next()) {
				f_average = Float.valueOf(rs_average.getString("scoreAverage"));
			}

			// ��ô��ڵ���ƽ���ֵ�����
			ResultSet rs_averageBig = dao.getRs("select * from student_score where score>='" + f_average + "'");
			while (rs_averageBig.next()) {
				averageBig++;
			}

			// ���С��ƽ���ֵ�����
			ResultSet rs_averageSmall = dao.getRs("select * from student_score where score<'" + f_average + "'");
			while (rs_averageSmall.next()) {
				averageSmall++;
			}

		} catch (SQLException e1) {
			System.out.println(e1);
		}

		// ��ʾƽ����
		JOptionPane.showMessageDialog(this,
				"ƽ����  = " + f_average + "\n���ڵ���ƽ������ " + averageBig + "��\nС��ƽ������ " + averageSmall + "��", "��ʾ",
				JOptionPane.INFORMATION_MESSAGE, null);
	}
	public static void main(String[] args) {
		new ScoreShowAll();
	}
	
	
	//������
	
	public void Score_fail() {
		int sum=0;
		int failsum=0;
		//��ȡ������
		ResultSet rs = dao.getRs("select * from student_score");
		try {
			while(rs.next()) {
				sum++;
			}
			//����������
		ResultSet rsfail = dao.getRs("select * from student_score where score<60");
		while(rsfail.next()) {
			failsum++;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "��������"+sum+"��\n������������"+failsum+"��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
	//��ȡѡ����һ��
	public void getRow() {
		row = table.getSelectedRow();
		if(row==-1) {
			return;
		}
		valueId = table.getValueAt(row, 0).toString();
		valueName = table.getValueAt(row, 1).toString();
		course=table.getValueAt(row, 2).toString();
		System.out.println(valueName);
	}
	//���ɾ����ť
	public void deleteScore() {
		getRow();
		if(row!=-1) {
			int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i==0) {
				
				ResultSet rs=null;
				double cur_score=0;
				String cur_course_name=null;
				String cur_student_name=null;
				double cur_credit=0;
				
				double sum_sc=0;
				int sum_credit=0;
				int c_sum=0;
				try {
					System.out.println(valueName);
					cur_student_name=valueName;
					rs=dao.getRs("select * from student_score where student_name='"+valueName+"' and course_name='"+course+"'");
					while(rs.next()) {
//						if(cur_student_name.equals(rs.getString("student_name")))
						cur_score=rs.getDouble("score");
						cur_course_name=rs.getString("course_name");
//						cur_student_name=rs.getString("student_name");
					}
					rs=dao.getRs("select * from student_course where course_name='"+cur_course_name+"'");
					while(rs.next()) {
						cur_credit=rs.getDouble("course_score");
					}
					rs=dao.getRs("select * from student_study_score where student_name='"+cur_student_name+"'");
					while(rs.next()) {
						sum_sc=rs.getDouble("creditxscore");
						sum_credit=rs.getInt("credit");
						c_sum=rs.getInt("course_sum");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(cur_score+"  "+cur_course_name+" "+cur_score);
				int res=dao.getUpdate("update student_study_score set creditxscore="+
				        (sum_sc-cur_score*cur_credit)+",credit="+(sum_credit-cur_credit)
				        +",course_sum="+(c_sum-1)+" where student_name='"+cur_student_name+"'"
						);
				if(res==1) {
					JOptionPane.showMessageDialog(null, "����ɼ�ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "����ɼ�ɾ��ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				}
				dao.getUpdate("delete from student_score where student_name='"+valueName+"' and course_name='"+course+"'");
				sql="select * from student_score";
				UpdateTable();
			}
		}else {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����һ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//����޸İ�ť
	public void UpdateScore() {
		getRow();
		if(row!=-1) {
			ScoreUpdate update=new ScoreUpdate(valueName,course);
			if(update.isUpdate) {
				sql="select * from student_score";
				UpdateTable();
			}
		}else {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�һ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_print) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(source==jb_sort) {//����
				score_order();
			}
			if(source==jb_avg) {//ƽ����
				score_avg();
			}
			if(source==jb_fail) {//������
				Score_fail();
			}
			if(source==jb_update) {//�޸�
				UpdateScore();
			}
			if(source==jb_delete) {//ɾ��
				deleteScore();
			}
			if(source==jb_return) {//����
				dispose();
			}
		}
		
	}

}
