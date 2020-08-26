import java.util.Arrays;

public enum Command {
    LIST(new String[]{"list", "ls"}, "Lists all tasks", ""),
    DONE(new String[]{"done"}, "Mark a task as done","<task number>"),
    DELETE(new String[]{"delete"}, "Delete a task","<task number>"),
    TODO(new String[]{"todo"}, "Create a todo task","<desc>"),
    DEADLINE(new String[]{"deadline"}, "Create a task with a deadline","<desc> /by <datetime>"),
    EVENT(new String[]{"event"}, "Create an event","<desc> /on <datetime>"),
    HELP(new String[]{"help"}, "Gets the list of commands", ""),
    BYE(new String[]{"bye", "exit"}, "Exits the program", ""),
    EMPTY(new String[]{""}, null, null);

    private final String[] names;
    private final String desc;
    private final String useCase;

    Command(String[] names, String desc, String useCase) {
        this.names = names;
        this.desc = desc;
        this.useCase = useCase;
    }

    public static Command getCommandType(String input) {
        if (input.isBlank()) {
            return EMPTY;
        }
        for (Command c : Command.values()) {
            for (String name : c.names) {
                if (name.equals(input)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static String getCommandList() {
        StringBuilder output = new StringBuilder("These are the commands in my dictionary:\n");
        for (Command c : Command.values()) {
            if (c != EMPTY) {
                output.append(String.format("    %-35s%s\n", Arrays.toString(c.names) + " " + c.useCase, c.desc));
            }
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }
}
