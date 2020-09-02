import java.util.Scanner;

/**
 * Parser class describes the behaviour of a
 * parser that parses commands entered by the client
 */
public class Parser {

    /**
     * Constructs a Parser instance.
     */
    public Parser() {
    }

    /**
     * Verifies whether the string entered
     * is a valid command. It will throw an exception if
     * the command is not valid
     * @param command The command string entered by the client.
     * @return The String, regarding the type of the command entered.
     * @throws DukeInvalidCommandException If the command is not valid.
     * @throws DukeIncompleteCommandException If the command is not complete
     */
    public String checkCommand(String command) throws DukeInvalidCommandException, DukeIncompleteCommandException {
        if (command.split(" ").length != 1) {
            return getCommand(command);
        }

        String wrongCommand = getCommand(command);
        if (wrongCommand.equals("todo") || wrongCommand.equals("deadline") || wrongCommand.equals("event")) {
            throw new DukeIncompleteCommandException(String.format(
                            "    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a %s cannot be empty.\n" +
                            "    ____________________________________________________________\n", wrongCommand));
        }

        else if (wrongCommand.equals("delete") || wrongCommand.equals("done")) {
            throw new DukeIncompleteCommandException(String.format(
                    "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please indicate the index of the task which you would like to %s.\n" +
                    "    ____________________________________________________________\n", wrongCommand.equals("done") ? "mark as done" : wrongCommand));
        }

        throw new DukeInvalidCommandException("    ____________________________________________________________\n" +
                                              "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                              "    ____________________________________________________________");
    }

    /**
     * Extracts the command from the string entered by the client.
     *
     * @param command The String containing the command entered by the client.
     * @return The String, regarding the command entered.
     */
    private String getCommand(String command) {
        return command.split(" ")[0];
    }

    protected String trimCommand(String task_type, String command) {
        return command.replace(task_type, "").trim();
    }
}



