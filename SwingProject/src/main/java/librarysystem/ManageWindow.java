package librarysystem;

import dataaccess.Auth;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import java.awt.*;

public class ManageWindow extends JFrame {

    private static final long serialVersionUID = -760156396736751840L;

    String[] links;
    JList<String> linkList;
    //context for CardLayout
    JPanel cards;
    JPanel buttonBar;

    public ManageWindow(Auth auth) {
        setSize(860, 500);

        String[] items;
        //auth
        if (auth == Auth.ADMIN){
            items = new String[]{"Welcome", "Add Member", "Add Book Copy"};
        }else if (auth == Auth.LIBRARIAN){
            items = new String[]{"Welcome", "Checkout Book"};
        }else{
            items = new String[]{"Welcome", "Add Member", "Add Book Copy", "Checkout Book","Add Book","All Members"};
        }

        linkList = new JList<String>(items);
        createPanels();
        createButtonBar();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(200);

        JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, buttonBar);
        outerPane.setDividerLocation(400);

        add(outerPane, BorderLayout.CENTER);
    }


    /* Organize panels into a CardLayout */
    public void createPanels() {
        var da = new DataAccessFacade();

        // Create the Welcome Panel with detailed welcome content
        JPanel panelWelcome = new JPanel();
        panelWelcome.setLayout(new BorderLayout());

        JLabel labelWelcome = new JLabel("<html>"
                + "<div style='text-align: center;'>"
                + "<h1>Welcome to the Library Management System!</h1>"
                + "<p>We are delighted to have you here! Our Library Management System is designed to streamline your experience and make managing library resources effortless and enjoyable.</p>"
                + "<ul style='text-align: left;'>"
                + "<li><b>📚 Browse and Manage Books</b>: Explore a vast collection of books, update information, and track their availability with ease.</li>"
                + "<li><b>👥 Manage Library Members</b>: Add new members, update member details, and keep track of borrowing history.</li>"
                + "<li><b>📊 View Detailed Reports</b>: Access comprehensive reports to monitor the library's operations and make informed decisions.</li>"
                + "<li><b>💼 User-Friendly Tools</b>: Enjoy our intuitive interface that allows you to focus more on your tasks and less on complexity.</li>"
                + "</ul>"
                + "<p>If you need assistance or have any questions, our help section is just a click away!</p>"
                + "<p><b>Happy Managing,</b><br>The Library Management System Team</p>"
                + "</div>"
                + "</html>");
        labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        panelWelcome.add(labelWelcome, BorderLayout.CENTER);

        PanelAllMembers allPanelMembers = new PanelAllMembers();

        JPanel panelAddBook = (new PanelAddBook()).getMainPanel();
        JPanel PanelAddBookCopy = (new PanelAddBookCopy(da)).getMainPanel();
        JPanel panelCheckoutBook = (new PanelCheckoutBook()).getMainPanel();
        JPanel panelAllMembers = (allPanelMembers).getMainPanel();
        JPanel panelAddMember = (new PanelAddMember(da, allPanelMembers)).getMainPanel();
        cards = new JPanel(new CardLayout());
        cards.add(panelWelcome, "Welcome");
        cards.add(panelAddMember, "Add Member");
        cards.add(PanelAddBookCopy, "Add Book Copy");
        cards.add(panelCheckoutBook, "Checkout Book");
        cards.add(panelAddBook, "Add Book");
        cards.add(panelAllMembers, "All Members");

        // Connect JList elements to CardLayout panels
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, value);
        });
    }

    public void createButtonBar() {
        buttonBar = new JPanel();
        JButton button = new JButton("Log Out");
        addLogoutButtonListener(button);
        buttonBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonBar.add(button);
    }
    private void addLogoutButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LoginWindow.manageFrame.setVisible(false);
            LibrarySystem.INSTANCE.setVisible(true);
        });
    }



}
