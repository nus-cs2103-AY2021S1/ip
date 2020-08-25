package duke.task;

import java.time.LocalDateTime;

/**
 * Encapsulates an event.
 */
public class Event extends Task {
    private static final String DELIMITER_COMMAND = " /at ";

    private LocalDateTime dateInfo;

    /**
     * Constructs an event task that it not yet complete.
     *
     * @param description Description for the event.
     * @param dateInfo Date of the event in DD/MM/YYYY HH:mm format.
     * @throws TaskException If the description is empty.
     */
    public Event(String description, LocalDateTime dateInfo) throws TaskException {
        super(description);
        this.dateInfo = dateInfo;
    }

    /**
     * Constructs an event with full set of parameters.
     *
     * @param description Description for the event.
     * @param dateInfo Date of the event in DD/MM/YYYY HH:mm format.
     * @param isCompleted If the task is completed already.
     * @throws TaskException If the description is empty.
     */
    public Event(String description, String dateInfo, boolean isCompleted) throws TaskException {
        super(description, isCompleted);
        this.dateInfo = LocalDateTime.parse(dateInfo);
    }

    /**
     * Constructs an event task with parameters as a single string.
     *
     * @param args Parameters for the args in the form of "[description] /at [date in DD/MM/YYYY HH:mm]"
     * @return New event instance.
     * @throws TaskException If the description is empty or the given string cannot be delimited.
     */
    public static Event create(String args) throws TaskException {
        String[] argsList = args.split(DELIMITER_COMMAND);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Event(argsList[0], date);
        }
    }

    /**
     * Converts the stored representation to a Event instance.
     *
     * @param storageString Representation as given by its own `toStorageString()`.
     * @return New Event task based on the storage representation.
     * @throws TaskException If the description is empty, or if storage representation cannot be delimited.
     */
    public static Event parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 3) {
            throw new TaskException("Invalid storage string");
        }
        return new Event(inputList[0], inputList[1], decodeCompletionFlag(inputList[2]));
    }

    /**
     * Returns the type of the task.
     *
     * @return The Event task type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns the storage representation.
     *
     * @return The storage representation.
     */
    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + dateInfo + DELIMITER_STORAGE + storeCompletionFlag();
    }

    /**
     * Returns the string representation of the current instance.
     *
     * @return String representation of the current instance.
     */
    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo.format(Task.DISPLAY_FORMAT);
    }
}
