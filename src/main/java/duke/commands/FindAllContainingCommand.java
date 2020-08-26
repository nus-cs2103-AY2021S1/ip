package duke.commands;

import duke.MessageManager;
import duke.TaskManager;
import duke.Ui;
import duke.patterns.InputPattern;
import duke.tasks.Task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to find all containing a keyword.
 */
public class FindAllContainingCommand extends Command {

    /**
     * Class constructor.
     * @param input the user input
     */
    public FindAllContainingCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * @param taskManager the taskManager
     * @param ui the ui to return output to
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Pattern r = Pattern.compile(InputPattern.FIND_ALL_CONTAINING);
        Matcher m = r.matcher(input);
        m.find();
        String query = m.group("content");
        List<Task> matchedTasks = taskManager.findAllContaining(query);
        String message = MessageManager.getFindAllContainingMessage(matchedTasks);
        ui.sendMessage(message);
    }
}
