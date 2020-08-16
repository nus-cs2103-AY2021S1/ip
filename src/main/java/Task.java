public class Task {
    protected String name;
    protected int number;
    protected boolean done;

    public Task(String name, int number) {
        this.name = name;
        this.number = number;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return number + ".[" + getStatusIcon() + "]" + name;
    }
}
