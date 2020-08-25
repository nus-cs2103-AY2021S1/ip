import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class HelpCommand extends Command {
    private static List<Command> allCommands = Arrays.asList(
            new ByeCommand(),
            new DeadlineCommand("dummy value", new Date()),
            new DeleteCommand(0),
            new DoneCommand(0),
            new EventCommand("dummy value", new Date()),
            new ListCommand(),
            new ToDoCommand("dummy value")
    );
    
    HelpCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        response.add("Here are all the available commands:");
        for (Command command : allCommands) {
            response.add(command.toString());
        }
        ui.printResponse(response.toString());
    }
}
