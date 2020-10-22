package clippy.task;

/**
 * Represents a task that occurs at a specified period in time.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructs an event with the given description and period.
     *
     * @param desc User-specified literal description of the event.
     * @param at User-specified period of the event.
     */
    public Event(String desc, String at) {
        super(desc);
        this.at = at;
        taskType = TaskType.EVENT;
    }

    /**
     * Updates the period aspect of the event to the given period.
     *
     * @param newTime User-specified period.
     */
    @Override
    public void updateTime(String newTime) {
        this.at = newTime;
    }

    @Override
    public String toString() {
        String taskTypeIndicator = "[" + taskType + "]";
        String atString = "(at: " + at + ")";
        
        return taskTypeIndicator + super.toString() + " " + atString;
    }

    /**
     * Generates and return a String encapsulating details of the event to be stored in the save file.
     *
     * @return A String encapsulating details of the event to be stored in the save file.
     */
    @Override
    public String generateSaveFileData() {
        return "E" + "|" + (isDone ? "1" : "0") + "|" + desc + "|" + at;
    }
}
