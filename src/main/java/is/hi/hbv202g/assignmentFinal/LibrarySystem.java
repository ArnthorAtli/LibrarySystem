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

    public String borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        for(Lending e:lendings){
            if(e.getBook().equals(book)){
                return "beingBorrowed";
            }
        }
        for(Book w:books){
            if(w.equals(book)){
                Lending lend = new Lending(book,user);
                lendings.add(lend);
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
                lendings.remove(w);
                return true; // Once found, no need to continue the loop
            }
        }
        return false;
    }
    public ArrayList<Book> getBooks(){
        return books;
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public ArrayList<Lending> getLendings(){
        return lendings;
    }
}
