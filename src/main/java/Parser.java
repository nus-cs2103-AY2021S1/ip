import java.util.Scanner;

public class Parser {

    public Parser() {
    }

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

    private String getCommand(String command) {
        return command.split(" ")[0];
    }
}



