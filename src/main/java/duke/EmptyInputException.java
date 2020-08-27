package duke;

public class EmptyInputException extends Exception {
    private final String taskName;
    public EmptyInputException(String taskName) {
        super();
        this.taskName = taskName;
    }
    public String toString() {
        return "____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a " + this.taskName + " cannot be empty.\n" +
                "____________________________________________________________";
    }
}
