package librarysystem;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelAddMember  {
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField IDField;
    private JTextField firstNameField;
    private JTextField lastNameField;

    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField cellField;

    public void clearData() {
        IDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
    }
    public PanelAddMember() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("Add Member");
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

        JLabel ID = new JLabel("ID");
        JLabel firstName = new JLabel("First Name");
        JLabel lastName = new JLabel("Last Name");
        JLabel street   = new JLabel("Street");
        JLabel city     = new JLabel("City");
        JLabel state    = new JLabel("State");
        JLabel zip      = new JLabel("Zip");
        JLabel cell     = new JLabel("Cell");

        IDField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        streetField = new JTextField(10);
        cityField = new JTextField(10);
        stateField = new JTextField(10);
        zipField = new JTextField(10);
        cellField = new JTextField(10);


        leftPanel.add(ID);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(firstName);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(lastName);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(street);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(city);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(state);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(zip);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(cell);


        rightPanel.add(IDField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(firstNameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(lastNameField);

        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(streetField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(cityField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(stateField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(zipField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(cellField);

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Add Member");
        addMemberButtonListener(addBookButton);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.SOUTH);

    }
    private void addMemberButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            if (IDField.getText()==null || IDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID");
                return;
            }

            DataAccess da = new DataAccessFacade();
            HashMap<String, LibraryMember> map = da.readMemberMap();
            if (map.containsKey(IDField.getText())){
                JOptionPane.showMessageDialog(null, "Member already exists");
            }
            else{
                Address address = new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
                LibraryMember libraryMember = new LibraryMember(
                        IDField.getText(), firstNameField.getText(), lastNameField.getName(), cellField.getText(), address);
                da.saveNewMember(libraryMember);
                JOptionPane.showMessageDialog(null,"successfully added");
            }


        });
    }

}