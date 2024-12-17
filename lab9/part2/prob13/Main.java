package prob13;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Book book = new Book("test", 3);
		List<BookCopy> copies = book.getCopies();
		copies.forEach(copy -> copy.changeAvailability());
		
		System.out.println(book.isAvailable());

	}

}
