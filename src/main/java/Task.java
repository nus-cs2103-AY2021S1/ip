public abstract class Task {

    private final String task;
    private boolean done = false;

    protected Task(String task) {
        this.task = task;
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

}
