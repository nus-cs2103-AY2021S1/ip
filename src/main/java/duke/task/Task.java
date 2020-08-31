package duke.task;

public abstract class Task {
    protected String description;
    protected Boolean isDone;

    public enum TaskType {
        DEADLINE("deadline <task description> /by <yyyy-mm-dd>"),
        EVENT("event <event description> /at <event location>"),
        TODO("todo <task description>");
        
        private final String format;
        
        TaskType(String format) {
            this.format = format;
        }
        
        public String getFormat() {
            return this.format;
        }
    }


    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    public static String getFormat(String taskTypeString) {
        return TaskType.valueOf(taskTypeString.toUpperCase()).getFormat();
    }
    
    public abstract String getParsedTask();
    
    public String getCheckBox() {
        if (this.isDone) {
            return "[\u2713]";
        } else {
            return "[\u2718]";
        }
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getCheckBox() + " " + this.description;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.description.equals(otherTask.description) && this.isDone == otherTask.isDone;
        } else {
            return false;
        }
    }
}
