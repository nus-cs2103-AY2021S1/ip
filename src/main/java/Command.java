import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    TODO (Todo::todoCommand,
            DukeException.Errors.TODO_EMPTY_DESCRIPTION,
            "(.*)",
            "todo"),
    DEADLINE (Deadline::deadlineCommand,
            DukeException.Errors.DEADLINE_BAD_FORMAT,
            "(.*?) /by (.*)",
            "deadline"),
    EVENT (Event::eventCommand,
            DukeException.Errors.EVENT_BAD_FORMAT,
            "(.*) /at (.*)",
            "event"),
    LIST (TaskList::listCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "",
            "list"),
    // TODO add more specific errors for these two below
    DONE (TaskList::doneCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "(\\d+)",
            "done"),
    DELETE (TaskList::deleteCommand,
            DukeException.Errors.UNKNOWN_COMMAND,
            "(\\d+)",
            "delete");
    private CommandExecutable exec;
    private DukeException.Errors matchError;
    private Pattern format;
    private String name;

    Command(CommandExecutable exec, DukeException.Errors matchError, String formatString, String name) {
        this.exec = exec;
        this.format = Pattern.compile(formatString);
        this.matchError = matchError;
        this.name = name;
    }

    Matcher matcher(String rawInput) {
        if(!rawInput.startsWith(this.name)) return null;
        return this.format.matcher(rawInput.substring(this.name.length()).trim());
    }

    public void dispatch(TaskList taskList, String[] args) throws  DukeException {
        this.exec.run(taskList, args);
    }

    public DukeException matchError(){
        return new DukeException(this.matchError);
    }
}
