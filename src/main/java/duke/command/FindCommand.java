package duke.command;

import duke.TaskList;

public class FindCommand extends Command {
    public FindCommand() {
        this.commandText = "find";
    }

    @Override
    public String execute(String text, TaskList taskList) {
        return taskList.matchTasks(text);
    }
}
