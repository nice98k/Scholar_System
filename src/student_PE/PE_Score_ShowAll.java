package student_PE;

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


public class PE_Score_ShowAll extends JDialog{
	JLabel jl_title=new JLabel("��   ��   ��   ��   ��   ��");
	Font f=new Font("΢��",Font.BOLD,25);
	Font font=new Font("΢��",Font.PLAIN,18);
	JButton jb_print=new JButton("�� ӡ");
	JButton jb_sort=new JButton("�� ��");
	JButton jb_require=new JButton("�� ѯ");
//	JButton jb_fail=new JButton("������");
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
	public String pe_name;
	public String name;
	public double score=0;
	public PE_Score_ShowAll() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(510,650);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new XYLayout());
		this.setResizable(false);
		init();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new PE_Score_ShowAll();
	}
	public void init() {
		jl_title.setFont(f);
		this.getContentPane().add(jl_title,new XYConstraints(120, 10, 300, 30));
		
		jp.setLayout(new XYLayout());
		jp.setBorder(BorderFactory.createEtchedBorder());
		
		jb_print.setFont(font);
		jb_print.addActionListener(new JButtonActionListener());
		jp.add(jb_print,new XYConstraints(25, 15, 88, 37));
		
		jb_sort.setFont(font);
		jb_sort.addActionListener(new JButtonActionListener());
		jp.add(jb_sort,new XYConstraints(190, 15, 88, 37));
		
		jb_require.setFont(font);
		jb_require.addActionListener(new JButtonActionListener());
		jp.add(jb_require,new XYConstraints(360, 15, 88, 37));
		
//		jb_fail.setFont(font);
//		jb_fail.addActionListener(new JButtonActionListener());
//		jp.add(jb_fail,new XYConstraints(372, 15, 88, 37));
		this.getContentPane().add(jp,new XYConstraints(10, 56, 480, 75));
		
		sql="select * from student_PE";
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
		colum.add("�ӷ�������");
		colum.add("�ӷ���ֵ");
		Vector row=new Vector();
		ResultSet rs = dao.getRs(sql);
		try {
			while(rs.next()) {
				Vector value=new Vector();
				
				value.add(rs.getString("student_number"));
				value.add(rs.getString("student_name"));
				value.add(rs.getString("PE_name"));
				value.add(rs.getString("PE_score"));
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
			sql = "select * from student_PE order by PE_score asc";
			UpdateTable();
		}else {//�Գɼ�����
			jb_sort.setText("�� ��");
			sql = "select * from student_PE order by PE_score desc";
			UpdateTable();
		}
		
	}
	//�����ѯ
	public void score_require() {
		
		PE_score_require update=new PE_score_require();

	}
//	//������
//	public void Score_fail() {
//		int sum=0;
//		int failsum=0;
//		//��ȡ������
//		ResultSet rs = dao.getRs("select * from student_intelligent");
//		try {
//			while(rs.next()) {
//				sum++;
//			}
//			//����������
//		ResultSet rsfail = dao.getRs("select * from student_intelligent where score<60");
//		while(rsfail.next()) {
//			failsum++;
//		}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JOptionPane.showMessageDialog(null, "��������"+sum+"��\n������������"+failsum+"��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
//	}
	//��ȡѡ����һ��
	public void getRow() {
		row = table.getSelectedRow();
		if(row==-1) {
			return;
		}
		name = table.getValueAt(row, 1).toString();      // ��ȡѧ��
		pe_name = table.getValueAt(row, 2).toString();     //
		score=Double.valueOf(table.getValueAt(row, 3).toString());
//		System.out.println(name);
//		System.out.println(pe_name);
	}
	//���ɾ����ť
	public void deleteScore() {
		getRow();
		if(row!=-1) {
			int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i==0) {
				dao.getUpdate("delete from student_PE where student_name='"+name+"' and PE_name='"+pe_name.trim()+"'");
				new PE_Score_Update(name,score);
				sql="select * from student_PE";
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
			PE_Score_Update update=new PE_Score_Update(name,pe_name);
			if(update.isUpdate) {
				sql="select * from student_PE";
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
			if(source==jb_require) {//��ѯ
				score_require();
			}
//			if(source==jb_fail) {//������
//				Score_fail();
//			}
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
