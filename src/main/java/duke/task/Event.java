package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates an event
 */
public class Event extends Task {

    private final int NUM_OF_DATA_STRINGS = 4;
    private final String TASK_TYPE_STRING = "event";
    private final String PRINTING_STRING = "[E][%s] %s (at: %s)";

    /**
     * Constructor
     *
     * @param description Description of the event
     * @param isComplete Completion status of the event
     * @param date Date of the event
     */
    public Event(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    /**
     * Gets the string representation of the event to be written into the file upon exit
     *
     * @return String representation of the event in the file
     */
    @Override
    public String[] getDataStrings() {
        String[] dataStrings = new String[NUM_OF_DATA_STRINGS];
        dataStrings[0] = TASK_TYPE_STRING;
        dataStrings[1] = String.valueOf(isComplete);
        dataStrings[2] = description;
        dataStrings[3] = date.toString();
        return dataStrings;
    }

    /**
     * Gets the string representation of the event to be printed in the UI.
     *
     * @return String representation of the event displayed on the UI
     */
    @Override
    public String toString() {
        return String.format(PRINTING_STRING, getStatusIcon(), description, getDateString());
    }
}
