package duke.task;

public class ToDo extends Task {
    /**
     * Creates a ToDo task.
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String output() {
        return "T" + super.output() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks whether the given object equals this ToDo task.
     * @param obj the given object to compare
     * @return true if the object is a ToDo and the description equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo o = (ToDo) obj;
            return description.equals(o.description);
        } else {
            return false;
        }
    }
}
