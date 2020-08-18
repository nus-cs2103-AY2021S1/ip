public class Task {
    protected int number;
    protected Boolean done;
    protected String icon; //tick or cross
    protected String name;
    public static int numberOfTasks = 1;

    private static String tick = "[✓]";
    private static String cross = "[✗]";

    Task(String name) {
        this.number = numberOfTasks;
        this.name = name;
        this.done = false;
        this.icon = cross;
        numberOfTasks++;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone() {
        this.icon = tick;
        this.done = true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", icon, name);
    }
}