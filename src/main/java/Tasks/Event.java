package Tasks;

public class Event extends Task {

    private String duration;

    /**
     * Constructor for the class.
     * @param name
     * @param duration
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Generate a string to show the task.
     * @return a string with the relevant information.
     */
    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (at: " + duration + ")";
    }

    /**
     * Generate a string of correct format to save to file.
     * @return a string.
     */
    @Override
    public String toSave() {
        return "E " + super.toSave() + " | " + this.duration;
    }
}
