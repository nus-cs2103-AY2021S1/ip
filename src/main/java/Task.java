public class Task {
    String name;
    boolean completed;

    Task(String name) {
        this.name = name;
        this.completed = false;
    }

    Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getStatusIcon() {
        return (this.completed ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    Task completeTask() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
            return "[" + this.getStatusIcon() + "]" + this.name;
    }
}
