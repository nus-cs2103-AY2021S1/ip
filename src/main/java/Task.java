public class Task {
    private String info;
    private boolean done;

    Task(String info) {
        this.info = info;
        done = false;
    }

    public void checked() {
        this.done = true;
    }

    @Override
    public String toString() {
        String tick = "✔";
        String cross = "✘";
        if (done) {
            return "[" + tick + "] " + this.info;
        } else {
            return "[" + cross + "] " + this.info;
        }
    }
}
