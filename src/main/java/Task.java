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

    String getName() { return this.name; }

    String getType() { return ""; }

    String getTime() { return ""; }

    public String getStatusIcon() {
        return (this.completed ? "o" : "x"); //return O or X symbols
    }

    Task completeTask() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
