package is.hi.hbv202g.assignmentFinal;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import is.hi.hbv202g.assignmentFinal.backend.*;
import is.hi.hbv202g.assignmentFinal.backend.Author;
import is.hi.hbv202g.assignmentFinal.frontend.*;

import static org.junit.Assert.*;


/**
 * Unit test for simple Main.
 */
public class LibrarySystemTest {
    LibrarySystem librarySystem;

    @Before
    public void createAEmptyLibrarySystem() {
        librarySystem = new LibrarySystem();
    }

    @Test
    public void testAddBookWithTitleAndNameOfSingleAuthor() {
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Hunger Games", "John Doe");
        assertEquals(1, librarySystem.getBooks().size());
        assertEquals("Hunger Games", librarySystem.findBookByTitle("Hunger Games").getTitle());
        assertEquals("John Doe", librarySystem.findBookByTitle("Hunger Games").getAuthors().get(0).getName());
    }

    @Test
    public void testAddBookWithTitleAndAuthorList() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Jane Smith"));
        authors.add(new Author("Tom Brown"));
        librarySystem.addBookWithTitleAndAuthorList("Hunger Games 2", authors);
        assertEquals(1, librarySystem.getBooks().size());
        assertEquals("Hunger Games 2", librarySystem.findBookByTitle("Hunger Games 2").getTitle());
        assertEquals(authors, librarySystem.findBookByTitle("Hunger Games 2").getAuthors());
    }

    @Test
    public void testAddStudentUser() {
        librarySystem.addStudentUser("Alice", true);
        assertEquals(1, librarySystem.getUsers().size());
        assertTrue(librarySystem.getUsers().get(0) instanceof Student);
        assertEquals("Alice", librarySystem.findUserByName("Alice").getName());
        assertTrue(((Student) librarySystem.findUserByName("Alice")).isFeePaid());
    }

    @Test
    public void testExtendLending() {
        // Add a book to the library
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Introduction to Programming", "Alice Smith");
        // Add a faculty member user
        librarySystem.addFacultyMemberUser("Bob", "Computer Science");
        // Borrow the book
        User user = librarySystem.findUserByName("Bob");
        Book book = librarySystem.findBookByTitle("Introduction to Programming");
        librarySystem.borrowBook(user, book);
        // Extend the lending
        LocalDate newDueDate = LocalDate.now().plusDays(7); // Extend by 7 days
        try {
            librarySystem.extendLending((FacultyMember) user, book, newDueDate);
        } catch (UserOrBookDoesNotExistException e) {
            fail("Should not throw UserOrBookDoesNotExistException");
        }
        // Verify the new due date
        assertEquals(newDueDate, librarySystem.getLendings().get(0).getDueDate());
    }

    @Test
    public void testBorrowCollection() {
        // Add a collection to the library
        List<String> titles = Arrays.asList("Book 1", "Book 2", "Book 3");
        Author author = new Author("John Doe");
        librarySystem.addCollectionOfBooksWithSingleAuthor("Collection 1", titles, author);
        // Add a student user
        librarySystem.addStudentUser("Alice", true);
        User user = librarySystem.findUserByName("Alice");
        // Borrow the collection
        assertEquals("CollectionAvailable", librarySystem.borrowCollection(user, "Collection 1"));
        // Verify that all books in the collection are borrowed
        for (Book book : librarySystem.getCollections().get(0).getBookCollection()) {
            assertTrue(book.isBeingBorrowed());
        }
    }

    @Test
    public void testReturnBook() {
        // Add a book to the library
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("The Great Gatsby", "F. Scott Fitzgerald");
        // Add a student user
        librarySystem.addStudentUser("Alice", true);
        User user = librarySystem.findUserByName("Alice");
        Book book = librarySystem.findBookByTitle("The Great Gatsby");
        // Borrow the book
        librarySystem.borrowBook(user, book);
        // Return the book
        assertTrue(librarySystem.returnBook(user, book));
        // Verify that the book is no longer being borrowed
        assertFalse(book.isBeingBorrowed());
    }

    @Test
    public void testFindBookByTitle_NotFound() {
        // Attempt to find a non-existing book
        assertNull(librarySystem.findBookByTitle("Non-existent Book"));
    }

    @Test
    public void testFindUserByName_NotFound() {
        // Attempt to find a non-existing user
        assertNull(librarySystem.findUserByName("Non-existent User"));
    }

    @Test
    public void testAddCollectionOfBooksWithListOfAuthors() {
        // Add a collection of books with a list of authors
        List<String> titles = Arrays.asList("Book 4", "Book 5");
        List<Author> authors = Arrays.asList(new Author("John Smith"), new Author("Emily Brown"));
        librarySystem.addCollectionOfBooksWithListOfAuthors("Collection 2", titles, authors);
        // Verify that the collection was added successfully
        assertEquals(1, librarySystem.getCollections().size());
    }

    @Test
    public void testAddFacultyMemberUser() {
        // Add a faculty member user
        librarySystem.addFacultyMemberUser("Prof. Smith", "Computer Science");
        // Verify that the faculty member user was added successfully
        assertNotNull(librarySystem.findUserByName("Prof. Smith"));
        assertTrue(librarySystem.findUserByName("Prof. Smith") instanceof FacultyMember);
    }

    @Test
    public void testBorrowBook_NotAvailable() {
        // Attempt to borrow a book that is not available
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Introduction to Algorithms", "Thomas H. Cormen");
        // Add a student user
        librarySystem.addStudentUser("Bob", true);
        User user = librarySystem.findUserByName("Bob");
        Book book = librarySystem.findBookByTitle("Introduction to Algorithms");
        // Borrow the book
        librarySystem.borrowBook(user, book);
        // Attempt to borrow the same book again
        assertEquals("beingBorrowed", librarySystem.borrowBook(user, book));
    }

    @Test
    public void testBorrowCollection_CollectionDoesNotExist() {
        // Attempt to borrow a collection that does not exist
        librarySystem.addStudentUser("Carol", true);
        User user = librarySystem.findUserByName("Carol");
        assertEquals("CollectionDoesNotExist", librarySystem.borrowCollection(user, "Non-existent Collection"));
    }

    @Test
    public void testAddBookWithTitleAndAuthorList_EmptyAuthorList() {
        // Attempt to add a book with an empty author list
        List<Author> authors = new ArrayList<>();
        librarySystem.addBookWithTitleAndAuthorList("Book Without Authors", authors);
        // Verify that no book is added
        assertEquals(0, librarySystem.getBooks().size());
    }
    @Test
    public void testGetLendings() {
        // Add a book and a user, and borrow the book
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book 1", "Author 1");
        librarySystem.addStudentUser("Alice", true);
        User user = librarySystem.findUserByName("Alice");
        Book book = librarySystem.findBookByTitle("Book 1");
        librarySystem.borrowBook(user, book);

        // Check if lendings contain the borrowing information
        assertEquals(1, librarySystem.getLendings().size());
        assertEquals(book, librarySystem.getLendings().get(0).getBook());
        assertEquals(user, librarySystem.getLendings().get(0).getUser());
    }

    @Test
    public void testGetCollections() {
        // Add a collection to the library
        List<String> titles = Arrays.asList("Book 1", "Book 2", "Book 3");
        Author author = new Author("John Doe");
        librarySystem.addCollectionOfBooksWithSingleAuthor("Collection 1", titles, author);

        // Check if collections contain the added collection
        assertEquals(1, librarySystem.getCollections().size());
        assertEquals("Collection 1", librarySystem.getCollections().get(0).getNameOfCollection());
        assertEquals(titles.size(), librarySystem.getCollections().get(0).getBookCollection().size());
    }

    @Test
    public void testAddCollectionOfBooksWithSingleAuthor() {
        // Add a collection of books with a single author
        List<String> titles = Arrays.asList("Book 1", "Book 2", "Book 3");
        Author author = new Author("John Doe");
        librarySystem.addCollectionOfBooksWithSingleAuthor("Collection 1", titles, author);

        // Check if the collection was added successfully
        assertEquals(1, librarySystem.getCollections().size());
        assertEquals("Collection 1", librarySystem.getCollections().get(0).getNameOfCollection());
        assertEquals(titles.size(), librarySystem.getCollections().get(0).getBookCollection().size());
    }

}
