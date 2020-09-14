package duke;

import java.util.Optional;
import java.util.regex.Matcher;

import duke.command.CommandEnums;
import duke.exceptions.DukeCommandException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIoException;
import duke.tasks.TaskManager;
import duke.tasks.TextParser;
import duke.ui.UserInterface;



/**
 * Backend Object Class for the duke.Duke Chatbot Interface
 */
public class Duke {
    private static final TextParser textParser = new TextParser();
    private final TaskManager taskManager;
    private final UserInterface ui;
    /**
     * Constructor for the duke.Duke Chatbot, if is old initialisation, will read from txt file
     * Eles it will initialise a new TaskManager class
     * @param ui The user interface to use.
     */

    public Duke(UserInterface ui) {
        assert ui != null : "ui will never be null";
        TaskManager list1;
        try {
            list1 = new TaskManager(System.getProperty("user.dir"));
        } catch (DukeIoException e) {
            list1 = new TaskManager(System.getProperty("user.dir"), true);
        }
        taskManager = list1;
        this.ui = ui;
        this.ui.start("Friend");
    }
    private void parseRun(String input) throws DukeException {
        for (CommandEnums cmd : CommandEnums.values()) {
            Optional<Matcher> maybeMatcher = cmd.matcher(input);
            if (maybeMatcher.isEmpty()) {
                continue;
            }
            Matcher match = maybeMatcher.get();
            if (!match.find()) {
                throw cmd.commandError(input);
            }
            int count = match.groupCount();
            String[] arguments = new String[count];
            for (int i = 1; i <= count; i++) {
                arguments[i - 1] = match.group(i);
            }
            cmd.execute(taskManager, ui, arguments);
            return;
        }
        throw new DukeCommandException(input);
    }

    /**
     * Runs the Next Iteration of Duke
     * To be invoked to iterate the continuation of Duke Application flow.
     */
    public void nextIteration() {
        String input = textParser.cleanInput(ui.nextLine());
        try {
            parseRun(input);
        } catch (DukeException e) {
            ui.systemMessage(e.toString());
        }
    }

    @Override
    public String toString() {
        return "Duke{"
                + "taskManager=" + taskManager
                + ", ui=" + ui
                + '}';
    }
}
