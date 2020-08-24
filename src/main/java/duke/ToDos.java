package duke;

/**
 * ToDos class will handle tasks categorised as to-do
 */
class ToDos extends Task {

    /**
     * Initialise ToDos using description only.
     * @param description
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Initialise ToDos using description and isDone.
     * Used when knowledge about isDone is needed, eg. loading existing list from hard disk.
     * @param description
     * @param isDone
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return string format of to-do task.
     * @return String description of to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return string format of to-do task that will be written on a text file.
     * @return String description of to-do task
     */
    @Override
    public String writeToFile() { return "T" + super.writeToFile(); }
}
