package is.hi.hbv202g.assignmentFinal.backend;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    private ArrayList<Lending> lendings;

    private ArrayList<Collection> collections;
    /**
     * Initializes a new Library System with empty collections for books, users, lendings, and book collections.
     */
    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.lendings = new ArrayList<>();
        this.collections = new ArrayList<>();
    }

    /**
     * Adds a book with a single author
     *
     * @param  title title of the book
     * @param  authorName name of the author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        Author author = new Author(authorName);
        Book book = new Book(title, author.getName());
        books.add(book);
    }

    /**
     * Adds a new book with multiple authors to the library.
     *
     * @param title The title of the book.
     * @param authors A list of authors of the book.
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) {
        Book book = null;
        try {
            book = new Book(title, authors);
        } catch (EmptyAuthorListException e) {
            System.out.println("Author list is empty");
            return;
        }
        books.add(book);
    }
    /**
     * Adds a new student user to the library system.
     *
     * @param name The name of the student.
     * @param feePaid Indicates whether the student has paid the necessary fees.
     */
    public void addStudentUser(String name, boolean feePaid) {
        User student = new Student(name, feePaid);
        users.add(student);
    }
    /**
     * Adds a new faculty member to the library system.
     *
     * @param name The name of the faculty member.
     * @param department The department with which the faculty member is affiliated.
     */
    public void addFacultyMemberUser(String name, String department) {
        User facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }
    /**
     * Finds a book by its title.
     *
     * @param title The title of the book to find.
     * @return The book if found, or null if no book matches the title.
     */
    public Book findBookByTitle(String title) {
        for(Book w:books){
            if(w.getTitle().equals(title)){
                return w;
            }
        }
        return null;
    }
    /**
     * Finds a user by their name.
     *
     * @param name The name of the user to find.
     * @return The user if found, or null if no user matches the name.
     */
    public User findUserByName(String name) {
        for(User w:users){
            if(w.getName().equals(name)){
                return w;
            }
        }
        return null;
    }
    /**
     * Attempts to borrow a book for a user. If the book is already borrowed, it will not be lent again.
     *
     * @param user The user who is attempting to borrow the book.
     * @param book The book that the user wants to borrow.
     * @return A string indicating if the book is currently available ("available"),
     *         already borrowed ("beingBorrowed"), or does not exist in the library ("bookDoesNotExist").
     */
    public String borrowBook(User user, Book book){
        for(Lending e:lendings){
            if(e.getBook().equals(book)){
                return "beingBorrowed";
            }
        }
        for(Book w:books){
            if(w.equals(book)){
                Lending lend = new Lending(book,user);
                lendings.add(lend);
                book.setBeingBorrowed(true);
                return "available";
            }
        }
        return "bookDoesNotExist";
    }
    /**
     * Extends the lending period for a specific book and user. Throws an exception if the book
     * is not currently lent out to the user.
     *
     * @param user The user who currently has the book.
     * @param book The book whose lending period is to be extended.
     * @param newDueDate The new due date for the book.
     * @throws UserOrBookDoesNotExistException if the book is not currently lent to the user.
     */
    public void extendLending(User user, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        boolean lendingFound = false;
        for (Lending w : lendings) {
            if (w.getUser().equals(user) && w.getBook().equals(book)) {
                w.setDueDate(newDueDate);
                lendingFound = true;
                break; // Once found, no need to continue the loop
            }
        }
        if (!lendingFound) {
            throw new UserOrBookDoesNotExistException(user.getName() + " is not lending " + book.getTitle());
        }
    }

    /**
     * Attempts to return a borrowed book. If the book is found and returned successfully,
     * the book is marked as not being borrowed.
     *
     * @param user The user returning the book.
     * @param book The book being returned.
     * @return true if the book was successfully returned, false if the book was not found in active lendings.
     */
    public boolean returnBook(User user, Book book) {

        for (Lending w : lendings) {
            if (w.getUser().equals(user) && w.getBook().equals(book)) {
                book.setBeingBorrowed(false);
                lendings.remove(w);
                return true; // Once found, no need to continue the loop
            }
        }
        return false;
    }
    /**
     * Returns a list of all books in the library.
     *
     * @return An ArrayList of all books.
     */
    public ArrayList<Book> getBooks(){
        return books;
    }
    /**
     * Returns a list of all users registered in the library.
     *
     * @return An ArrayList of all users.
     */
    public ArrayList<User> getUsers(){
        return users;
    }
    /**
     * Returns a list of all lendings currently active in the library.
     *
     * @return An ArrayList of all lendings.
     */
    public ArrayList<Lending> getLendings(){
        return lendings;
    }

    /**
     * Returns a list of all collections within the library.
     *
     * @return An ArrayList of all collections.
     */
    public ArrayList<Collection> getCollections(){
        return collections;
    }

    /**
     * Adds a collection of books with the same author
     *
     * @param titles list of book titles
     * @param author name of author
     * @param nameOfCollection the name of the collection
     */
    public void addCollectionOfBooksWithSingleAuthor(String nameOfCollection,List<String> titles, Author author){
        Collection collection = new Collection(nameOfCollection);
        for(String title: titles){
            addBookWithTitleAndNameOfSingleAuthor(title, author.getName());
            collection.addBook(findBookByTitle(title));
        }
        collections.add(collection);
    }
    /**
     * Adds a collection of books with the same authors
     * @param titles list of book titles
     * @param authors name of authors
     * @param nameOfCollection the name of the collection
     */
    public void addCollectionOfBooksWithListOfAuthors(String nameOfCollection,List<String> titles, List<Author> authors){
        Collection collection = new Collection(nameOfCollection);
        for(String title: titles){
            addBookWithTitleAndAuthorList(title, authors);
            collection.addBook(findBookByTitle(title));
        }
        collections.add(collection);
    }

    /**
     * @param user name of the user that is borrowing
     * @param nameOfCollection name of the collection
     * @return a string that says if the collection exists or if it is avalible or not
     */
    public String borrowCollection(User user, String nameOfCollection){
        //check if collection is in the library
        for(Collection collection:collections){
            if(nameOfCollection.equals(collection.getNameOfCollection())){
                //check if every book in the collection is available to borrow
                for(Book book:collection.getBookCollection()){
                    if(book.isBeingBorrowed()) return "notAllAvailable";
                }
                //if the collection is available we borrow each book in the collection
                for(Book book:collection.getBookCollection()){borrowBook(user,book);}
                return "CollectionAvailable";
            }
        }
        return "CollectionDoesNotExist";
    }
}
