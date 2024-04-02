package is.hi.hbv202g.assignmentFinal;

public class Reader {
    //List of all the commands
    private final String[] listOfCommands =
            {"help", "addBook", "addStudent", "addFacultyMember","findBook",
    "findUser","borrowBook","extendLending","returnBook","status","quit"};

    private final PrintStatements print = new PrintStatements();

    public String[] readInput(String input) {
        //split the input at every -
        String[] splitInput = input.split("-");
        //the first element of splitInput is a whitespace thus we create a new array with the length N-1
        String[] commandAndRest = new String[splitInput.length-1];

        //we take every element of splitInput and trim it and put it in commandAndRest
        for(int w = 1;w<splitInput.length;w++){
            commandAndRest[w-1] = splitInput[w].trim();
        }

        String command = commandAndRest[0];
        //check if command is legal
        for (String c : listOfCommands) {
            if (command.equals(c)) {
                //check if arguments are legal
                if(argsLegal(command,commandAndRest)){
                    return commandAndRest;
                }
            }
        }

        return null;
    }
    private boolean argsLegal(String command,String[]comAndArgs){
        int numOfArgs = comAndArgs.length-1;
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
            default -> false;
        };
    }

}
