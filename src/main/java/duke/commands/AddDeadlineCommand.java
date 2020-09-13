package duke.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.MessageManager;
import duke.TaskManager;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Deadline;

/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Initializes an add deadline command.
     *
     * @param input User input.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Adds deadline to storage and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        Pattern r = Pattern.compile(InputPattern.ADD_DEADLINE);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetimeDue = m.group("datetimeDue");
        String priority = m.group("priority");
        try {
            Deadline deadline = taskManager.addDeadline(content, datetimeDue, priority);
            return MessageManager.getAddSuccessMessage(deadline, taskManager);
        } catch (DukeException | IOException exception) {
            this.isError = true;
            return exception.getMessage();
        }
    }
}
