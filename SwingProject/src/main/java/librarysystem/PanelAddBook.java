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
import java.util.List;

public class PanelAddBook  {
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
    public PanelAddBook() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("Add Book");
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


        JLabel ISBN = new JLabel("ISBN");
        JLabel Title = new JLabel("Title");
        JLabel maxCheckoutLength = new JLabel("Max Checkout Length");
        JLabel firstName = new JLabel("Author First Name");
        JLabel lastName = new JLabel("Author Last Name");
        JLabel street   = new JLabel("Street");
        JLabel city     = new JLabel("City");
        JLabel state    = new JLabel("State");
        JLabel zip      = new JLabel("Zip");
        JLabel cell     = new JLabel("Cell");

        ISBNField = new JTextField(10);
        titleField = new JTextField(10);
        maxCheckoutLengthField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        streetField = new JTextField(10);
        cityField = new JTextField(10);
        stateField = new JTextField(10);
        zipField = new JTextField(10);
        cellField = new JTextField(10);

        leftPanel.add(ISBN);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(Title);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(maxCheckoutLength);
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


        rightPanel.add(ISBNField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(titleField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(maxCheckoutLengthField);
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
        JButton addBookButton = new JButton("Add Book");
        addMemberButtonListener(addBookButton);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.SOUTH);

    }
    private void addMemberButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            if (ISBNField.getText()==null || ISBNField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ISBN");
                return;
            }

            DataAccess da = new DataAccessFacade();
            HashMap<String, Book> map = da.readBooksMap();
            if (map.containsKey(ISBNField.getText())){
                JOptionPane.showMessageDialog(null, "Book already exists");
            }
            else{
                Address address = new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
                Author author = new Author(firstNameField.getText(), lastNameField.getName(),cellField.getText(),address,null);
                List<Author> authorList = new ArrayList<Author>();
                authorList.add(author);
                Book book = new Book(ISBNField.getText(), titleField.getText(),Integer.parseInt(maxCheckoutLengthField.getText()), authorList);
                da.saveNewBook(book);
                JOptionPane.showMessageDialog(null,"successfully added");
            }


        });
    }

}
