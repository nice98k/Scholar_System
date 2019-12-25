package class_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import data_base_dao.UserDao;


public class ClassShowAll extends JDialog{
	JPanel jp1=new JPanel();
	JRadioButton jrb_collage=new JRadioButton("按学院查询：");
	JRadioButton jrb_major=new JRadioButton("按专业查询：");
	JRadioButton jrb_class=new JRadioButton("按班级查询：");
	ButtonGroup bg=new ButtonGroup();
	JLabel jl_title=new JLabel("班  级  管  理  信  息");
	JLabel jl_collage=new JLabel("请选择学院：");
	JLabel jl_major_collage=new JLabel("请选择学院：");
	JLabel jl_major=new JLabel("请选择专业：");
	JLabel jl_class=new JLabel("请输入班级：");
	JComboBox jcb_collage=new JComboBox();
	JComboBox jcb_major_collage=new JComboBox();
	JComboBox jcb_major=new JComboBox();
	JButton jb_collage=new JButton("查询");
	JButton jb_major=new JButton("查询");
	JButton jb_class=new JButton("查询");
	JButton jb_update=new JButton("修改");
	JButton jb_delete=new JButton("删除");
	JButton jb_select=new JButton("还原");
	JTextField jtf_class=new JTextField();
	Font font=new Font("微软",Font.PLAIN,16);
	Font f=new Font("微软",Font.PLAIN,25);
	DefaultTableModel model;
	JTable table;
	JScrollPane jsp=new JScrollPane();
	UserDao dao=new UserDao();
	private String valueRow;
	private int row;
	String sql;
	String valueRowClass;
	public ClassShowAll() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(540,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setLayout(new XYLayout());
		init();
		this.setVisible(true);
	}
	public void init() {
		jp1.setLayout(new XYLayout());
		bg.add(jrb_collage);
		bg.add(jrb_major);
		bg.add(jrb_class);
		
		jl_title.setFont(f);
		this.getContentPane().add(jl_title,new XYConstraints(136, 10, 300, 30));
		
		jrb_collage.setFont(font);
		jrb_collage.addActionListener(new JRadioButtonActionListener());
		jrb_collage.addItemListener(new JRadioButtonItemListener());
		jp1.add(jrb_collage,new XYConstraints(21, 22, 130, 30));
		
		jl_collage.setFont(font);
		jp1.add(jl_collage,new XYConstraints(153, 22, 120, 30));
		
		
		jcb_collage.setFont(font);
		jp1.add(jcb_collage,new XYConstraints(253, 22, 140, 30));
		
		jb_collage.setFont(font);
		jb_collage.addActionListener(new JRadioButtonActionListener());
		jp1.add(jb_collage,new XYConstraints(424, 22, 68, 30));
		
		jrb_major.setFont(font);
		jrb_major.addActionListener(new JRadioButtonActionListener());
		jrb_major.addItemListener(new JRadioButtonItemListener());
		jp1.add(jrb_major,new XYConstraints(21, 65, 130, 30));
		
		jl_major_collage.setFont(font);
		jp1.add(jl_major_collage,new XYConstraints(153, 65, 120, 30));
		
		jcb_major_collage.setFont(font);
		jcb_major_collage.addActionListener(new JRadioButtonActionListener());
		jp1.add(jcb_major_collage,new XYConstraints(253, 65, 140, 30));
		
		jl_major.setFont(font);
		jp1.add(jl_major,new XYConstraints(153, 105, 120, 30));
		
		jcb_major.setFont(font);
		jp1.add(jcb_major,new XYConstraints(253, 105, 140, 30));
		
		jb_major.setFont(font);
		jb_major.addActionListener(new JRadioButtonActionListener());
		jp1.add(jb_major,new XYConstraints(424, 105, 68, 30));
		
		jrb_class.setFont(font);
		jrb_class.addActionListener(new JRadioButtonActionListener());
		jrb_class.addItemListener(new JRadioButtonItemListener());
		jp1.add(jrb_class,new XYConstraints(21, 155, 130, 30));

		jl_class.setFont(font);
		jp1.add(jl_class,new XYConstraints(153, 155, 120, 30));
		
		jtf_class.setFont(font);
		jp1.add(jtf_class,new XYConstraints(253, 155, 140, 30));
		
		jb_class.setFont(font);
		jb_class.addActionListener(new JRadioButtonActionListener());
		jp1.add(jb_class,new XYConstraints(424, 155, 68, 30));
		jp1.setBorder(BorderFactory.createEtchedBorder());
		this.getContentPane().add(jp1, new XYConstraints(10, 59, 510, 200));
		
		sql="select * from student_class";
		UpdateTable();
		jsp.getViewport().add(table);
		this.getContentPane().add(jsp,new XYConstraints(10, 279, 510, 223));
		
		jb_update.setFont(font);
		jb_update.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_update,new XYConstraints(124, 520, 68, 30));
		
