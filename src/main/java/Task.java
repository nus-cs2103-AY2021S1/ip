public class Task {
    protected String description;
    protected boolean isDone;
    protected String duration = "";

    public Task() { }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) throws ToDoException {
        if (description.equals("")) throw new ToDoException();
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description, boolean isDone, String duration) {
        this.description = description;
        this.isDone = isDone;
        this.duration = duration;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String toString(){
        String returnString = "";
        returnString += "[T]" + this.getStatusIcon() + this.description;
        if (!duration.equals("")) returnString += " (duration: " + this.duration + ")";
        return returnString;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
}
