package is.hi.hbv202g.assignmentFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintStatements {


    //Prints a welcome message to the user at the start of the application.
    public void welcomeText() {
        System.out.println("Welcome to the library, at the moment we are completely empty");
        System.out.println("If you need any help, please type -help");
    }
    //Prints an error message when an unrecognized command is entered.
    public void commandNotFound() {
        System.out.println("Please type a legal command");
        System.out.println("If you need any help, type -help");
    }
    /**
     * Prints help text from a file to guide the user on how to use the library system commands.
     * @throws FileNotFoundException if the help text file is not found.
     */
    public void helpText() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("helpText.txt"));
        while (textFile.hasNextLine()) {
            System.out.println(textFile.nextLine());
        }
    }
    //Text that tells author he is missing a title or author
    public void missingAuthorOrTitleText() {
        System.out.println("You are missing a title or a author");
        System.out.println("to add a book use the command: -addBook -title -author");
    }
    /**
     * Prints a confirmation that a book with a single author has been added to the library.
     *
     * @param title The title of the book added.
     * @param author The author of the book added.
     */
    public void addBookWithTitleAndNameOfSingleAuthorText(String title, String author) {
        System.out.println("The book " + title + " after " + author + " was added in the Library");
    }


    /**
     * Prints a confirmation that a book with a list of authors has been added to the library.
     *
     * @param title The title of the book.
     * @param listOfAuthors The list of authors of the book.
     */
    public void addBookWithTitleAndAuthorListText(String title, List<Author> listOfAuthors) {
        StringBuilder authors = new StringBuilder();
        //We take the name of each author and append it to a StringBuilder
        for (Author author : listOfAuthors) {
            authors.append(author.getName()).append(", ");
        }
        //We remove the last ", " from the StringBuilder
        authors.delete(authors.length() - 2, authors.length() - 1);

        //Print out the results
        System.out.println("The book " + title + " was added to the Library after the authors:");
        System.out.println(authors);
    }
    //Text that tells user he is missing student name or his fee is paid
    public void missingNameOrFeeText() {
        System.out.println("You are missing the name of the student or if his fee is paid");
        System.out.println("Use the command: -addStudent -name -feeIsPaid(true/false)");
    }

    /**
     * Checks that user has the right amount of arguments.
     * Guides user to use various commands.
     * @param type type of command
     */
    public void notCorrectAmountOfArgs(String type) {
        switch (type) {
            case ("addStudent"):
                System.out.println(type + " takes 2 arguments");
                System.out.println("Use the command: -addStudent -name -feeIsPaid(true/false)");
                break;
            case ("addFacultyMember"):
                System.out.println(type + " takes 2 arguments");
                System.out.println("Use the command: -addFacultyMember -name -department ");
                break;
            case ("findBook"):
                System.out.println("the -findBook command takes one argument, the title of the book");
                System.out.println("Use the command: -findBook -title");
                break;
            case ("findUser"):
                System.out.println("the -findUser command takes one argument, the name of the user");
                System.out.println("Use the command: -findUser -name");
                break;
            case ("borrowBook"):
                System.out.println(type + " takes 2 arguments");
                System.out.println("Use the command: -borrowBook -user -title");
                break;
            case ("returnBook"):
                System.out.println(type + " takes 2 arguments");
                System.out.println("Use the command: -returnBook -user -title");
                break;
            case "help", "quit", "status":
                System.out.println("The command " + type + " doesn't take arguments, just type -" + type);
                break;
            case ("extendLending"):
                System.out.println(type + " takes 3 arguments");
                System.out.println("Use the command: -extendLending -user -title -newDueDate");
                break;
            case("addCollection"):
                System.out.println(type + " takes the minimum of 5 arguments");
                System.out.println("Use the command: -addCollection -nameOfCollection -T -title1 -title2... -A -author1 -author2...");
                break;
            case("borrowCollection"):
                System.out.println(type + " takes 2 arguments");
                System.out.println("Use the command: -borrowCollection -nameOfCollection -user");
                break;
        }
    }
<<<<<<< HEAD


    //asks user if fee is paid or not
=======
    //Alerts the user that the fee status should be a boolean value.
