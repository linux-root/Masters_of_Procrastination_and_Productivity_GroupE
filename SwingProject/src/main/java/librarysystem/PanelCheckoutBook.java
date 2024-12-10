package librarysystem;
import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PanelCheckoutBook {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;
    private JPanel filterPanel; // Panel for filtering by member ID

    private JTextField memberIDField;
    private JTextField ISBNField;
    private JTextField filterMemberIDField; // Field for filter
    private JTable checkoutTable;
    private JScrollPane tableScrollPane;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelCheckoutBook() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Define the panels
        defineTopPanel();
        defineMiddlePanel();
        defineFilterPanel(); // Define the filter panel

        // Add them to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
        mainPanel.add(filterPanel, BorderLayout.SOUTH); // Add filter panel below the main content
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel label = new JLabel("Checkout Book");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(label);
    }

    public void defineMiddlePanel() {
        outerMiddle = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel memberID = new JLabel("Member ID:");
        JLabel ISBN = new JLabel("ISBN:");

        memberIDField = new JTextField(15);
        ISBNField = new JTextField(15);

        JButton addBookButton = new JButton("Checkout Book");
        buttonListener(addBookButton);

        // Arrange form components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(memberID, gbc);
        gbc.gridx = 1;
        formPanel.add(memberIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(ISBN, gbc);
        gbc.gridx = 1;
        formPanel.add(ISBNField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addBookButton, gbc);

        // Initialize table
        String[] columns = {"Member", "Book Title", "ISBN", "Checkout Date"};
        checkoutTable = new JTable(new String[0][0], columns);
        tableScrollPane = new JScrollPane(checkoutTable);
        tableScrollPane.setPreferredSize(new Dimension(600, 300));

        // Use JSplitPane for layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setDividerLocation(150);
        splitPane.setResizeWeight(0.3);

        outerMiddle.add(splitPane, BorderLayout.CENTER);

        // Initial table load
        refreshTableData();
    }

    public void defineFilterPanel() {
        filterPanel = new JPanel();
        JLabel filterLabel = new JLabel("Filter by Member ID:");
        filterMemberIDField = new JTextField(15);
        JButton filterButton = new JButton("Filter");

        // Listener for filter button
        filterButton.addActionListener(e -> filterByMemberID());

        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(filterLabel);
        filterPanel.add(filterMemberIDField);
        filterPanel.add(filterButton);
    }

    // Filter table data based on member ID input
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

    private void buttonListener(JButton button) {
        button.addActionListener(evt -> {
            if (memberIDField.getText().isEmpty() || ISBNField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter valid member ID and ISBN");
                return;
            }

            DataAccess da = new DataAccessFacade();
            HashMap<String, LibraryMember> memberMap = da.readMemberMap();
            HashMap<String, Book> bookMap = da.readBooksMap();

            if (!memberMap.containsKey(memberIDField.getText())) {
                JOptionPane.showMessageDialog(null, "Member ID not found");
                return;
            }
            if (!bookMap.containsKey(ISBNField.getText())) {
                JOptionPane.showMessageDialog(null, "ISBN not found");
                return;
            }

            Book book = bookMap.get(ISBNField.getText());
            if (!book.isAvailable()) {
                JOptionPane.showMessageDialog(null, "No copies of the book are available");
                return;
            }

            LibraryMember member = memberMap.get(memberIDField.getText());
            BookCopy bookCopy = book.getNextAvailableCopy();
            if (bookCopy == null) {
                JOptionPane.showMessageDialog(null, "No copy available");
            } else {
                bookCopy.changeAvailability();

                CheckoutRecord checkoutRecord = new CheckoutRecord(member);
                checkoutRecord.addCheckoutRecordEntry(bookCopy, new Date(), null);

                da.saveCheckoutRecord(checkoutRecord);

                JOptionPane.showMessageDialog(null, "Successfully checked out book");
                clearFields();
                refreshTableData();
            }
        });
    }

    public void clearFields() {
        memberIDField.setText("");
        ISBNField.setText("");
    }
}
