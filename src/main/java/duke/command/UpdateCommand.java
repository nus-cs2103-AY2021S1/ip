package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidUpdateInputException;

import java.io.IOException;

/**
 * UpdateCommand deals with update input.
 */
public class UpdateCommand extends Command {
    String input;

    public UpdateCommand(String input) {
        this.input = input.startsWith(" ")
                ? input.substring(1)
                : input;
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

    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException, IOException {
        try {
            if (input.length() == 0) {
                throw new InvalidUpdateInputException();
            }
            int num = Integer.parseInt(input.substring(0, 1));
            boolean isInvalidInput = num > taskList.getNumOfTask() || num <= 0;
            if (isInvalidInput) {
                throw new InvalidUpdateInputException();
            }

            boolean containsEditDescription = input.contains(" d/");
            boolean containsEditDatetime = input.contains(" dt/");

            if (containsEditDescription && !containsEditDatetime) {
                String description = input.substring(input.indexOf(" d/") + 3);
                Task task = taskList.editTaskDescription(num, description);
                taskList.updateData(storage);
                return Ui.showUpdate(num, task);
            } else if (containsEditDatetime && !containsEditDescription) {
                String datetime = input.substring(input.indexOf(" dt/") + 4);
                String[] datetimeArray = datetime.split(" ");

                if (datetimeArray.length != 2) {
                    throw new InvalidDateTimeException();
                }
                String date = setDate(datetimeArray[0]);
                String time = setTime(datetimeArray[1]);
                Task task = taskList.editTaskDatetime(num, date, time);
                taskList.updateData(storage);
                return Ui.showUpdate(num, task);
            } else if (containsEditDescription && containsEditDatetime) {
                if (input.indexOf(" dt/") < input.indexOf(" d/")) {
                    throw new InvalidUpdateInputException();
                } else {
                    String description = input.substring(input.indexOf(" d/") + 3, input.indexOf(" dt/"));
                    String datetime = input.substring(input.indexOf(" dt/") + 4);
                    String[] datetimeArray = datetime.split(" ");

                    if (datetimeArray.length != 2) {
                        throw new InvalidDateTimeException();
                    }
                    String date = setDate(datetimeArray[0]);
                    String time = setTime(datetimeArray[1]);
                    Task taskUpdateDescription = taskList.editTaskDescription(num, description);
                    Task taskUpdateDatetime = taskList.editTaskDatetime(num, date, time);
                    taskList.updateData(storage);
                    return Ui.showUpdate(num, taskUpdateDatetime);
                }
            } else {
                throw new InvalidUpdateInputException();
            }
        } catch (NumberFormatException | IOException e) {
            throw new InvalidUpdateInputException();
        }
    }
}
