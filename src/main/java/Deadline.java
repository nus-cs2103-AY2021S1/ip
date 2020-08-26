import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a Deadline task which needs to be 
 * done before a specific time.
 */
public class Deadline extends Task {
    private String dueBy;

    /**
     * Create a Deadline object that can accept both formatted and unformatted due dates.
     * Formatted due dates have to be in the format: dd/MM/yyyy HHmm
     * @param description
     * @param dueBy
     * @throws PandaBotException
     */
    public Deadline(String description, String dueBy) throws PandaBotException {
        super(description);
        // check if a formatted date and time is given
        String input = dueBy.strip();
        String[] dT = input.split(" ");
        if (dT.length > 1) {
            try {
                DateAndTime dateTime = new DateAndTime(dT[0], dT[1]);
                this.dueBy = dateTime.toString();
            } catch (DateTimeParseException e) {
                // couldn't parse the input -> unformatted deadline
                this.dueBy = dueBy;
            }
        } else {
            this.dueBy = dueBy;
        }
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
    
    @Override
    public String saveAsText() {
        return "D | " + super.saveAsText() + " | " + dueBy;
    }
}
