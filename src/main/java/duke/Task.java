package duke;

public class Task {
    private int status;
    private int category;
    private String command;
    protected static int DONE = 1;
    protected static int DOING = 2;
    protected static int TASK_TODO = 1;
    protected static int TASK_DEADLINE = 2;
    protected static int TASK_EVENT = 3;
    protected static int TASK_CATEGORY = 0;


    public Task(int category, int status, String command) {
        this.category = category;
        this.status = status;
        this.command = command;
    }

    public int getCategory() {
        return this.category;
    }

    public String getStatusIcon() {
        return ((this.status == DONE) ? "\u2713" : "\u2718");
    }

    public void markTaskAsDone() {
        if (this.status != DONE) {
            this.status = DONE;
        }
    }

    public void markTaskAsDeleted() {
        this.status = DOING;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String category = "";
        if (this.category == TASK_TODO) {
            category = "ToDo";
        } else if (this.category == TASK_DEADLINE) {
            category = "DeadLine";
        } else if (this.category == TASK_EVENT) {
            category = "Event";
        }
        return "[" + category + "][" + this.getStatusIcon() + "] " + this.command;
    }

}