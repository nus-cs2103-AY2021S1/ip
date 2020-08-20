public class Task {
    public boolean isCompleted;
    public String description;
    public String type;
    public String due;

    public Task(String type, String description) {
        this.isCompleted = false;
        this.type = type;
        this.description = description;
    }
    public Task(String type, String description, String due) {
        this.isCompleted = false;
        this.type = type;
        this.description = description;
        this.due = due;
    }
    public void markDone() {
        this.isCompleted = true;
    }
    public String getStatusIcon() {
        return (this.isCompleted) ? "\u2713" : "\u2718";
    }
    @Override
    public String toString() {
        String typeString = "";
        String dueString = "";
        if (this.type.equals("todo")) {
            typeString = "[T]";
            dueString = "";
        } else if (this.type.equals("deadline")) {
            typeString = "[D]";
            dueString = "(" + this.due + ")";
        } else if (this.type.equals("event")) {
            typeString = "[E]";
            dueString = "(" + this.due + ")";
        }
        String complete = (this.isCompleted) ? "\u2713" : "\u2718";
        return typeString + " [" + complete + "] " + description  + dueString;
    }
}
