package duke;

import java.util.Scanner;

/**
 * This class is the one handling all the parsing of user input
 * and return the appropriate enum command. Any invalid input
 * will return an invalid command enum type.
 */
public class InputParser {

    private Scanner myObj;
    private Ui ui;

    public InputParser() {
        // Initialise the Scanner object to get input from user
        this.myObj = new Scanner(System.in);
        this.ui = new Ui();
    }

    /**
     * Reads and returns the input from user
     * as a string.
     *
     * @return String input from user
     */
    public String getInput() {
        return myObj.nextLine().trim();
    }

    /**
     * Takes the given input and parses it to
     * return the appropriate command enum type.
     * @param input input from the user
     * @return CommandType type of command to be executed
     */
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

        if (input.startsWith("list ")) {
            if (input.equals("list all")) {
                return CommandType.LIST_ALL;
            }

            if (input.equals("list all done")) {
                return CommandType.LIST_ALL_DONE;
            }

            if (input.equals("list all not done")) {
                return CommandType.LIST_ALL_NOT_DONE;
            }

            if (input.equals("list todos")) {
                return CommandType.LIST_TODOS;
            }

            if (input.equals("list todos done")) {
                return CommandType.LIST_TODOS_DONE;
            }

            if (input.equals("list todos not done")) {
                return CommandType.LIST_TODOS_NOT_DONE;
            }

            if (input.equals("list deadlines")) {
                return CommandType.LIST_DEADLINES;
            }

            if (input.equals("list deadlines done")) {
                return CommandType.LIST_DEADLINES_DONE;
            }

            if (input.equals("list deadlines not done")) {
                return CommandType.LIST_DEADLINES_NOT_DONE;
            }

            if (input.equals("list events")) {
                return CommandType.LIST_EVENTS;
            }

            if (input.equals("list events done")) {
                return CommandType.LIST_EVENTS_DONE;
            }

            if (input.equals("list events not done")) {
                return CommandType.LIST_EVENTS_NOT_DONE;
            }
        }

        if (input.startsWith("find ")) {
            return CommandType.LIST_BY_KEYWORD;
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

    /**
     * Returns true if the input requests a task to be
     * created without a description.
     *
     * @param input input from user
     * @return boolean true if description is missing
     */
    public boolean isEmptyDescription(String input) {
        return input.split(" ").length == 1;
    }

    /**
     * Returns true if the input requests a deadline to
     * be created without a date and time.
     *
     * @param input input from user
     * @return boolean true if date and time is missing
     */
    public boolean hasDeadlineBy(String input) {
        return input.contains("/by")
                && input.split(" /by ").length == 2;
    }

    /**
     * Returns true if the input requests an event to
     * be created without a start and end date and time
     *
     * @param input input from user
     * @return boolean true if start and end date and time
     *         is missing
     */
    public boolean hasEventStartEndTime(String input) {
        return input.contains("/at")
                && input.split(" /at ").length == 2
                && input.split(" /at ")[1].split(" ").length == 2
                && input.split(" /at ")[1].split(" ")[1]
                .split("-").length == 2;
    }
}
