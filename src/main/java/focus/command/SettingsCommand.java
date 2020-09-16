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
     * Returns the number of days that user wants.
     *
     * @param input User's input.
     * @return Number of days.
     * @throws FocusException If input does not meet criteria.
     */
    private String updateDays(String input) throws FocusException {
        String numberOfDays;
        try {
            numberOfDays = input.substring(7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSettingsCommandException();
        }
        if (numberOfDays.isBlank()) {
            throw new InvalidSettingsCommandException();
        }
        assert !numberOfDays.isEmpty() : "Number of days should not be blank here.";
        return numberOfDays;
    }

    /**
     * Returns the name user wants to change.
     *
     * @param input User's input.
     * @return User's new name.
     * @throws FocusException If input does not meet criteria.
     */
    private String updateName(String input) throws FocusException {
        String name;
        try {
            name = input.substring(7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSettingsCommandException();
        }
        if (name.isBlank()) {
            throw new InvalidSettingsCommandException();
        }
        assert !name.isEmpty() : "Number of days should not be blank here.";
        return name;
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

        if (checker.contains("/days")) {
            String numberOfDays = updateDays(checker);
            storage.updateNumberOfDays(numberOfDays);
            return "\tThe period of reminders has been changed to: " + numberOfDays + " days!";
        } else {
            String name = updateName(checker);
            Storage.updateName(name);
            return "\tYour name has been changed to: " + name + "!";
        }
    }
}
