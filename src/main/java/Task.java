public abstract class Task {

    private boolean done = false;

    abstract protected String getTask();

    protected void markDone() {
        this.done = true;
    }

    protected boolean isDone() {
        return this.done;
    }

}
