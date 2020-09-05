package duke.tasks;

public class TaskToDo extends Task {

    public TaskToDo(String description) {
        super(description);
    }

    public TaskToDo(String description, boolean done)
    {
        super(description);
        isDone = done;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"T", isDone ? "1" : "0", description, "na"};
    }

    @Override
    public String toString()
    {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
