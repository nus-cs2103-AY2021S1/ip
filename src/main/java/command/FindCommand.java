package command;


import exception.DukeKeywordException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * FindCommand executes when use specify "find" in the command.
 */
public class FindCommand extends Command {

    private String command;

    /**
     * Constructs a new FindCommand with the specified
     * user command.
     * @param command String user command
     */
    public FindCommand(String command) {
        this.command = command;
    }


    /**
     * Executes parsed user command. The result is:
     * 1. Shows the list of tasks with that keyword to the
     * user.
     *
     * @param tasks TaskList list of task
     * @param ui Ui updating user to show intended messages.
     * @param storage Storage Updates external file whenever needed.
     * @throws DukeKeywordException Thrown when user failed to specify
     * keyword in the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeKeywordException {
        String keyword = Parser.findKeywordParser(this.command);
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for (Task task : tasks.getTasks()) {
            boolean isKeywordExist = task.getDescription().contains(keyword);

            if (isKeywordExist) {
                sb.append(ui.formatMessage((i) + ". " + task + "\n"));
                i++;
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        return ui.getMessageTemplate(ui
                .formatMessage("Here are the matching tasks in your list:\n" + sb.toString()));
    }
}
