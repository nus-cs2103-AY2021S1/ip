package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * represents a command to display the possible commands
 */
public class HelpCommand extends Command {

    /**
     * class constructor
     */
    public HelpCommand() {
        super("help");
        this.isExit = false;
    }

    /**
     * returns the list of commands available to the user
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return the list of commands available to the user
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return getHelpMessage();
    }

    private String getHelpMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("available commands are: \n    ")
                .append("1. todo <todo_description>\n    ")
                .append("2. deadline <deadline_description> /by <date>\n    ")
                .append("3. event <event_description> /at <date>\n    ")
                .append("4. done <task_number>\n    ")
                .append("5. delete <task number>\n    ")
                .append("6. list\n    ")
                .append("7. clear\n    ")
                .append("8. find <keyword>\n    ")
                .append("9. bye");

        return sb.toString();
    }
}
