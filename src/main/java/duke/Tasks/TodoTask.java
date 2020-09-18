package duke.tasks;

public class TodoTask extends Task {

    private static String indicator = "[T]";

    public TodoTask(String name) {
        super(name);
    }

    public boolean equals(Task task) {
        return task instanceof TodoTask && this.description.equals(task.description);
    }

    @Override
    public String toString() {
        return indicator + super.toString();
    }

    @Override
    public String saveString() { return "T/break/" + this.done + "/break/" + description; }
}
