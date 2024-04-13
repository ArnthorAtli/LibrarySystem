package is.hi.hbv202g.assignmentFinal;

public class Reader {
    private final PrintStatements print = new PrintStatements();

    /**
     *
     * @param input a String that the user types in
     * @return an array with the command and arguments, returns null if illegal
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
     * @param comAndArgs the command and the arguments
     * @return a boolean if the command and arguments are legal
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
            case "borrowBook","returnBook" -> {
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
            default -> {
                print.commandNotFound();
                yield false;
            }
        };
    }

}
