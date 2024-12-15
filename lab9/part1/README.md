## 1. Implement methods using stream pipelines

[source code](./prob1)
```java
//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
public static List<String> allWhoseZipContains3() {
    DataAccess da = new DataAccessFacade();
    Collection<LibraryMember> members = da.readMemberMap().values();
    List<LibraryMember> mems = new ArrayList<>();
    mems.addAll(members);
    //implement
    return mems.stream().filter(member -> member.getAddress().getZip().contains("3")).map(LibraryMember::getMemberId).toList();

}
```


```java
//Returns a list of all isbns of books having at least two copies
public static List<String> allHavingAtLeastTwoCopies() {
    DataAccess da = new DataAccessFacade();
    Collection<Book> books = da.readBooksMap().values();
    List<Book> bs = new ArrayList<>();
    bs.addAll(books);
    //implement
    return bs.stream().filter(book -> book.getCopies().length >= 2).map(Book::getIsbn).toList();
}
```

```java
//Returns a list of all isbns of  Books that have multiple authors
public static List<String> allHavingMultipleAuthors() {
    DataAccess da = new DataAccessFacade();
    Collection<Book> books = da.readBooksMap().values();
    List<Book> bs = new ArrayList<>();
    bs.addAll(books);
    //implement
    return bs.stream().filter(book -> book.getAuthors().size() > 1).map(Book::getIsbn).toList();

}
```
