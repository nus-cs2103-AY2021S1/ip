package duke.command;

import duke.message.Message;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewCommand extends Command {
    public ViewCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        LocalDate date = Parser.parseDate(fullCommand.substring(5));
        ArrayList<Task> tasks = taskList.getTasksOnDate(date);
        return new CommandResult(Message.viewTasksOnDateMessage(tasks));
    }
}
