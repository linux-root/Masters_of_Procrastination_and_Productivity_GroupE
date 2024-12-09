    package librarysystem;

    import business.Address;
    import business.LibraryMember;
    import dataaccess.DataAccess;
    import dataaccess.DataAccessFacade;

    import javax.swing.*;
    import java.awt.*;
    import java.util.HashMap;

    public class PanelAddMember  {
        public JPanel getMainPanel() {
            return mainPanel;
        }
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

        public void clearData() {
            IDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
        }
        public PanelAddMember() {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            defineTopPanel();
            defineMiddlePanel();
            mainPanel.add(topPanel, BorderLayout.NORTH);
            mainPanel.add(outerMiddle, BorderLayout.CENTER);
        }

        public void defineTopPanel() {
            topPanel = new JPanel();
            JLabel AddBookLabel = new JLabel("Add Member");
            Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
            topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            topPanel.add(AddBookLabel);
        }

        public void defineMiddlePanel() {
            outerMiddle = new JPanel(new BorderLayout());

            // Create form panel with GridBagLayout
            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            // Define labels and text fields
            JLabel[] labels = {
                    new JLabel("ID"), new JLabel("First Name"), new JLabel("Last Name"),
                    new JLabel("Street"), new JLabel("City"), new JLabel("State"),
                    new JLabel("Zip"), new JLabel("Cell")
            };

            JTextField[] fields = {
                    IDField = new JTextField(15), firstNameField = new JTextField(15),
                    lastNameField = new JTextField(15), streetField = new JTextField(15),
                    cityField = new JTextField(15), stateField = new JTextField(15),
                    zipField = new JTextField(15), cellField = new JTextField(15)
            };

            // Add labels and text fields in aligned rows
            for (int i = 0; i < labels.length; i++) {
                gbc.gridx = 0; gbc.gridy = i;
                formPanel.add(labels[i], gbc);

                gbc.gridx = 1;
                formPanel.add(fields[i], gbc);
            }

            // Add form panel to the main container
            outerMiddle.add(formPanel, BorderLayout.CENTER);

            // Add button at the bottom
            JButton addMemberButton = new JButton("Add Member");
            addMemberButtonListener(addMemberButton);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(addMemberButton);
            outerMiddle.add(buttonPanel, BorderLayout.SOUTH);
        }

        private void addMemberButtonListener(JButton btn) {
            btn.addActionListener(evt -> {
                if (IDField.getText()==null || IDField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID");
                    return;
                }

                DataAccess da = new DataAccessFacade();
                HashMap<String, LibraryMember> map = da.readMemberMap();
                if (map.containsKey(IDField.getText())){
                    JOptionPane.showMessageDialog(null, "Member already exists");
                }
                else{
                    Address address = new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
                    LibraryMember libraryMember = new LibraryMember(
                            IDField.getText(), firstNameField.getText(), lastNameField.getName(), cellField.getText(), address);
                    da.saveNewMember(libraryMember);
                    JOptionPane.showMessageDialog(null,"successfully added");
                    resetFormFields();
                }


            });
        }



        private void resetFormFields() {
            JTextField[] fields = {
                    IDField, firstNameField, lastNameField, streetField,
                    cityField, stateField, zipField, cellField
            };

            for (JTextField field : fields) {
                field.setText("");
            }
        }
    }