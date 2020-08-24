import java.util.regex.Matcher;
import java.util.Optional;

public class Parser {
    private TaskList taskList;
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    public void parseAndRun(String input) throws DukeException {
        for (Command command : Command.values()) {
            Optional<Matcher> maybeMatcher = command.matcher(input);
            if(maybeMatcher.isEmpty()) continue;
            Matcher matcher = maybeMatcher.get();
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
        throw DukeException.Errors.UNKNOWN_COMMAND.create();
    }
}
