public class Task {
    public String name;
    public boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    String getIcon() {
        return isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "] ";
    }

    void taskIsDone() {
        this.isDone = true;
    }

}
