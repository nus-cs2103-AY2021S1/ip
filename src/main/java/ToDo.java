public class ToDo extends Task {

    public ToDo(int taskNum, String taskName) {
        super(taskNum, taskName);
    }

    public String toString() {
        return String.format("%d. [T][%s] %s", taskNum, getStatusIcon(), taskName);
    }
}
