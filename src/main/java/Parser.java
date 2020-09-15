import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    /**
     * Generate a corresponding command based of the input.
     * @param s
     * @return a corresponding command.
     * @throws IncorrectInputException
     */
    public static Command parse(String s) throws IncorrectInputException {
        int j = s.indexOf(' ');
        String firstWord = "";
        if (j > -1) {
            firstWord = s.substring(0, j);
        } else {
            firstWord = s;
        }
        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                char x = s.charAt(s.length() - 1);
                int i = Character.getNumericValue(x);
                return new DoneCommand(i);
            case "find":
                String description = s.substring(j + 1);
                return new FindCommand(description);
            case "delete":
                char y = s.charAt(s.length() - 1);
                int k = Character.getNumericValue(y);
                return new DeleteCommand(k);
            case "todo":
                if (s.length() != "todo".length()) {
                    ToDo toDo = new ToDo(s.replace("todo ", ""));
                    return new AddCommand(toDo);
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                if (s.length() != "deadline".length()) {
                    String[] value = s.split(" /by ");
                    String date = value[1];
                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(date, inputFormat);
                    dateTime.format(outputFormat);
                    Deadline deadline = new Deadline(value[0].replace("deadline ", ""), dateTime);
                    return new AddCommand(deadline);
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of an deadline cannot be empty.");
                }
            case "event":
                if (s.length() != "event".length()) {
                    String[] value = s.split(" /at ");
                    Event event = new Event(value[0].replace("event ", ""), value[1]);
                    return new AddCommand(event);
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            default:
                return new HelpCommand();
        }
    }
}
