package taskbot.logic;

import taskbot.command.Command;
import taskbot.exceptions.TaskbotException;
import taskbot.parser.Parser;
import taskbot.storage.Storage;
import taskbot.task.TaskList;

/**
 * This class handles logical inputs.
 */
public class Taskbot {
    private TaskList taskList;

    public Taskbot() {
        taskList = new TaskList(new Storage(System.getProperty("user.dir")));
    }

    /**
     * Returns appropriate response given user input.
     * @param input User input containing a command to be executed.
     * @return A line of text signifying success or failure of carrying out the command.
     */
    public String getResponse(String input) {
        try {
            // Process user input and get the relevant command
            Command cmd = Parser.parse(input);
            // Execute the command
            return cmd.execute(taskList);
        } catch (TaskbotException e) {
            return e.getMessage() + "\n";
        }
    }
}
