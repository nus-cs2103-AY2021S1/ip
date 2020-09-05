package dukechatbot.executor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dukechatbot.command.Command;
import dukechatbot.command.DeleteCommand;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of delete command.
 * Executes the action of deleting the task in the task list
 * given the task item number.
 * Returns appropriate response message if task item number is invalid.
 */
public class DeleteCommandExecutor extends CommandExecutor {

    /**
     * Deletes task to the task list if list item number is valid.
     *
     * @param command
     * @param taskList
     */
    @Override
    public String execute(Command command, TaskList taskList) {
        try {
            int parameter = Integer.parseInt(((DeleteCommand) command).getArgument());
            String response = taskList.delete(parameter - 1);
            int taskListSize = taskList.getCurrentSize();
            String additionalResponse = String.format("Now you have %d tasks in the list.",
                    taskListSize);
            List<String> responses = Arrays.asList(DukeConstants.DELETE_OUTPUT,
                    response, additionalResponse);
            return DukeOutput.output(responses, Collections.singletonList(1));
        } catch (NumberFormatException exception) {
            return DukeOutput.output("\u2639 OOPS!!! the task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException exception) {
            return DukeOutput.output("\u2639 OOPS!!! the task number has to be valid");
        }
    }
}
