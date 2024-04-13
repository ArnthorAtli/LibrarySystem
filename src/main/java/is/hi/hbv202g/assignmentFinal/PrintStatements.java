package is.hi.hbv202g.assignmentFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintStatements {


    // text that welcomes the user
    public void welcomeText() {
        System.out.println("Welcome to the library, at the moment we are completely empty");
        System.out.println("If you need any help, please type -help");
    }

    public void commandNotFound() {
        System.out.println("Please type a legal command");
        System.out.println("If you need any help, type -help");
    }

    public void helpText() throws FileNotFoundException {
        Scanner textFile = new Scanner(new File("helpText.txt"));
        while (textFile.hasNextLine()) {
            System.out.println(textFile.nextLine());
        }
    }

    public void missingAuthorOrTitleText() {
        System.out.println("You are missing a title or a author");
        System.out.println("to add a book use the command: -addBook -title -author");
    }

    public void addBookWithTitleAndNameOfSingleAuthorText(String title, String author) {
        System.out.println("The book " + title + " after " + author + " was added in the Library");
    }


    public void addBookWithTitleAndAuthorListText(String title, List<Author> listOfAuthors) {
        StringBuilder authors = new StringBuilder();
        //we take the name of each author and append it to a StringBuilder
        for (Author author : listOfAuthors) {
            authors.append(author.getName()).append(", ");
        }
        //we remove the last ", " from the StringBuilder
        authors.delete(authors.length() - 2, authors.length() - 1);

        //print out the results
        System.out.println("The book " + title + " was added to the Library after the authors:");
        System.out.println(authors);
    }

    public void missingNameOrFeeText() {
        System.out.println("You are missing the name of the student or if his fee is paid");
        System.out.println("Use the command: -addStudent -name -feeIsPaid(true/false)");
    }

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
            case "help", "status", "quit":
                System.out.println("The command " + type + " doesn't take arguments, just type -" + type);
                break;
            case ("extendLending"):
                System.out.println(type + " takes 3 arguments");
                System.out.println("Use the command: -extendLending -user -title -newDueDate");
                break;
        }
    }

    public void feeIsPaidNotBoolean() {
        System.out.println("Please ensure that the feeIsPaid is either true or false");
        System.out.println("Use the command: -addStudent -name -feeIsPaid(true/false)");
    }

    public void missingNameOrDepartment() {
        System.out.println("You are missing the name of the member or his department");
        System.out.println("Use the command: -addFacultyMember -name -department ");
    }

    public void findBook(Book book, String title) {
        if(book!=null){
            System.out.println("The book "+ title+" is in the library");
        }
        else System.out.println("The book "+title +" is not in the library");
    }

    public void findUser(User user, String name) {
        if(user!=null){
            System.out.println(name +" is a user in the library");
        }
        else System.out.println(name +" is not a user");
    }

    public void userNotFound(String name) {
        System.out.println(name+" is not a user");
    }
    public void bookNotFound(String title) {
        System.out.println(title+" is not in the library");
    }

    public void bookAvailableToBorrow(String title, String name) {
        System.out.println(name+" is now borrowing the book: "+title);
    }

    public void bookIsBeingBorrowed(String title) {
        System.out.println(title+" is being borrowed");
    }

    public void addedUser(String name) {
        System.out.println(name + " is now a User ");
    }

    public void userOrBookNotFound() {
        System.out.println("The user or book are not in the library system");
    }

    public void bookAvailableToReturn(String title) {
        System.out.println(title+" has been returned");
    }

    public void bookIsNotBeingBorrowed(String title) {
        System.out.println(title+ " is not being borrowed thus you can't return it");
    }
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
        if(!lendings.isEmpty()){
            System.out.println("**********");
            System.out.println("Lendings");
            for(Lending lending:lendings){
                System.out.println(lending.getBook().getTitle());
            }
            System.out.println("**********");
        }

    }
}
