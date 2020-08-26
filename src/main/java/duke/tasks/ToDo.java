package duke.tasks;

/** This class represents a todo. */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * @return A String representing the ToDo object, to be used when saving todos to the storage file.
     */
    @Override
    public String txtFileFormat() {
        return "T ~/~ " + super.txtFileFormat();
    }

    /**
     * @return A String representing the ToDo object, to be used when printing the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
