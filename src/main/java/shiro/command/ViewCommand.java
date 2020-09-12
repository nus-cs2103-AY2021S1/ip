package shiro.command;

import shiro.message.Message;
import shiro.parser.Parser;
import shiro.storage.Storage;
import shiro.task.Task;
import shiro.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * represents a command to view deadline and events on the given date
 */
public class ViewCommand extends Command {
    /**
     * class constructor
     * @param fullCommand the full command given by he user
     */
    public ViewCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * parses the string given by the user into a LocalDate object
     * find the list of tasks that to be done on the given date
     * finally, return a command result containing the list of tasks to be done on the given date
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return command result containing the list of tasks to be done on the given date
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        LocalDate date = Parser.parseDate(fullCommand.substring(5));
        ArrayList<Task> tasks = taskList.getTasksOnDate(date);
        return new CommandResult(Message.viewTasksOnDateMessage(tasks));
    }
}
