package duke.commands;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.errors.EventException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Event;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles cases when Event is keyword
 */
public class EventCommand extends AddCommand {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public EventCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Adds Event task or handle exceptions
     *
     * @param tasks to change the taskList if necessary when no error
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file in the if necessary when no error
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException if there no description after Event no time or time is wrong format
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return process(tasks, storage);
            //Returns string if correct input and updates tasks and file in storage if correct input by user, else
            // throws exception
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Returns String if the user input is correct and throws exception otherwise
     *
     * @param tasks used to add Event into tasks if user input is correct
     * @param storage used to update file in storage that contains file if user input is correct
     * @return String informing user that Event has been added to list
     * @throws DukeException thrown if user input is wrong
     */
    private String process(TaskList tasks, Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            throw new EventException(true, false, false, false, false);
        }
        String[] dataSplit = splitData();
        //splits String into different of Event name, start and end time and/or date
        Event event = provide(dataSplit[0], dataSplit[1], dataSplit[2]);
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
    private String[] splitData() throws EventException {
        String s = "";
        int index = -1;
        int end = -1;
        boolean startPresent = false;
        boolean endPresent = false;
        String start = "";
        for (int i = lengthOfKeyword; i < commandDescription.length(); i++) {
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
    private static Event provide(String name, String start, String end) throws DukeException {
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


}

