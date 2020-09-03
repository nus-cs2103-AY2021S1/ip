package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String writeToFile() {
        String isDoneString = this.isDone ? " 1 @ " : " 0 @ ";
        return "T @" + isDoneString + this.description + System.lineSeparator();
    }

    /**
     * Returns a string that represents the todo task
     *
     * @return String of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
