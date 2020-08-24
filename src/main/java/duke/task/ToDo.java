package main.java.duke.task;

public class ToDo extends Task {

    public ToDo(String description, boolean isComplete) {
        super(description, isComplete, null);
    }

    @Override
    public String[] getDataString() {
        return new String[] {"todo", String.valueOf(isComplete), description};
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
