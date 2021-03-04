package olivia.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

/**
 * Parser class that parses the user input, finds the corresponding
 * command, and returns an output String to be printed by the GUI.
 */

public class Parser {

    /** Hashmap that maps an input string to a Command object */
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

    /** Wrapper that contains Olivia's Storage, TaskList and Ui */
    private final Wrapper wrapper;

    /**
     * Constructor that creates a Parser object with Olivia's current Wrapper..
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     */

    public Parser(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    /**
     * Parses the input String from the user and activates the corresponding command.
     * @param fullCommand input String from the user.
     * @return output String to the user.
     */

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
