import java.util.regex.Matcher;
import java.util.regex.MatchResult;

public class Parser {
    private TaskList taskList;
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    public void parseAndRun(String input) throws DukeException {
        for (Command command : Command.values()) {
            Matcher matcher = command.matcher(input);
            if (matcher == null) continue;
            // Should we throw a more specific error here?
            if (!matcher.find()) throw command.matchError();
            int count = matcher.groupCount();
            String[] args = new String[count];
            for (int i=1;i<=count;i++) {
                args[i-1] = matcher.group(i);
            }
            command.dispatch(this.taskList, args);
            return;
        }
        // if we are here means no command has matched
        throw new DukeException(DukeException.Errors.UNKNOWN_COMMAND);
    }
}
