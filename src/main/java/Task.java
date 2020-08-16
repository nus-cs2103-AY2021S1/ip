public class Task {

    private final String name;
    private final short id;
    private static short count = 0;

    public Task(String name) {
        this.name = name;
        id = ++count;
    }

    public String getName() {
        return name;
    }

    public short getId() {
        return id;
    }

    public static short getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("%s. %s", id, name);
    }
}
