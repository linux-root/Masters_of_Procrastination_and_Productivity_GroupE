package librarysystem;

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
    private DataAccess da;
    private PanelAddBookCopy panelAddBookCopy;
    private DefaultListModel<Author> availableAuthorsModel;
    private DefaultListModel<Author> selectedAuthorsModel;
    private JLabel selectedAuthorsLabel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelAddBook(DataAccess da, PanelAddBookCopy panelAddBookCopy) {
        this.da = da;
        this.panelAddBookCopy = panelAddBookCopy;
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

        // Available authors list
        JLabel availableAuthorsLabel = new JLabel("Available Authors:");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.2;
        outerMiddle.add(availableAuthorsLabel, gbc);

        availableAuthorsModel = new DefaultListModel<>();
        JList<Author> availableAuthorsList = new JList<>(availableAuthorsModel);
        availableAuthorsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane availableAuthorsScrollPane = new JScrollPane(availableAuthorsList);
        availableAuthorsScrollPane.setPreferredSize(new Dimension(200, 100));
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        outerMiddle.add(availableAuthorsScrollPane, gbc);

        // Selected authors list
        JLabel selectedAuthorsTitleLabel = new JLabel("Selected Authors:");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.2;
        outerMiddle.add(selectedAuthorsTitleLabel, gbc);

        selectedAuthorsModel = new DefaultListModel<>();
        JList<Author> selectedAuthorsList = new JList<>(selectedAuthorsModel);
        JScrollPane selectedAuthorsScrollPane = new JScrollPane(selectedAuthorsList);
        selectedAuthorsScrollPane.setPreferredSize(new Dimension(200, 100));
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        outerMiddle.add(selectedAuthorsScrollPane, gbc);

        // Selected authors names display
        selectedAuthorsLabel = new JLabel("Selected Authors' Names: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        outerMiddle.add(selectedAuthorsLabel, gbc);

        // Buttons to manage selected authors
        JButton addSelectedAuthorsButton = new JButton("Add Selected Authors");
        JButton removeSelectedAuthorsButton = new JButton("Remove Selected Author");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JPanel authorButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        authorButtonPanel.add(addSelectedAuthorsButton);
        authorButtonPanel.add(removeSelectedAuthorsButton);
        outerMiddle.add(authorButtonPanel, gbc);

        // Add "Add Book" button
        JButton addBookButton = new JButton("Add Book");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Load available authors and add listeners
        loadAvailableAuthors();
        addSelectedAuthorsButton.addActionListener(e -> addSelectedAuthors(availableAuthorsList));
        removeSelectedAuthorsButton.addActionListener(e -> {
            Author selectedAuthor = selectedAuthorsList.getSelectedValue();
            if (selectedAuthor != null) {
                selectedAuthorsModel.removeElement(selectedAuthor);
                updateSelectedAuthorsLabel();
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

    private void loadAvailableAuthors() {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Author> authorsMap = da.readAuthors();

        for (Author author : authorsMap.values()) {
            availableAuthorsModel.addElement(author);
        }
    }

    private void addSelectedAuthors(JList<Author> availableAuthorsList) {
        List<Author> selectedAuthors = availableAuthorsList.getSelectedValuesList();
        for (Author author : selectedAuthors) {
            if (!selectedAuthorsModel.contains(author)) {
                selectedAuthorsModel.addElement(author);
            }
        }
        updateSelectedAuthorsLabel();
    }

    private void updateSelectedAuthorsLabel() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < selectedAuthorsModel.size(); i++) {
            Author author = selectedAuthorsModel.get(i);
            if (i > 0) names.append(", ");
            names.append(author.getFirstName()).append(" ").append(author.getLastName());
        }
        selectedAuthorsLabel.setText("Selected Authors' Names: " + names);
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
            for (int i = 0; i < selectedAuthorsModel.size(); i++) {
                authors.add(selectedAuthorsModel.get(i));
            }

            if (authors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please add at least one author.");
                return;
            }
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
            selectedAuthorsModel.clear();
            updateSelectedAuthorsLabel();
            this.panelAddBookCopy.refreshBookListPanel();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format for checkout length.");
        }
    }
}
