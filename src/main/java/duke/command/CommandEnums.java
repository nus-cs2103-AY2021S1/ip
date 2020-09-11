package duke.command;

import duke.exceptions.DukeCommandException;
import duke.exceptions.DukeException;
import duke.tasks.TaskManager;
import duke.ui.UserInterface;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandEnums {
    TODO(CommandList.TODO , "(.*)" , "todo"),
    DEADLINE(CommandList.DEADLINE, "(.*) -by (.*)", "deadline"),
    EVENT(CommandList.EVENT , "(.*) -at (.*)" , "event"),
    HELP(CommandList.HELP, "(.*)", "help"),
    LIST(CommandList.LIST, "" , "list"),
    FIND(CommandList.FIND, "(.*)" , "find"),
    DELETE(CommandList.DELETE , "(\\d+)" , "delete"),
    DONE(CommandList.DONE, "(\\d+)" , "done"),
    BYE(CommandList.BYE , "" , "bye");
    
    private final CommandExecuter executer;
    private final Pattern format;
    private final String name;
    
    CommandEnums(CommandExecuter executer, String formatString, String name) {
        this.executer = executer;
        this.format = Pattern.compile(formatString);
        this.name = name;
    }

    /**
     * Parse string output to return a optional.of(regex.Matcher) if there is a match
     * Otherwise, returns Optional.empty(). Matcher is configured to return the arguments to the Command
     * in its Capture Groups.
     * @param rawInput The user input
     * @return Optional regex Match if exists, else empty
     */
    public Optional<Matcher> matcher(String rawInput) {
        if (!rawInput.startsWith(this.name)) {
            return Optional.empty();
        }
//        System.out.print(this.name);
        Matcher matcher = this.format.matcher(rawInput.substring(this.name.length()).trim());
        return Optional.of(matcher);
    }

    /**
     * Executes the task using the taskmanager and user interface
     * @param taskManager stored taskManager in parser
     * @param ui User Interface
     * @param args String argument
     * @throws DukeException an Exception in the internal commandlist
     */
    public void execute(TaskManager taskManager, UserInterface ui, String[] args) throws DukeException{
        this.executer.run(taskManager, ui , args);
    }

    /**
     * Input given that is known to be wrong
     * @param input userinput
     * @return Error Message
     */
    public DukeCommandException commandError(String input) {
        return new DukeCommandException(input);
    }
}
