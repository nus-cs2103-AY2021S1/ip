public abstract class Task {
    protected String name;
    protected boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
