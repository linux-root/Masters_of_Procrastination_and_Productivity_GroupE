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
    private void defineMiddlePanel() {
        outerMiddle = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fieldHeight = 30;

        // Add form fields
        addFormField(outerMiddle, "ISBN", ISBNField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Title", titleField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Max Checkout Length", maxCheckoutLengthField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Name", firstNameField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Address", cityField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Cell", cellField = new JTextField(), gbc, fieldHeight);

        mainPanel.add(outerMiddle, BorderLayout.CENTER);

        // Add Book Button
        JButton addBookButton = new JButton("Add Book");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, String label, JTextField field, GridBagConstraints gbc, int fieldHeight) {
        JLabel lbl = new JLabel(label + ":");
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.2;
        panel.add(lbl, gbc);

        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(200, fieldHeight));
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(field, gbc);
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