package duke.commands;

import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatEventException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class HelpCommand extends Command {
    private static final String unsureString = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private static final String listingString = "Here are the list of commands available:\n";
    private static final String s1 = "1. list\n";
    private static final String s2 = "2. bye\n";
    private static final String s3 = "3. todo \'task name\' (e.g. todo task 1)\n";
    private static final String s4 = "4. deadline \'task name\' /by \'any date format\' "
            + "(e.g. deadline project /by YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    private static final String s5 = "5. event \'event name\' /at \'any date format\' "
            + "(e.g. event project /at YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    private static final String s6 = "6. delete ___ (e.g. delete 1)  *Note that it should be a value more than 0*\n";
    private static final String s7 = "7. done ___ (e.g. done 1)  *Note that it should be a value more than 0*\n";
    private static final String s8 = "8. find ___ (e.g. find book) *Note that only 1 keyword is allowed*";
    /**
     * Creates a HelpCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public HelpCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return getListOfCommands();
    }

    /**
     * Generates the list of commands available in Duke
     * 
     * @return A list of commands available in Duke
     */
    public String getListOfCommands() {
        return unsureString + listingString + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
    }
    
}
