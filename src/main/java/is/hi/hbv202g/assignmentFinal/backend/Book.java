package is.hi.hbv202g.assignmentFinal.backend;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private boolean beingBorrowed;
    /**
     * Constructs a new Book with a single author.
     * Initializes the list of authors and sets the being borrowed status to false.
     *
     * @param title The title of the book.
     * @param authorName The name of the author to add to the book's list of authors.
     */
    public Book(String title, String authorName) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
        this.beingBorrowed = false;

    }
    /**
     * Constructs a new Book with a list of authors. Throws exception if the list is empty.
     *
     * @param title The title of the book.
     * @param authors A list of authors for the book.
     * @throws EmptyAuthorListException if the list of authors is empty.
     */
    public Book(String title, List<Author> authors) throws EmptyAuthorListException  {
        if(authors.isEmpty()){
            throw new EmptyAuthorListException("The Author list is empty");
        }
        this.title = title;
        this.authors = authors;
    }
    /**
     * Returns the list of authors of the book.
     *
     * @return the list of authors.
     */
    public List<Author> getAuthors() {
        return authors;
    }
    /**
     * Sets the list of authors for the book. Throws an exception if the list is empty.
     *
     * @param authors A list of authors to be set for the book.
     * @throws EmptyAuthorListException if the list of authors is empty.
     */
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if(authors.isEmpty()){
            throw new EmptyAuthorListException("Author list empty");
        }
        this.authors = authors;

    }
    /**
     * Adds a single author to the list of authors of the book.
     *
     * @param author The author to add to the book.
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
    }
    /**
     * Returns the title of the book.
     *
     * @return the title of the book.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Sets the being borrowed status of the book.
     *
     * @param status The new borrowed status of the book.
     */
    public void setBeingBorrowed(boolean status){
        this.beingBorrowed = status;
    }
    /**
     * Checks if the book is currently being borrowed.
     *
     * @return true if the book is being borrowed, false otherwise.
     */
    public boolean isBeingBorrowed(){return this.beingBorrowed;}

}