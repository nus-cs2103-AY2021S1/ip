package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

public class DeadlineCommand extends Command {
    public String input;

    public DeadlineCommand(String input) throws NullDeadlineInputException {
        if (input.length() == 0) {
            throw new NullDeadlineInputException();
        }
        this.input = input;
    }

    private String setDate(String date) throws InvalidDateTimeException {
        String[] split = date.split("/");
        if (date.length() != 10 || split.length != 3) {
            throw new InvalidDateTimeException();
        }
        String setDate = split[0] + "-" + split[1] + "-" + split[2];
        return setDate;
    }

    private String setTime(String time) throws InvalidDateTimeException {
        if (time.length() != 4) {
            throw new InvalidDateTimeException();
        }
        String setTime = time.substring(0, 2) + ":" + time.substring(2);
        return setTime;
    }

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
