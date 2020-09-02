package duke.command;

import java.io.IOException;

import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidEventInputException;
import duke.exception.NullEventInputException;

/**
 * EventCommand deals with event input.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Initiate EventCommand.
     * @param input  User input
     * @throws NullEventInputException
     */
    public EventCommand(String input) throws NullEventInputException {
        if (input.length() == 0) {
            throw new NullEventInputException();
        }
        this.input = input;
    }

    /**
     * Re-format date to be readable.
     * @param date  Input date
     * @return String date
     * @throws InvalidDateTimeException
     */
    private String setDate(String date) throws InvalidDateTimeException {
        String[] split = date.split("/");
        if (date.length() != 10 || split.length != 3) {
            throw new InvalidDateTimeException();
        }
        String setDate = split[0] + "-" + split[1] + "-" + split[2];
        return setDate;
    }

    /**
     * Re-format time to be readable.
     * @param time  Input time
     * @return String time
     * @throws InvalidDateTimeException
     */
    private String setTime(String time) throws InvalidDateTimeException {
        if (time.length() != 4) {
            throw new InvalidDateTimeException();
        }
        String setTime = time.substring(0, 2) + ":" + time.substring(2);
        return setTime;
    }

    /**
     * Create Event to be added.
     * @param description  Event description
     * @param datetime     Event date and time
     * @return Event
     * @throws InvalidDateTimeException
     */
    private Event createEvent(String description, String datetime) throws InvalidDateTimeException {
        String[] datetimeArray = datetime.split(" ");

        if (datetimeArray.length != 2) {
            throw new InvalidDateTimeException();
        }
        String date = setDate(datetimeArray[0]);
        String time = setTime(datetimeArray[1]);
        return new Event(description, date, time);
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Execute event command.
     * @param tasks    TaskList for Event to be added
     * @param storage  Storage to update save file
     * @throws InvalidEventInputException
     * @throws InvalidDateTimeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidEventInputException,
            InvalidDateTimeException, IOException {
        String[] eventTaskArray = input.split(" /at ");
        if (eventTaskArray.length != 2) {
            throw new InvalidEventInputException();
        }
        String eventDescription = eventTaskArray[0];
        String at = eventTaskArray[1];
        Event event = createEvent(eventDescription, at);
        tasks.addTask(event);
        tasks.updateData(storage);
        return Ui.showAdd(event, tasks);
    }
}
