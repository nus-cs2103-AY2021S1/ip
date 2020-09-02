package dukechatbot.executor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dukechatbot.command.Command;
import dukechatbot.command.DoneCommand;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of done command.
 * Executes the action of marking the task in the task list as done
 * given the task item number.
 * Returns appropriate response message if task item number is invalid.
 */
public class DoneCommandExecutor extends CommandExecutor {

    /**
     * Marks task done given the task item position in the task list.
     * 
     * @param command
     * @param taskList
     */
    @Override
    public void execute(Command command, TaskList taskList) {
        try {
            int parameter = Integer.parseInt(((DoneCommand) command).getArgument());
            String response = taskList.markDone(parameter - 1);
            List<String> responses = Arrays.asList(DukeConstants.DONE_OUTPUT, response);
            DukeOutput.output(responses, Collections.singletonList(1));
        } catch (NumberFormatException exception) {
            DukeOutput.output("\u2639 OOPS!!! the task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output("\u2639 OOPS!!! the task number has to be valid");
        }
    }
}
