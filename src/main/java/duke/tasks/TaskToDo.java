package duke.tasks;

public class TaskToDo extends Task {

    public TaskToDo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo Task.
     * @param description
     * @param done Whether task is completed.
     */
    public TaskToDo(String description, boolean done) {
        super(description);
        isDone = done;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"T", isDone ? "1" : "0", description, "na"};
    }

    /*@Override
    public void postpone(int amount, ChronoUnit timeUnit) {

    }

    @Override
    public void advance(int amount, ChronoUnit timeUnit) {

    }*/

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
