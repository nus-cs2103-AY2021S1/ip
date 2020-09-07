package dukechatbot.executor;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import dukechatbot.command.AddCommand;
import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.parser.TaskParser;
import dukechatbot.task.Task;
import dukechatbot.tasklist.TaskList;


/**
 * Represents executor of add command.
 * Executes the action of adding the task to the task list.
 */
public class AddCommandExecutor extends CommandExecutor {

    /**
     * Creates a Task object from the command.
     * Adds task to the task list.
     * Prints response from Duke.
     *
     * @param command
     * @param taskList
     */
    @Override
    public String execute(Command command, TaskList taskList) {
        Task task;
        try {
            task = TaskParser.parseTask(((AddCommand) command).getArgument(), (
                    (AddCommand) command).getTaskType());
        } catch (IndexOutOfBoundsException | DateTimeParseException 
                | NoSuchElementException exception) { 
            return DukeOutput.output(exception.getMessage());
            
        }
        taskList.add(task);
        String taskListSizeInfo = String.format("Now you have %d tasks in the list.",
                taskList.getCurrentSize());
        List<String> responses = Arrays.asList(DukeConstants.ADD_TASK_OUTPUT,
                task.toString(), taskListSizeInfo);
        return DukeOutput.output(responses, Collections.singletonList(1));
    }
}
