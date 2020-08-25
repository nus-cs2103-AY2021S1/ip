package duke.commands;

import duke.*;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Event;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventCommand extends Command {

    public AddEventCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

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
