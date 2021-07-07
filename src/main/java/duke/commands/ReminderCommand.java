package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.IncorrectReminderInputException;
import duke.exceptions.InvalidReminderFormatException;

/**
 * Class to initiate the reminder command.
 */
public class ReminderCommand extends Command {
    public ReminderCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the reminder command and shows the list of task that is within the input date.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @return String message of the command.
     * @throws InvalidReminderFormatException If the format of reminder command is wrong.
     * @throws IncorrectReminderInputException If the string after reminder command is not a number or is less
     * than 1.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidReminderFormatException,
            IncorrectReminderInputException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 2) {
            throw new InvalidReminderFormatException();
        }

        int numberOfDays = convertToNumber(tempArray[1]);
        if (numberOfDays <= 0) {
            throw new IncorrectReminderInputException();
        }

        TaskList upcomingTask = taskList.findUpcomingEvents(numberOfDays);

        return ui.reminderMessage(numberOfDays, upcomingTask.toString(), upcomingTask.getTaskListLength());
    }

    /**
     * Converts a given string to a number.
     * Returns the number in int form.
     *
     * @param number Item to convert to int.
     * @throws IncorrectReminderInputException If the string after reminder command is not a number.
     */
    public int convertToNumber(String number) throws IncorrectReminderInputException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectReminderInputException();
        }
    }
}
