package librarysystem;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PanelCheckoutBook {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;
    private JPanel filterPanel;

    private JTextField memberIDField;
    private JTextField ISBNField;
    private JTextField filterMemberIDField;
    private JTable checkoutTable;
    private JScrollPane tableScrollPane;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelCheckoutBook() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Define panels
        defineTopPanel();
        defineMiddlePanel();
        defineFilterPanel();

        // Add panels to main layout
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
        mainPanel.add(filterPanel, BorderLayout.SOUTH);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        JLabel label = new JLabel("Checkout Book");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(new Color(0, 102, 204));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(label);
    }

    private void defineMiddlePanel() {
        outerMiddle = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel memberIDLabel = new JLabel("Member ID:");
        JLabel ISBNLabel = new JLabel("ISBN:");

        memberIDField = new JTextField(15);
        ISBNField = new JTextField(15);

        JButton checkoutButton = new JButton("Checkout Book");
        checkoutButton.setBackground(new Color(0, 153, 76));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.addActionListener(e -> checkoutBook());

        // Arrange form components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(memberIDLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(memberIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(ISBNLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ISBNField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(checkoutButton, gbc);

        // Initialize table
        String[] columns = {"Member", "Book Title", "ISBN", "Checkout Date"};
        checkoutTable = new JTable(new DefaultTableModel(columns, 0));
        tableScrollPane = new JScrollPane(checkoutTable);
        tableScrollPane.setPreferredSize(new Dimension(600, 300));

        // Split pane for form and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setDividerLocation(150);
        splitPane.setResizeWeight(0.3);

        outerMiddle.add(splitPane, BorderLayout.CENTER);
        refreshTableData();
    }

    private void defineFilterPanel() {
        filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel filterLabel = new JLabel("Filter by Member ID:");
        filterMemberIDField = new JTextField(15);
        JButton filterButton = new JButton("Filter");

        filterButton.setBackground(new Color(0, 102, 204));
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false);
        filterButton.addActionListener(e -> filterByMemberID());

        filterPanel.add(filterLabel);
        filterPanel.add(filterMemberIDField);
        filterPanel.add(filterButton);
    }

    private void checkoutBook() {
        String memberID = memberIDField.getText().trim();
        String isbn = ISBNField.getText().trim();

        if (memberID.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both Member ID and ISBN");
            return;
        }

        DataAccessFacade da = new DataAccessFacade();
        //da.emptyRecord();

        HashMap<String, LibraryMember> memberMap = da.readMemberMap();
        HashMap<String, Book> bookMap = da.readBooksMap();

        if (!memberMap.containsKey(memberID)) {
            JOptionPane.showMessageDialog(null, "Member ID not found");
            return;
        }

        if (!bookMap.containsKey(isbn)) {
            JOptionPane.showMessageDialog(null, "ISBN not found");
            return;
        }

        Book book = bookMap.get(isbn);
        if (!book.isAvailable()) {
            JOptionPane.showMessageDialog(null, "No copies of the book are available");
            return;
        }

        LibraryMember member = memberMap.get(memberID);
        BookCopy bookCopy = book.getNextAvailableCopy();
        if (bookCopy != null) {
            bookCopy.changeAvailability();

            CheckoutRecord checkoutRecord = new CheckoutRecord(member);
            checkoutRecord.addCheckoutRecordEntry(bookCopy, new Date(), null);
            da.saveCheckoutRecord(checkoutRecord);

            JOptionPane.showMessageDialog(null, "Book checked out successfully");
            refreshTableData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "No available copy of the book");
        }
    }

    private void filterByMemberID() {
        String filterMemberID = filterMemberIDField.getText().trim();
        if (filterMemberID.isEmpty()) {
            // No filter applied, show all data
            refreshTableData(); // Refresh with no filter
        } else {
            System.out.println("Filtering by Member ID: " + filterMemberID);
            DataAccess da = new DataAccessFacade();
            HashMap<String, CheckoutRecord> checkoutRecords = da.readCheckoutRecordsMap();

            List<String[]> filteredDataList = new ArrayList<>();

            for (CheckoutRecord record : checkoutRecords.values()) {
                LibraryMember member = record.getMember();
                if (member.getMemberId().equals(filterMemberID)) {
                    String memberName = member.getFirstName();
                    for (CheckoutRecordEntry entry : record.getCheckoutRecordEntries()) {
                        String[] row = {
                                member.getMemberId() + " - " + memberName,
                                entry.getBookCopy().getBook().getTitle(),
                                entry.getBookCopy().getBook().getIsbn(),
                                entry.getCheckoutDate().toString()
                        };
                        filteredDataList.add(row);
                    }
                }
            }

            String[][] filteredData = filteredDataList.toArray(new String[0][0]);

            // Update the table with filtered data
            checkoutTable.setModel(new javax.swing.table.DefaultTableModel(filteredData, new String[]{"Member", "Book Title", "ISBN", "Checkout Date"}));
        }
    }

    private void refreshTableData() {
        DataAccess da = new DataAccessFacade();
        HashMap<String, CheckoutRecord> checkoutRecords = da.readCheckoutRecordsMap();

        String[] columns = {"Member", "Book Title", "ISBN", "Checkout Date"};
        List<String[]> dataList = new ArrayList<>();

        for (CheckoutRecord record : checkoutRecords.values()) {
            LibraryMember member = record.getMember();
            String memberName = member.getFirstName();
            for (CheckoutRecordEntry entry : record.getCheckoutRecordEntries()) {
                String[] row = {
                        member.getMemberId() + " - " + memberName,
                        entry.getBookCopy().getBook().getTitle(),
                        entry.getBookCopy().getBook().getIsbn(),
                        entry.getCheckoutDate().toString()
                };
                dataList.add(row);
            }
        }

        String[][] data = dataList.toArray(new String[0][0]);

        // Update the table model
        checkoutTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    private void clearFields() {
        memberIDField.setText("");
        ISBNField.setText("");
    }
}
