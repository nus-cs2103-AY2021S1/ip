import java.util.Date;

enum TaskType {
    TODO{
        @Override
        public String toString() {
            return "todo";
        }
    },
    DEADLINE{
        @Override
        public String toString() {
            return "deadline";
        }
    },
    EVENT{
        @Override
        public String toString() {
            return "event";
        }
    }
}

public abstract class Task {

    protected String description;
    protected boolean isDone;
    private final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isOccuringOn(Date date) {
        return false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
