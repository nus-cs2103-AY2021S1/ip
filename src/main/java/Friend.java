public class Friend {

    private String name;
    private int phoneNumber;
    private boolean isClose;

    /**
     * Constructor for the class.
     * @param name
     */
    public Friend(String name) {
        this.name = name;
        this.phoneNumber = 91234567;
        this.isClose = false;
    }

    /**
     * Constructor for the class.
     * @param name
     * @param phoneNumber
     * @param isClose
     */
    public Friend(String name, int phoneNumber, boolean isClose) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isClose = isClose;
    }

    /**
     * Update name of an existing friend.
     * @param newName
     */
    public void updateName(String newName) {
        this.name = newName;
    }

    /**
     * Update phone number of an existing friend.
     * @param newNumber
     */
    public void updateNumber(int newNumber) {
        this.phoneNumber = newNumber;
    }

    /**
     * Update the relationship status of an existing friend.
     * @param isClose
     */
    public void updateRelation(boolean isClose) {
        this.isClose = isClose;
    }

    /**
     * Get the name of the friend.
     * @return a String of the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get a string of the details of the friend.
     * @return a string of the friend.
     */
    @Override
    public String toString() {
        return this.name + " (number: " + this.phoneNumber + ", isClose: " + this.isClose + ")";
    }
}
