package executor;

import command.Command;
import command.DeleteCommand;
import constant.DukeConstants;
import dukeoutput.DukeOutput;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeleteCommandExecutor extends CommandExecutor {

    @Override
    public void execute(Command command, TaskList taskList) {
        try {
            int parameter = Integer.parseInt(((DeleteCommand) command).getArgument());
            String response = taskList.delete(parameter - 1);
            int taskListSize = taskList.getCurrentSize();
            String additionalResponse = String.format("Now you have %d tasks in the list.",
                    taskListSize);
            List<String> responses = Arrays.asList(DukeConstants.DELETE_OUTPUT, response, additionalResponse);
            DukeOutput.output(responses, Collections.singletonList(1));
        } catch (NumberFormatException exception) {
            DukeOutput.output("☹ OOPS!!! the task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output("☹ OOPS!!! the task number has to be valid");
        }    }
}