>>>>>>> 71c72004f72a4dda84926635cedf4b97f42e35a8
    public void feeIsPaidNotBoolean() {
        System.out.println("Please ensure that the feeIsPaid is either true or false");
        System.out.println("Use the command: -addStudent -name -feeIsPaid(true/false)");
    }
    //Alerts the user when necessary information for registering a faculty member is missing.
    public void missingNameOrDepartment() {
        System.out.println("You are missing the name of the member or his department");
        System.out.println("Use the command: -addFacultyMember -name -department ");
    }

    /**
     *Tells user if the book being searched for is in the library or not
     *
     * @param book the book you are looking for
     * @param title title of book being searched for
     */
    public void findBook(Book book, String title) {
        if(book!=null){
            System.out.println("The book "+ title+" is in the library");
        }
        else System.out.println("The book "+title +" is not in the library");
    }

    /**
     *Tells you if the name is a user or not
     *
     * @param user the user you are looking for
     * @param name name of user
     */
    public void findUser(User user, String name) {
        if(user!=null){
            System.out.println(name +" is a user in the library");
        }
        else System.out.println(name +" is not a user");
    }
    /**
     *Alerts the user that a specified user name does not correspond to any registered user.
     *
     * @param name name of user
     */
    public void userNotFound(String name) {
        System.out.println(name+" is not a user");
    }

    /**
     * Tells you that the title you are looking for is not in the library
     *
     * @param title title of book
     */
    public void bookNotFound(String title) {
        System.out.println(title+" is not in the library");
    }

    /**
     * Tells the user he is now borrowing a specific title
     * @param title title of the book
     * @param name name of user
     */
    public void bookAvailableToBorrow(String title, String name) {
        System.out.println(name+" is now borrowing the book: "+title);
    }

    /**
     * Title is being borrowed
     *
     * @param title title of borrowed book
     */
    public void bookIsBeingBorrowed(String title) {
        System.out.println(title+" is being borrowed");
    }

    /**
     * Name is now a user
     * @param name name of user
     */
    public void addedUser(String name) {
        System.out.println(name + " is now a User ");
    }
    //Text that tells the user or book is not in the system
    public void userOrBookNotFound() {
        System.out.println("The user or book are not in the library system");
    }
    /**
     * text that says the book has been returned
     * @param title title of returned book
     */
    public void bookAvailableToReturn(String title) {
        System.out.println(title+" has been returned");
    }

    /**
     * text that says title of the book is not being borrowed
     * @param title title of the book
     */
    public void bookIsNotBeingBorrowed(String title) {
        System.out.println(title+ " is not being borrowed thus you can't return it");
    }
    //Notifies the user that the format for adding a collection is incorrect and provides the correct command syntax.
    public void addCollectionNotCorrect(){
        System.out.println("You did not use the -addCollection command right");
        System.out.println("Please use the command: -addCollection -nameOfCollection -T -title1 -title2... -A -author1 -author2...");
    }

    /**
     *Shows info about the library system, about the state of books, users and lendings.
     *
     * @param librarySystem library system
     */
    public void status(LibrarySystem librarySystem){
        ArrayList<Book> books= librarySystem.getBooks();
        ArrayList<User> users = librarySystem.getUsers();
        ArrayList<Lending> lendings = librarySystem.getLendings();
        System.out.println("The Library has "+ books.size()+ " books and "+lendings.size() +" of them are currently being borrowed");
        System.out.println("There are "+users.size() +" users in the library system");
        //if there are any users we print them out
        if(!users.isEmpty()){
            System.out.println("**********");
            System.out.println("Users:");
            for(User user:users){
                System.out.println(user.getName());
            }
            System.out.println("**********");
        }
        //if there are any books we print them out
        if(!books.isEmpty()){
            System.out.println("**********");
            System.out.println("Books:");
            for(Book book:books){
                List<Author> listOfAuthors = book.getAuthors();
                StringBuilder authors = new StringBuilder();
                //we take the name of each author and append it to a StringBuilder
                for (Author author :listOfAuthors ) {
                    authors.append(author.getName()).append(", ");
                }
                //we remove the last ", " from the StringBuilder
                authors.delete(authors.length() - 2, authors.length() - 1);
                System.out.println(book.getTitle()+" after: "+ authors);
            }
            System.out.println("**********");
        }
        //if there is andy lendings we print them out
        if(!lendings.isEmpty()){
            System.out.println("**********");
            System.out.println("Lendings");
            for(Lending lending:lendings){
                System.out.println(lending.getBook().getTitle());
            }
            System.out.println("**********");
        }

    }
    /**
     * Notifies the user that a new collection of books has been successfully added to the library.
     * This method also lists the authors and titles included in the new collection.
     * @param nameOfCollection The name of the newly added collection.
     * @param titles The titles of the books included in the collection.
     * @param authors The authors of the books included in the collection.
     */
    public void collectionAdded(String nameOfCollection, List<String> titles, List<Author> authors) {
        System.out.println("The collection "+ nameOfCollection +" was added to the Library");
        System.out.println("Authors:");
        for (Author author: authors){
            System.out.println(author.getName());
        }
        System.out.println("Books:");
        for (String title: titles){
            System.out.println(title);
        }
    }
    /**
     * Notifies the user that a specified collection is now being borrowed by a user.
     * @param nameOfCollection The name of the collection being borrowed.
     * @param user The name of the user borrowing the collection.
     */
    public void collectionAvailableToBorrow(String nameOfCollection, String user) {
        System.out.println(user+" is now borrowing the collection "+ nameOfCollection);
    }
    //Alerts the user that the collection is not available for borrowing because one or more books in the collection are currently borrowed.
    public void someBookInCollectionIsBeingBorrowed() {
        System.out.println("The collection is not available to borrow because not all books are in the library");
    }
    /**
     * Informs the user that a specified collection name does not exist in the library.
     * @param collection The name of the collection being searched for.
     */
    public void collectionNotFound(String collection) {
        System.out.println(collection+" is not a collection in the library");
    }
    //Alerts the user that the value provided for days to add is not a valid integer.
    // */
    public void daysNotInt() {
        System.out.println("-daysToAdd has to be an integer greater or equal to 1");
        System.out.println("Please use the command: -extendLending -user -title -daysToAdd");
    }
    //Notifies the user that the lending period for a book has been successfully extended.
    public void exstendedLending() {
        System.out.println("The lending was extended");
    }
    //Informs the user that a specified lending could not be found in the library system.
    public void lendingNotFound() {
        System.out.println("The lending was not found");
    }
}
