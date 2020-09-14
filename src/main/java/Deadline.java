public class Deadline extends Task {

    protected String deadline;


    /**
     * Contructs an event object.
     * @param newTask Name of event.
     * @param deadline Time at which event happens.
     */
    public Deadline(String newTask, String deadline) {
        super(newTask);
        this.deadline = deadline;
    }

    /**
     * Return the file format form for the task.
     */
    public String fileFormat() {
        return "D" + " | " + super.fileFormat() + " | " + deadline;
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverted() {
        TimeParser inputTime = new TimeParser(deadline);
        String outputTime = inputTime.timeConverter();
        return "D" + " | " + super.fileFormat() + " | " + outputTime;

    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")"; }
}
