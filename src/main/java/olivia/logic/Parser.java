package olivia.logic;

import olivia.command.Command;
import olivia.command.DeadlineCommand;
import olivia.command.DeleteCommand;
import olivia.command.DoneCommand;
import olivia.command.EventCommand;
import olivia.command.ExitCommand;
import olivia.command.FindCommand;
import olivia.command.ListCommand;
import olivia.command.ToDoCommand;
import olivia.command.UpdateCommand;
import olivia.resource.Wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;

/**
 * Parser class that parses the user input.
 */

public class Parser {

    private final Wrapper wrapper;

    public Parser(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public static final Map<String, Command> COMMANDS = Map.of(
            "bye", new ExitCommand(),
            "done", new DoneCommand(),
            "find", new FindCommand(),
            "list", new ListCommand(),
            "todo", new ToDoCommand(),
            "event", new EventCommand(),
            "delete", new DeleteCommand(),
            "update", new UpdateCommand(),
            "deadline", new DeadlineCommand()
    );

    public String parse(String fullCommand) {
        String[] parsedString = fullCommand.split(" ");
        String command = parsedString[0];
        if (Optional.ofNullable(COMMANDS.get(command)).isEmpty()) {
            return "I'm sorry, I don't know what that means...";
        }
        List<String> inputs = new ArrayList<>(List.of(parsedString));
        inputs.remove(0);
        return COMMANDS.get(command).apply(this.wrapper, inputs);
    }

}
