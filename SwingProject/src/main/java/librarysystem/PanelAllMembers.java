package librarysystem;

import business.Address;
import business.Author;
import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.HashMap;

import business.LibraryMember;
import java.util.List;

public class PanelAllMembers  {
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField ISBNField;
    private JTextField titleField;
    private JTextField maxCheckoutLengthField;

    private JTextField firstNameField;
    private JTextField lastNameField;

    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField cellField;

    public void clearData() {
        ISBNField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
    }
    public PanelAllMembers() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("All Members");
        Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(AddBookLabel);
    }

    public void defineMiddlePanel() {
        outerMiddle = new JPanel();
        outerMiddle.setLayout(new BorderLayout());

        //set up left and right panels
        JPanel middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> memberMap = da.readMemberMap();
        if (memberMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No members found.");
            return;
        }
        // Create a list model
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (LibraryMember member : memberMap.values()) {
            if(!member.getFirstName().isEmpty()) {
                listModel.addElement(member.getMemberId() + " - " + member.getFirstName());
            }
        }
        // Display the list in a dialog
        JList<String> memberList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(memberList);
        leftPanel.add(scrollPane);

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);



    }
}
