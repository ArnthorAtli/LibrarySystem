package is.hi.hbv202g.assignmentFinal;

public class FacultyMember extends User {
    private String department;
    /**
     * Constructs a new FacultyMember with a specified name and department.
     *
     * @param name the name of the faculty member, passed to the User superclass.
     * @param department the department with which the faculty member is affiliated.
     */
    public FacultyMember(String name, String department) {
        super(name);
        this.department = department;
    }
    /**
     * Returns the department of the faculty member.
     *
     * @return the department of this faculty member.
     */
    public String getDepartment() {
        return department;
    }
    /**
     * Sets the department of the faculty member.
     *
     * @param department the new department to be set for this faculty member.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}

