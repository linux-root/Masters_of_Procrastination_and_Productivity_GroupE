package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;


public class ApplicationMain {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
       	UIManager.setLookAndFeel(new FlatIntelliJLaf());
        EventQueue.invokeLater(() ->
	         {
	            LibrarySystem.INSTANCE.setTitle("Sample Library Application");
	            LibrarySystem.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                 try {
                     LibrarySystem.INSTANCE.init();
                 } catch (URISyntaxException e) {
                     throw new RuntimeException(e);
                 }
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
