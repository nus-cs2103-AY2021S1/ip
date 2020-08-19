public class Task {
    protected Boolean done;
    protected String icon; //tick or cross
    protected String name;

    private static String tick = "O";
    private static String cross = "X";

    Task(String name) {
        this.name = name;
        this.done = false;
        this.icon = cross;
    }

    public String getName() {
        return name;
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
        return String.format("[%s] %s", icon, name);
    }
}