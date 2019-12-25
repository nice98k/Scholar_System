package Intelligent_score;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import data_base_dao.UserDao;
import student_info.StudentUpdate;

public class Intelligent_score_require extends JDialog {

	Font f=new Font("������", Font.BOLD, 27);
	Font font=new Font("Dialog", Font.PLAIN, 20);
	JLabel jl_title = new JLabel();
	JLabel jl_select = new JLabel();	
	Object select[]= {"ȫ����ʾ","��ѧ�Ų�ѯ","���ӷ����ѯ"};
	JComboBox jcb_select = new JComboBox(select);
	JPanel jp_number = new JPanel();
	JLabel jl_number = new JLabel();
	JButton jb_number = new JButton("��ѯ");
	JPanel jp_name = new JPanel();
	JLabel jl_name = new JLabel();
	JTextField jtf_name = new JTextField();
	JButton jb_name = new JButton("��ѯ");
	JTextField jtf_number = new JTextField();
	String sql;
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	String[] arrField = { "ѧ��", "����", "������", "�ӷַ�ֵ" };
	DefaultTableModel model = new DefaultTableModel();
	JButton jb_update = new JButton("�޸�");
	JButton jb_return = new JButton("����");
	JButton jb_delete = new JButton("ɾ��");
	UserDao dao=new UserDao();
	private int intRow;
	public String s_name;
	public  String score_name;
    public double score=0;
	public Intelligent_score_require() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(833,650);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new XYLayout());
		this.setModal(true);
		jbInit();
		this.setVisible(true);
	}
	private void jbInit() {
		jl_title.setFont(f);
		jl_title.setText("������Ϣ��ѯ");
		this.getContentPane().add(jl_title, new XYConstraints(321, 12, 180, 38));
		
		jl_select.setFont(font);
		jl_select.setText("��ѡ���ѯ��ʽ��");
		jcb_select.setFont(font);
		jcb_select.addActionListener(new JCmoboxBoxAction());
		this.getContentPane().add(jl_select, new XYConstraints(169, 74, 161, 32));
		this.getContentPane().add(jcb_select, new XYConstraints(370, 76, 0, 30));
		
		jl_number.setText("������ѧ�ţ�");
		jl_number.setFont(font);
		jtf_number.setFont(font);
		jb_number.setFont(font);
		jb_number.addActionListener(new JButonAction());
		jp_number.setBorder(BorderFactory.createEtchedBorder());
		jp_number.setLayout(new XYLayout());
		jp_number.add(jl_number, new XYConstraints(29, 8, 125, 26));
		jp_number.add(jtf_number, new XYConstraints(164, 8, 149, 25));
		jp_number.add(jb_number, new XYConstraints(360, 8, 80, 29));
		this.getContentPane().add(jp_number, new XYConstraints(150, 125, 510, 50));
		
		jl_name.setText("������ӷ��");
		jl_name.setFont(font);
		jtf_name.setFont(font);
		jb_name.setFont(font);
		jb_name.addActionListener(new JButonAction());
		jp_name.setBorder(BorderFactory.createEtchedBorder());
		jp_name.setLayout(new XYLayout());
		jp_name.add(jl_name, new XYConstraints(21, 8, 164, 31));
		jp_name.add(jtf_name, new XYConstraints(206, 8, 132, 27));
		jp_name.add(jb_name, new XYConstraints(381, 8, 85, 27));
		this.getContentPane().add(jp_name, new XYConstraints(150, 125, 510, 50));

		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jTable1.setCellSelectionEnabled(true);
		sql="select * from student_intelligent";
		UpdateRecord();
		jScrollPane1.getViewport().add(jTable1, null);
		this.getContentPane().add(jScrollPane1, new XYConstraints(10, 193, 800, 325));
		
		jb_update.setFont(font);
		jb_update.addActionListener(new JButonAction());
		this.getContentPane().add(jb_update, new XYConstraints(225, 545, 95, 34));
		
		jb_return.setFont(font);
		jb_return.addActionListener(new JButonAction());
		this.getContentPane().add(jb_return, new XYConstraints(525, 545, 95, 34));
		
		jb_delete.setFont(font);
		jb_delete.addActionListener(new JButonAction());
		this.getContentPane().add(jb_delete, new XYConstraints(375, 545, 95, 34));
		
		jp_number.setVisible(false);
		jp_name.setVisible(false);
	}
	public void UpdateRecord() {
		
		Object[][] arrTmp = {}; // �趨�����ֶ�
		Vector vec;
		model = new DefaultTableModel(arrTmp, arrField) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);

		try {
			ResultSet rs = dao.getRs(sql);
			while (rs.next()) {
				vec = new Vector();
				vec.add(rs.getString("student_number").trim());
				vec.add(rs.getString("student_name").trim());
				vec.add(rs.getString("intelligent_name").trim());
				vec.add(rs.getString("intelligent_score").trim());
				model.addRow(vec);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		jScrollPane1.getHorizontalScrollBar();
		jTable1.setGridColor(Color.blue);
		jTable1.setDragEnabled(true);
		jTable1.setSelectionForeground(Color.red);
		jTable1.setSelectionBackground(Color.green);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setRowSelectionAllowed(true);
		jTable1.setShowVerticalLines(true);
		jTable1.getTableHeader().setReorderingAllowed(false);//�����ͷ�����ƶ�
	}
	//���ѡ������
	public void Row() {
		intRow = jTable1.getSelectedRow();//�����ѡ�е���
		if (intRow == -1)
			return;
			//�����ѡ���е����� 0��ʾ�ǵ�һ������
			s_name = model.getValueAt(intRow, 1).toString();
			score_name = model.getValueAt(intRow, 2).toString();
			score=Double.valueOf(model.getValueAt(intRow, 3).toString());
//			System.out.println(s_name+"    "+score_name);
	}
	
//	public static void main(String[] args) {
//		new Moral_score_require();
//	}
	
	
	
	//ɾ��
	public void deleteStudent() {
		 dao.getUpdate("delete from student_intelligent where student_name='" + s_name + "' and intelligent_name='"+score_name+"'");
		 new Intelligent_Score_Update(s_name,score);
		 
	}
	class JCmoboxBoxAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(jcb_select.getSelectedIndex()==0) {//ȫ����ʾ
				jp_number.setVisible(false);
				jp_name.setVisible(false);
				sql="select * from student_intelligent";
				UpdateRecord();
			}
			if(jcb_select.getSelectedIndex()==1) {//��ѧ�Ų�����
				jp_name.setVisible(false);
				jp_number.setVisible(true);
			}
			if(jcb_select.getSelectedIndex()==2) {//��������ѯ
				jp_number.setVisible(false);
				jp_name.setVisible(true);
			}
			
		}
		
	}
	class JButonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object soucre=e.getSource();
			if(soucre==jb_number) {
				//ѧ�Ų�ѯ
				if(jtf_number.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"������Ҫ��ѯ��ѧ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else {
					sql="select * from student_intelligent where student_number='"+jtf_number.getText().trim()+"'";
					UpdateRecord();
				}
				
			}
			if(soucre==jb_name) {
				//������ѯ
				if(jtf_name.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"������Ҫ��ѯ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else {
					sql="select * from student_intelligent where intelligent_name like'"+"%"+jtf_name.getText().trim()+"%"+"'";
					UpdateRecord();
				}
				
			}
			if(soucre==jb_delete) {//ɾ��
				Row();
				if(intRow!=-1) {
					int i = JOptionPane.showConfirmDialog(null,"��ȷ��Ҫɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
					if(i==0) {
						deleteStudent();
						sql="select * from student_intelligent";
						UpdateRecord();
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ϣ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//�޸�
			if(soucre==jb_update) {
				Row();
				if(intRow!=-1) {
					Intelligent_Score_Update update=new Intelligent_Score_Update(s_name,score_name);
					
					if(update.isUpdate) {
						sql = "select * from student_intelligent";
						UpdateRecord();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ϣ","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			if(soucre==jb_return) {
				dispose();
			}
		}

		
	}
}