		jb_delete.setFont(font);
		jb_delete.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_delete,new XYConstraints(243, 520, 68, 30));
		
		jb_select.setFont(font);
		jb_select.addActionListener(new JButtonActionListener());
		this.getContentPane().add(jb_select,new XYConstraints(352, 520, 68, 30));
		
		jcb_collage.setEnabled(false);
		jcb_major.setEnabled(false);
		jcb_major_collage.setEnabled(false);
		jb_collage.setEnabled(false);
		jb_major.setEnabled(false);
		jb_class.setEnabled(false);
		jtf_class.setEnabled(false);
	}
	//更新表格
	public void UpdateTable() {
		Vector cloum=new Vector();
		cloum.add("班级编号");
		cloum.add("学院名称");
		cloum.add("专业名称");
		cloum.add("班级名称");
		Vector row=new Vector();
		ResultSet rs = dao.getRs(sql);
		try {
			while(rs.next()) {
				Vector vc=new Vector();
				vc.add(rs.getString("class_id"));
				vc.add(rs.getString("collage_name"));
				vc.add(rs.getString("major_name"));
				vc.add(rs.getString("class_name"));
				row.add(vc);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model=new DefaultTableModel(row,cloum) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		table=new JTable(model);
		table.setGridColor(Color.blue);
		table.setDragEnabled(true);
		table.setSelectionForeground(Color.red);
		table.setSelectionBackground(Color.green);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setShowVerticalLines(true);
		table .getTableHeader().setReorderingAllowed(false);//表格列头不可移动
		jsp.getViewport().add(table);
	}
	//显示所有学院信息
	public void ShowAllCollage(JComboBox jcb) {
		jcb.addItem("请选择学院");
		ResultSet rs = dao.getRs("select * from student_collage");
		try {
			while(rs.next()) {
				String collage_name = rs.getString("collage_name");
				jcb.addItem(collage_name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据学院显示专业
	public void JCB_major() {
		jcb_major.removeAllItems();
		ResultSet rs = dao.getRs("select * from student_major where collage_name='"+String.valueOf(jcb_major_collage.getSelectedItem())+"'");
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
	//点击学院的查询按钮
	public void JB_collage() {
		if(jcb_collage.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择学院","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			sql="select * from student_class where collage_name='"+String.valueOf(jcb_collage.getSelectedItem())+"'";
			dao.getRs(sql);
			UpdateTable();
		}
		
	}
	//点击专业的查询按钮
	public void JB_major() {
		if(jcb_major_collage.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选学院","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			sql="select * from student_class where major_name='"+String.valueOf(jcb_major.getSelectedItem())+"'";
			dao.getRs(sql);
			UpdateTable();
		}
	}
	//点击班级的查询按钮
	public void JB_class() {
		String regex1="^[0-9]{1}$";
		boolean flag1=jtf_class.getText().matches(regex1);
		if(jtf_class.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入你要查找的班级","提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(!flag1) {
			JOptionPane.showMessageDialog(null,"班级只有输入纯数字，并且只有1-9","提示",JOptionPane.INFORMATION_MESSAGE);
		}else {
			sql="select * from student_class where class_name='"+jtf_class.getText()+"'";
			dao.getRs(sql);
			UpdateTable();
		}
	}
	//获得选定的行
	public void getRow() {
		row = table.getSelectedRow();
		if(row==-1) {
			return;
		}
		valueRow = table.getValueAt(row, 0).toString();
		valueRowClass = table.getValueAt(row, 3).toString();
	}
	//删除
	public void Tabledelete() {
		getRow();
		if(row!=-1) {
			int i = JOptionPane.showConfirmDialog(null, "您确认要删除？","提示",JOptionPane.YES_NO_OPTION);
			if(i==0) {
				dao.getUpdate(sql);
				sql="select * from student_class";
				UpdateTable();
			}
		}else {
			JOptionPane.showMessageDialog(null, "请至少选中一个","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//修改
	public void TableUpdate() {
		getRow();
		if(row!=-1) {
			ClassUpdate update=new ClassUpdate(valueRow,valueRowClass);
			if(update.isUpdate) {
				sql="select * from student_class";
				UpdateTable();
			}
		}else {
			JOptionPane.showMessageDialog(null, "请选择要修改的一行","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void main(String[] args) {
		new ClassShowAll();
	}
	class JButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jb_delete) {//删除
				Tabledelete();
			}
			if(source==jb_update) {//修改
				TableUpdate();
				
			}
			if(source==jb_select) {//返回
				sql="select * from student_class";
				UpdateTable();
			}
			
		}
		
	}
	class JRadioButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source==jrb_collage) {//选中学院单选按钮时
				jcb_collage.setEnabled(true);
				jb_collage.setEnabled(true);
				ShowAllCollage(jcb_collage);
			}
			if(source==jrb_major) {//选中专业单选按钮时
				jcb_major.setEnabled(true);
				jcb_major_collage.setEnabled(true);
				jb_major.setEnabled(true);
				ShowAllCollage(jcb_major_collage);
			}
			if(source==jrb_class) {//选中班级单选按钮时
				jtf_class.setEnabled(true);
				jb_class.setEnabled(true);
			}
			if(source==jb_collage) {
				JB_collage();
			}
			if(source==jcb_major_collage) {
				JCB_major();
			}
			if(source==jb_major) {
				JB_major();
			}
			if(source==jb_class) {
				JB_class();
			}
		}
		
	}
	class JRadioButtonItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if(source==jrb_collage) {//取消单选按钮时
				jcb_collage.setEnabled(false);
				jb_collage.setEnabled(false);
			}
			if(source==jrb_major) {
				jcb_major.setEnabled(false);
				jcb_major_collage.setEnabled(false);
				jb_major.setEnabled(false);
			}
			if(source==jrb_class) {
				jtf_class.setEnabled(false);
				jb_class.setEnabled(false);
			}
		}
		
	}
}
