import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeParseException;
/**
 * Parses input by the user and identifies the commands.
 */
public class Parser {
    /**
     * Parses the input by the user in the user interface.
     * @param currInput the current input of the user.
     * @param sizeOfList The current size of the task list.
     * @return int A number that represents the status of the input.
     * @throws InvalidCommandException If command is of invalid format.
     */
    public int parse(String currInput, int sizeOfList) throws InvalidCommandException{
        //Cuts white space before and after the command
        String input = currInput.trim();

        if (isListCommand(input)) {
            return 1;
        } else if (isDoneCommand(input)) {
            verifyDoneCommand(input, sizeOfList);
            return 2;
        } else if (isDeleteCommand(input)) {
            verifyDeleteCommand(input, sizeOfList);
            return 3;
        } else if (isFindCommand(input)) {
            verifyFindCommand(input);
            return 4;
        } else {
            //Is a task command
            return 5;
        }
    }

    /**
     * Retrieves the index for a delete or done command in the input string.
     * @param input The user input.
     * @return int The index of the task which the user wants to delete/mark as done.
     */
    public int getIndex(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Retrieves the keyword for a find command in the input string.
     * @param input The user input.
     * @return String The keyword that the user is looking for.
     */
    public String getKeyword(String input) {
        return input.trim().split(" ", 2)[1].trim();
    }

    /**
     * Translates a task command to a Task object.
     * @param input The user input.
     * @return Task The task to be put in the schedule.
     * @throws InvalidInputException If input is not a valid command.
     * @throws InvalidCommandException If command is of invalid format.
     */
    public Task getTask(String input) throws InvalidInputException, InvalidCommandException {
        String[] parts = input.split(" ", 2);

        try {
            if (input.startsWith("todo")) {
                //Verify the todo command
                verifyTodo(input);

                return new Task(parts[1]);
            } else if (input.startsWith("deadline")) {
                //Verify the deadline command has the format "deadline ___ /by ___
                verifyDeadline(input);

                //Split into description and date, time info
                String[] split = parts[1].split("/by");

                //Should be of format yyyy-mm-dd x:x
                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);

                return new Deadline(split[0], date, time);
            } else if (input.startsWith("event")) {
                //Verify the event command has the format "event ___ /at ___
                verifyEvent(input);

                //Split into description and date, time info
                String[] split = parts[1].split("/at");

                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);

                return new Event(split[0], date, time);
            } else {
                throw new InvalidInputException("Sorry, I don't know what that means :(");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (InvalidInputException e) {
            throw e;
        }
    }

    private static void verifyTodo(String input) throws InvalidCommandException{
        String[] parts = input.split(" ");

        if (parts.length <= 1) {
            throw new InvalidCommandException("Sorry, the description of a todo cannot be empty :(");
        }
    }

    private static void verifyDeadline(String input) throws InvalidCommandException {
        if (input.contains(" /by ")) {

            //Checks if there is a spacing after the deadline word
            if (input.charAt(8) == ' ') {
                String[] parts = input.split(" ", 2);

                //Parts 1 should include the description + " /by " + date and time info
                if (parts[1].startsWith("/by")) {
                    throw new InvalidCommandException("Sorry, missing deadline description :(");
                } else {
                    //Split into description and date, time info
                    String[] split = parts[1].split("/by");

                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing deadline time and date :(");
                    } else if (split[0].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing deadline description :(");
                    }
                }
            } else {
                throw new InvalidCommandException("Sorry please leave a space after the deadline command!");
            }
        } else {
            throw new InvalidCommandException("Sorry, missing /by keyword, make sure to leave a space before " +
                    "and after the /by keyword!");
        }
    }

    private static void verifyEvent(String input) throws InvalidCommandException {
        if (input.contains(" /at ")) {

            //Checks is there is a space after event keyword
            if (input.charAt(5) == ' ') {

                //Split into description + " /at " + date, time info
                String[] parts = input.split(" ", 2);

                if (parts[1].startsWith("/at")) {
                    throw new InvalidCommandException("Sorry, missing event description :(");
                } else {

                    //Split into description and date, time info
                    String[] split = parts[1].split("/at");

                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing event date and time :(");
                    } else if (split[0].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing event description :(");
                    }
                }
            } else {
                throw new InvalidCommandException("Sorry please leave a space after the event command!");
            }
        } else {
            throw new InvalidCommandException("Sorry, missing /at keyword, make sure to leave a space before " +
                    "and after the /at keyword!");
        }
    }

    private static boolean isDeleteCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("delete") && parts.length == 2;
    }

    private static void verifyDeleteCommand(String input, int numOfTasks) throws InvalidCommandException {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean numInRange = index > -1 && index < numOfTasks;

            if (!numInRange) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the delete command!"));
        }
    }

    //Checks if starts with done and is two parts
    private static boolean isDoneCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("done") && parts.length == 2;
    }

    //Checks if number input is within taskList range and valid
    private static void verifyDoneCommand(String input, int numOfTasks) throws InvalidCommandException {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean numInRange = index > -1 && index < numOfTasks;

            if (!numInRange) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the done command!"));
        }
    }

    //Checks if
    private static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    private static void verifyFindCommand(String input) throws InvalidCommandException{
        if (input.length() == 4 || input.split(" ").length < 2) {
            throw new InvalidCommandException("find what?");
        } else if (!(input.charAt(4) == ' ')) {
            throw new InvalidCommandException("Please make sure there is a space after the find keyword!");
        }
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Checks if input is "bye" which is the terminating command
     * @param input The user input.
     * @return boolean True if command is equals to "bye", returns false otherwise.
     */
    public boolean isTerminateCommand(String input) {
        return input.equals("bye");
    }

    private static LocalDate getDate(String string) throws InvalidCommandException {
        //Currently only accepts date in yyyy-mm-dd format
        try {
            //Removing the whitespace before and after the string
            String timeDate = string.trim();

            //Split into date + time
            String[] parts = timeDate.split(" ", 2);

            if (parts.length < 2) {
                throw new InvalidCommandException("Missing time");
            }

            String date = parts[0].trim();

            return LocalDate.parse(date);
        } catch (DateTimeParseException e ) {
            throw new InvalidCommandException("Please give your date in yyyy-mm-dd format!");
        }
    }

    private static LocalTime getTime(String string) throws InvalidCommandException {
        //Currently only accepts time in x:x format
        try {
            //Removing the whitespace before and after the string
            String timeDate = string.trim();

            //Split into date + time
            String[] parts = timeDate.split(" ", 2);

            if (parts.length < 2) {
                throw new InvalidCommandException("Missing time");
            }

            String date = parts[1].trim();

            return LocalTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please give your time in hh:mm format!");
        }
    }
}
