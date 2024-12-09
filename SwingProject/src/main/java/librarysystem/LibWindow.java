package librarysystem;

import java.net.URISyntaxException;

public interface LibWindow {
	void init() throws URISyntaxException;
	boolean isInitialized();
	void isInitialized(boolean val);
	void setVisible(boolean b);
}

