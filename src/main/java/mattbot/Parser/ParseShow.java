package mattbot.Parser;

import mattbot.Command.ShowFunctionCommand;

/**
 * Represents the bridge that calls the appropriate show all commands command.
 */
public class ParseShow extends Parse {

    /**
     * Calls the show command.
     * Returns all existing commands and the syntax.
     *
     * @return String command list.
     */
    public static String execute2() {
        return ShowFunctionCommand.execute2();
    }
}
