package duke.task;

public abstract class Task {
    protected final String description;
    protected Boolean done;

    public enum TaskType {
        DEADLINE("deadline <task description> /by <yyyy-mm-dd>"),
        EVENT("event <event description> /at <event location>"),
        TODO("todo <task description>");
        
        private String format;
        
        TaskType(String format) {
            this.format = format;
        }
        
        public String getFormat() {
            return this.format;
        }
    }


    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    
    public static String getFormat(String taskTypeString) {
        return TaskType.valueOf(taskTypeString).getFormat();
    }
    
    public abstract String getParsedTask();
    
    public String getCheckBox() {
        if (this.done) {
            return "[\u2713]";
        } else {
            return "[\u2718]";
        }
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return getCheckBox() + " " + this.description;
    }
}
