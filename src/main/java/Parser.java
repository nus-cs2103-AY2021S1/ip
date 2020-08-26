import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeParseException;

/**
 * Parses input by the user and identifies the commands.
 */
public class Parser {

    /**
     * Parses the input by the user in the user interface.
     * @param currInput the current of the user.
     * @return int A number that represents the status of the input.
     * @throws InvalidCommandException If command is of invalid format.
     * @throws InvalidInputException If input is not a valid command.
     */
    public static int parse(String currInput) throws InvalidCommandException, InvalidInputException{
        if (isListCommand(currInput)) {
            return 1;
        } else if (isDoneCommand(currInput)) {
            verifyDoneCommand(currInput, TaskList.size());
            return 2;
        } else if (isDeleteCommand(currInput)) {
            verifyDeleteCommand(currInput, TaskList.size());
            return 3;
            //Save the task list
        } else if (isTerminateCommand(currInput)) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * Translates a task command to a Task object.
     * @param input
     * @return Task The task to be put in the schdule.
     * @throws InvalidInputException If input is not a valid command.
     * @throws InvalidCommandException If command is of invalid format.
     */
    public static Task getTask(String input) throws InvalidInputException, InvalidCommandException {
        String[] parts = input.split(" ", 2);

        try {
            if (input.startsWith("todo")) {
                //Verify the todo command
                verifyTodo(input);
                return new Task(parts[1]);
            } else if (input.startsWith("deadline")) {
                //Verify the deadline command
                verifyDeadline(input);
                String[] split = parts[1].split("/by");

                //Should be of format yyyy-mm-dd x:x
                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);
                return new Deadlines(split[0], date, time);
            } else if (input.startsWith("event")) {
                //Verify the event command
                verifyEvent(input);
                String[] split = parts[1].split("/at");
                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);
                return new Events(split[0], date, time);
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
            if (input.charAt(8) == ' ') {
                String[] parts = input.split(" ", 2);
                if (parts[1].startsWith("/by")) {
                    throw new InvalidCommandException("Sorry, missing deadline description :(");
                } else {
                    String[] split = parts[1].split("/by");
                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing deadline time :(");
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
            if (input.charAt(5) == ' ') {
                String[] parts = input.split(" ", 2);
                if (parts[1].startsWith("/at")) {
                    throw new InvalidCommandException("Sorry, missing event description :(");
                } else {
                    String[] split = parts[1].split("/at");
                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing event time :(");
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
            boolean result = index > -1 && index < numOfTasks;
            if (!result) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the delete command!"));
        }
    }

    private static boolean isDoneCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("done") && parts.length == 2;
    }

    private static void verifyDoneCommand(String input, int numOfTasks) throws InvalidCommandException {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean result = index > -1 && index < numOfTasks;
            if (result) {

            } else {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the done command!"));
        }
    }

    private static boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Checks if input is "bye" which is the terminating command
     * @param input
     * @return boolean True if command is equals to "bye", returns false otherwise.
     */
    public static boolean isTerminateCommand(String input) {
        return input.equals("bye");
    }

    private static LocalDate getDate(String string) throws InvalidCommandException {
        //Currently only accepts date in yyyy-mm-dd format
        //Removing the whitespace before and after the string
        try {
            String timeDate = string.trim();
            String[] parts = timeDate.split(" ", 2);
            String date = parts[0].trim();
            System.out.println(LocalDate.parse(date));
            return LocalDate.parse(date);
        } catch (DateTimeParseException e ) {
            throw new InvalidCommandException("Please give your date in yyyy-mm-dd format!");
        }
    }

    private static LocalTime getTime(String string) throws InvalidCommandException {
        //Currently only accepts time in x:x format
        //Removing the whitespace before and after the string
        try {
            String timeDate = string.trim();
            String[] parts = timeDate.split(" ", 2);
            String date = parts[1].trim();
            System.out.println(LocalTime.parse(date));
            return LocalTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please give your time in hh:mm format!");
        }
    }
}
