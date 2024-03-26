package is.hi.hbv202g.assignment8;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    private ArrayList<Lending> lendings;

    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.lendings = new ArrayList<>();
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        Author author = new Author(authorName);
        Book book = new Book(title, author.getName());
        books.add(book);
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        Book book = new Book(title, authors);
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

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for(Book w:books){
            if(w.getTitle().equals(title)){
                return w;
            }
        }
        throw new UserOrBookDoesNotExistException("The book "+title+" is not in the library" );
    }

    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for(User w:users){
            if(w.getName().equals(name)){
                return w;
            }
        }
        throw new UserOrBookDoesNotExistException(name+" is not a user");
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        for(Lending e:lendings){
            if(e.getBook().equals(book)){
                throw new UserOrBookDoesNotExistException("The book "+book.getTitle()+" is already being borrowed");
            }
        }
        for(Book w:books){
            if(w.equals(book)){
                Lending lend = new Lending(book,user);
                lendings.add(lend);
                return;
            }
        }
        throw new UserOrBookDoesNotExistException("The book "+book.getTitle()+" is not in the library" );
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

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        boolean lendingFound = false;
        for (Lending w : lendings) {
            if (w.getUser().equals(user) && w.getBook().equals(book)) {
                lendings.remove(w);
                lendingFound = true;
                break; // Once found, no need to continue the loop
            }
        }
        if (!lendingFound) {
            throw new UserOrBookDoesNotExistException(user.getName() + " is not lending " + book.getTitle());
        }
    }
}
