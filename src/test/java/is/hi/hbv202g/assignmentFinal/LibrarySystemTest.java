package is.hi.hbv202g.assignmentFinal;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple Main.
 */
public class LibrarySystemTest
{
    LibrarySystem librarySystem;
    @Before
    public void createAEmptyLibrarySystem()
    {
        librarySystem  = new LibrarySystem();
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

}
