public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
        System.out.println(">> Yay! The following task is marked as done:\n>> " + this.toString());
    }

    public String getStatusIcon() {
        return this.completed ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
