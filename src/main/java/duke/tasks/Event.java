package duke.tasks;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.errors.EventException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class Event extends Task {
    private String startDateAndOrTime; //the start date and/or time of the event
    private String endDateAndOrTime; //the end date and/or time of the event
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalTime startTime;
    private LocalTime endTime;
    /**
     * Assigns the name, done and day variables with values and used to initialize Event task
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param start assigns this.dayTime to dayTime value
     */
    public Event(String name, String start, String end) {
        super(name);
        this.startDateAndOrTime = start;
        this.endDateAndOrTime = end;
    }

    /**
     * Assigns the name, done and day variables with values and used to initialize Event task
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     * @param startDateAndOrTime argument used to assign this.startDateAndOrTime with value
     * @param endDateAndOrTime  argument assigns this.endDateAndOrTime with value
     */
    public Event(String name, boolean done, String startDateAndOrTime, String endDateAndOrTime) {
        super(name, done);
        this.startDateAndOrTime = startDateAndOrTime;
        this.endDateAndOrTime = endDateAndOrTime;
    }

    /**
     * Overrides the toString methods
     *
     * @return the specific representation for Event class as mentioned with [E] indicating that it is a Event class
     *      * and also mentions the Event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.startDateAndOrTime + "-" + this.endDateAndOrTime + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Event
     *
     * @return the string representation
     */
    public String inputListFormat() {
        return "E" + super.inputListFormat() + "| " + this.startDateAndOrTime + "-" + this.endDateAndOrTime;
        //format of Tasks to appear in file in Storage
    }
    public static String addEventTask(TaskList tasks, Ui ui, Storage storage, String commandDescription) throws DukeException {
        return process(tasks, storage, commandDescription);
    }
    /**
     * Returns String if the user input is correct and throws exception otherwise
     *
     * @param tasks used to add Event into tasks if user input is correct
     * @param storage used to update file in storage that contains file if user input is correct
     * @return String informing user that Event has been added to list
     * @throws DukeException thrown if user input is wrong
     */
    private static String process(TaskList tasks, Storage storage, String commandDescription) throws DukeException {
        String[] dataSplit = splitData(commandDescription);
        //splits String into different of Event name, start and end time and/or date
        Event event = eventTask(dataSplit[0], dataSplit[1], dataSplit[2]);
        //gives the event or throws exception
        return updateTaskList(storage, event, tasks);
        //updates the Task list in Storage and TaskList since the Event is added
    }
    /**
     * splits the data into Deadline description and the Deadline date and/ or time. If the date and/or time is absent
     * then DeadlineException is thrown.
     *
     * @return the String array where the first String is the name of the Deadline
     * and the second is the date and/or time
     * of start for event, third is the date and/or time of end for event.
     * @throws EventException thrown when the time and/or date is absent.
     */
    private static String[] splitData(String commandDescription) throws EventException {
        String s = "";
        int index = -1;
        int end = -1;
        boolean startPresent = false;
        boolean endPresent = false;
        String start = "";
        for (int i = 5; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '/') {
                index = i;
                startPresent = true; //the presence of / indicates that the start time is present.
                break;
            }
            s = s + commandDescription.charAt(i);
        }
        for (int i = index + 1; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '-' && i != commandDescription.length() - 1) {
                end = i;
                endPresent = true; // - indicates that end time is present
                break;
            }
            start = start + commandDescription.charAt(i);
        }
        if (!startPresent) {
            throw new EventException(false, false, false, false, true);
        }
        if (!endPresent) {
            throw new EventException(false, true, false, false, false);
        }
        String[] dataSplit = new String[3];

        assert !s.substring(1, s.length() - 1).contains("/"); //description cannot contain
        assert !commandDescription.substring(index + 4).contains("/at"); //start date and/or time cannot contain /at
        assert !commandDescription.substring(end + 1).contains("-"); //end date and/or time cannot contain -


        dataSplit[0] = s.substring(1, s.length() - 1);
        dataSplit[1] = commandDescription.substring(index + 4, end);
        dataSplit[2] = commandDescription.substring(end + 1);
        return dataSplit;
    }
    /**
     * This method creates a deadline task by checking whether the date and/or time given is in the correct
     * format. If it is then Deadline task is returned else, DeadlineException is returned.
     *
     * @param name description of Deadline task
     * @param start gives the dateTime of the start to check whether they are in the correct format
     * @param end gives the dateTime of the end to check whether they are in the correct format
     * @return deadline if the dateTime is in the correct format
     * @throws DeadlineException if the dateTime is in the incorrect format
     */
    private static Event eventTask(String name, String start, String end) throws DukeException {
        Event e;
        try {
            LocalDate startDate = stringToLocalDate(start); //converts start to date
            LocalDate endDate = stringToLocalDate(end); //converts end to date

            if (startDate.isAfter(endDate)) {
                throw new EventException(false, false, true, false, false); //if start > end then it throws this error.
            }
            e = new Event(name, startDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        } catch (EventException event) {
            throw new EventException(false, false, true, false, false);
        } catch (DateTimeException d) {
            try {
                LocalDateTime startDateTime = stringToLocalDateTime(start); //converts start to dateTime
                LocalDateTime endDateTime = stringToLocalDateTime(end); //converts end to dateTime
                if (startDateTime.isAfter(endDateTime)) {
                    throw new EventException(false, false, true, false, false); //if start > end it throws this error
                }
                e = new Event(name, startDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (EventException event) {
                throw new EventException(false, false, true, false, false);
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTime(start); //converts start to time
                    LocalTime endDate = stringToLocalTime(end); //converts end to time
                    if (parsedDate.isAfter(endDate)) {
                        throw new EventException(false, false, true, false, false); //if start > end it throws error
                    }
                    e = new Event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (EventException y) {
                    throw new EventException(false, false, true, false, false);
                } catch (DateTimeException z) {
                    throw new EventException(false, false, false, true, false);
                }
            }
        }
        return e;
    }
    public static Event eventTask(String name, String start, String end, boolean isDone) throws DukeException {
        Event e;
        try {
            LocalDate startDate = stringToLocalDateExistingTask(start); //converts start to date
            LocalDate endDate = stringToLocalDateExistingTask(end); //converts end to date

            if (startDate.isAfter(endDate)) {
                throw new EventException(false, false, true, false, false); //if start > end then it throws this error.
            }
            e = new Event(name, startDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        } catch (EventException event) {
            throw new EventException(false, false, true, false, false);
        } catch (DateTimeException d) {
            try {
                LocalDateTime startDateTime = stringToLocalDateTimeExistingTask(start); //converts start to dateTime
                LocalDateTime endDateTime = stringToLocalDateTimeExistingTask(end); //converts end to dateTime
                if (startDateTime.isAfter(endDateTime)) {
                    throw new EventException(false, false, true, false, false); //if start > end it throws this error
                }
                e = new Event(name, startDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (EventException event) {
                throw new EventException(false, false, true, false, false);
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTimeExistingTask(start); //converts start to time
                    LocalTime endDate = stringToLocalTimeExistingTask(end); //converts end to time
                    if (parsedDate.isAfter(endDate)) {
                        throw new EventException(false, false, true, false, false); //if start > end it throws error
                    }
                    e = new Event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (EventException y) {
                    throw new EventException(false, false, true, false, false);
                } catch (DateTimeException z) {
                    throw new EventException(false, false, false, true, false);
                }
            }
        }
        e.setDone(isDone);
        return e;
    }
}
