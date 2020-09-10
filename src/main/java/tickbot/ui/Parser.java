package tickbot.ui;

/**
 * The class to represent the command parser.
 */
public class Parser {
    private Runner runner = new Runner();

    /**
     * Executes a command.
     * @param command the command to execute.
     */
    public boolean executeCommand(String command) {
        if (command.isBlank()) {
            return true; // empty line, continue inputing
        }
        String[] args = command.split("\\s+");
        assert args.length > 1; // command must have a name
        switch (args[0]) {
        case "bye": {
            Output.printMessage("See you next time!");
            return false; // end of input
        }
        case "help": {
            runner.help(args);
            break;
        }
        case "done": {
            runner.done(args);
            break;
        }
        case "delete": {
            runner.delete(args);
            break;
        }
        case "list": {
            runner.list(args);
            break;
        }
        case "todo": {
            runner.todo(args);
            break;
        }
        case "deadline": {
            runner.deadline(args);
            break;
        }
        case "event": {
            runner.event(args);
            break;
        }
        case "find": {
            runner.find(args);
            break;
        }
        case "tag": {
            runner.tag(args);
            break;
        }
        default: {
            Output.printMessage("Unknown command " + args[0] + ".");
        }
        }
        return true; // continue inputing
    }
}
