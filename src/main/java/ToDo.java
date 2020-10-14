public class ToDo extends Task {

    /**
     * Creates a new ToDo Task with the input name.
     *
     * @param in Task name
     */
    public ToDo(String in) {
        super(in);
    }

    /**
     * Creates the string version of this ToDo for display.
     *
     * @return The string formatting of this ToDo.
     */
    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[T] [" + doneStatus + "] " + task;
    }

    /**
     * Creates the string version of this ToDo for the save file.
     *
     * @return The string formatting of this ToDo.
     */
    public String saveText() {
        return "T | " + super.saveText();
    }
}
