package Duke.Helpers;

import Duke.Commands.*;

/**
 * This is a Parser class that determines which command operation to choose, which in turn determines
 * the action to be taken.
 */
public class Parser {
    /**
     * Returns a Command, depending on the string being input
     *
     * @param string where the first words determines command to be returned
     * @return Command is returned based on the first word of param string
     */
    public static Command parse(String string)  {
        assert !string.contains(",");//to ensure that no , appears when multiple inputs are given
        if (string.length() >= 3 && string.equals("bye")) { //represents ExitCommand
            return new ExitCommand(string);
        } else if (string.length() >= 4 && string.equals("list")) { //represents ListCommand
            return new ListCommand(string);
        } else if (string.length() >= 6 && string.substring(0, 6).equals("delete")) { //represents deleteCommand
            return new DeleteCommand(string);
        } else if (string.length() >= 4 && string.substring(0, 4).equals("done")) { //represents doneCommand
            return new DoneCommand(string);
        } else if (string.length() >= 4 && string.substring(0, 4).equals("todo")) { //represents ToDoCommand
            return new TodoCommand(string);
        } else if (string.length() >= 5 && string.substring(0, 5).equals("event")) { //represents EventCommand
            return new EventCommand(string);
        } else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) { //represents DeadlineCommand
            return new DeadlineCommand(string);
        } else if (string.length() >= 4 && string.substring(0, 4).equals("find")) { //represents FindCommand
            return new FindCommand(string);
        } else { //rest are RandomCommand
            return new RandomCommand(string);
        }
    }
}
