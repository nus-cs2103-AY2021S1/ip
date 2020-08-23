package duke.task;

public class Task {
    private static final String TASK_MARKED_BEFORE = "This duke.task has already been marked!";
    private static final String TASK_MARKED = "Nice! I've marked this duke.task as done:";
    
    //@@author Damith C. Rajapakse
    //Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    private String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }
    //@@author

    public void markAsDone() {
        if (this.isDone) {
            System.out.println(TASK_MARKED_BEFORE);
        } else {
            this.isDone = true;
            System.out.println(TASK_MARKED);
            System.out.println(this);
        }
    }

    public String formatStyling() {
        return String.format(",%s,%d\n", description, getTaskStatus());
    }

    private int getTaskStatus() {
        return isDone ? 1 : 0;
    }



    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}