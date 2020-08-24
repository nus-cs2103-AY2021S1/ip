package duke.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Stores all the commands to be run.
 */
public enum Command {
    TODO(CommandLibrary.todoCommand,
            DukeException.Errors.TODO_EMPTY_DESCRIPTION,
            "(.*)",
            "todo"),
    DEADLINE(CommandLibrary.deadlineCommand,
            DukeException.Errors.DEADLINE_BAD_FORMAT,
            "(.*?) /by (.*)",
            "deadline"),
    EVENT(CommandLibrary.eventCommand,
            DukeException.Errors.EVENT_BAD_FORMAT,
            "(.*) /at (.*)",
            "event"),
    LIST(CommandLibrary.listCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "",
            "list"),
    BYE(CommandLibrary.byeCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "",
            "bye"),
    // TODO add more specific errors for these two below
    DONE(CommandLibrary.doneCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "(\\d+)",
            "done"),
    DELETE(CommandLibrary.deleteCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "(\\d+)",
            "delete"),
    FIND(CommandLibrary.findCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "(.*)",
            "find");
    private final CommandExecutable exec;
    private final DukeException.Errors matchError;
    private final Pattern format;
    private final String name;

    Command(CommandExecutable exec, DukeException.Errors matchError, String formatString, String name) {
        this.exec = exec;
        this.format = Pattern.compile(formatString);
        this.matchError = matchError;
        this.name = name;
    }

    /**
     * Parses the String input and returns a Optional.of(regex.Matcher) if there is a match,
     * otherwise returns Optional.empty(). The matcher will be configured to return the arguments
     * to the Command in its capture groups. The regex used to match can be found in the Command enum.
     * @param rawInput String raw input from the user.
     * @return Optional regex.Matcher if there is a match, else empty().
     */
    public Optional<Matcher> matcher(String rawInput) {
        if (!rawInput.startsWith(this.name)) {
            return Optional.empty();
        }
        Matcher matcher = this.format.matcher(rawInput.substring(this.name.length()).trim());
        return Optional.of(matcher);
    }

    public void dispatch(TaskList taskList, Ui ui, String[] args) throws DukeException {
        this.exec.run(taskList, ui, args);
    }

    public DukeException matchError() {
        return this.matchError.create();
    }
}
