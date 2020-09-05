package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.CalendarException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.task.Event;

public class EventCommand extends Command {

    public EventCommand(String task) {
        super(task);
    }

    /**
     * Processes all the done command to determine the correct output.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return processEvent(this.task, taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes all the event command to determine the correct output.
     *
     * @param theRest  Parsed string containing task details.
     * @param taskList List containing all the task(s).
     * @param ui       UI of the bot
     * @param storage  Storage managing the file in hard disk.
     * @throws EventException If user's input is incomplete or in the wrong format.
     */

    public String processEvent(
        String theRest, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event;
        try {
            String task = getEventTask(theRest);
            String[] details = parseEventDetails(theRest);
            LocalDate localDate = getEventDate(details);
            ArrayList<LocalTime> localTime = getEventTime(details);
            LocalTime startTime;
            LocalTime endTime;

            if (localTime.size() == 2) {
                startTime = localTime.get(0);
                endTime = localTime.get(1);
                event = new Event(task, false, localDate, startTime, endTime);
            } else if (localTime.size() == 1) {
                startTime = localTime.get(0);
                event = new Event(task, localDate, startTime);
            } else {
                event = new Event(task, localDate);
            }

            Storage.updateData(taskList.getTasks());
            return taskList.saveToList(event);

        } catch (EventException | CalendarException exc) {
            throw exc;
        }
    }

    /**
     * Parses the event details.
     *
     * @param theRest event details.
     * @return Parsed Event data.
     * @throws EventException
     */
    public String[] parseEventDetails(String theRest) throws EventException {
        try {
            String[] taskAndEventAndTime = theRest.split(" /at ", 2);
            String[] dateAndTime = taskAndEventAndTime[1].split(" ");
            return dateAndTime;
        } catch (IndexOutOfBoundsException i) {
            throw new EventException("Please specify the task and event date.");
        }
    }

    public String getEventTask(String theRest) {
        String[] taskAndEventAndTime = theRest.split(" /at ", 2);
        return taskAndEventAndTime[0];
    }

    public LocalDate getEventDate(String[] dateDetails) throws DukeException {

        try {
            String date = dateDetails[0];
            try {
                LocalDate localDate = LocalDate.parse(date);
                return localDate;
            } catch (DateTimeParseException e) {
                throw new CalendarException("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EventException("Please specify the task and event date.");
        }
    }

    public ArrayList<LocalTime> getEventTime(String[] dateDetails) throws DukeException {

        ArrayList<LocalTime> localTime = new ArrayList<>();
        if (dateDetails.length > 1) {

            String[] startEndTime = dateDetails[1].split("-", 2);
            String startTime = startEndTime[0];
            LocalTime localStartTime;
            LocalTime localEndTime;

            if (startEndTime.length == 2) {
                String endTime = startEndTime[1];

                try {
                    localStartTime = LocalTime.parse(startTime);
                    localEndTime = LocalTime.parse(endTime);
                    localTime.add(localStartTime);
                    localTime.add(localEndTime);

                } catch (DateTimeParseException e) {
                    throw new CalendarException("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
                }
            } else {
                localStartTime = LocalTime.parse(startTime);
                localTime.add(localStartTime);
            }
        }
        return localTime;
    }


    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     *
     * @param other Other object to compare.
     * @return True if this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof EventCommand) {
            EventCommand eventCommand = (EventCommand) other;
            return this.task.equals(eventCommand.getTask());
        }
        return false;
    }
}
