package duke.tasks;

import java.time.LocalDate;

import duke.exception.DukeInvalidTaskException;


/**
 * Task object
 */
public class Task {
    protected String taskName;
    protected Boolean isDone;
    protected Boolean isRepetitive = false;
    protected LocalDate dateTime;
    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY,
        NONE
    }
    protected Frequency repeatedFrequency;

    /**
     * Initializes the task object
     *
     * @param taskName task name or description
     * @throws DukeInvalidTaskException
     */
    public Task(String taskName) throws DukeInvalidTaskException {
        assert taskName != null : "TaskName should not be null!";
        if (!taskName.equals(null) && !taskName.equals(" ")) {
            this.taskName = taskName;
            this.isDone = false;
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    /**
     * Marks the task as done
     */
    public void checkOff() {
        this.isDone = true;
    }

    /**
     * Checks if the task is done
     * @return a boolean indicating if the task is done
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the task name
     * @return String representing the task's name
     */
    public String getTaskName() {
        return this.taskName;
    }

    protected void setIsRepetitive() {
        isRepetitive = true;
    }

    /**
     * checks if task is repetitive
     * @return true if task is repetitive
     */
    public Boolean getIsRepetitive() {
        return isRepetitive;
    }

    /**
     * Returns the frequency of the task.
     * @return a string describing the frequency
     */
    public String getFrequency() {
        return this.repeatedFrequency.toString();
    }

    /**
     * updates the date based on frequency stated
     */
    public void updateDate() {
        assert dateTime != null : "dateTime should not be null!";
        switch (repeatedFrequency) {
        case DAILY:
            dateTime = dateTime.plusDays(1);
            break;
        case WEEKLY:
            dateTime = dateTime.plusWeeks(1);
            break;
        case MONTHLY:
            dateTime = dateTime.plusMonths(1);
            break;
        case YEARLY:
            dateTime = dateTime.plusYears(1);
            break;
        default:
            System.out.println("This is not a repetitive task");
            break;
        }
    }
    public String getDate() {
        return dateTime.toString();
    }

    /**
     * Gets a representation of the task object
     * @return String object representing the task
     */
    @Override
    public String toString() {
        String finished = this.isDone ? "✓" : "✗";
        String toReturn = "[" + finished + "]" + taskName;
        return toReturn;
    }

    /**
     * Translate a string frequency into a Enum Frequency object
     * @param frequency
     * @return Frequency
     */
    public static Frequency translateToFrequency(String frequency) {
        switch (frequency) {
        case "daily":
            return Frequency.DAILY;
        case "weekly":
            return Frequency.WEEKLY;
        case "monthly":
            return Frequency.MONTHLY;
        case "yearly":
            return Frequency.YEARLY;
        default:
            return Frequency.NONE;
        }
    }
}
