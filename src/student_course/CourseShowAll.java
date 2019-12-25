package student_course;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data_base_dao.UserDao;


public class CourseShowAll extends JDialog{
	JLabel jl_title=new JLabel("��  ��  ��  ��");
	Font font=new Font("΢��",Font.BOLD,25);
	Font f=new Font("΢��",Font.PLAIN,18);
	JPanel cont=new JPanel();
	JScrollPane jsp=new JScrollPane();
	JTable table;
	JButton jb_update=new JButton("��  ��");
	JButton jb_delete=new JButton("ɾ  ��");
	JButton jb_return=new JButton("��  ��");
	DefaultTableModel model;
	UserDao dao=new UserDao();
	String sql;
	private int row;
	private String valueRow;
	String valueRowName;
	public CourseShowAll() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(508, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setLayout(null);
		init();
		this.setVisible(true);
	}
	public void init() {
		cont.setLayout(null);
		jl_title.setFont(font);
		jl_title.setBounds(new Rectangle(173,10,300,30));
		this.getContentPane().add(jl_title);
		
		sql="select * from student_course";
		UpdateTable();
		jsp.getViewport().add(table);
		jsp.setBounds(new Rectangle(10,43,475,450));
		this.getContentPane().add(jsp);
		
		jb_update.setFont(f);
		jb_update.addActionListener(new JButtonActionListener());
		jb_update.setBounds(new Rectangle(56,508,86,40));
		this.getContentPane().add(jb_update);
		
		jb_delete.setFont(f);
		jb_delete.addActionListener(new JButtonActionListener());
		jb_delete.setBounds(new Rectangle(187,508,86,40));
		this.getContentPane().add(jb_delete);
		
		jb_return.setFont(f);
		jb_return.addActionListener(new JButtonActionListener());
		jb_return.setBounds(new Rectangle(324,508,86,40));
		this.getContentPane().add(jb_return);

	}
	
	public void UpdateTable() {
		Vector colum=new Vector();
		colum.add("�γ̱��");
		colum.add("רҵ����");
		colum.add("�γ�����");
		colum.add("�γ�ѧ��");
		Vector row=new Vector();
		ResultSet rs = dao.getRs(sql);
		try {
			while(rs.next()) {
				Vector vc=new Vector();
				vc.add(rs.getString("course_id"));
				vc.add(rs.getString("major_name"));
				vc.add(rs.getString("course_name"));
				vc.add(rs.getString("course_score"));
				row.add(vc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model=new DefaultTableModel(row,colum) {
			//���ñ�񲻿ɱ༭
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		table=new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		jsp.getViewport().add(table);
	}
	
	//���ѡ�е���
	public void getRow() {
		row = table.getSelectedRow();
		if(row==-1) {
			return;
		}
		valueRow = table.getValueAt(row, 0).toString();
		valueRowName = table.getValueAt(row, 2).toString();
	}
	//���ɾ����ť
	public void  DeleteTable() {
		getRow();
		if(row!=-1) {
			int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i==0) {
				sql = "delete from student_course where course_id='" + valueRow + "'";
				dao.getUpdate(sql);
				sql="select * from student_course";
				UpdateTable();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "������ѡ��һ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//����޸İ�ť
	public void Updatecourse() {
		getRow();
		if(row!=-1) {
			CourseUpdate update=new CourseUpdate(valueRow,valueRowName);
			if(update.isUpdate) {
				sql = "select * from student_course";
				UpdateTable();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�һ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source=e.getSource();
			if(source==jb_update) {//�޸�
				Updatecourse();
			}
			if(source==jb_delete) {//ɾ��
				DeleteTable();
			}
			if(source==jb_return) {
				dispose();
			}
		}
		
	}
}
