public class Task {
    protected Boolean isDone;
    protected String icon; //tick or cross
    protected String name;
    protected String taskType;

    private final static String TICK = "O";
    private final static String CROSS = "X";

    Task(String name) {
        this.name = name;
        this.isDone = false;
        this.icon = CROSS;
        this.taskType = "Task";
    }
    
    Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.icon = isDone ? TICK : CROSS;
        this.taskType = "Task";
    }

    public String encode() {
        return isDone
                ? String.format("%s | 1 | %s", taskType, name)
                : String.format("%s | 0 | %s", taskType, name);
    }

    public void markDone() {
        this.icon = TICK;
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", icon, name);
    }
}