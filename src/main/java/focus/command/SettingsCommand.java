package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidSettingsCommandException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the SettingCommand to let users change settings. */
public class SettingsCommand extends Command {
    /**
     * Returns false since SettingsCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes SettingsCommand to change settings.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String to notify user settings are changed.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String checker;
        if (input.length() == 8) {
            throw new InvalidSettingsCommandException();
        }
        assert !(input.length() <= 8) : "Input length should be more than 8 here.";
        try {
            checker = input.split("settings")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidSettingsCommandException();
        }

        int end = checker.indexOf("/");
        if (end == -1) {
            throw new InvalidSettingsCommandException();
        }

        String numberOfDays;
        try {
            numberOfDays = checker.substring(7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSettingsCommandException();
        }
        if (numberOfDays.isBlank()) {
            throw new InvalidSettingsCommandException();
        }
        assert !numberOfDays.isEmpty() : "Number of days should not be blank here.";
        storage.updateSettings(numberOfDays);
        return "\tThe period of reminders has been changed to: " + numberOfDays + " days!";
    }
}
