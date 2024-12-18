package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>();
    private LibraryMember member;

    public CheckoutRecord(LibraryMember member) {
        this.member = member;
    }
    public void addCheckoutRecordEntry(BookCopy bookCopy, Date checkoutDate, Date dueDate) {
        CheckoutRecordEntry entry = new CheckoutRecordEntry(this, bookCopy, checkoutDate, dueDate);
        checkoutRecordEntries.add(entry);
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    public void setCheckoutRecordEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
        this.checkoutRecordEntries = checkoutRecordEntries;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }


    private static final long serialVersionUID = -2226197332290714013L;

}
