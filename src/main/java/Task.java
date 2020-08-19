public class Task {
    protected String taskname;
    protected boolean status;

    Task (String taskname, boolean status) {
        this.taskname = taskname;
        this.status = status;
    }

    protected String getTaskname() {
        return this.taskname;
    }

    protected String getStatusIcon() {
        return (status ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected Task markAsDone() {
        return new Task(this.taskname, true);
    }
}
