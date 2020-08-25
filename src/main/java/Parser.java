import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandArgs = fullCommand.split(" ");
        if (commandArgs[0].equals(CommandType.BYE.getName())) {
            return new ExitCommand();
        } else if (commandArgs[0].equals(CommandType.LIST.getName())) {
            try {
                return new ListCommand(LocalDate.parse(commandArgs[1]));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                return new ListCommand();
            }
        } else if (commandArgs[0].equals(CommandType.DONE.getName())) {
            try {
                return new DoneCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The number of the task to be marked as done has to be provided.");
            }
        } else if (commandArgs[0].equals(CommandType.DELETE.getName())) {
            try {
                return new DeleteCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The number of the task to be deleted has to be provided.");
            }
        } else if (commandArgs[0].equals(CommandType.TODO.getName())) {
            String description = Parser.reassembleString(commandArgs, 1, commandArgs.length);
            return new AddCommand(CommandType.TODO, description, null);
        } else if (commandArgs[0].equals(CommandType.DEADLINE.getName())) {
            int byIdx = Arrays.asList(commandArgs).indexOf("/by");
            if (byIdx < 0) {
                throw new DukeException("The deadline date has to be provided to the deadline task.");
            }
            String description = Parser.reassembleString(commandArgs, 1, byIdx);
            String by = Parser.reassembleString(commandArgs, byIdx + 1, commandArgs.length);
            return new AddCommand(CommandType.DEADLINE, description, by);
        } else if (commandArgs[0].equals(CommandType.EVENT.getName())) {
            int atIdx = Arrays.asList(commandArgs).indexOf("/at");
            if (atIdx < 0) {
                throw new DukeException("The event date has to be provided to the event task.");
            }
            String description = Parser.reassembleString(commandArgs, 1, atIdx);
            String at = Parser.reassembleString(commandArgs, atIdx + 1, commandArgs.length);
            return new AddCommand(CommandType.EVENT, description, at);
        } else if (commandArgs[0].equals(CommandType.FIND.getName())) {
            String keyword = Parser.reassembleString(commandArgs, 1, commandArgs.length);
            if (keyword.length() == 0) {
                throw new DukeException("The keyword has to be provided for the find command");
            }
            return new FindCommand(keyword);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static String reassembleString(String[] arr, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }
}
