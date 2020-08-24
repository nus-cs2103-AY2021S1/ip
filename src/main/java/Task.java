public abstract class Task {

    private boolean isDone = false;

    abstract protected String getTask();

    protected void markDone() {
        this.isDone = true;
    }

    protected boolean isDone() {
        return this.isDone;
    }

}

