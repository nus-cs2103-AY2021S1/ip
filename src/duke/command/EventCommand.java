package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    public EventCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processEvent(this.task, taskList, ui, storage);
        } catch (EventException e) {
            System.out.println(e.getMessage());
        }
    }

    public void processEvent(String theRest, TaskList taskList, Ui ui, Storage storage) throws EventException {
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

                    if(dateTime.length < 2) {
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
                            event = new Event(eventDesc, false, localDate, localStartTime, localEndTime);
                        }
                    }

                    taskList.saveToList(event);
                    storage.updateData(taskList.getTasks());

                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
                }

            } catch (IndexOutOfBoundsException e) {
                throw new EventException("Please specify the event name and date.");
            }

        } catch (DukeException d) {
            throw new EventException("Please specify the event name and date.");
        }
    }

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
