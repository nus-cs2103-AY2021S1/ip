import exception.UnknownCommandException;

import java.util.HashMap;

public class DukeCommandSet {

    private static DukeCommandSet instance;
    public static DukeCommandSet getInstance() {
        if (instance == null) {
            instance = new DukeCommandSet();
        }

        return instance;
    }

    private HashMap<String, Command> commandSet;

    public DukeCommandSet() {
        commandSet = new HashMap<>();

        registerCommand(new ExitCommand());
        registerCommand(new ListCommand());
        registerCommand(new DoneCommand());
        registerCommand(new TodoCommand());
        registerCommand(new DeadlineCommand());
        registerCommand(new EventCommand());
    }

    private void registerCommand(Command command) {
        for (String name : command.names) {
            commandSet.put(name, command);
        }
    }

    public Command getCommand(String commandName) throws UnknownCommandException {
        if (!commandSet.containsKey(commandName)) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line;

            throw new UnknownCommandException(errMessage);
        }

        return commandSet.get(commandName);
    }
}
