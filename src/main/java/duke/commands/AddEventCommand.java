package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Event;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add an event.
 */
public class AddEventCommand extends Command {
    /**
     * Class constructor.
     *
     * @param input the user input
     */
    public AddEventCommand(String input) {
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
        Pattern r = Pattern.compile(InputPattern.addEventPattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetime = m.group("datetime");
        try {
            Event event = taskManager.addEvent(content, datetime);
            ui.sendMessage(MessageManager.getAddSuccessMessage(event, taskManager));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
