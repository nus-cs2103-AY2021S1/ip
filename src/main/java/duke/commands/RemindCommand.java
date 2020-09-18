package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";
    private static final int SIZE_OFFSET = -1;

    /**
     * Creates an instance of a Remind Command with the appropriate
     * task to be flagged for reminder as the command description.
     *
     * @param commandDescription Description of the command body.
     */
    public RemindCommand(String commandDescription) {
        super(commandDescription, false);
    }

    /**
     * Executes the command and returns Duke's response.
     * Marks the appropriate task for reminders.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + SIZE_OFFSET);
            task.setReminder();
            storage.saveData(taskList, ui);
            return ui.displayRemindTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        } catch (NumberFormatException e) {
            return ui.showError("Looks like your input was invalid! Enter --help for more information");
        }
    }

}
