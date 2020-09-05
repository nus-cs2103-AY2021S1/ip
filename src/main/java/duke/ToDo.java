package duke;

/**
 * ToDos class will handle tasks categorised as to-do
 */
class ToDo extends Task {
    /**
     * Initialises ToDos using description only.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initialises ToDos using description and isDone.
     * Used when knowledge about isDone is needed, eg. loading existing list from hard disk.
     *
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string format of to-do task.
     *
     * @return String description of to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string format of to-do task that will be written on a text file.
     *
     * @return String description of to-do task
     */
    @Override
    public String writeToFile() {
        return "T" + super.writeToFile();
    }
}
