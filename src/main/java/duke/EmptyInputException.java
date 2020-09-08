package duke;

public class EmptyInputException extends Exception {
    private final String taskName;

    /**
     * Returns an exception when the description of the task is incomplete
     * It is a constructor of an exception which takes in an argument of task name String
     * @param taskName The task name will be reflected at the toString method
     *
     * @return EmptyInputException;
     */
    public EmptyInputException(String taskName) {
        super();
        this.taskName = taskName;
    }

    /**
     * Returns a String to indicate what error occurs
     *
     * @return String;
     */
    public String toString() {
        return "_____________________________________________\n"
                + "     ☹ OOPS!!! The description of a " + this.taskName + " cannot be empty.\n"
                + "_____________________________________________";
    }
}
