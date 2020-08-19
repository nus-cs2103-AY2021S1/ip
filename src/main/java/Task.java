public class Task {
    protected int number;
    protected Boolean done;
    protected String icon; //tick or cross
    protected String name;
    public static int numberOfTasks = 0;

    private static String tick = "O";
    private static String cross = "X";

    Task(String name) {
        numberOfTasks++;
        this.number = numberOfTasks;
        this.name = name;
        this.done = false;
        this.icon = cross;
    }
    
    public static void reduceTaskCount() {
        numberOfTasks--;
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
        return String.format("[%s] %s", icon, name);
    }
}