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
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    /**
     * Initializes a delete task command.
     *
     * @param input User input.
     */
    public DeleteTaskCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Deletes a task from storage and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        Pattern r = Pattern.compile(InputPattern.DELETE_TASK);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = taskManager.deleteTask(taskNumber);
            return MessageManager.getDeleteSuccessMessage(task, taskManager);
        } catch (DukeException | IOException exception) {
            this.isError = true;
            return exception.getMessage();
        }
    }
}
