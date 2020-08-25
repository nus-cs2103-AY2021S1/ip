package seedu.bob.commands;

import seedu.bob.data.task.Deadline;
import seedu.bob.data.task.Tasklist;

import seedu.bob.exceptions.BobEmptyTaskException;
import seedu.bob.exceptions.BobInvalidDateAndTimeException;

import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

/**
 * Adds a deadline to the Bob's tasklist.
 */
public class DeadlineCommand extends Command {
    private final String input;

    /**
     * Creates a deadline command.
     *
     * @param input User input.
     * @throws BobEmptyTaskException If there is no description for deadline.
     */
    public DeadlineCommand(String input) throws BobEmptyTaskException {
        //Removes all whitespaces and checks if input is empty
        if (input.replaceAll("\\s+","").length() == 0) {
            throw new BobEmptyTaskException();
        }

        this.input = input.startsWith(" ")
                    ? input.substring(1)
                    : input;
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
        // If length of date is not same as "YYYY/MM/DD" and components are not separated by "/"
        if (date.length() != 10 || split.length != 3) {
            throw new BobInvalidDateAndTimeException();
        }
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
        // If length of time is not same as "HHMM"
        if (time.length() != 4) {
            throw new BobInvalidDateAndTimeException();
        }
        return time.substring(0, 2) + ":" + time.substring(2);
    }

    /**
     * Adds a deadline to the list.
     *
     * @param description Description of the deadline.
     * @param dateAndTime Date and time of the deadline.
     * @return String message regarding adding of deadline.
     * @throws BobInvalidDateAndTimeException If format of date and time is invalid.
     */
    private Deadline createDeadline(String description, String dateAndTime) throws
            BobInvalidDateAndTimeException {
        // Checks if there is a space between "/by" and "date and time"
        String temp = dateAndTime.startsWith(" ")
                ? dateAndTime.substring(1)
                : dateAndTime;
        String[] dateAndTimeSplit = temp.split(" ");

        // If format of date and time is invalid (in this case, not separated by one space)
        if (dateAndTimeSplit.length != 2) {
            throw new BobInvalidDateAndTimeException();
        }

        String date = formatDate(dateAndTimeSplit[0]);
        String time = formatTime(dateAndTimeSplit[1]);
        return new Deadline(description, date, time);
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes deadline command.
     * @param tasks Bob's tasklist.
     * @param ui Bob's ui.
     * @param storage Bob's storage.
     * @throws BobInvalidDateAndTimeException if no/invalid date and time is stated.
     * @throws IOException If an error occurs while updating file.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws BobInvalidDateAndTimeException, IOException {
        String[] splitD = input.split("/by");
        if (splitD.length == 1) {
            throw new BobInvalidDateAndTimeException();
        }
        String description = splitD[0];
        String dateAndTime = splitD[1];
        Deadline deadline = createDeadline(description, dateAndTime);

        tasks.addTask(deadline);
        tasks.updateData(storage);
        ui.showAddMessage(deadline, tasks);
    }
}
