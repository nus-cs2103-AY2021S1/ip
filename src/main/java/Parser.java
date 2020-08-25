import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that will make sense of user commands and break down
 * to various components for ease of processing.
 */
public class Parser {
    /**
     * Returns an array of string where the commands for tasks have been broken
     * into its various parts for ease of processing/
     * E.g. deadline task returns [deadline, task description, date, time]
     * event = [event, task description, date, start time, end time]
     * @param command The user command.
     * @return An array of string which has been processed.
     * @throws InvalidDateAndTimeException If input date/time is invalid.
     * @throws InvalidTaskNumber If an improper integer task number is called in delete and done.
     * @throws NoDescriptionException If there is no task description for addition of tasks.
     * @throws NotTaskException If an invalid command is being input.
     */
    public String[] parse(String command) throws InvalidDateAndTimeException,
            InvalidTaskNumber, NoDescriptionException, NotTaskException {
        String[] cmd = command.split(" ");
        switch(cmd[0].toLowerCase()) {
        case "bye":
        case "list":
        case "find":
            return cmd;
        case "print":
            //check if is valid date
            try {
                LocalDate.parse(cmd[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return cmd;
            } catch (DateTimeParseException e) {
                throw new InvalidDateAndTimeException();
            }
        //check if an integer is given for taskNum
        case "done":
        case "delete":
            try {
                Integer.parseInt(cmd[1]);
                return cmd;
            } catch (NumberFormatException e) {
                throw new InvalidTaskNumber();
            }
        case "todo":
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                return new String[]{cmd[0], command.replaceFirst(cmd[0] + " ", "")};
            }
        case "deadline":
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                //check if it is valid date and time
                try {
                    LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime.parse(cmd[cmd.length - 1], DateTimeFormatter.ofPattern("HHmm"));
                    String[] splitBySlash = command.split("/");
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            cmd[cmd.length - 2], cmd[cmd.length - 1]};
                } catch (DateTimeParseException e){
                    throw new InvalidDateAndTimeException();
                }
            }
        case "event":
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                try {
                    //check for valid date and time
                    LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime.parse(cmd[cmd.length - 1].split("-")[0], DateTimeFormatter.ofPattern("HHmm"));
                    LocalTime.parse(cmd[cmd.length - 1].split("-")[1], DateTimeFormatter.ofPattern("HHmm"));
                    String[] splitBySlash = command.split("/");
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            cmd[cmd.length - 2], cmd[cmd.length - 1].split("-")[0],
                            cmd[cmd.length - 1].split("-")[1]};
                } catch (DateTimeParseException e) {
                    throw new InvalidDateAndTimeException();
                }
            }
        default:
            throw new NotTaskException();
        }
    }
}
