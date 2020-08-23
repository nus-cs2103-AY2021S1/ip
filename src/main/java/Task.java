public class Task {
    protected String description;
    protected taskStatus status;
    protected taskType type;

    enum taskStatus {
        DONE("1"), NOTDONE("0");

        public final String num;

        taskStatus(String num) {
            this.num = num;
        }

        public String getNum() {
            return num;
        }
    }

    enum taskType {
        TODO, DEADLINE, EVENT
    }

    public Task(String desc, String type) {
        this.status = taskStatus.NOTDONE;
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
        return (this.status == taskStatus.DONE
                ? "\u2713" // return tick
                : "\u2718");  // or X symbols
    }

    public void markAsDone() {
        this.status = taskStatus.DONE;
    }

    public boolean isDone() {
        return this.status == taskStatus.DONE;
    }

    public String toSaveFormat() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}