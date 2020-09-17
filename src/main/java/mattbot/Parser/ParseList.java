package mattbot.Parser;

import mattbot.Command.ListCommand;

/**
 * Represents the bridge that calls the appropriate list task command.
 */
public class ParseList extends Parse {

    /**
     * Calls the list command.
     * Returns all the currently tracked tasks.
     *
     * @return String all current tasks.
     */
    public static String execute2() {
        return ListCommand.execute2();
    }
}
