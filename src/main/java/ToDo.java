/**
 * Encapsulates a ToDo item
 */
public class ToDo extends Task {

    /**
     * Instantiates a ToDo with a description of it.
     *
     * @param description an explanation of what the task is about
     */
    ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor to instantiate a todo with customised status.
     * @param description description of the todo
     * @param done status of the todo
     */
    ToDo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Marks a todo as 'done'.
     * @return a todo that is done
     */
    @Override
    ToDo markAsDone() {
        return new ToDo(this.description, true);
    }

    @Override
    public String toString() {
        return ConsoleColors.YELLOW_BACKGROUND.getColor() + "TODO" + ConsoleColors.RESET.getColor()
            + " " + super.toString();
    }
}
