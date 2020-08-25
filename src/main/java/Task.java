public class Task {
    protected boolean isCompleted;
    protected String taskName;

    protected Task(String name, boolean isCompleted) {
        this.taskName = name;
        this.isCompleted = isCompleted;
    }

    public static Task newTask(String name){
        return new Task(name, false);
    }

    public static Task existingTask(String name, boolean isCompleted){
        return new Task(name, isCompleted);
    }

    public Task markAsDone(){
        this.isCompleted = true;
        return this;
    }

    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isToday(){
        return false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

    public String toSaveString(){
        return (this.isCompleted ? "1" : "0") + " | " + this.taskName;
    }
}
