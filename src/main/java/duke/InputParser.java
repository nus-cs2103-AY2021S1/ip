package duke;

import java.util.Scanner;

public class InputParser {

    private Scanner myObj;
    private Ui ui;

    public InputParser() {
        // Initialise the Scanner object to get input from user
        this.myObj = new Scanner(System.in);
        this.ui = new Ui();
    }

    public String getInput() {
        return myObj.nextLine().trim();
    }

    public CommandType parseInput(String input) {
        if (input.isEmpty()) {
            return CommandType.INVALID_IS_EMPTY;
        }

        if (input.equals("help")) {
            return CommandType.HELP;
        }

        if (input.startsWith("todo")) {
            if (isEmptyDescription(input)) {
                return CommandType.INVALID_EMPTY_DESCRIPTION;
            }
            return CommandType.TODO;
        }

        if (input.startsWith("deadline")) {
            if (isEmptyDescription(input)) {
                return CommandType.INVALID_EMPTY_DESCRIPTION;
            }
            if (!hasDeadlineBy(input)) {
                return CommandType.INVALID_DEADLINE_NO_BY;
            }
            return CommandType.DEADLINE;
        }

        if (input.startsWith("event")) {
            if (isEmptyDescription(input)) {
                return CommandType.INVALID_EMPTY_DESCRIPTION;
            }
            if (!hasEventStartEndTime(input)) {
                return CommandType.INVALID_EVENT_NO_START_END;
            }
            return CommandType.EVENT;
        }

        if (input.equals("list")) {
            return CommandType.LIST;
        }

        if (input.startsWith("done ")) {
            return CommandType.DONE;
        }

        if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        }

        if (input.equals("bye")) {
            return CommandType.BYE;
        }

        return CommandType.INVALID_COMMAND;
    }

    public boolean isValidCommand(String input) {
        return input.equals("help")
                || input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event")
                || input.equals("list")
                || input.startsWith("done")
                || input.startsWith("delete")
                || input.equals("bye");
    }

    public boolean isEmptyDescription(String input) {
        return input.split(" ").length == 1;
    }

    public boolean hasDeadlineBy(String input) {
        return input.contains("/by")
                && input.split(" /by ").length == 2;
    }

    public boolean hasEventStartEndTime(String input) {
        return input.contains("/at")
                && input.split(" /at ").length == 2
                && input.split(" /at ")[1].split(" ").length == 2
                && input.split(" /at ")[1].split(" ")[1]
                .split("-").length == 2;
    }
}
