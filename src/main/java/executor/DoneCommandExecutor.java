package executor;

import command.Command;
import command.DoneCommand;
import constant.DukeConstants;
import dukeoutput.DukeOutput;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoneCommandExecutor extends CommandExecutor {

    @Override
    public void execute(Command command, TaskList taskList) {
        try {
            int parameter = Integer.parseInt(((DoneCommand) command).getArgument());
            String response = taskList.markDone(parameter - 1);
            List<String> responses = Arrays.asList(DukeConstants.DONE_OUTPUT, response);
            DukeOutput.output(responses, Collections.singletonList(1));
        } catch (NumberFormatException exception) {
            DukeOutput.output("\u2639 OOPS!!! the task number has to be a postive integer.");
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output("\u2639 OOPS!!! the task number has to be valid");
        }
    }
}
