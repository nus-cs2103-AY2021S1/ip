public class Task {

    private final String name;
    private boolean done;

    public Task(String name, TaskType taskType) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            done = false;
        }
    }

    public Task(String name, TaskType taskType, boolean done) throws DukeEmptyDescException {
        if (Ui.isBlankString(name)) {
            throw new DukeEmptyDescException(taskType);
        } else {
            this.name = name;
            this.done = done;
        }
    }


    private String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Completes the task.
     */
    public void complete() {
        done = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }

    public String toData() {
        return toString();
    }

}