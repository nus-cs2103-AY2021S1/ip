package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {
    public static final String COMMAND = "update";
    public static final String DESCRIPTION_SPECIFIER = "description";
    public static final String TIME_SPECIFIER = "time";

    private String[] changes;
    private int index;

    // cannot have space + / in

    /**
     * Create an updateCommand
     * @param stringOfChanges List of updates
     */
    public UpdateCommand(int index, String stringOfChanges) {
        changes = (" " + stringOfChanges.trim()).split(" /");
        this.index = index;
    }

    private boolean checkValidation() {
        String[] specifiers = {DESCRIPTION_SPECIFIER, TIME_SPECIFIER};
        int[] appearanceTimes = {0, 0};

        for (String change : changes) {
            if (change.equals("")) {
                continue;
            }
            boolean hasSpecifier = false;
            for (int i = 0; i < specifiers.length; i++) {
                String specifier = specifiers[i];
                if (change.indexOf(specifier) == 0) {
                    hasSpecifier = true;
                    appearanceTimes[i]++;
                }
                if (appearanceTimes[i] > 1) {
                    return false;
                }
            }
            if (!hasSpecifier) {
                return false;
            }
        }
        return true;
    }

    /**
     * Change the task's info if applicable
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     * @return A message noticing that the task has been updated
     * @throws DukeException If some specifier cannot be identified
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!checkValidation()) {
            throw new DukeException("Invalid specifier(s)");
        }
        try {
            Task newTask = tasks.get(index).clone();
            for (String change : changes) {
                if (change.indexOf(DESCRIPTION_SPECIFIER) == 0) {
                    String newDescription = change.substring(DESCRIPTION_SPECIFIER.length()).trim();
                    if (newDescription.equals("")) {
                        throw new DukeException("new description cannot be empty");
                    }
                    newTask.setDescription(newDescription);
                }
            }
            tasks.set(index, newTask);
            return ui.showUpdateComplete(index + 1, newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
