import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import is.hi.hbv202g.assignmentFinal.backend.LibrarySystem;
import is.hi.hbv202g.assignmentFinal.frontend.CommandExecutor;
import is.hi.hbv202g.assignmentFinal.frontend.PrintStatements;
import is.hi.hbv202g.assignmentFinal.frontend.Reader;


public class Main {
    /**
     * The main method that initializes and controls the flow of the library management application.
     * It sets up necessary classes for handling printing, reading inputs, and executing commands.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a Scanner to read input from the user
        Scanner input = new Scanner(System.in);
        boolean active = true;

        //Create a class that contains all the text messages
        PrintStatements print = new PrintStatements();
        //Create a class that reads the input
        Reader reader = new Reader();
        //Create a class that executes the commands
        CommandExecutor executor = new CommandExecutor();
        LibrarySystem myLibrarySystem = new LibrarySystem();

        //Welcome message for the user
        print.welcomeText();
        //Keep the user interface active until the user quits
        while (active) {
            //Read the input and split it at every "-"
            //The reader detect if the command or arguments are not legal and lets the user know
            String[] commandsAndArgs = reader.readInput(input.nextLine());

            //If the command and arguments are legal we find the command and execute it
            if (commandsAndArgs != null) {
                myLibrarySystem = executor.executeCommand(myLibrarySystem, commandsAndArgs);
                //If the user quits, exit the loop
                if(myLibrarySystem==null){
                    active=false;
                }
            }
        }
    }
}
