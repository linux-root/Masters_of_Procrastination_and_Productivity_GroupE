package librarysystem;

import business.Address;
import business.Author;
import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PanelAddBook {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;

    private JTextField ISBNField;
    private JTextField titleField;
    private JTextField maxCheckoutLengthField;

    private JTextField authorFirstNameField;
    private JTextField authorLastNameField;
    private JTextField authorStreetField;
    private JTextField authorCityField;
    private JTextField authorStateField;
    private JTextField authorZipField;
    private JTextField authorCellField;

    private DefaultListModel<Author> authorListModel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelAddBook() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        JLabel addBookLabel = new JLabel("Add Book");
        Util.adjustLabelFont(addBookLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addBookLabel);
    }

    private void defineMiddlePanel() {
        outerMiddle = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fieldHeight = 30;

        // Add form fields for book details
        addFormField(outerMiddle, "ISBN", ISBNField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Title", titleField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Max Checkout Length", maxCheckoutLengthField = new JTextField(), gbc, fieldHeight);

        // Add fields for author details
        addFormField(outerMiddle, "Author First Name", authorFirstNameField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Author Last Name", authorLastNameField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Street", authorStreetField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "City", authorCityField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "State", authorStateField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "ZIP", authorZipField = new JTextField(), gbc, fieldHeight);
        addFormField(outerMiddle, "Cell", authorCellField = new JTextField(), gbc, fieldHeight);

        // Add authors list
        JLabel authorsLabel = new JLabel("Authors:");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.2;
        outerMiddle.add(authorsLabel, gbc);

        authorListModel = new DefaultListModel<>();
        JList<Author> authorList = new JList<>(authorListModel);
        JScrollPane authorScrollPane = new JScrollPane(authorList);
        authorScrollPane.setPreferredSize(new Dimension(200, 100));
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        outerMiddle.add(authorScrollPane, gbc);

        // Add buttons to manage authors
        JButton addAuthorButton = new JButton("Add Author");
        JButton removeAuthorButton = new JButton("Remove Selected Author");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JPanel authorButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        authorButtonPanel.add(addAuthorButton);
        authorButtonPanel.add(removeAuthorButton);
        outerMiddle.add(authorButtonPanel, gbc);

        // Add "Add Book" button
        JButton addBookButton = new JButton("Add Book");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners
        addAuthorButton.addActionListener(e -> addAuthor());
        removeAuthorButton.addActionListener(e -> {
            Author selectedAuthor = authorList.getSelectedValue();
            if (selectedAuthor != null) {
                authorListModel.removeElement(selectedAuthor);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an author to remove.");
            }
        });
        addBookButton.addActionListener(e -> addBook());
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

    private void addAuthor() {
        String firstName = authorFirstNameField.getText();
        String lastName = authorLastNameField.getText();
        String street = authorStreetField.getText();
        String city = authorCityField.getText();
        String state = authorStateField.getText();
        String zip = authorZipField.getText();
        String cell = authorCellField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty() || cell.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all author fields.");
            return;
        }

        Address address = new Address(street, city, state, zip);
        Author author = new Author(firstName, lastName, cell, address, null);
        authorListModel.addElement(author);

        // Clear author fields
        authorFirstNameField.setText("");
        authorLastNameField.setText("");
        authorStreetField.setText("");
        authorCityField.setText("");
        authorStateField.setText("");
        authorZipField.setText("");
        authorCellField.setText("");
    }

    private void addBook() {
        String isbn = ISBNField.getText();
        String title = titleField.getText();
        String maxCheckoutLength = maxCheckoutLengthField.getText();

        if (isbn.isEmpty() || title.isEmpty() || maxCheckoutLength.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all book fields.");
            return;
        }

        try {
            int checkoutLength = Integer.parseInt(maxCheckoutLength);
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < authorListModel.size(); i++) {
                authors.add(authorListModel.get(i));
            }

            if (authors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please add at least one author.");
                return;
            }

            DataAccess da = new DataAccessFacade();
            HashMap<String, Book> booksMap = da.readBooksMap();
            if (booksMap.containsKey(isbn)) {
                JOptionPane.showMessageDialog(null, "Book with this ISBN already exists.");
                return;
            }

            Book book = new Book(isbn, title, checkoutLength, authors);
            da.saveNewBook(book);
            JOptionPane.showMessageDialog(null, "Book added successfully!");

            // Clear form fields
            ISBNField.setText("");
            titleField.setText("");
            maxCheckoutLengthField.setText("");
            authorListModel.clear();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format for checkout length.");
        }
    }
}
