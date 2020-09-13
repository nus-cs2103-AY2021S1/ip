package duke.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.MessageManager;
import duke.TaskManager;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Event;

/**
 * Represents a command to add an event.
 */
public class AddEventCommand extends Command {
    /**
     * Initializes an add event command.
     *
     * @param input User input.
     */
    public AddEventCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Adds event to storage and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        Pattern r = Pattern.compile(InputPattern.ADD_EVENT);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetime = m.group("datetime");
        String priority = m.group("priority");
        try {
            Event event = taskManager.addEvent(content, datetime, priority);
            return MessageManager.getAddSuccessMessage(event, taskManager);
        } catch (DukeException | IOException exception) {
            this.isError = true;
            return exception.getMessage();
        }
    }
}
