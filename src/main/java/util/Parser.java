package util;

import java.time.LocalDate;

/**
 * Deals with making sense of the user command
 * */
public class Parser {

    /**
     * Parses the provided user input into something Duke understands.
     *
     * @param userInput String containing the user's input.
     * @return The command interpreted.
     * @throws DukeException  If input is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        // Check if it is a single word command
        if (userInput.equals("bye")) {
            System.out.println();
            return Command.EXIT;
        } else if (userInput.equals("list")) {
            return Command.LIST;
        }

        // Process input
        String[] instructions = userInput.split(" ", 2);
        // Check command
        if (instructions[0].equals("todo")) {
            // Command for to do Tasks creation
            // Add check for command details
            if (hasCmdDetails(instructions)) {
                // If command has details, return command to add to do
                return Command.TODO;
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        } else if (instructions[0].equals("deadline")) {
            // Command: "deadline <taskName> /by <deadline>"
            if (hasCmdDetails(instructions)) {
                // Extract Details from command
                String[] details = instructions[1].split(" /by ", 2);

                // Check if have valid deadline
                if (hasCmdDetails(details)) {
                    checkDate(details[1]);
                    return Command.DEADLINE;
                } else {
                    // Something wrong with command, throw exception
                    throw new DukeException(wrongCmdMsg(instructions[0]));
                }
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }

        } else if (instructions[0].equals("event")) {
            // Command: "deadline <taskName> /at <event>"
            if (hasCmdDetails(instructions)) {
                // Extract Details from command
                String[] details = instructions[1].split(" /at ", 2);

                // Check if have valid event time
                if (hasCmdDetails(details)) {
                    return Command.EVENT;
                } else {
                    // Something wrong with command, throw exception
                    throw new DukeException(wrongCmdMsg(instructions[0]));
                }
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        } else if (instructions[0].equals("done")) {
            // Command: "done <task>"
            // If valid <task>, mark done
            if (hasCmdDetails(instructions)) {
                try {
                    Integer.parseInt(instructions[1]);
                    // Only add if task number is a valid number
                    return Command.DONE;
                } catch (NumberFormatException e) {
                    throw new DukeException("Sorry, I don't think that's a valid number...");
                }
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        } else if (instructions[0].equals("delete")) {
            // Command: "delete <taskNumber>"
            // If valid <taskNumber>, remove from list
            if (hasCmdDetails(instructions)) {
                try {
                    Integer.parseInt(instructions[1]);
                    // Only delete if task number is a valid number
                    return Command.DELETE;
                } catch (NumberFormatException e) {
                    throw new DukeException("Sorry, I don't think that's a valid number...");
                }
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        } else if (instructions[0].equals("find")) {
            // Command: "find <keyword>"
            if (hasCmdDetails(instructions)) {
                return Command.FIND;
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        } else if (instructions[0].equals("update")) {
            // Command: "done <task>"
            // If valid <task>, mark done
            if (hasCmdDetails(instructions)) {
                try {
                    Integer.parseInt(instructions[1]);
                    // Only add if task number is a valid number
                    return Command.UPDATE;
                } catch (NumberFormatException e) {
                    throw new DukeException("Sorry, I don't think that's a valid number...");
                }
            } else {
                // Something wrong with command, throw exception
                throw new DukeException(wrongCmdMsg(instructions[0]));
            }
        }
        return Command.INVALID;
    }

    /**
     * Checks whether command has follow-up details.
     *
     * @param cmd String array with the command sectioned out.
     * @return True/False value of whether the command is complete.
     * @throws DukeException  If command has missing data.
     */
    public static boolean hasCmdDetails(String[] cmd) throws DukeException {
        try {
            if (cmd[1].equals("") || cmd[1].trim().length() == 0) {
                // Make sure command has follow up details
                // Check if there is second word
                // Check if second word is just whitespace
                // If so, command has no details
                return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            // Check if there is even anything after command
            // Exception thrown if userInput.split() fails
            // Command has no details, throw exception
            throw new DukeException("Whoops! I think you forgot to finish your command. "
                    + "Sorry but I need it. D:");
        }
    }

    /**
     * Checks that the String is in the right date format.
     *
     * @param dateString String that may be a LocalDate.
     * @throws DukeException  If dateString is not a LocalDate.
     */
    public static void checkDate(String dateString) throws DukeException {
        try {
            LocalDate df = LocalDate.parse(dateString);
        } catch (Exception e) {
            // Check if string can be recognized as a valid LocalDate
            // If can't, print out error message
            throw new DukeException("Whoops! I think there is an error in your date."
                    + "\nThe format is 'YYYY-MM-DD'."
                    + "\nPlease Try Again!");
        }
    }

    /**
     * Generates and returns wrong command exception message.
     *
     * @param command Command string that went wrong.
     */
    private static String wrongCmdMsg(String command) {
        return "Whoops! Something went wrong and I can't process your "
                + command + " command. Sorry! D:";
    }
}
