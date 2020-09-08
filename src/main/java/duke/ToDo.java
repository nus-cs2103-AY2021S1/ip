package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    /**
     * Returns a string that represents the Event task which will be written in the documented file.
     *
     * @return String represents Event task in duke.txt.
     */
    public String writeToFile() {
        String isDoneString = this.isDone ? " 1 @ " : " 0 @ ";
        return "T @" + super.writeToFile() + System.lineSeparator();
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
