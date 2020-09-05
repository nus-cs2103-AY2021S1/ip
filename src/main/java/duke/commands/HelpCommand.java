package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class HelpCommand extends Command {
    private static final String EXTRA_SPACE = "    ";
    private static final String unsureString = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private static final String listingString = "Here are the list of commands available:\n";
    private static final String S1 = "1. list\n";
    private static final String S2 = "2. bye\n";
    private static final String S3 = "3. todo \'task name\' (e.g. todo task 1)\n";
    private static final String S4 = "4. delete ___ (e.g. delete 1)  *Note that it should be a value more than 0*\n";
    private static final String S5 = "5. done ___ (e.g. done 1)  *Note that it should be a value more than 0*\n";
    private static final String S6 = "6. find ___ (e.g. find book) *Note that only 1 keyword is allowed*\n";
    private static final String S7 = "7. remind _ _ (e.g.remind 1 y)" + "\n" + EXTRA_SPACE
            + " *Sets reminder on task 1 in task list , y or n represents yes or no respectively*\n";
    private static final String S8 = "8. deadline \'task name\' /by \'any date format\' " + "\n" + EXTRA_SPACE
            + "(e.g. deadline project /by YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    private static final String S9 = "9. event \'event name\' /at \'any date format\' " + "\n" + EXTRA_SPACE
            + "(e.g. event project /at YYYY-MM-DD HHMM or YYYY-MM-DD)\n";

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
        return unsureString + listingString + S1 + S2 + S3 + S4 + S5 + S6 + S7 + S8 + S9;
    }
}
