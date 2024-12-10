package librarysystem;
import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class PanelCheckoutBook  {
    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField memberIDField;
    private JTextField ISBNField;


    public void clearData() {
        memberIDField.setText("");
        ISBNField.setText("");
    }
    public PanelCheckoutBook() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel label = new JLabel("Checkout Book");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(label);
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

        JLabel memberID = new JLabel("member ID");
        JLabel  ISBN = new JLabel(" ISBN");

        memberIDField = new JTextField(10);
        ISBNField = new JTextField(10);

        leftPanel.add(memberID);
        leftPanel.add(Box.createRigidArea(new Dimension(0,22)));
        leftPanel.add(ISBN);

        rightPanel.add(memberIDField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,18)));
        rightPanel.add(ISBNField);


        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Checkout Book");
        buttonListener(addBookButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addBookButton);
        outerMiddle.add(buttonPanel, BorderLayout.SOUTH);

    }
    private void buttonListener(JButton butn) {
        butn.addActionListener(evt -> {
            if (memberIDField.getText()==null || memberIDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid memberID");
                return;
            }
            if (ISBNField.getText()==null || ISBNField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ISBN");
                return;
            }

            DataAccess da = new DataAccessFacade();
            HashMap<String, LibraryMember> memberMap = da.readMemberMap();
            HashMap<String, Book> bookMap = da.readBooksMap();
            if (!memberMap.containsKey(memberIDField.getText())){
                JOptionPane.showMessageDialog(null, "Member ID is not found");
                return;
            }
            if (!bookMap.containsKey(ISBNField.getText())){
                JOptionPane.showMessageDialog(null, "ISBN is not found");
                return;
            }
            Book book = bookMap.get(ISBNField.getText());
            if (!book.isAvailable()){
                JOptionPane.showMessageDialog(null, "None of copies of the Book is available");
                return;
            }

            //checkout book
            LibraryMember member = memberMap.get(memberIDField.getText());
            BookCopy bookCopy = book.getNextAvailableCopy();
            if (bookCopy == null){
                JOptionPane.showMessageDialog(null, "No copy available");
            }
            else{
                bookCopy.changeAvailability();

                CheckoutRecord checkoutRecord = new CheckoutRecord(member);
                checkoutRecord.addCheckoutRecordEntry(bookCopy, new Date(), null);
                //persisted...
                da.saveCheckoutRecord(checkoutRecord);


                JOptionPane.showMessageDialog(null,"successfully Checkout Book");
                chearFields();
            }



            
        });
    }

    public void chearFields() {
        memberIDField.setText("");
        ISBNField.setText("");
    }
}
