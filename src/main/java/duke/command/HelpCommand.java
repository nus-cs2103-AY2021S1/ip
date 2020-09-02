package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    public HelpCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return getHelpMessage();
    }

    public String getHelpMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("to add tasks to your list: \n    ")
                .append("ToDo - type 'todo <description of your todo>'\n    ")
                .append("Deadline - type 'deadline <description of your deadline> /by <due date>'\n    ")
                .append("Event - type 'event <description of your event> /at <due date>\n").append("\n")
                .append("for other functions: \n    ")
                .append("type 'done <task number>' to mark that task as done\n    ")
                .append("type 'delete <task number>' to remove that task from your list\n    ")
                .append("type 'list' to see your list of tasks\n    ")
                .append("type 'find <keyword>' to search for tasks containing that keyword\n    ")
                .append("type 'bye' to exit");

        return sb.toString();
    }
}
