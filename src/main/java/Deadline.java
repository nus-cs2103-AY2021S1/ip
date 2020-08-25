public class Deadline extends Task {
    protected Date deadlineDate;
    protected Timing deadlineTime;

    /**
     * Instantiates Deadline object.
     * @param description Description of deadline command.
     * @param deadlineDate Date of deadline.
     */
    public Deadline(String description, String deadlineDate) {
        super(description, TaskType.DEADLINE);
        this.deadlineDate = new Date(deadlineDate);
        Task.TOTAL_TASKS++;
    }

    /**
     * Instantiates Deadline object.
     * @param description Description of deadline command.
     * @param deadlineDate Date of deadline.
     * @param deadlineTime Time of deadline.
     */
    public Deadline(String description, String deadlineDate, String deadlineTime) {
        super(description, TaskType.DEADLINE);
        this.deadlineDate = new Date(deadlineDate);
        this.deadlineTime = new Timing(deadlineTime);
        Task.TOTAL_TASKS++;
    }

    /**
     * Instantiates Deadline object.
     * @param boolDone 1 if it is a done command, 0 if it is not a done command.
     * @param description Description of deadline command.
     * @param deadlineDate Date of deadline.
     * @param deadlineTime Time of deadline.
     */
    public Deadline(int boolDone, String description, String deadlineDate, String deadlineTime) {
        super(description, TaskType.DEADLINE, boolDone);
        this.deadlineDate = new Date(deadlineDate);
        this.deadlineTime = new Timing(deadlineTime);
        Task.TOTAL_TASKS++;
    }

    /**
     * Gets Date.
     * @return Date of deadline object.
     */
    public Date getDate() {
        return deadlineDate;
    }

    /**
     * Gets Timing.
     * @return Timing of deadline object.
     */
    public Timing getTiming() {
        return deadlineTime;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineDate.toString() + ", " + deadlineTime.toString() + ")";
    }
}
