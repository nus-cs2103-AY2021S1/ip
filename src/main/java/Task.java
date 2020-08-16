public class Task {

    private static short count = 0;
    private final short id;
    private final String name;
    private boolean Done = false;

    public Task(String name) {
        this.name = name;
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
        return String.format("%s. %s", id, name);
    }

    public boolean isDone() {
        return Done;
    }

    public void markAsDone() {
        Done = true;
    }

}
