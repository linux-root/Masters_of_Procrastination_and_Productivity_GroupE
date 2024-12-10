package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;


public class ApplicationMain {

	public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        EventQueue.invokeLater(() ->
	         {
	            LibrarySystem.INSTANCE.setTitle("MIU Library Company");
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
