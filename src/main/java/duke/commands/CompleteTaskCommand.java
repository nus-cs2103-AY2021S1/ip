package duke.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.MessageManager;
import duke.TaskManager;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Task;

/**
 * Represents a command to complete a task.
 */
public class CompleteTaskCommand extends Command {
    /**
     * Initializes a complete task command.
     *
     * @param input User input.
     */
    public CompleteTaskCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Completes specified task and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {

        Pattern r = Pattern.compile(InputPattern.COMPLETE_TASK);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = taskManager.completeTask(taskNumber);
            return MessageManager.getCompleteSuccessMessage(task);
        } catch (DukeException | IOException exception) {
            this.isError = true;
            return exception.getMessage();
        }
    }
}
