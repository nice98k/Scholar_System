package scholar_compute;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.isqlviewer.swing.JCheckBoxAction;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import com.sun.javafx.collections.MappingChange.Map;

import data_base_dao.UserDao;
import javafx.scene.layout.Pane;






public class ComprehensiveScoreShowAll extends JDialog{
	JLabel jl_title=new JLabel("综   合   测   评   成   绩   管   理");
	Font f=new Font("微软",Font.BOLD,25);
	Font font=new Font("微软",Font.PLAIN,18);
	JButton jb_print=new JButton("打印");
	
	JButton jb_nopass=new JButton("挂科统计");
	JButton jb_input=new JButton("奖项输入");
	JButton jb_compute=new JButton("计算");
	JButton jb_fresh=new JButton("刷新");
	JButton jb_return=new JButton("返回");
	Object select[]= {"请选择排序方式","按算术平均成绩排序","按综测成绩排序"};
	JComboBox jcb_sort = new JComboBox(select);
	JPanel jp=new JPanel();
	JTable table;
	DefaultTableModel model;
	JScrollPane jsp=new JScrollPane();
	UserDao dao=new UserDao();
	String sql;
	int count=0;
	Container f1=this.getContentPane();
	private int row;
	private String valueName;
	private String valueId;
	private String course=null;
    
    
	Queue<String> squeue=new LinkedList<String>();
	int s0=0;
	int s1=0;
	int s2=0;
	int s3=0;
	int s4=0;
    
