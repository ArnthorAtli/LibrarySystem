package is.hi.hbv202g.assignmentFinal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    //create a librarySystem
    public static void main(String[] args) {
        //create a Scanner to read input from the user
        Scanner input = new Scanner(System.in);
        boolean active = true;

        //create a class that contains all the text messages
        PrintStatements print = new PrintStatements();
        //create a class that reads the input
        Reader reader = new Reader();
        //create a class that executes the commands
        CommandExecutor executor = new CommandExecutor();

        LibrarySystem myLibrarySystem = new LibrarySystem();

        //welcome the user
        print.welcomeText();
        //keep the user interface active until the user quits
        while (active) {
            //read the input and split it at every -
            //the reader detect if the command or arguments are not legal and lets the user know
            String[] commandsAndArgs = reader.readInput(input.nextLine());

            //if the command and arguments are legal we find the command and execute it
            if (commandsAndArgs != null) {
                myLibrarySystem = executor.executeCommand(myLibrarySystem, commandsAndArgs);
                //if the user quits
                if(myLibrarySystem==null){
                    active=false;
                }
            }
        }
    }
}
