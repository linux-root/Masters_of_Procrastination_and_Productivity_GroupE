package main;

//import com.formdev.flatlaf.FlatLightLaf;
import librarysystem.LibrarySystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Locale;

import javax.swing.*;


public class ApplicationMain {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		Locale.setDefault(Locale.ENGLISH);
       	//UIManager.setLookAndFeel(new FlatLightLaf());
        EventQueue.invokeLater(() ->
	         {
	            LibrarySystem.INSTANCE.setTitle("Sample Library Application");
	            LibrarySystem.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            
	            LibrarySystem.INSTANCE.init();
	            centerFrameOnDesktop(LibrarySystem.INSTANCE);
	            LibrarySystem.INSTANCE.setVisible(true);
	         });
	   }
	   
	   public static void centerFrameOnDesktop(Component f) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int height = toolkit.getScreenSize().height;
			int width = toolkit.getScreenSize().width;
			int frameHeight = f.getSize().height;
			int frameWidth = f.getSize().width;
			f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
		}

}
