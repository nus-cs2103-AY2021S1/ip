public class Task {
    protected String task;
    protected boolean isDone;
    protected Type type;

    public Task(String task, Type type) {
        this.task = task;
        this.isDone = false;
        this.type = type;
    }

    public Task(String task, Type type, boolean isDone) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
    }

    public String getTask() {
        return this.task;
    }

    public String getTime() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public Type getType() {
        return this.type;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getTask();
    }
}
