package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * DeadlineCommand deals with deadline input.
 */
public class DeadlineCommand extends Command {
    public String input;

    /**
     * Initiate DeadlineCommand.
     * @param input  User input
     * @throws NullDeadlineInputException
     */
    public DeadlineCommand(String input) throws NullDeadlineInputException {
        if (input.length() == 0) {
            throw new NullDeadlineInputException();
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
     * Create Deadline to be added.
     * @param description  Deadline description
     * @param datetime     Deadline date and time
     * @return Deadline
     * @throws InvalidDateTimeException
     */
    private Deadline createDeadline(String description, String datetime) throws InvalidDateTimeException {
        String[] datetimeArray = datetime.split(" ");

        if (datetimeArray.length != 2) {
            throw new InvalidDateTimeException();
        }
        String date = setDate(datetimeArray[0]);
        String time = setTime(datetimeArray[1]);
        return new Deadline(description, date, time);
    }

    @Override
    public boolean isExited() { return false;}

    /**
     * Execute deadline command.
     * @param tasklist  TaskList for Deadline to be added
     * @param ui        User interface used
     * @param storage   Storage to update save file
     * @throws InvalidDeadlineInputException
     * @throws IOException
     * @throws InvalidDateTimeException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws InvalidDeadlineInputException, IOException, InvalidDateTimeException {
        String[] deadlineTaskArray = input.split(" /by ");
        if (deadlineTaskArray.length != 2) {
            throw new InvalidDeadlineInputException();
        }
        String deadlineDescription = deadlineTaskArray[0];
        String by = deadlineTaskArray[1];
        Deadline deadline = createDeadline(deadlineDescription, by);
        tasklist.addTask(deadline);
        tasklist.updateData(storage);
        ui.showAdd(deadline, tasklist);
    }
}
