package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.*;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, Author> readAuthors();
	public void saveNewAuthor(Author author);
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book);
	public void saveNewBookCopy(BookCopy bookCopy);
	public void saveCheckoutRecord(CheckoutRecord record);
	public  HashMap<String,CheckoutRecord> readCheckoutRecordsMap();
}
