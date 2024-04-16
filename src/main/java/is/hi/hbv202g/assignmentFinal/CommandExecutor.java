package is.hi.hbv202g.assignmentFinal;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExecutor {
    /**
     *
     * @param myLibrarySystem The library System
     * @param commandsAndArgs An array containing Strings of legal command and arguments
     * @return The LibrarySystem after the command is executed, returns null if the user quits
     */
    public LibrarySystem executeCommand(LibrarySystem myLibrarySystem, String[] commandsAndArgs){
        String command = commandsAndArgs[0];
        PrintStatements print = new PrintStatements();
        switch(command)

        {
            //-help command prints out instructions on how the commands work
            case "help":
                try {
                    print.helpText();
                } catch (FileNotFoundException e) {
                    System.out.println("HelpText file not found");
                }
                return myLibrarySystem;
            /*we have to return myLibrarySystem; addBook into 2 cases:
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
                return myLibrarySystem;

            /*we have to return myLibrarySystem; addStudent into 3 cases:
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
                return myLibrarySystem;
            //-addFacultyMember -name -department
            case "addFacultyMember":
                myLibrarySystem.addFacultyMemberUser(commandsAndArgs[1], commandsAndArgs[2]);
                print.addedUser(commandsAndArgs[1]);
                return myLibrarySystem;

            case "findBook":
                print.findBook(myLibrarySystem.findBookByTitle(commandsAndArgs[1]), commandsAndArgs[1]);
                return myLibrarySystem;
            case "findUser":
                print.findUser(myLibrarySystem.findUserByName(commandsAndArgs[1]), commandsAndArgs[1]);
                return myLibrarySystem;
            //-borrowBook -user -title
            case "borrowBook":
                Book bookToBorrow = myLibrarySystem.findBookByTitle(commandsAndArgs[2]);
                //Check if the user is a user
                User userToBorrow = myLibrarySystem.findUserByName(commandsAndArgs[1]);
                if (userToBorrow == null) {
                    print.userNotFound(commandsAndArgs[1]);
                    return myLibrarySystem;
                }
                /* We have to let the user know of these three cases
                 * The book is available and the user borrows the book
                 * The book is being borrowed thus the user can't borrow it
                 * The book is not in the library  */
                else {
                    String statusOfBook;
                    statusOfBook = myLibrarySystem.borrowBook(userToBorrow, bookToBorrow);
                    switch (statusOfBook) {
                        case "available":
                            print.bookAvailableToBorrow(bookToBorrow.getTitle(), userToBorrow.getName());
                            return myLibrarySystem;
                        case "beingBorrowed":
                            print.bookIsBeingBorrowed(bookToBorrow.getTitle());
                            return myLibrarySystem;
                        case "bookDoesNotExist":
                            print.bookNotFound(bookToBorrow.getTitle());
                            return myLibrarySystem;
                    }
                }
                return myLibrarySystem;
            //-extendLending -user -title -daysToAdd
            case "extendLending":
                int days;
                try{
                    days = Integer.parseInt(commandsAndArgs[3]);
                }
                catch (NumberFormatException e){
                    print.daysNotInt();
                    return myLibrarySystem;
                }
                if(days<=0){
                    print.daysNotInt();
                    return myLibrarySystem;
                }
                if(myLibrarySystem.findBookByTitle(commandsAndArgs[2])==null){
                    print.bookNotFound(commandsAndArgs[2]);
                    return myLibrarySystem;
                }
                Book book = myLibrarySystem.findBookByTitle(commandsAndArgs[2]);
                for(Lending lending:myLibrarySystem.getLendings()){
                    if((lending.getUser().getName().equals(commandsAndArgs[1]))&&(lending.getBook().equals(book))){
                        try {
                            myLibrarySystem.extendLending(lending.getUser(),book, lending.getDueDate().plusDays(days));
                        } catch (UserOrBookDoesNotExistException e) {
                            throw new RuntimeException(e);
                        }
                        print.exstendedLending();
                        return myLibrarySystem;
                    }
                }
                print.lendingNotFound();
                return myLibrarySystem;

            //-returnBook -user -title
            case "returnBook":
                Book bookToBeReturned = myLibrarySystem.findBookByTitle(commandsAndArgs[2]);
                User userReturning = myLibrarySystem.findUserByName(commandsAndArgs[1]);
                //If the user or book does not exist
                if ((bookToBeReturned == null) || (userReturning == null)) {
                    print.userOrBookNotFound();
                }
                //If the book can be returned
                else if (myLibrarySystem.returnBook(userReturning, bookToBeReturned)) {
                    print.bookAvailableToReturn(bookToBeReturned.getTitle());
                }
                //If the book is not being borrowed
                else {
                    print.bookIsNotBeingBorrowed(bookToBeReturned.getTitle());
                }
                return myLibrarySystem;

            case "addCollection":
                String nameOfCollection = commandsAndArgs[1];
                List<Author> authors = new ArrayList<>();
                int authorIndex = 0;
                if(!commandsAndArgs[2].equals("T")){
                    print.addCollectionNotCorrect();
                    return myLibrarySystem;
                }
                for(int w =0; w<commandsAndArgs.length;w++){
                    if(commandsAndArgs[w].equals("A")){
                        authorIndex = w;
                    }
                }
                if(authorIndex<4){
                    print.addCollectionNotCorrect();
                    return myLibrarySystem;
                }
                List<String> titles = new ArrayList<>(Arrays.asList(commandsAndArgs).subList(3, authorIndex));
                for(int w = authorIndex+1;w<commandsAndArgs.length;w++){
                    authors.add(new Author(commandsAndArgs[w]));
                }
                if(authors.size()==1){
                    myLibrarySystem.addCollectionOfBooksWithSingleAuthor(nameOfCollection,titles,authors.get(0));
                }
                else{myLibrarySystem.addCollectionOfBooksWithListOfAuthors(nameOfCollection,titles,authors);}
                print.collectionAdded(nameOfCollection,titles,authors);
                return myLibrarySystem;

            case "borrowCollection":

                User userToBorrowCollection = myLibrarySystem.findUserByName(commandsAndArgs[2]);
                if (userToBorrowCollection == null) {
                    print.userNotFound(commandsAndArgs[2]);
                    return myLibrarySystem;
                }
                else {
                    String statusOfCollection = myLibrarySystem.borrowCollection(userToBorrowCollection, commandsAndArgs[1]);
                    switch (statusOfCollection) {
                        case "CollectionAvailable":
                            print.collectionAvailableToBorrow(commandsAndArgs[1], userToBorrowCollection.getName());
                            return myLibrarySystem;
                        case "notAllAvailable":
                            print.someBookInCollectionIsBeingBorrowed();
                            return myLibrarySystem;
                        case "CollectionDoesNotExist":
                            print.collectionNotFound(commandsAndArgs[1]);
                            return myLibrarySystem;
                    }
                }
                return myLibrarySystem;
            case "status":
                print.status(myLibrarySystem);
                return myLibrarySystem;
            case "quit":
                print.status(myLibrarySystem);
                return null;
        }
        return myLibrarySystem;
    }
}
        

