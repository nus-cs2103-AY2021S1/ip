import java.util.Date;

enum TaskType {
    TODO{
        @Override
        public String getSymbol() {
            return "T";
        }
        @Override
        public String toString() {
            return "todo";
        }
    },
    DEADLINE{
        @Override
        public String getSymbol() {
            return "D";
        }
        @Override
        public String toString() {
            return "deadline";
        }
    },
    EVENT{
        @Override
        public String getSymbol() {
            return "E";
        }
        @Override
        public String toString() {
            return "event";
        }
    };
    public abstract String getSymbol();
}

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected final TaskType taskType;

    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }


    public String getSavedString() {
        return taskType.getSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public boolean isOccuringOn(Date date) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + taskType.getSymbol() + "]" + getStatusIcon() + " " + description;
    }
}
