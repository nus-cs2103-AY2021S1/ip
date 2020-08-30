package Parser;

import Command.AddDeadlineCommand;
import Command.AddEventCommand;
import Command.AddTodoCommand;
import Command.WrongCommand;
import Errors.ErrorExceptions;

/**
 * Represents the bridge that calls the appropriate add task command.
 */
public class ParseAddTask extends Parse {
    /**
     * Decomposes the user input and pass to the specific add task commands depending on what
     * type of task is called.
     *
     * @param s task name.
     * @param input user input.
     * @throws ErrorExceptions when the task cannot be created.
     */
    public static void execute(String s, String input) throws ErrorExceptions {
        String current = s;
        if (current.equals("todo")) {
            AddTodoCommand.execute(input);
        } else if (current.equals("deadline")){
            AddDeadlineCommand.execute(input);
        } else if (current.equals("event")) {
            AddEventCommand.execute(input);
        } else {
            WrongCommand.execute();
        }
    }
}
