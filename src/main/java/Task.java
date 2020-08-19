public class Task {
    private String name;
    private boolean done;
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        done = true;
    }
    @Override
    public String toString() {
        char sign = (done == true ? '✓' : '✗');
        return String.format("[%c] %s", sign, name);
    }
}