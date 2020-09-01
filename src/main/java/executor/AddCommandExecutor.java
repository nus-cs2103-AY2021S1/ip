package executor;

import command.AddCommand;
import command.Command;
import constant.DukeConstants;
import dukeoutput.DukeOutput;
import parser.TaskParser;
import task.Task;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddCommandExecutor extends CommandExecutor {

    @Override
    public void execute(Command command, TaskList taskList) {
        Task task;
        try {
            task = TaskParser.parseTask(((AddCommand) command).getArgument(),
                    ((AddCommand) command).getTaskType());
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output(exception.getMessage());
            return;
        }
        taskList.add(task);
        String taskListSizeInfo = String.format("Now you have %d tasks in the list.", taskList.getCurrentSize());
        List<String> responses = Arrays.asList(DukeConstants.ADD_TASK_OUTPUT, task.toString(), taskListSizeInfo);
        DukeOutput.output(responses, Collections.singletonList(1));
    }
}