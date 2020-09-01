package duke;

import command.AddToListCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;

/**
 * A Parser object deals with making sense of user command.
 *
 * @author amelia
 * @version 2.0
 * @since 2020-09-01
 */
public class Parser {
    private TaskList currList;

    public Parser(TaskList currList) {
        this.currList = currList;
    }

    /**
     * Looks through the input message to determine actions to be taken.
     *
     * @param inputMsg User's input message to the chat bot.
     * @return Command to be executed.
     */
    public Command processMsg(String inputMsg) {
        // user specified action, to identify type of action
        String actionType = inputMsg.split(" ")[0];

        if (inputMsg.equals("list")) {
            return new ListCommand();
        } else if (actionType.equals("done")) {
            return new DoneCommand();
        } else if (actionType.equals("delete")) {
            return new DeleteCommand();
        } else if (actionType.equals("find")) {
            return new FindCommand();
        } else {
            return new AddToListCommand();
        }
    }
}
