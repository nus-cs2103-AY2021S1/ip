public class Task {
    private String name;
    private boolean done;

    public Task() {
        this.name = "";
        this.done = false;
    }

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
            check = "\u2713";
        } else {
            check = "\u2718";
        }
        return "[" + check + "] " + this.name;
    }
}
