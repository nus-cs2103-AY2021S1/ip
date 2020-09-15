package duke.task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description that can be marked as done.
 */
public abstract class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String recurrence;
    protected LocalDate dateRepeated;

    Task(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        isDone = false;
        this.recurrence = "";
    }

    /**
     * Checks if the string contains time that has the same format as hhmm."
     *
     * @param str The string that may contain time.
     * @return True if the string contains time, else false.
     */
    public boolean containsTime(String str) {
        Pattern p = Pattern.compile(".* ([01]?[0-9]|2[0-3])[0-5][0-9]");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    protected String formatDate(String str) {
        LocalDate ld = LocalDate.parse(str);
        return ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    protected String formatDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime ldt = LocalDateTime.parse(str, formatter);
        return ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Updates the dateRepeated.
     */
    public void repeatTask() {
        isDone = false;
        dateRepeated = LocalDate.now();
    }

    /**
     * Marks the task as complete.
     */
    public void completeTask() {
        isDone = true;
    }

    protected String getStatusIcon() {
        return isDone ? "[✓] " : "[✗] ";
    }

    /**
     * Formats the task to be stored in the text file.
     *
     * @return A string representing the task in a format to be stored in the text file.
     */
    abstract public String formatTaskForFile();

    /**
     * Gets the recurrence interval of the task.
     *
     * @return The recurrence interval.
     */
    public String getRecurrence() {
        return recurrence;
    }

    /**
     * Gets the date last repeated for the task.
     *
     * @return The date last repeated.
     */
    public LocalDate getDateRepeated() {
        return dateRepeated;
    }

    /**
     * Adds the task's recurrence interval and date repeated.
     *
     * @param recurrence The recurrence interval to be added to the task.
     * @param dateRepeated The date repeated to be added to the task.
     */
    public void addRecurrence(String recurrence, LocalDate dateRepeated) {
        this.recurrence = recurrence;
        this.dateRepeated = dateRepeated;
    }

    /**
     * Checks if the task is recurring.
     *
     * @return True if the task is recurring, else false.
     */
    public boolean isRecurring() {
        return recurrence != "";
    }

    /**
     * Checks if the task's description matches the string.
     *
     * @param keyword The string that may match the task's description.
     * @return True if the task's description matches the string, else false.
     */
    public boolean match(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
