package librarysystem;

import dataaccess.Auth;

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

        setSize(660, 500);

        String[] items;
        //auth
        if (auth == Auth.ADMIN){
            items = new String[]{"Welcome", "Add Member", "Add Book Copy"};
        }else if (auth == Auth.LIBRARIAN){
            items = new String[]{"Welcome", "Checkout Book"};
        }else{
            items = new String[]{"Welcome", "Add Member", "Add Book Copy", "Checkout Book"};
        }

        linkList = new JList<String>(items);
        createPanels();
        createButtonBar();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(100);

        JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, buttonBar);
        outerPane.setDividerLocation(400);

        add(outerPane, BorderLayout.CENTER);
    }


    /* Organize panels into a CardLayout */
    public void createPanels() {

        JPanel panelWelcome = new JPanel();
        JLabel labelWelcome = new JLabel("Welcome to Library System");
        labelWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        panelWelcome.add(labelWelcome);

        JPanel panelAddMember = (new PanelAddMember()).getMainPanel();
        JPanel panelAddBook   = (new PanelAddBook()).getMainPanel();
        JPanel PanelAddBookCopy = (new PanelAddBookCopy()).getMainPanel();
        JPanel panelCheckoutBook = (new PanelCheckoutBook()).getMainPanel();

        cards = new JPanel(new CardLayout());
        cards.add(panelWelcome, "Welcome");
        cards.add(panelAddMember, "Add Member");
        //cards.add(panelAddBook, "Add Book");
        cards.add(PanelAddBookCopy, "Add Book Copy");
        cards.add(panelCheckoutBook, "Checkout Book");

        //connect JList elements to CardLayout panels
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue().toString();
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
