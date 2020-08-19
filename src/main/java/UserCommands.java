import java.util.Arrays;

public class UserCommands {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";

    public static UserCommandType parseUserCommand(String command) throws InvalidCommandException {
        if (command.equals(LIST_COMMAND)) {
            return UserCommandType.LIST;
        } else if (command.equals(EXIT_COMMAND)) {
            return UserCommandType.EXIT;
        } else if (command.startsWith(DONE_COMMAND)) {
            return UserCommandType.DONE;
        } else if (command.startsWith(TODO_COMMAND)) {
            return UserCommandType.TODO;
        } else if (command.startsWith(DEADLINE_COMMAND)) {
            return UserCommandType.DEADLINE;
        } else if (command.startsWith(EVENT_COMMAND)) {
            return UserCommandType.EVENT;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static String[] parseTask(String command) throws InvalidCommandException {
        String[] components = command.split(" ");
        String taskType = components[0];

        if (taskType.equals(TODO_COMMAND)) {
            if (components.length < 2) {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
            return new String[]{
                    String.join(" ", Arrays.copyOfRange(components, 1, components.length))
            };
        } else if (taskType.equals(DEADLINE_COMMAND) || taskType.equals(EVENT_COMMAND)) {
            for (int i = 0; i < components.length - 1; i++) {
                if (components[i].equals("/at") || components[i].equals("/by")) {
                    return new String[]{ // Task name, Date
                            String.join(" ", Arrays.copyOfRange(components, 1, i)),
                            String.join(" ", Arrays.copyOfRange(components, i + 1, components.length))
                    };
                }
            }
            throw new InvalidCommandException("No date provided");
        } else {
            throw new InvalidCommandException();
        }
    }

    public static int getDoneIndex(String command) throws InvalidCommandException {
        String[] components = command.split(" ");
        if (components.length != 2) {
            throw new InvalidCommandException("Done command should have 2 components");
        }
        return Integer.parseInt(components[1]) - 1;
    }

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super("☹ OOPS!!! " + errorMessage);
        }

        public InvalidCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
