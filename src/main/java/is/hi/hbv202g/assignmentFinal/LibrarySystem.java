package is.hi.hbv202g.assignmentFinal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    private ArrayList<Lending> lendings;

    private ArrayList<Collection> collections;

    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.lendings = new ArrayList<>();
        this.collections = new ArrayList<>();
    }

    /**
     * adds a book with a single author
     * @param  title title of the book
     * @param  authorName name of the author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        Author author = new Author(authorName);
        Book book = new Book(title, author.getName());
        books.add(book);
    }

    /**
     * adds a book with a list of authors
     * @param  title title of the book
     * @param  authors list of the authors
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

    public void addStudentUser(String name, boolean feePaid) {
        User student = new Student(name, feePaid);
        users.add(student);
    }

    public void addFacultyMemberUser(String name, String department) {
        User facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }

    public Book findBookByTitle(String title) {
        for(Book w:books){
            if(w.getTitle().equals(title)){
                return w;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        for(User w:users){
            if(w.getName().equals(name)){
                return w;
            }
        }
        return null;
    }

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

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        boolean lendingFound = false;
        for (Lending w : lendings) {
            if (w.getUser().equals(facultyMember) && w.getBook().equals(book)) {
                w.setDueDate(newDueDate);
                lendingFound = true;
                break; // Once found, no need to continue the loop
            }
        }
        if (!lendingFound) {
            throw new UserOrBookDoesNotExistException(facultyMember.getName() + " is not lending " + book.getTitle());
        }
    }

    //returns true if the book can be returned else returns false
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
     * @return list of books
     */
    public ArrayList<Book> getBooks(){
        return books;
    }
    /**
     * @return list of users
     */
    public ArrayList<User> getUsers(){
        return users;
    }
    /**
     * @return list of lendings
     */
    public ArrayList<Lending> getLendings(){
        return lendings;
    }

    /**
     * @return list of collections
     */
    public ArrayList<Collection> getCollections(){
        return collections;
    }

    /**
     * Adds a collection of books with the same author
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
