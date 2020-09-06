package duke.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.MessageManager;
import duke.TaskManager;
import duke.patterns.InputPattern;
import duke.tasks.Task;

/**
 * Represents a command to find all containing a keyword.
 */
public class FindAllContainingCommand extends Command {

    /**
     * Initializes a find all containing an input command.
     *
     * @param input User input.
     */
    public FindAllContainingCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Finds commands containing keyword and returns a message.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        Pattern r = Pattern.compile(InputPattern.FIND_ALL_CONTAINING);
        Matcher m = r.matcher(input);
        m.find();
        String query = m.group("content");
        List<Task> matchedTasks = taskManager.findAllContaining(query);
        String message = MessageManager.getFindAllContainingMessage(matchedTasks);
        return message;
    }
}
