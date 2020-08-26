package duke.task;

public class ToDo extends Task {
    public ToDo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Describes todo task.
     *
     * @return String that describes todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Describes todo task to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "T" + super.saveToHardDisk();
    }
}
