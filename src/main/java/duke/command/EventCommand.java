package duke.command;

import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidUpdateInputException;
import duke.exception.NullEventInputException;

import java.io.IOException;

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
        boolean isEmptyInput = input.trim().length() == 0;
        boolean isNotEmptyInput = !isEmptyInput;
        if (isEmptyInput) {
            throw new NullEventInputException();
        }
        assert isNotEmptyInput;
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

        boolean isIncorrectLength = date.length() != 10;
        boolean isIncorrectFormat = split.length != 3;
        boolean isInvalidDate = isIncorrectLength || isIncorrectFormat;
        boolean isValidDate = !isInvalidDate;

        if (isInvalidDate) {
            throw new InvalidDateTimeException();
        }

        assert isValidDate;
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

        boolean isIncorrectLength = time.length() != 4;
        boolean isValidTime = !isIncorrectLength;

        if (isIncorrectLength) {
            throw new InvalidDateTimeException();
        }

        assert isValidTime;
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
    private Event createEvent(String description, String datetime) throws InvalidDateTimeException, InvalidUpdateInputException {
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
            InvalidDateTimeException, IOException, InvalidUpdateInputException {
        String[] eventTaskArray = input.split(" /at ");

        boolean isInvalidInput = eventTaskArray.length != 2;
        boolean isValidInput = !isInvalidInput;

        if (isInvalidInput) {
            throw new InvalidEventInputException();
        }

        assert isValidInput;
        String eventDescription = eventTaskArray[0];
        String at = eventTaskArray[1];
        Event event = createEvent(eventDescription, at);
        tasks.addTask(event);
        tasks.updateData(storage);
        return Ui.showAdd(event, tasks);
    }
}
