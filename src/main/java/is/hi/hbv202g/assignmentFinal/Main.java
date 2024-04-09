package is.hi.hbv202g.assignmentFinal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    //create a librarySystem
    public static void main(String[] args) throws FileNotFoundException, UserOrBookDoesNotExistException {
        //create a Scanner to read input from the user
        Scanner input = new Scanner(System.in);
        boolean active = true;

        //create a class that contains all the text messages
        PrintStatements print = new PrintStatements();
        //create a class that reads the input
        Reader reader = new Reader();
        LibrarySystem myLibrarySystem = new LibrarySystem();


        //welcome the user
        print.welcomeText();
        //keep the user interface active until the user quits
        while (active) {
            //read the input and split it at every -
            //the reader detect if the command or arguments are not legal and lets the user know
            String[] commandsAndArgs = reader.readInput(input.nextLine());

            //if the command and arguments are legal we find the command and execute it
            if(commandsAndArgs!=null) {
                String command = commandsAndArgs[0];
                switch (command) {
                    //-help command prints out instructions on how the commands work
                    case "help":
                        print.helpText();
                        break;
                    /*we have to break addBook into 2 cases:
                     * The book has one author
                     * The book has a list of authors*/
                    case "addBook":
                        //if the book has one author
                        if (commandsAndArgs.length == 3) {
                            myLibrarySystem.addBookWithTitleAndNameOfSingleAuthor(commandsAndArgs[1], commandsAndArgs[2]);
                            print.addBookWithTitleAndNameOfSingleAuthorText(commandsAndArgs[1], commandsAndArgs[2]);
                        }
                        //if the book has a list of authors
                        else {
                            List<Author> authors = new ArrayList<>();
                            for (int w = 2; w < commandsAndArgs.length; w++) {
                                authors.add(new Author(commandsAndArgs[w]));
                            }
                            myLibrarySystem.addBookWithTitleAndAuthorList(commandsAndArgs[1], authors);
                            print.addBookWithTitleAndAuthorListText(commandsAndArgs[1], authors);
                        }
                        break;

                    /*we have to break addStudent into 3 cases:
                     * The feeIsPaid is true
                     * The feeIsPaid is false
                     * The feeIsPaid is not a boolean
                     */
                    case "addStudent":
                        //if the fee is paid add the student with true
                        if (commandsAndArgs[2].equals("true")) {
                            myLibrarySystem.addStudentUser(commandsAndArgs[1], true);
                            print.addedUser(commandsAndArgs[1]);
                        }
                        //if the fee is not paid add the student with false
                        else if (commandsAndArgs[2].equals("false")) {
                            myLibrarySystem.addStudentUser(commandsAndArgs[1], false);
                            print.addedUser(commandsAndArgs[1]);
                        }
                        //else ask the user to type the command correctly
                        else
                            print.feeIsPaidNotBoolean();
                        break;
                    //-addFacultyMember -name -department
                    case "addFacultyMember":
                        myLibrarySystem.addFacultyMemberUser(commandsAndArgs[1], commandsAndArgs[2]);
                        print.addedUser(commandsAndArgs[1]);
                        break;

                    case "findBook":
                        print.findBook(myLibrarySystem.findBookByTitle(commandsAndArgs[1]), commandsAndArgs[1]);
                        break;
                    case "findUser":
                        print.findUser(myLibrarySystem.findUserByName(commandsAndArgs[1]), commandsAndArgs[1]);
                        break;
                    //-borrowBook -user -title
                    case "borrowBook":
                        Book bookToBorrow = myLibrarySystem.findBookByTitle(commandsAndArgs[2]);
                        //Check if the user is a user
                        User userToBorrow = myLibrarySystem.findUserByName(commandsAndArgs[1]);
                        if (userToBorrow == null) {
                            print.userNotFound(commandsAndArgs[1]);
                            break;
                        }
                        /* We have to let the user know of these three cases
                         * The book is available and the user borrows the book
                         * The book is being borrowed thus the user can't borrow it
                         * The book is not in the library  */
                        else {
                            switch (myLibrarySystem.borrowBook(userToBorrow, bookToBorrow)) {
                                case "available":
                                    print.bookAvailableToBorrow(bookToBorrow.getTitle(), userToBorrow.getName());
                                    break;
                                case "beingBorrowed":
                                    print.bookIsBeingBorrowed(bookToBorrow.getTitle());
                                    break;
                                case "bookDoesNotExist":
                                    print.bookNotFound(bookToBorrow.getTitle());
                                    break;
                            }
                        }
                        break;

                    case "extendLending":

                        break;
                    //-returnBook -user -title
                    case "returnBook":
                        Book bookToBeReturned = myLibrarySystem.findBookByTitle(commandsAndArgs[2]);
                        User userReturning = myLibrarySystem.findUserByName(commandsAndArgs[1]);
                        //If the user or book does not exist
                        if((bookToBeReturned==null)||(userReturning==null)){
                            print.userOrBookNotFound();
                        }
                        //If the book can be returned
                        else if(myLibrarySystem.returnBook(userReturning,bookToBeReturned)){
                            print.bookAvailableToReturn(bookToBeReturned.getTitle());
                        }
                        //If the book is not being borrowed
                        else{
                            print.bookIsNotBeingBorrowed(bookToBeReturned.getTitle());
                        }
                        break;
                    case "status":
                        print.status(myLibrarySystem);
                        break;
                    case "quit":
                        print.status(myLibrarySystem);
                        active = false;
                        break;
                }
            }
        }
    }
}
