package Mattbot.Parser;

import Mattbot.Command.AddDeadlineCommand;
import Mattbot.Command.AddEventCommand;
import Mattbot.Command.AddTodoCommand;
import Mattbot.Command.WrongCommand;
import Mattbot.Errors.ErrorExceptions;

/**
 * Represents the bridge that calls the appropriate add task command.
 */
public class ParseAddTask extends Parse {
    /**
     * Decomposes the user input and pass to the specific add task commands depending on what
     * type of task is called.
     * Returns the added task message.
     *
     * @param s task name.
     * @param input user input.
     * @return String task added message.
     * @throws ErrorExceptions when the task cannot be created.
     */
    public static String execute2(String s, String input) throws ErrorExceptions {
        String current = s;
        if (current.equals("todo")) {
            return AddTodoCommand.execute2(input);
        } else if (current.equals("deadline")) {
            return AddDeadlineCommand.execute2(input);
        } else if (current.equals("event")) {
            return AddEventCommand.execute2(input);
        } else {
            return WrongCommand.execute2();
        }
    }
}
