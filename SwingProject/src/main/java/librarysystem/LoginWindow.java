package librarysystem;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class LoginWindow extends JFrame implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();
	public static JFrame manageFrame;

	private boolean isInitialized = false;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel messageLabel;

	private LoginWindow() {}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public void clear() {
		messageLabel.setText("");
	}

	public void init() {
		if (isInitialized) return;

		setTitle("Library Login");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(60, 60, 200));
		headerPanel.setPreferredSize(new Dimension(100, 60));
		JLabel headerLabel = new JLabel("Library Login");
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
		headerPanel.add(headerLabel);

		// Form Panel
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		gbc.gridx = 0; gbc.gridy = 0;
		formPanel.add(usernameLabel, gbc);

		usernameField = new JTextField(15);
		usernameField.setMinimumSize(usernameField.getPreferredSize());
		gbc.gridx = 1;
		formPanel.add(usernameField, gbc);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		gbc.gridx = 0; gbc.gridy = 1;
		formPanel.add(passwordLabel, gbc);

		passwordField = new JPasswordField(15);
		passwordField.setMinimumSize(passwordField.getPreferredSize());
		gbc.gridx = 1;
		formPanel.add(passwordField, gbc);

		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.RED);
		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
		formPanel.add(messageLabel, gbc);

		JButton loginButton = new JButton("Login");
		loginButton.setBackground(new Color(60, 120, 200));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.setPreferredSize(new Dimension(100, 30));
		addLoginButtonListener(loginButton);

		gbc.gridx = 1; gbc.gridy = 3;
		formPanel.add(loginButton, gbc);

		// Add components to frame
		add(headerPanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		isInitialized = true;
	}

	private void addLoginButtonListener(JButton loginButton) {
		loginButton.addActionListener(evt -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());

			DataAccess da = new DataAccessFacade();
			HashMap<String, User> map = da.readUserMap();

			if (!map.containsKey(username)) {
				messageLabel.setText("User does not exist.");
			} else if (!map.get(username).getPassword().equals(password)) {
				messageLabel.setText("Invalid username or password.");
			} else {
				messageLabel.setText("");
				User user = map.get(username);
				Auth auth = user.getAuthorization();

				LibrarySystem.hideAllWindows();
				manageFrame = new ManageWindow(auth);
				manageFrame.setTitle("MIU Library Company");
				manageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Util.centerFrameOnDesktop(manageFrame);
				manageFrame.setVisible(true);
			}
		});
	}
}
