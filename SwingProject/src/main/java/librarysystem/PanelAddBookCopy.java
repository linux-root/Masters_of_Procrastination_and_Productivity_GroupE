package librarysystem;

import business.Book;
import business.BookCopy;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAddBookCopy {
    private JPanel mainPanel;

    public PanelAddBookCopy(DataAccess da) {
        // Create the main JPanel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());

        // Book list panel (Screen 1)
        JPanel bookListPanel = new JPanel(new BorderLayout());
        JLabel bookListLabel = new JLabel("Select a Book:");
        bookListPanel.add(bookListLabel, BorderLayout.NORTH);

        // JList to display books
        Book[] books = da.readBooksMap().values().toArray(new Book[0]);
        JList<Book> bookList = new JList<>(books);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane bookScrollPane = new JScrollPane(bookList);
        bookListPanel.add(bookScrollPane, BorderLayout.CENTER);

        // Add the bookListPanel to the left
        mainPanel.add(bookListPanel, BorderLayout.WEST);

        // Add book copy panel (Screen 2)
        JPanel addBookCopyPanel = new JPanel();
        addBookCopyPanel.setLayout(new BoxLayout(addBookCopyPanel, BoxLayout.Y_AXIS));
        JLabel addCopyLabel = new JLabel("Add Book Copy");
        addCopyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel selectedBookLabel = new JLabel("No book selected");
        selectedBookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel copiesLabel = new JLabel("Copies:");
        copiesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Use a JSpinner for numeric input
        JSpinner copyCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        copyCountSpinner.setMaximumSize(new Dimension(200, 30)); // Restrict size

        JButton addCopyButton = new JButton("Add Copy");
        addCopyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        addBookCopyPanel.add(addCopyLabel);
        addBookCopyPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        addBookCopyPanel.add(selectedBookLabel);
        addBookCopyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        addBookCopyPanel.add(copiesLabel);
        addBookCopyPanel.add(copyCountSpinner);
        addBookCopyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        addBookCopyPanel.add(addCopyButton);

        // Add the addBookCopyPanel to the right
        mainPanel.add(addBookCopyPanel, BorderLayout.CENTER);

        // Add selection listener to the book list
        bookList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Book selectedBook = bookList.getSelectedValue();
                if (selectedBook != null) {
                    selectedBookLabel.setText("Selected Book: " + selectedBook.getTitle());
                }
            }
        });

        // Add action listener to the Add Copy button
        addCopyButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            int copyCount = (int) copyCountSpinner.getValue();
            if (selectedBook == null) {
                JOptionPane.showMessageDialog(null, "Please select a book first.");
            } else {
                selectedBook.addCopy(copyCount);
                da.saveNewBook(selectedBook);
                JOptionPane.showMessageDialog(null,
                        "Added " + copyCount + " copies to " + selectedBook.getTitle() + ".");
                copyCountSpinner.setValue(1); // Reset to default value
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Book Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            frame.add(new PanelAddBookCopy(new DataAccessFacade()).mainPanel);

            frame.setVisible(true);
        });
    }
}
