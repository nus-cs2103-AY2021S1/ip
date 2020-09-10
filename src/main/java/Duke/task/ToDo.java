package duke.task;

/**
 * Represents a ToDo that can be added to Duke's Task List.
 */
public class ToDo extends Task {

    /**
     * Constructs the ToDo with given description.
     * @param description  String of the description of the Todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor that constructs the ToDo with done status.
     * @param description  String of the description of the ToDo.
     * @param isDone  Whether the Todo is already done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * String representation of the ToDo.
     * @return  String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of the ToDo to be written to a file.
     * @return  String representation of the ToDo to be written to a file.
     */
    @Override
    public String getFileSaveText() {
        return "T " + super.getFileSaveText();
    }
}
