package scholarship_main;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;




import login_register.Login;

public class StudentMain {
	public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
//		javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login login=new Login();
			}
		});
	}
}
