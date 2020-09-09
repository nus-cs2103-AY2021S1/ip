package duke.task;

import java.time.LocalDateTime;

/**
 * Encapsulates an event.
 */
public class Event extends Task {
    private static final String DELIMITER_AT = " /from ";
    private static final String DELIMITER_BY = " /till ";
    private static final String FORMAT = "The format should be <description> "
            + "/from <time in DD/MM/YYYY HH:mm> /till <time in DD/MM/YYYY HH:mm>";

    private LocalDateTime dateFrom;
    private LocalDateTime dateTill;

    /**
     * Constructs an event task that it not yet complete.
     *
     * @param description Description for the event.
     * @param dateFrom Date of the event start in DD/MM/YYYY HH:mm format.
     * @param dateTill Date of the event ends in DD/MM/YYYY HH:mm format.
     * @throws TaskException If the description is empty.
     */
    public Event(String description, LocalDateTime dateFrom, LocalDateTime dateTill) throws TaskException {
        super(description);
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
    }

    /**
     * Constructs an event with full set of parameters.
     *
     * @param description Description for the event.
     * @param dateFrom Date of the event start in DD/MM/YYYY HH:mm format.
     * @param dateTill Date of the event ends in DD/MM/YYYY HH:mm format.
     * @param isCompleted If the task is completed already.
     * @throws TaskException If the description is empty.
     */
    private Event(String description, LocalDateTime dateFrom, LocalDateTime dateTill,
                  boolean isCompleted) throws TaskException {
        super(description, isCompleted);
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
    }

    /**
     * Constructs an event task with parameters as a single string.
     *
     * @param args Parameters for the args in the form of "[description]
     *             /from [date in DD/MM/YYYY HH:mm] /till [date in DD/MM/YYYY HH:mm]"
     * @return New event instance.
     * @throws TaskException If the description is empty or the given string cannot be delimited.
     */
    public static Event create(String args) throws TaskException {
        String[] fromList = args.split(DELIMITER_AT);
        if (fromList.length < 2) {
            throw new TaskException("I need more arguments for to track an Event.\n"
            + FORMAT);
        }

        String[] tillList = fromList[1].split(DELIMITER_BY);
        if (tillList.length < 2) {
            throw new TaskException("I need more arguments for to track an Event.\n"
                    + FORMAT);
        }

        String description = fromList[0];
        String fromDateString = tillList[0];
        String tillDateString = tillList[1];
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, Task.DATE_FORMAT_PARSE);
        LocalDateTime tillDate = LocalDateTime.parse(tillDateString, Task.DATE_FORMAT_PARSE);
        return new Event(description, fromDate, tillDate);
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
        if (inputList.length < 4) {
            throw new TaskException("Invalid storage string");
        }
        return new Event(inputList[0], LocalDateTime.parse(inputList[1]), LocalDateTime.parse(inputList[2]),
                isTaskCompleted(inputList[3]));
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
        return description + DELIMITER_STORAGE
                + dateFrom + DELIMITER_STORAGE + dateTill + DELIMITER_STORAGE + storeCompletionFlag();
    }

    /**
     * Returns the string representation of the current instance.
     *
     * @return String representation of the current instance.
     */
    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description
                + "\nPeriod: " + dateFrom.format(Task.DATE_FORMAT_DISPLAY) + " to "
                + dateTill.format(Task.DATE_FORMAT_DISPLAY);
    }
}
