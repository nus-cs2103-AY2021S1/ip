import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

public class Parser {
  public static String getTaskDescription(CommandType type, String cmd) throws DukeException {
    String[] cmdParts = cmd.split(type.toString() + " ", 2);

    if (cmdParts.length != 2 || cmdParts[1].equals("")) {
      throw Ui.taskDescriptionEmptyException(type);
    }

    return cmdParts[1];
  }

  public static int getTaskTargetIndex(CommandType type, String cmd) throws DukeException {
    String description = Parser.getTaskDescription(type, cmd);
    try {
      return Integer.parseInt(description);
    } catch (NumberFormatException e) {
      throw Ui.taskIndexFormatException(type);
    }
  }

  public static LocalDate parseDateString(String dateString) throws DukeException {
    try {
      return LocalDate.parse(dateString);
    } catch (DateTimeParseException e) {
      throw Ui.taskDateFormatException();
    }
  }

  public static Command parseCommandString(String commandString) throws DukeException {
    Optional<CommandType> commandType =
        Stream.of(CommandType.values()).filter(command -> command.is(commandString)).findAny();

    if (commandType.isPresent()) {
      return Command.create(commandType.get(), commandString);
    } else {
      throw Ui.commandInvalidException();
    }
  }
}
