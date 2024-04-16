package is.hi.hbv202g.assignmentFinal.backend;

public class UserOrBookDoesNotExistException extends Exception {
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
