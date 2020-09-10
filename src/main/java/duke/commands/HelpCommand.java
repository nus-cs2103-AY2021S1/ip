package duke.commands;

import static duke.util.FormatChecker.checkHelpFormat;
import static duke.util.Keyword.KEYWORD_HELP_BYE;
import static duke.util.Keyword.KEYWORD_HELP_DEADLINE;
import static duke.util.Keyword.KEYWORD_HELP_DELETE;
import static duke.util.Keyword.KEYWORD_HELP_DISPLAY_MESSAGE;
import static duke.util.Keyword.KEYWORD_HELP_DONE;
import static duke.util.Keyword.KEYWORD_HELP_EVENT;
import static duke.util.Keyword.KEYWORD_HELP_FIND;
import static duke.util.Keyword.KEYWORD_HELP_LIST;
import static duke.util.Keyword.KEYWORD_HELP_REMIND;
import static duke.util.Keyword.KEYWORD_HELP_TODO;
import static duke.util.Keyword.KEYWORD_HELP_UNSURE;

import duke.exception.InvalidFormatHelpException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class HelpCommand extends Command {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatHelpException {
        checkHelpFormat(inputArr);
        return getListOfCommands();
    }

    /**
     * Generates the list of commands available in Duke
     *
     * @return A list of commands available in Duke
     */
    public String getListOfCommands() {
        return KEYWORD_HELP_UNSURE + KEYWORD_HELP_DISPLAY_MESSAGE + KEYWORD_HELP_LIST + KEYWORD_HELP_BYE
                + KEYWORD_HELP_TODO + KEYWORD_HELP_DELETE + KEYWORD_HELP_DONE + KEYWORD_HELP_FIND
                + KEYWORD_HELP_REMIND + KEYWORD_HELP_DEADLINE + KEYWORD_HELP_EVENT;
    }
}
