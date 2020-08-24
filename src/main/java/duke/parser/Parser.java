package duke.parser;

import java.util.Optional;
import java.util.regex.Matcher;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Parser.
     * @param taskList TaskList to pass to commands.
     * @param ui Ui object that commands will use.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the String input, then runs the respective Command (if input is valid)
     * using the Parser object's internal TaskList and Ui objects.
     * @param input String raw input from user.
     * @throws DukeException If there are any parse errors, or Command has any errors.
     */
    public void parseAndRun(String input) throws DukeException {
        for (Command command : Command.values()) {
            Optional<Matcher> maybeMatcher = command.matcher(input);
            if (maybeMatcher.isEmpty()) {
                continue;
            }
            Matcher matcher = maybeMatcher.get();
            // Should we throw a more specific error here?
            if (!matcher.find()) {
                throw command.matchError();
            }
            int count = matcher.groupCount();
            String[] args = new String[count];
            for (int i = 1; i <= count; i++) {
                args[i - 1] = matcher.group(i);
            }
            command.dispatch(this.taskList, this.ui, args);
            return;
        }
        // if we are here means no command has matched
        throw DukeException.Errors.UNKNOWN_COMMAND.create();
    }
}
