public class Task {

    private boolean completed;
    private String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.completed = true;
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
