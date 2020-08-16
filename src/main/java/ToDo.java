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

    @Override
    public String toString() {
        return ConsoleColors.YELLOW_BACKGROUND.getColor() + "TODO" + ConsoleColors.RESET.getColor()
            + super.toString();
    }
}
