package clippy.task;

import clippy.exception.InvalidDateFormatException;

import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.LocalDate;

import java.util.Locale;

/**
 * Represents a task that needs to be done by a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a deadline if user-specified date is in the correct format of YYYY-MM-DD.
     * 
     * @param desc User-specified literal description of the deadline.
     * @param byString User-specified date of the deadline in YYYY-MM-DD format.
     * @throws InvalidDateFormatException If user-specified date is in the wrong format.
     */
    public Deadline(String desc, String byString) throws InvalidDateFormatException {
        super(desc);
        
        try {
            this.by = LocalDate.parse(byString);
            taskType = TaskType.DEADLINE;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
        
    }

    /**
     * Updates the time aspect of the deadline to the given time.
     * 
     * @param newTime User-specified date of the deadline in YYYY-MM-DD format.
     */
    @Override
    public void updateTime(String newTime) {
        this.by = LocalDate.parse(newTime);
    }
    
    @Override
    public String toString() {
        int day = by.getDayOfMonth();
        String month = by.getMonth().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH);
        int year = by.getYear();
        String byString = "(by: " + month + " " + day + " " + year + ")";
        
        String taskTypeIndicator = "[" + taskType + "]";

        return taskTypeIndicator + super.toString() + " " + byString;
    }

    /**
     * Generates and return a String encapsulating details of the deadline to be stored in the save file.
     * 
     * @return A String encapsulating details of the deadline to be stored in the save file.
     */
    @Override
    public String generateSaveFileData() {
        return "D" + "|" + (isDone ? "1" : "0") + "|" + desc + "|" + by;
    }

}
