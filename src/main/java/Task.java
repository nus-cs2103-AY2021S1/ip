public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean getStatus() {
        return this.done;
    }

    public void setStatus(boolean b) {
        this.done = b;
    }

    @Override
    public String toString() {
        return this.name;
    }
}