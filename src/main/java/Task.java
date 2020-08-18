public abstract class Task {

    public static String icon;
    protected String taskString;
    protected Boolean status;

    Task(String taskString) {
        this.taskString = taskString;
        this.status = false;
    }

    public void markAsDone() {
        this.status = true;
    }

    public String toString() {
        return this.taskString;
    }

    public boolean getStatus() {
        return this.status;
    }
}
