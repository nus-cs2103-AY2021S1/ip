package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.NoMatchFoundException;
import duke.task.Task;

/**
 * Represents a FindTaskCommand
 */
public class FindTaskCommand extends Command {

    /**
     * Keyword to match task descriptions to.
     */
    protected String keyword;

    /**
     * Creates a FindTaskCommand object.
     * @param keyword Keyword to be matched.
     */
    public FindTaskCommand(String keyword) {
        super(CommandType.FINDTASK);
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes a find task command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return List of tasks that match the keyword.
     * @throws NoMatchFoundException For when no matches are found.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws NoMatchFoundException {
        boolean hasMatch = false;
        String s = ui.printPrompt("These are the tasks that match the keyword:\n");
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                s += ui.printTask(taskList, i);
                hasMatch = true;
            } else {
                continue;
            }
        }
        if (!hasMatch) {
            throw new NoMatchFoundException();
        }
        return s + ui.printAdditionActionMessage();
    }
}
