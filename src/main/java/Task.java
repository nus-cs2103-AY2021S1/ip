public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public String toString() {
        String check;
        if (this.done) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + check + "] " + this.name;
    }
}
