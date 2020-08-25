public abstract class Task implements Encodable<Task>{


    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public void markAsDone() {
        this.isDone = true;

    }

    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }



}
