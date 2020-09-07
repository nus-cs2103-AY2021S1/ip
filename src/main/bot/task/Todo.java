package bot.task;

/**
 * The most basic form of task without date.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Serialises the object.
     *
     * @return A string that is formatted to be read and stored in Storage.
     */
    @Override
    public String toFileFormat() {
        return "T" + " | " + super.toFileFormat() + "\n";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
