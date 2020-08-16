public class Task {
    protected String description;
    protected taskStatus isDone;
    protected taskType type;

    enum taskStatus {
        DONE, NOTDONE
    }

    enum taskType {
        TODO, DEADLINE, EVENT
    }

    public Task(String desc, String type) {
        this.isDone = taskStatus.NOTDONE;
        this.description = desc;
        if (type.equals("todo")) {
            this.type = taskType.TODO;
        } else if (type.equals("deadline")) {
            this.type = taskType.DEADLINE;
        } else {
            this.type = taskType.EVENT;
        }
    }

    public String getStatusIcon() {
        return (this.isDone == taskStatus.DONE
                ? "\u2713" // return tick
                : "\u2718");  // or X symbols
    }

    public void markAsDone() {
        this.isDone = taskStatus.DONE;
    }

    public boolean isDone() {
        return this.isDone == taskStatus.DONE;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}