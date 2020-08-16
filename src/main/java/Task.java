public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentStatus() throws DukeException{
        if (getDescription() == "") throw new DukeException("");
        System.out.println(" Got it. I've added this task:");
        return " ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription();
    }

    public String getCurrentShortStatus() {
        return "  ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription();
    }
}
