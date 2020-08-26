import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    // Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
    private String dueBy;
    
    // can accept dueby as a formatted or unformatted date and time 
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
