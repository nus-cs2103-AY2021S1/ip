package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class Help extends Instruction {

    public Help(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        return "********************************List of commands********************************\n" +
                "help\n" +
                "list\n" +
                "todo <task description>\n" +
                "deadline <task description> /by <YYYY-MM-DD> *Optional*<HH:MM>\n" +
                "event <task description> /at <YYYY-MM-DD> *Optional*<HH:MM>\n" +
                "find <task index>\n" +
                "done <task index\n" +
                "delete <task index>\n" +
                "statistic\n";
    }
}
