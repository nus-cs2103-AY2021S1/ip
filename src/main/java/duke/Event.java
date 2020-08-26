package duke;

/**
 * Event class for tasks that have a set timing.
 */
public class Event extends Task {
    private DateTime time;

    /**
     * Constructor for Event class.
     *
     * @param title Title of task.
     * @param time  Time of task.
     */
    public Event(String title, String time) {
        super(title);
        this.time = new DateTime(time);
    }

    /**
     * Overloads constructor for Event class.
     *
     * @param title      Title of task.
     * @param isComplete Boolean to represent completion status of task.
     * @param time       Time of task.
     */
    public Event(String title, Boolean isComplete, String time) {
        super(title, isComplete);
        this.time = new DateTime(time);
    }

    /**
     * Overrides method of saveString method in Task.
     *
     * @return A string to that can be saved into the .txt file
     */
    @Override
    public String saveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("E|%d|%s|%s", completeSymbol, this.title, this.time.saveString());
    }

    /**
     * Overrides the standard toString method.
     *
     * @return A String describing the task with deadline.
     */
    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[E]%s %s (at: %s)", completeSymbol, this.title, this.time);
    }
}