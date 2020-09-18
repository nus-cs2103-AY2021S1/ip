package main.java;

/**
 * A kind of the tasks, which has a specific deadline
 */
public class FixedDurationTasks extends Task {
    protected double hours;

    public FixedDurationTasks(String description, double hours) {
        super(description);
        this.hours = hours;
    }

    public FixedDurationTasks(String description, double hours, boolean isDone) {
        super(description, isDone);
        this.hours = hours;
        this.isDone = isDone;
    }

    /**
     * gives a String representing the time needed for the task
     * @return a String representing the time needed for the task
     */
    String getHours() {
        return String.format("%.2f", hours);
    }

    @Override
    public String getDescription() {
        return "[F][" + getStatusIcon()
                + "]" + description + "(needs " + getHours() + " hours)";
    }

    @Override
    public void printDescription() {
        System.out.println(getDescription());
    }
}
