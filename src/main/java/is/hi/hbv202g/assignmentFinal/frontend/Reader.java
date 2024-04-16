package is.hi.hbv202g.assignmentFinal.frontend;

public class Reader {
    private final PrintStatements print = new PrintStatements();

    /**
     * Parses the user input into a command and its corresponding arguments.
     * The input should start with a hyphen (-) followed by the command and its arguments, each separated by a hyphen.
     * This method also validates whether the parsed command and arguments are in a legal format.
     *
     * @param input a String representing the user's input
     * @return an array where the first element is the command and the subsequent elements are the arguments;
     *         returns null if the command is illegal or improperly formatted
     */
    public String[] readInput(String input) {
        //split the input at every -
        String[] splitInput = input.split("-");

        //the command is definitely illegal
        if(splitInput.length<2){
            print.commandNotFound();
            return null;
        }
        //the first element of splitInput is a whitespace thus we create a new array with the length N-1
        String[] commandAndRest = new String[splitInput.length-1];

        //we take every element of splitInput and trim it and put it in commandAndRest
        for(int w = 1;w<splitInput.length;w++){
            commandAndRest[w-1] = splitInput[w].trim();
        }


        //check if command and arguments are legal
       if(isLegal(commandAndRest)){
           return commandAndRest;
       }

        return null;
    }

    /**
     * Validates whether the parsed command and its arguments are legal based on predefined rules.
     * Each command has specific requirements for the number of arguments it accepts.
     *
     * @param comAndArgs an array of Strings where the first element is the command and the rest are arguments
     * @return true if the command and its arguments are legal; false otherwise
     */
    private boolean isLegal(String[]comAndArgs){
        int numOfArgs = comAndArgs.length-1;
        String command = comAndArgs[0];
        return switch (command) {
            case "help","status","quit" -> {
                if (numOfArgs != 0) {
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "addBook" -> {
                if (numOfArgs < 2) {
                    print.missingAuthorOrTitleText();
                    yield false;
                }
                yield true;
            }
            case "addStudent" -> {
                if (numOfArgs < 2) {
                    print.missingNameOrFeeText();
                    yield false;
                } else if (numOfArgs > 2) {
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "addFacultyMember" -> {
                if (numOfArgs < 2) {
                    print.missingNameOrDepartment();
                    yield false;
                } else if (numOfArgs > 2) {
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "findBook", "findUser" -> {
                if (numOfArgs!=1){
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "borrowBook","returnBook", "borrowCollection" -> {
                if(numOfArgs!=2){
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "extendLending" -> {
                if(numOfArgs!=3){
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            case "addCollection" -> {
                if(numOfArgs<5){
                    print.notCorrectAmountOfArgs(command);
                    yield false;
                }
                yield true;
            }
            default -> {
                print.commandNotFound();
                yield false;
            }
        };
    }

}
