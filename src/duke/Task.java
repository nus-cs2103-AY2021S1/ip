package duke;

public class Task {
    public String name;
    public boolean isDone;
    public String time;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, String time) {
        this.name = name;
        this.time = time;
    }

    String getIcon() {
        return isDone ? "[" + "\u2713" + "] " : "[" + "\u2718" + "] ";
    }

    void taskIsDone() {
        this.isDone = true;
    }

    String getIndicator() {
        return "[T]";
    }

}
