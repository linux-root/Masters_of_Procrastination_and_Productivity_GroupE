package librarysystem;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelAddBookCopy  {
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField ISBNField;
    private JTextField titleField;
    private JTextField maxCheckoutLengthField;

    private JTextField copyNumField;

    private JTextField firstNameField;
    private JTextField lastNameField;

    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField cellField;

    public void clearData() {
        //ISBNField.setText("");
        titleField.setText("");
        maxCheckoutLengthField.setText("");
    }
    public PanelAddBookCopy() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("Add Book Copy");
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


        ISBNField = new JTextField(10);
        titleField = new JTextField(10);
        maxCheckoutLengthField = new JTextField(10);



        leftPanel.add(ISBN);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(Title);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(maxCheckoutLength);


        rightPanel.add(ISBNField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(titleField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(maxCheckoutLengthField);


        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add search button at bottom
        JButton searchButton = new JButton("Search BookCopy");
        searchButtonListener(searchButton);

        //add button at bottom
        JButton addBookButton = new JButton("Add Book Copy");
        addButtonListener(addBookButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(searchButton);
        buttonPanel.add(addBookButton);
        outerMiddle.add(buttonPanel, BorderLayout.SOUTH);

    }

    private void searchButtonListener(JButton butn){
        butn.addActionListener(evt -> {
            if (ISBNField.getText()==null || ISBNField.getText().isEmpty()) {
                clearData();
                JOptionPane.showMessageDialog(null, "Please enter a valid ISBN");
                return;
            }
            DataAccess da = new DataAccessFacade();
            HashMap<String, Book> map = da.readBooksMap();
            if (!map.containsKey(ISBNField.getText())){
                clearData();
                JOptionPane.showMessageDialog(null, "Book doesn't exist");
            }
            else{
                Book book = map.get(ISBNField.getText());
                titleField.setText(book.getTitle());
                titleField.setEditable(false);
                maxCheckoutLengthField.setText(String.valueOf(book.getMaxCheckoutLength()));
                maxCheckoutLengthField.setEditable(false);
            }

        });
    }

    private void addButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            if (ISBNField.getText()==null || ISBNField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ISBN");
                return;
            }
            DataAccess da = new DataAccessFacade();
            HashMap<String, Book> map = da.readBooksMap();
            if (!map.containsKey(ISBNField.getText())){
                JOptionPane.showMessageDialog(null, "Book doesn't exist");
            }
            else{
                Book book = map.get(ISBNField.getText());
                book.addCopy();
                //da.saveNewBookCopy(new BookCopy(book, book.getCopies().length +1,true));
                JOptionPane.showMessageDialog(null, "Add BookCopy Successfully");
            }


        });
    }

}

