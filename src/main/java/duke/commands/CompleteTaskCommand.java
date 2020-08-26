package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to complete a task.
 */
public class CompleteTaskCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public CompleteTaskCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     *
     * @param taskManager the taskManager
     * @param ui          the ui to return output to
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {

        Pattern r = Pattern.compile(InputPattern.completeTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = taskManager.completeTask(taskNumber);
            ui.sendMessage(MessageManager.getCompleteSuccessMessage(task));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
