package duke.command;

import java.io.IOException;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidUpdateInputException;
import duke.exception.NullDeadlineInputException;

/**
 * DeadlineCommand deals with deadline input.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Initiate DeadlineCommand.
     * @param input  User input
     * @throws NullDeadlineInputException
     */
    public DeadlineCommand(String input) throws NullDeadlineInputException {
        boolean isEmptyInput = input.trim().length() == 0;
        boolean isNotEmptyInput = !isEmptyInput;
        if (isEmptyInput) {
            throw new NullDeadlineInputException();
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
     * Create Deadline to be added.
     * @param description  Deadline description
     * @param datetime     Deadline date and time
     * @return Deadline
     * @throws InvalidDateTimeException
     */
    private Deadline createDeadline(String description, String datetime) throws InvalidDateTimeException, InvalidUpdateInputException {
        String[] datetimeArray = datetime.split(" ");

        boolean isInvalidFormat = datetimeArray.length != 2;
        boolean isValidFormat = !isInvalidFormat;

        if (isInvalidFormat) {
            throw new InvalidDateTimeException();
        }

        assert isValidFormat;
        String date = setDate(datetimeArray[0]);
        String time = setTime(datetimeArray[1]);
        return new Deadline(description, date, time);
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Execute deadline command.
     * @param tasklist  TaskList for Deadline to be added
     * @param storage   Storage to update save file
     * @throws InvalidDeadlineInputException
     * @throws IOException
     * @throws InvalidDateTimeException
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws InvalidDeadlineInputException,
            IOException, InvalidDateTimeException, InvalidUpdateInputException {
        String[] deadlineTaskArray = input.split(" /by ");

        boolean isInvalidInput = deadlineTaskArray.length != 2;
        boolean isValidInput = ! isInvalidInput;

        if (isInvalidInput) {
            throw new InvalidDeadlineInputException();
        }

        assert isValidInput;
        String deadlineDescription = deadlineTaskArray[0];
        String by = deadlineTaskArray[1];
        Deadline deadline = createDeadline(deadlineDescription, by);
        tasklist.addTask(deadline);
        tasklist.updateData(storage);
        return Ui.showAdd(deadline, tasklist);
    }
}
