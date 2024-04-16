package is.hi.hbv202g.assignmentFinal.backend;

public class Student extends User {
    private boolean feePaid;
    /**
     * Constructs a new Student with the specified name and fee payment status.
     *
     * @param name The name of the student. This is passed to the superclass constructor.
     * @param feePaid A boolean indicating whether the student has paid the fee.
     */
    public Student(String name, boolean feePaid) {
        super(name);
        this.feePaid = feePaid;
    }
    /**
     * Checks if the student has paid the fee.
     *
     * @return true if the fee has been paid; false otherwise.
     */
    public boolean isFeePaid() {
        return feePaid;
    }
    /**
     * Sets the student's fee payment status.
     *
     * @param feePaid A boolean representing the new fee payment status.
     */
    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }
}
