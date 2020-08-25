package duke.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    private static final String DELIMITER_COMMAND = " /by ";

    private LocalDateTime deadline;

    /**
     * Constructs a deadline task that it not yet complete.
     *
     * @param description Description for the deadline.
     * @param deadline Deadline for the deadline in DD/MM/YYYY HH:mm format.
     * @throws TaskException If the description is empty.
     */
    public Deadline(String description, LocalDateTime deadline) throws TaskException {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a deadline task with full set of parameters.
     *
     * @param description Description for the deadline.
     * @param deadline Deadline for the deadline in DD/MM/YYYY HH:mm format.
     * @param isCompleted If the task is completed already.
     * @throws TaskException If the description is empty.
     */
    public Deadline(String description, String deadline, boolean isCompleted) throws TaskException {
        super(description, isCompleted);
        this.deadline = LocalDateTime.parse(deadline);
    }

    /**
     * Constructs a deadline task with parameters as a single string.
     *
     * @param args Parameters for the args in the form of "[description] /by [deadline in DD/MM/YYYY HH:mm]"
     * @return New deadline instance.
     * @throws TaskException If the description is empty or the given string cannot be delimited.
     */
    public static Deadline create(String args) throws TaskException {
        String[] argsList = args.split(DELIMITER_COMMAND);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Deadline(argsList[0], date);
        }
    }

    /**
     * Converts the stored representation to a Deadline instance.
     *
     * @param storageString Representation as given by its own `toStorageString()`.
     * @return New Deadline task based on the storage representation.
     * @throws TaskException If the description is empty, or if storage representation cannot be delimited.
     */
    public static Deadline parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 3) {
            throw new TaskException("Invalid storage string");
        }
        return new Deadline(inputList[0], inputList[1], decodeCompletionFlag(inputList[2]));
    }

    /**
     * Returns the type of the task.
     *
     * @return The Deadline task type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns the storage representation.
     *
     * @return The storage representation.
     */
    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + deadline + DELIMITER_STORAGE + storeCompletionFlag();
    }

    /**
     * Returns the string representation of the current instance.
     *
     * @return String representation of the current instance.
     */
    @Override
    public String toString() {
        return printCompletionFlag() + " | D | " + description + " | By: " + deadline.format(Task.DISPLAY_FORMAT);
    }
}
