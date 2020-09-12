package duke.tasks;

import duke.errors.InvalidCommandException;

/**
 * The type Task.
 */
public abstract class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Done.
     */
    protected String done;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     * @param done        the done
     */
    public Task(String description, String done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Getter method to get Task Description
     *
     * @return Task Description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Takes in an array of Task values to form into a String to be stored on database
     *
     * @param arr String Array of Task values
     * @return String to be saved to database
     * @throws Exception the exception
     */
    public static String stringFormat(String[] arr) throws Exception {
        String s;
        switch (arr[0]) {
        case "T":
            s = String.format("%s,%s,%s\n", arr[0], arr[1], arr[2]);
            break;
        case "E":
            s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
            break;
        case "D":
            s = String.format("%s,%s,%s,%s\n", arr[0], arr[1], arr[2], arr[3]);
            break;
        default:
            throw new InvalidCommandException();
        }
        return s;
    }

    /**
     * Sets Status of Task to be done or not.
     *
     * @param checked String "0" to represent false, and "1" for true
     */
    public void setStatus(String checked) {
        this.done = checked;
    }

    /**
     * abstract object for child classes to return Task as a String Array of values
     *
     * @return String Array of values
     */
    public abstract String[] getStringArr();

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task c = (Task) o;

        // Compare the data members and return accordingly
        return description.equals(c.description);
    }

    @Override
    public String toString() {
        String icon = this.done.equals("1") ? "[✓]" : "[✗]";
        return String.format("%s %s", icon, this.description);
    }
}
