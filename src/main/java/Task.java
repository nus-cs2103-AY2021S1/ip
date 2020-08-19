public class Task {
    private Boolean isDone; // Whether a task is complete
    private String name; // The task name
    private TaskTypes type;

    public Task(Boolean isDone, String name, TaskTypes type) {
        this.isDone = isDone;
        this.name = name;
        this.type = type;
    }

    public void done() {
        isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String taskString = String.format("%s%s %s", this.showType(), this.showStatus(), name);
        return taskString;
    }

    public String showStatus() {
        if (isDone) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    public String showType() {
        switch (this.type) {
            case TODO: {
                return "[T]";
            }
            case DEADLINE: {
                return "[D]";
            }
            case EVENT: {
                return "[E]";
            }
            default: {
                return "";
            }
        }
    }
}
