package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an FindCommand that is part of the Command class, regarding finding a keyword in the chat.
 */
public class FindCommand extends Command {

    /**
     * Constructor for the find command.
     * @param command
     */
    public FindCommand(String command) {
        super(command, false);
    }

    /**
     * Executes the command to find tasks matching the keyword.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        TaskList finding = new TaskList();
        String keyWord = command.substring(5);
        for (Task t : list.getList()) {
            if (t.getDescription().contains(keyWord)) {
                finding.add(t);
            }
        }
        ui.printFoundTask(finding);
    }

    /**
     * Executes the command to find tasks matching the keyword on FXML.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        TaskList finding = new TaskList();
        String keyWord = command.substring(5);
        for (Task t : list.getList()) {
            if (t.getDescription().contains(keyWord)) {
                finding.add(t);
            }
        }
        return ui.printFoundTask(finding, true);
    }
}
