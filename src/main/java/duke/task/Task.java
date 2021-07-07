package duke.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Encapsulates a task
 * All types of task must extend from this class
 */
public abstract class Task {

    private final int NUM_OF_DATA_STRINGS = 3;
    private final String TASK_TYPE_STRING = "task";
    private final String PRINTING_STRING = "[%s] %s";

    /** Date of the task, null if date is not associated with the task */
    final LocalDate date;

    /** Description of the task */
    final String description;

    /** Completion status of the task */
    boolean isComplete;

    /**
     * Constructor
     *
     * @param description Description of the task
     * @param isCompleted Completion status of the task
     * @param date Date of the task, null if date is not associated with the task
     */
    protected Task(String description, boolean isCompleted, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isCompleted;
    }

    /**
     * Gets the string representation of the task to be written into the file upon exit
     *
     * @return String representation of the task in the file
     */
    public String[] getDataStrings() {
        String[] dataStrings = new String[NUM_OF_DATA_STRINGS];
        dataStrings[0] = TASK_TYPE_STRING;
        dataStrings[1] = String.valueOf(isComplete);
        dataStrings[2] = description;
        return dataStrings;
    }

    /**
     * Gets the string representation of the date of the task to be printed in the UI
     *
     * @return String representation of the date of the task in the UI
     */
    String getDateString() {
        if (date == null) {
            return null;
        }
        return date.getYear() + " " +
                date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                date.getDayOfMonth();
    }

    /**
     * Gets the status icon reflecting the completion status of the task.
     *
     * @return Status icon
     */
    String getStatusIcon() {
        if (isComplete) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Marks the task as complete
     */
    public void setComplete() {
        isComplete = true;
    }

    /**
     * Gets the string representation of the task to be printed in the UI
     *
     * @return String representation of the task in the UI
     */
    @Override
    public String toString() {
        return String.format(PRINTING_STRING, getStatusIcon(), description);
    }

    /**
     * Checks if the description contains specified keyword
     *
     * @param keyword Keyword
     * @return True if keyword is found in description, false otherwise
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
}
