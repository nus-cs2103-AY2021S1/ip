public class Task {
    private String name;
    private boolean done;

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
        String status = "[" + (this.done ? "✓" : "✗") + "] ";
        return status + this.getName();
    }

}
