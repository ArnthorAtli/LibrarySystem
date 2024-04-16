package is.hi.hbv202g.assignmentFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collection {
    private List<Book> books;
    private String nameOfCollection;

    /**
     * Constructs a new Collection with a specified name.
     * Initializes the list of books as an empty list.
     *
     * @param nameOfCollection The name of the book collection.
     */
    public Collection(String nameOfCollection){
        this.nameOfCollection = nameOfCollection;
        this.books = new ArrayList<>();
        }

    /**
     * Adds a book to the collection
     * @param book book to be added
     */
    public void addBook(Book book){
        this.books.add(book);
    }
    /**
     * Retrieves the book collection.
     * @return the list of books in the collection
     */
    public List<Book> getBookCollection(){
        return this.books;
    }

    /**
     * @return the name of the collection
     */
    public String getNameOfCollection() {
        return nameOfCollection;
    }
}
