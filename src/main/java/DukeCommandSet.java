package main.java;

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

        registerCommand(new Exit());
    }

    private void registerCommand(Command command) {
        for (String name : command.names) {
            commandSet.put(name, command);
        }
    }

    public Command getCommand(String commandName) {
        return commandSet.get(commandName);
    }
}
