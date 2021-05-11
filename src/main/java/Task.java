import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Task} class represents a task that
 * contains a description, isDone state and type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Initialises a {@code Task} object with its description and type.
     *
     * @param description Description of the task.
     * @param type        Type of task i.e. {@code Event}, {@code Deadline}, {@code Todo}
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the status of the task. A tick icon indicates task is complete,
     * and a cross icon indicates the task is incomplete.
     *
     * @return Status of the task (i.e. completion status).
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the task description.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + this.type + "][" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns task type.
     *
     * @return Type of task.
     */
    public String getType() {
        return this.type;
    }

    public String getTiming() {
        return null;
    }

    public boolean containsKey(String key) {
        return this.description.contains(key);
    }

    /**
     * Filters description for long String input.
     * Used in children classes of Event and Deadline
     * which contain additional time constraints.
     *
     * @param input       Full input description typed by user.
     * @param taskKeyWord Keyword that separates time constraint from task description.
     * @return Task Description without time constraints.
     */
    protected static String getDescriptionFromStringInput(String input, TimeConstraintKeyword taskKeyWord) {
        return getFullDescriptionArray(input, taskKeyWord.getKeyWord())[0];
    }

    protected static String getTimeConstraintFromStringInput(String input, TimeConstraintKeyword taskKeyWord) {
        return getFullDescriptionArray(input, taskKeyWord.getKeyWord())[1];
    }

    private static String[] getFullDescriptionArray(String input, String taskKeyWord) {
        String[] inputArray = input.split(" ", 2);
        String fullDescription = inputArray[1];
        String[] fullDescriptionArray = fullDescription.split(taskKeyWord, 2);
        return fullDescriptionArray;
    }

    protected static String tryFormatDateElseThrow(String timeConstraint) throws DateTimeParseException {
        String[] timeConstraintArray = timeConstraint.split(" ", 2);
        String potentiallyValidDate = timeConstraintArray[0];
        String remainingTimeConstraint;
        if (timeConstraintArray.length == 2) {
            remainingTimeConstraint = timeConstraintArray[1];
        } else {
            remainingTimeConstraint = "";
        }
        LocalDate date = LocalDate.parse(potentiallyValidDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + remainingTimeConstraint;
    }
}
