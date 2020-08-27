import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    // Check if given string can be parsed into an integer
    public static boolean isInteger(String secondWord) {
        try {
            Integer.parseInt(secondWord);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // Checks if user's input date is of the correct format (yyyy-mm-dd HH:MM)
    public static boolean isValidFormat(String inputDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(inputDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        // return the respective command
        String[] inputDataWords = fullCommand.split(" ");
        String firstWord = inputDataWords[0];

        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (firstWord.equals("done")) {
            if (inputDataWords.length != 2) {
                throw new DukeException("Invalid command provided. Please try again.");
            } else {
                // check if second word is an integer
                if (isInteger(inputDataWords[1])) {
                    int taskNumber = Integer.parseInt(inputDataWords[1]);
                    return new DoneCommand(taskNumber);
                } else {
                    throw new DukeException("Please enter a valid task number.");
                }
            }
        } else if (firstWord.equals("delete")) {
            if (inputDataWords.length != 2) {
                throw new DukeException("Invalid command provided. Please try again.");
            } else {
                // check if second word is an integer
                if (isInteger(inputDataWords[1])) {
                    int taskNumber = Integer.parseInt(inputDataWords[1]);
                    return new DeleteCommand(taskNumber);
                } else {
                    throw new DukeException("Please enter a valid task number.");
                }
            }
        } else if (firstWord.equals("todo")) {
            if (inputDataWords.length < 2) {
                throw new DukeException("The description of a " + firstWord + " cannot be empty.");
            } else {
                ToDo task = new ToDo(fullCommand.split("todo ")[1]);
                return new AddCommand(task);
            }
        } else if (firstWord.equals("deadline")) {
            if (inputDataWords.length < 2) {
                throw new DukeException("The description of a " + firstWord + " cannot be empty.");
            } else if (fullCommand.split("/by ").length < 2 || inputDataWords[1].equals("/by")) {
                throw new DukeException("The deadline of this task is not provided.\n"
                        + "   Please re-enter the desired deadline task\n"
                        + "   (e.g. deadline xxx /by yyyy-mm-dd HH:MM)");
            } else {
                String byString = fullCommand.split("/by ")[1];
                if (isValidFormat(byString)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime byLocalDate = LocalDateTime.parse(byString, formatter);

                    Deadline task = new Deadline(
                            fullCommand.split("deadline ")[1].split(" /by ")[0], byLocalDate);
                    return new AddCommand(task);
                } else {
                    throw new DukeException("   Please enter a valid deadline task\n"
                            + "   (e.g. deadline xxx /by yyyy-mm-dd HH:mm)");
                }
            }
        } else if (firstWord.equals("event")) {
            if (fullCommand.split(" ").length < 2 || inputDataWords[1].equals("/at")) {
                throw new DukeException("The description of a " + firstWord + " cannot be empty.");
            } else if (fullCommand.split("/at ").length < 2) {
                throw new DukeException("The duration of this task cannot be empty.\n"
                        + "   Please re-enter the desired event task\n"
                        + "   (e.g. event xxx /at yyyy-mm-dd HH:mm)");
            } else {
                String atString = fullCommand.split("/at ")[1];
                if (isValidFormat(atString)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime atLocalDate = LocalDateTime.parse(atString, formatter);

                    Event task = new Event(
                            fullCommand.split("event ")[1].split(" /at ")[0], atLocalDate);
                    return new AddCommand(task);
                } else {
                    throw new DukeException("   Please enter a valid event task\n"
                            + "   (e.g. event xxx /at yyyy-mm-dd HH:mm)");
                }
            }
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            // invalid commands
            throw new DukeException("Invalid command provided. Please try again.");
        }
    }
}
