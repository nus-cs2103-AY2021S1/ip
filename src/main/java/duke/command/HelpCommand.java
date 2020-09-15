package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to list out the type of commands that Duke understand.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a <code>HelpCommand</code> object.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Lists out the types of commands that Duke understands.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        String responseMessage = "Here is what you can do with me:\n\t "
                + "* help: list of the commands that I understand\n\t "
                + "* todo <description>: add a new TODO\n\t "
                + "* deadline <description> /by <dd/MM/yyyy HH:mm>: add a new DEADLINE\n\t "
                + "* event <description> /at <dd/MM/yyyy HH:mm>: add a new EVENT\n\t "
                + "* list: list out the tasks you have currently\n\t "
                + "* delete <task number>: delete the task from your list\n\t "
                + "* done <task number>: mark the task as complete\n\t "
                + "* retrieve <dd/MM/yyyy>: retrieves the tasks due on or happening on this date\n\t "
                + "* find <keyword>: retrieves the tasks that contain the keyword\n\t "
                + "* sort: sorts your list by their task type and then by their dates and time if any\n\t "
                + "* bye: exit the application";
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(responseMessage, shouldExit);
    }
}