	public ComprehensiveScoreShowAll() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(910,820);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new XYLayout());
		this.setResizable(false);
		init();
		this.setVisible(true);
		
		squeue.clear();
	}
	public void InitScore() {
		String tid=null;
		String tname=null;
		double tcredit=0;
		double tavg=0;
		double tintelligent=0;
		double tmoral=0;
		double tpe=0;
		double tfinal=0;
		ResultSet rs=null;
		ResultSet rt=null;
		try {
			
			rs=dao.getRs("select * from student_stu");
			while(rs.next()) {
				tid=null;
				tname=null;
				tcredit=0;
				tavg=0;
				tintelligent=0;
				tmoral=0;
				tpe=0;
				tfinal=0;
				double t_sc=0;
				
				tid=rs.getString("student_number");
				tname=rs.getString("student_name");
				rt=dao.getRs("select * from student_study_score where student_number='"+tid+"'");
				
				while(rt.next()) {
					t_sc=rt.getDouble("creditxscore");
					tcredit=rt.getInt("credit");
				}
				tavg=t_sc/tcredit*1.0;
				rt=dao.getRs("select * from student_intelligent_score where student_number='"+tid+"'");
				if(rt.next()) {
					tintelligent=rt.getDouble("sum");
				}
				rt=dao.getRs("select * from student_moral_score where student_number='"+tid+"'");
				if(rt.next()) {
					tmoral=rt.getDouble("sum");
				}
				
				rt=dao.getRs("select * from student_pe_score where student_number='"+tid+"'");
				if(rt.next()) {
						tpe=rt.getDouble("sum");
				}
				if(tmoral+70.0>100)
					tmoral=100.0;
				if(tpe+70>100)
					tpe=100.0;
//				if(tintelligent+tavg>100) {
//					tavg=100;
//				}
//				else {
//					tavg=tavg+tintelligent;
//				}
				tfinal=(tavg+tintelligent)*0.55+tmoral*0.3+tpe*0.1+100*0.05;
//				System.out.println(tname+"  "+tavg+"  "+tintelligent+"  "+tmoral+"  "+tpe+"  "+tfinal);
				dao.getUpdate("insert into final (student_number,student_name,credit_sum,avg_score,intelligent_add,moral_add,pe_add,final_score) values('"+
				        tid+"','"+tname+"','"+String.valueOf(tcredit)+"','"+String.valueOf(tavg)+"','"+String.valueOf(tintelligent)+"','"+String.valueOf(tmoral)+"','"+String.valueOf(tpe)+"','"+String.valueOf(tfinal)+"')");
			}
			
			
			//计算平均成绩排名
			int cur=1;
			int cnt=0;
		    BigDecimal last=null;
		    BigDecimal now=null;
		    tname=null;
		    boolean f=false;
//		    rs=dao.getRs("select * from final order by avg_score desc");
//		    while(rs.next()) {
//		    	System.out.println(rs.getString("avg_score"));
//		    }
			rs=dao.getRs("select * from final order by avg_score desc");
			while(rs.next()) {
				if(f==false) {
					last=new BigDecimal(rs.getDouble("avg_score"));
					tname=rs.getString("student_name");
//				    System.out.println(last+"  "+cur);
					dao.getUpdate("update final set avg_score_rank='"+cur+"' where student_name='"+tname+"'");
				    f=true;
				}
				else {
					now=new BigDecimal(rs.getDouble("avg_score"));
					tname=rs.getString("student_name");
					if(last.compareTo(now)==0) {
						cnt=cnt+1;
//						System.out.println(last+"  "+now+"  "+cur);
						dao.getUpdate("update final set avg_score_rank='"+cur+"' where student_name='"+tname+"'");
					}
					else 
					if(last.compareTo(now)>0){
						last=now;
//						System.out.println(last+"  55  "+now+"  "+(cur+cnt+1));
						cur=cur+cnt+1;
						dao.getUpdate("update final set avg_score_rank='"+cur+"' where student_name='"+tname+"'");
						cnt=0;
					}
				}
			}
			
			// 综测成绩排名
			cur=1;
			cnt=0;
		    last=null;
		    now=null;
		    tname=null;
		    f=false;
//		    rs=dao.getRs("select * from final order by final_score desc");
//		    while(rs.next()) {
//		    	System.out.println(rs.getString("avg_score"));
//		    }
			rs=dao.getRs("select * from final order by final_score desc");
			while(rs.next()) {
				if(f==false) {
					last=new BigDecimal(rs.getDouble("final_score"));
					tname=rs.getString("student_name");
//				    System.out.println(last+"  "+cur);
					dao.getUpdate("update final set final_rank='"+cur+"' where student_name='"+tname+"'");
				    f=true;
				}
				else {
					now=new BigDecimal(rs.getDouble("final_score"));
					tname=rs.getString("student_name");
					if(last.compareTo(now)==0) {
						cnt=cnt+1;
//						System.out.println(last+"  "+now+"  "+cur);
						dao.getUpdate("update final set final_rank='"+cur+"' where student_name='"+tname+"'");
					}
					else 
					if(last.compareTo(now)>0){
						last=now;
//						System.out.println(last+"  55  "+now+"  "+(cur+cnt+1));
						cur=cur+cnt+1;
						dao.getUpdate("update final set final_rank='"+cur+"' where student_name='"+tname+"'");
						cnt=0;
					}
				}
			}
			
			
			try {
				
				//不及格人数
				ResultSet rsfail = dao.getRs("select * from student_score where score<60");
				while(rsfail.next()) {
					tname=rsfail.getString("student_name");
					dao.getUpdate("update final set IsFail="+1+" where student_name='"+tname+"'");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class JCmoboxBoxAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(jcb_sort.getSelectedIndex()==1) {//平均成绩排序
				sql="select * from final order by avg_score_rank asc";
				UpdateTable();
			}
			else
			if(jcb_sort.getSelectedIndex()==2) {//综测成绩排序
				sql="select * from final order by final_rank asc";
				UpdateTable();
			}
		}
		
	}
	public void init() {
//		InitScore();
		
		jl_title.setFont(f);
		this.getContentPane().add(jl_title,new XYConstraints(265, 10, 520, 30));
		
		jp.setLayout(new XYLayout());
		jp.setBorder(BorderFactory.createEtchedBorder());
		
		jb_print.setFont(font);
		jb_print.addActionListener(new JButtonActionListener());
		jp.add(jb_print,new XYConstraints(10, 15, 88, 37));
		
		
		jb_compute.setFont(font);
		jb_compute.addActionListener(new JButtonActionListener());
		jp.add(jb_compute,new XYConstraints(123, 15, 88, 37));
		
		jb_fresh.setFont(font);
		jb_fresh.addActionListener(new JButtonActionListener());
		jp.add(jb_fresh,new XYConstraints(235, 15, 88, 37));
		
//		jb_sort.setFont(font);
//		jb_sort.addActionListener(new JButtonActionListener());
//		jp.add(jb_sort,new XYConstraints(340, 15, 88, 37));
		jcb_sort.setFont(font);
		jcb_sort.addActionListener(new JCmoboxBoxAction());
		this.getContentPane().add(jcb_sort, new XYConstraints(350, 76, 185, 30));
		
		jb_nopass.setFont(font);
		jb_nopass.addActionListener(new JButtonActionListener());
		jp.add(jb_nopass,new XYConstraints(540, 15, 88, 37));
		
		jb_input.setFont(font);
		jb_input.addActionListener(new JButtonActionListener());
		jp.add(jb_input,new XYConstraints(655, 15, 88, 37));
		
		jb_return.setFont(font);
		jb_return.addActionListener(new JButtonActionListener());
		jp.add(jb_return,new XYConstraints(772, 15, 88, 37));
		
		
		this.getContentPane().add(jp,new XYConstraints(10, 56, 877, 75));
		
		
		
		
		
		sql="select * from final";
		UpdateTable();
		jsp.getViewport().add(table);
		this.getContentPane().add(jsp,new XYConstraints(10, 148, 877, 626));
		

		
		
	}
	public void fresh() {
		UpdateTable();
	}
	public void UpdateTable() {
		Vector colum=new Vector();
		colum.add("学号");
		colum.add("学生姓名");
		colum.add("学分");
		colum.add("算术平均成绩");
		colum.add("智育加分");
		colum.add("德育加分");
		colum.add("体育加分");
		colum.add("综合测评成绩");
		colum.add("学习成绩排名");
		colum.add("综合测评排名");
		colum.add("所获奖学金");
		Vector row=new Vector();
		ResultSet rs = dao.getRs(sql);
		try {
			while(rs.next()) {
				Vector value=new Vector();
				value.add(rs.getString("student_number"));
				value.add(rs.getString("student_name"));
				value.add(rs.getString("credit_sum"));
				value.add(rs.getString("avg_score"));
				value.add(rs.getString("intelligent_add"));
				value.add(rs.getString("moral_add"));
				value.add(rs.getString("pe_add"));
				value.add(rs.getString("final_score"));
				value.add(rs.getString("avg_score_rank"));
				value.add(rs.getString("final_rank"));
				value.add(rs.getString("scholar"));
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
	
	
	public static void main(String[] args) {
		new ComprehensiveScoreShowAll();
	}
	
	
	//不及格
	
	public void score_nopass() {
		int sum=0;
		int failsum=0;
		ResultSet rs = dao.getRs("select * from student_stu");
		try {
			while(rs.next()) {
				sum++;
			}
			//不及格人数
			ResultSet rsfail = dao.getRs("select * from student_score where score<60");
			while(rsfail.next()) {
				failsum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "总人数有"+sum+"人\n不及格人数有"+failsum+"人","提示",JOptionPane.INFORMATION_MESSAGE);
		
	}
	//获取选定的一行
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
	
	public void setScholarItem() {
		ResultSet rs;
		ResultSet rt;
		int f=0;
		String temp=null;
		try {
			rs=dao.getRs("select * from final order by final_rank asc");
			while(rs.next()) {
				temp=null;
				f=0;
				f=rs.getInt("IsFail");
				temp=rs.getString("student_name");
				if(f!=1) {
					System.out.println(squeue.peek());
					dao.getUpdate("update final set scholar='"+squeue.poll()+"' where student_name='"+temp+"'");
				}
				else {
					dao.getUpdate("update final set scholar='挂科' where student_name='"+temp+"'");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void initSchloar() {
    	if(s0!=0) {
    		for(int i=0;i<s0;i++)
    		squeue.offer("国家奖学金");
    	}
    	if(s1!=0) {
    		for(int i=0;i<s1;i++)
    		squeue.offer("国家励志奖学金");
    	}
    	if(s2!=0) {
    		for(int i=0;i<s2;i++)
    		squeue.offer("校一等奖学金");
    	}
    	if(s3!=0) {
    		for(int i=0;i<s3;i++)
    		squeue.offer("校二等奖学金");
    	}
    	if(s4!=0) {
    		for(int i=0;i<s4;i++)
    		squeue.offer("校三等奖学金");
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
			if(source==jb_compute) {
				
				int option = JOptionPane.showConfirmDialog(f1, "确定所有的成绩已经输入且没有错误！");
		        if (JOptionPane.OK_OPTION == option) {
		            InitScore();
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "请仔细检查你的信息！","提示",JOptionPane.YES_NO_OPTION);
		        }
		        
			}
			if(source==jb_fresh) {
				fresh();
			}
			
			if(source==jb_nopass) {//挂科统计
				score_nopass();
			}
			if(source==jb_input) {//奖项输入
				ScholarItem s_item=new ScholarItem();
				s0=s_item.s0;
				s1=s_item.s1;
				s2=s_item.s2;
				s3=s_item.s3;
				s4=s_item.s4;
				initSchloar();
				setScholarItem();
				
			}
			if(source==jb_return) {//返回
				dispose();
			}
		}
		
	}

}
