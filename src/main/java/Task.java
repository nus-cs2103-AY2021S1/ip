public class Task {

    private final String task;
    private boolean done = false;

    private Task(String task) {
        this.task = task;
    }

    protected static Task makeTask(String task) {
        return new Task(task);
    }
    
    protected String getTask() {
        return this.task;
    }

    protected void markDone() {
        this.done = true;
    }

    protected boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
