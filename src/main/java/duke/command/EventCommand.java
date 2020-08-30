package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
        } catch (EventException e) {
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
        String theRest, TaskList taskList, Ui ui, Storage storage) throws EventException {
        try {
            String[] eventAndDateAndTime = theRest.split(" /at ", 2);
            Event event;

            try {
                String eventDesc = eventAndDateAndTime[0];
                String eventDateAndTime = eventAndDateAndTime[1];

                String[] dateTime = eventDateAndTime.split(" ", 2);

                String date = dateTime[0];

                try {
                    LocalDate localDate = LocalDate.parse(date);

                    if (dateTime.length < 2) {
                        event = new Event(eventDesc, localDate);
                    } else {

                        String time = dateTime[1];
                        String[] startEndTime = time.split("-");
                        if (startEndTime.length < 2) {
                            String startTime = startEndTime[0];
                            LocalTime localTime = LocalTime.parse(startTime);
                            event = new Event(eventDesc, false, localDate, localTime);
                        } else {
                            String startTime = startEndTime[0];
                            String endTime = startEndTime[1];
                            LocalTime localStartTime = LocalTime.parse(startTime);
                            LocalTime localEndTime = LocalTime.parse(endTime);
                            event = new Event(eventDesc, false,
                                localDate, localStartTime, localEndTime);
                        }
                    }

                    Storage.updateData(taskList.getTasks());
                    return taskList.saveToList(event);

                } catch (DateTimeParseException e) {
                    return "Please enter the date in "
                        + "YYYY/MM/DD format and time in HH:MM format.";
                }

            } catch (IndexOutOfBoundsException e) {
                throw new EventException("Please specify the event name and date.");
            }

        } catch (DukeException d) {
            throw new EventException("Please specify the event name and date.");
        }
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
