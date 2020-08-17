public class Task {

    private static short count = 0;
    private final short id;
    private final String name;
    private boolean Done = false;
    private static final String MESSAGE_BLANK_TASK = "Did you casually forget to put in the description of the task?";

    public Task(String name) throws BlankTaskException {
        if (name.isBlank()) {
            throw new BlankTaskException(MESSAGE_BLANK_TASK);
        }
        this.name = name.strip();
        id = ++count;
    }

    public static short getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public short getId() {
        return id;
    }

    @Override
    public String toString() {
        return (isDone() ? "[✓] " : "[✗] ") + name;
    }

    public boolean isDone() {
        return Done;
    }

    public void markAsDone() {
        Done = true;
    }

}
