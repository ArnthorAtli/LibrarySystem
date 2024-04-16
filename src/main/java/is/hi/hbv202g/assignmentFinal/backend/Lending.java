package is.hi.hbv202g.assignmentFinal.backend;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;
    /**
     * Constructs a new Lending instance initializing the book and user involved in the lending,
     * and setting the due date to 30 days from the current date.
     *
     * @param book the Book being lent.
     * @param user the User to whom the book is lent.
     */
    public Lending(Book book, User user) {
        this.book = book;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(30);
    }
    /**
     * Returns the due date of the book lending.
     *
     * @return the due date as a LocalDate object.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }
    /**
     * Sets the due date for when the book should be returned.
     *
     * @param dueDate the new due date as a LocalDate.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * Retrieves the book involved in this lending transaction.
     *
     * @return the Book that is lent.
     */
    public Book getBook() {
        return book;
    }
    /**
     * Sets the book involved in this lending transaction.
     *
     * @param book the Book to be lent.
     */
    public void setBook(Book book) {
        this.book = book;
    }
    /**
     * Retrieves the user involved in this lending transaction.
     *
     * @return the User to whom the book is lent.
     */
    public User getUser() {
        return user;
    }
    /**
     * Sets the user involved in this lending transaction.
     *
     * @param user the User to whom the book is lent.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
