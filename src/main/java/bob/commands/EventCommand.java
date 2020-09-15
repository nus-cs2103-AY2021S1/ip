package bob.commands;

import java.io.IOException;

import bob.common.MsgGenerator;
import bob.data.task.Event;
import bob.data.task.Tasklist;
import bob.exceptions.BobEmptyTaskException;
import bob.exceptions.BobInvalidDateAndTimeException;
import bob.storage.Storage;

/**
 * Adds an event to Bob's tasklist.
 */
public class EventCommand extends Command {
    private final String input;

    /**
     * Creates an event command.
     *
     * @param input User input.
     * @throws BobEmptyTaskException If there is no description for event.
     */
    public EventCommand(String input) throws BobEmptyTaskException {
        //Removes all whitespaces and checks if input is empty
        boolean isEmptyInput = input.trim().length() == 0;
        boolean isNotEmptyInput = !isEmptyInput;

        if (isEmptyInput) {
            throw new BobEmptyTaskException();
        }

        assert isNotEmptyInput;
        this.input = input.trim();
    }

    /**
     * Formats date to be parsed.
     *
     * @param date Inputted date.
     * @return Formatted date.
     * @throws BobInvalidDateAndTimeException If the inputted date and time has invalid format.
     */
    private String formatDate(String date) throws BobInvalidDateAndTimeException {
        String[] split = date.split("/");

        boolean isNotCorrectLength = date.length() != 10;
        boolean isNotValidFormat = split.length != 3;
        boolean isNotValidDate = isNotCorrectLength || isNotValidFormat;
        boolean isValidDate = !isNotValidDate;

        if (isNotValidDate) {
            throw new BobInvalidDateAndTimeException();
        }

        assert isValidDate;
        return split[0] + "-" + split[1] + "-" + split[2];
    }

    /**
     * Formats time to be parsed.
     *
     * @param time Inputted time.
     * @return Formatted time.
     * @throws BobInvalidDateAndTimeException If the inputted date and time has invalid format.
     */
    private String formatTime(String time) throws BobInvalidDateAndTimeException {
        boolean isNotCorrectLength = time.length() != 4;
        boolean isCorrectLength = !isNotCorrectLength;

        // If length of time is not same as "HHMM"
        if (isNotCorrectLength) {
            throw new BobInvalidDateAndTimeException();
        }

        assert isCorrectLength;
        return time.substring(0, 2) + ":" + time.substring(2);
    }

    /**
     * Adds an event to the list.
     *
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     * @return String message regarding adding of event.
     * @throws BobInvalidDateAndTimeException If format of date and time is invalid.
     */
    private Event createEvent(String description, String dateAndTime)
            throws BobInvalidDateAndTimeException {
        // Checks if there is a space between "/by" and "date and time"
        String temp = dateAndTime.trim();
        String[] dateAndTimeSplit = temp.split(" ");

        boolean isNotCorrectFormat = dateAndTimeSplit.length != 2;
        boolean isCorrectFormat = !isNotCorrectFormat;

        // If format of date and time is invalid (in this case, not separated by one space)
        if (isNotCorrectFormat) {
            throw new BobInvalidDateAndTimeException();
        }

        assert isCorrectFormat;
        String date = formatDate(dateAndTimeSplit[0]);
        String time = formatTime(dateAndTimeSplit[1]);
        return new Event(description, date, time);
    }

    @Override
    public boolean isExited() {
        return false;
    }


    /**
     * Executes event command.
     *
     * @param tasks Bob's tasklist.
     * @param storage Bob's storage.
     * @return String message response after executing event command.
     * @throws BobInvalidDateAndTimeException if no/invalid date and time is stated.
     * @throws IOException If an error occurs while updating file.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) throws BobInvalidDateAndTimeException, IOException {
        String[] split = input.split("/at");

        boolean isNotValidFormat = split.length == 1;
        boolean isValidFormat = !isNotValidFormat;

        if (isNotValidFormat) {
            throw new BobInvalidDateAndTimeException();
        }

        assert isValidFormat;
        String description = split[0];
        String dateAndTime = split[1];
        Event event = createEvent(description, dateAndTime);

        tasks.addTask(event);
        tasks.updateData(storage);
        return MsgGenerator.generateAddMessage(event, tasks);
    }

}
