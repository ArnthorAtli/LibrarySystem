package is.hi.hbv202g.assignmentFinal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import is.hi.hbv202g.assignmentFinal.frontend.Reader;

public class ReaderTest {

    private Reader reader;

    @Before
    public void setUp() {
        reader = new Reader();
    }

    @Test
    public void commandNotFound() {
        // Test when the command is not found
        assertNull(reader.readInput("invalidCommand"));
    }

    @Test
    public void helpCommand() {
        // Test the help command
        assertArrayEquals(new String[]{"help"}, reader.readInput("-help"));
    }
    @Test
    public void statusCommand() {
        // Test the status command
        assertArrayEquals(new String[]{"status"}, reader.readInput("-status"));
    }
    @Test
    public void quitCommand() {
        // Test the quit command
        assertArrayEquals(new String[]{"status"}, reader.readInput("-status"));
    }

    @Test
    public void addBookCommand() {
        // Test the addBook command with correct arguments
        assertArrayEquals(new String[]{"addBook", "Title", "Author"}, reader.readInput("-addBook - Title - Author"));
        // Test the addBook command with missing arguments
        assertNull(reader.readInput("-addBook - Title"));
    }

    @Test
    public void addStudentCommand() {
        // Test the addStudent command with correct arguments
        assertArrayEquals(new String[]{"addStudent", "Name", "true"}, reader.readInput("-addStudent - Name - true"));
        // Test the addStudent command with missing arguments
        assertNull(reader.readInput("-addStudent - Name"));
        // Test the addStudent command with too many arguments
        assertNull(reader.readInput("-addStudent - Name - true - extraArg"));
    }
    @Test
    public void addFacultyMemberCommand() {
        // Test the addFacultyMember command with correct arguments
        assertArrayEquals(new String[]{"addFacultyMember", "Name", "Department"}, reader.readInput("-addFacultyMember - Name - Department"));
        // Test the addFacultyMember command with missing arguments
        assertNull(reader.readInput("-addFacultyMember - Name"));
        // Test the addFacultyMember command with too many arguments
        assertNull(reader.readInput("-addFacultyMember - Name - Department - extraArg"));
    }

    @Test
    public void findBookCommand() {
        // Test the findBook command with correct arguments
        assertArrayEquals(new String[]{"findBook", "Title"}, reader.readInput("-findBook - Title"));
        // Test the findBook command with missing arguments
        assertNull(reader.readInput("-findBook"));
        // Test the findBook command with too many arguments
        assertNull(reader.readInput("-findBook - Title - extraArg"));
    }

    @Test
    public void findUserCommand() {
        // Test the findUser command with correct arguments
        assertArrayEquals(new String[]{"findUser", "Name"}, reader.readInput("-findUser - Name"));
        // Test the findUser command with missing arguments
        assertNull(reader.readInput("-findUser"));
        // Test the findUser command with too many arguments
        assertNull(reader.readInput("-findUser - Name - extraArg"));
    }

    @Test
    public void borrowBookCommand() {
        // Test the borrowBook command with correct arguments
        assertArrayEquals(new String[]{"borrowBook", "User", "Book"}, reader.readInput("-borrowBook - User - Book"));
        // Test the borrowBook command with missing arguments
        assertNull(reader.readInput("-borrowBook - User"));
        // Test the borrowBook command with too many arguments
        assertNull(reader.readInput("-borrowBook - User - Book - extraArg"));
    }

    @Test
    public void returnBookCommand() {
        // Test the returnBook command with correct arguments
        assertArrayEquals(new String[]{"returnBook", "User", "Book"}, reader.readInput("-returnBook - User - Book"));
        // Test the returnBook command with missing arguments
        assertNull(reader.readInput("-returnBook - User"));
        // Test the returnBook command with too many arguments
        assertNull(reader.readInput("-returnBook - User - Book - extraArg"));
    }

    @Test
    public void borrowCollectionCommand() {
        // Test the borrowCollection command with correct arguments
        assertArrayEquals(new String[]{"borrowCollection", "User", "CollectionName"}, reader.readInput("-borrowCollection - User - CollectionName"));
        // Test the borrowCollection command with missing arguments
        assertNull(reader.readInput("-borrowCollection - User"));
        // Test the borrowCollection command with too many arguments
        assertNull(reader.readInput("-borrowCollection - User - CollectionName - extraArg"));
    }

    @Test
    public void addCollectionCommand() {
        // Test the addCollection command with correct arguments
        assertArrayEquals(new String[]{"addCollection", "CollectionName","T","Book1", "Book2","A","author1"}, reader.readInput("-addCollection - CollectionName -T- Book1 - Book2 -A-author1"));
        // Test the addCollection command with missing arguments
        assertNull(reader.readInput("-addCollection - CollectionName"));
    }
}