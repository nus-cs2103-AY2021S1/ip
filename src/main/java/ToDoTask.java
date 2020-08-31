public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, boolean done)
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
