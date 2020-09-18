package duke.task;

import duke.task.Task;

public class Todo extends Task {
    private  String identity;

    public Todo(String description) {
        super(description);
        this.identity = "T";
    }

    /**
     * Getter for identifier to help write tasks to duke.txt
     *
     * @return
     */
    @Override
    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}