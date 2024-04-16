package is.hi.hbv202g.assignmentFinal.backend;

public abstract class User {
    protected String name;
    /**
     * Constructs a new User with the specified name.
     *
     * @param name The name of the user. This value should be non-null and ideally non-empty.
     */
    public User(String name) {
        this.name = name;
    }
    /**
     * Retrieves the name of the user.
     *
     * @return The current name of this user.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets or updates the name of the user.
     *
     * @param name The new name to be set for the user. This value should be non-null.
     */
    public void setName(String name) {
        this.name = name;
    }
}
