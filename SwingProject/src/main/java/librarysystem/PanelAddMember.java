package librarysystem;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;

public class PanelAddMember {
    private DataAccess da;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;

    private JTextField IDField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField cellField;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PanelAddMember(DataAccess da) {
        this.da = da;
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        defineTopPanel();
        defineMiddlePanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Add Member");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(titleLabel);
    }

    private void defineMiddlePanel() {
        outerMiddle = new JPanel(new BorderLayout(10, 10));

        // Member Info Panel
        JPanel memberInfoPanel = new JPanel(new GridBagLayout());
        memberInfoPanel.setBorder(createTitledBorder("Member Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel[] memberLabels = {
                new JLabel("ID:"), new JLabel("First Name:"), new JLabel("Last Name:"),
                new JLabel("Cell:")
        };

        JTextField[] memberFields = {
                IDField = new JTextField(20), firstNameField = new JTextField(20),
                lastNameField = new JTextField(20), cellField = new JTextField(20)
        };

        for (int i = 0; i < memberLabels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            memberInfoPanel.add(memberLabels[i], gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            memberInfoPanel.add(memberFields[i], gbc);
        }

        // Address Panel
        JPanel addressPanel = new JPanel(new GridBagLayout());
        addressPanel.setBorder(createTitledBorder("Address Information"));

        JLabel[] addressLabels = {
                new JLabel("Street:"), new JLabel("City:"),
                new JLabel("State:"), new JLabel("Zip:")
        };

        // Create JTextFields for the address
        streetField = new JTextField("1234 Elm St", 20); // Default text for testing
        cityField = new JTextField("United Field", 20);  // Default text for testing
        stateField = new JTextField("Iowa", 20);         // Default text for testing
        zipField = new JTextField("12345", 20);          // Default text for testing

        // Set preferred size for address fields
        streetField.setPreferredSize(new Dimension(300, 30)); // Wider width
        cityField.setPreferredSize(new Dimension(300, 30));   // Wider width
        stateField.setPreferredSize(new Dimension(300, 30));  // Wider width
        zipField.setPreferredSize(new Dimension(300, 30));    // Wider width

        JTextField[] addressFields = {
                streetField, cityField, stateField, zipField
        };

        for (int i = 0; i < addressLabels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.2;  // Assign weight to labels (optional, could leave as default)
            gbc.fill = GridBagConstraints.HORIZONTAL;
            addressPanel.add(addressLabels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;  // Allow input fields to take up more space horizontally
            addressPanel.add(addressFields[i], gbc);
        }

        // Combine Member Info and Address Panels
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.add(memberInfoPanel, BorderLayout.NORTH);
        formPanel.add(addressPanel, BorderLayout.CENTER);

        outerMiddle.add(formPanel, BorderLayout.CENTER);

        // Add Button
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setBackground(new Color(0, 153, 76));
        addMemberButton.setForeground(Color.WHITE);
        addMemberButton.setFocusPainted(false);
        addMemberButton.addActionListener(e -> addMemberAction());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addMemberButton);
        outerMiddle.add(buttonPanel, BorderLayout.SOUTH);
    }

    private Border createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 1, true),
                title, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
                new Color(0, 102, 204)
        );
        return border;
    }

    private void addMemberAction() {
        String id = IDField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String cell = cellField.getText().trim();
        String street = streetField.getText().trim();
        String city = cityField.getText().trim();
        String state = stateField.getText().trim();
        String zip = zipField.getText().trim();

        if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || cell.isEmpty() ||
                street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HashMap<String, LibraryMember> memberMap = da.readMemberMap();

        if (memberMap.containsKey(id)) {
            JOptionPane.showMessageDialog(null, "Member already exists", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Address address = new Address(street, city, state, zip);
            LibraryMember member = new LibraryMember(id, firstName, lastName, cell, address);
            da.saveNewMember(member);
            JOptionPane.showMessageDialog(null, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            resetFormFields();
        }
    }

    private void resetFormFields() {
        JTextField[] fields = {
                IDField, firstNameField, lastNameField, cellField,
                streetField, cityField, stateField, zipField
        };
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
