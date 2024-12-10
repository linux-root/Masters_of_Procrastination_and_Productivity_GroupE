package librarysystem;

import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class PanelAllMembers {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelAllMembers() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        defineTopPanel();
        defineMiddlePanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    private void defineTopPanel() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("All Members");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(titleLabel);
    }

    private void defineMiddlePanel() {
        outerMiddle = new JPanel(new BorderLayout(10, 10));

        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> memberMap = da.readMemberMap();

        if (memberMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No members found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create column names
        String[] columnNames = {"Member ID", "First Name", "Last Name", "Address"};

        // Create a custom table model to disable cell editing
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };

        // Populate table with library members
        for (LibraryMember member : memberMap.values()) {
            String memberId = member.getMemberId();
            String firstName = member.getFirstName();
            String lastName = member.getLastName();
            String address = member.getAddress().toString(); // Assuming Address has a proper toString() method

            tableModel.addRow(new Object[]{memberId, firstName, lastName, address});
        }

        // Create table
        JTable memberTable = new JTable(tableModel);
        memberTable.setFillsViewportHeight(true);

        // Set table appearance
        memberTable.setRowHeight(25);
        memberTable.setFont(new Font("Arial", Font.PLAIN, 14));
        memberTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        memberTable.getTableHeader().setBackground(new Color(0, 102, 204));
        memberTable.getTableHeader().setForeground(Color.WHITE);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(memberTable);
        outerMiddle.add(scrollPane, BorderLayout.CENTER);
    }
}
