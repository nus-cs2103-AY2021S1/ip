public class Task {

    private final String name;
    private boolean isDone;

    public Task(String name, TaskType taskType) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            isDone = false;
        }
    }

    public Task(String name, TaskType taskType, boolean isDone) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            this.isDone = isDone;
        }
    }


    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Completes the task.
     */
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }

    public String toData() {
        return toString();
    }

}