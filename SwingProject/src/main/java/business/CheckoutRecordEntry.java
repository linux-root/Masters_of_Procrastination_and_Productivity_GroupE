package business;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {
    private CheckoutRecord checkoutRecord;
    private BookCopy bookCopy;
    private Date checkoutDate;
    private Date dueDate;

    //public CheckoutRecordEntry() {}

    public CheckoutRecordEntry(CheckoutRecord checkoutRecord, BookCopy bookCopy, Date checkoutDate, Date dueDate) {
        this.checkoutRecord = checkoutRecord;
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
