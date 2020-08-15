public class Task {
    String task;
    boolean done;

    private Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getDone() {
        return this.done;
    }

    public static Task setTask(String task) {
        return new Task(task);
    }

    public void setDone() {
        this.done = true;
    }
}
