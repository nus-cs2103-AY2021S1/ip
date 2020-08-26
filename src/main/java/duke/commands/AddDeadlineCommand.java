package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Deadline;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public AddDeadlineCommand(String input) {
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
        Pattern r = Pattern.compile(InputPattern.addDeadlinePattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetimeDue = m.group("datetimeDue");
        try {
            Deadline deadline = taskManager.addDeadline(content, datetimeDue);
            ui.sendMessage(MessageManager.getAddSuccessMessage(deadline, taskManager));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
