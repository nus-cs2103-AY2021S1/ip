import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    /**
     * Generate a corresponding command based of the input.
     * @param s
     * @return a corresponding command.
     * @throws IncorrectInputException, EmptyInputException
     */
    public static Command parse(String s) throws IncorrectInputException, EmptyInputException {
        String[] arr = s.split(" ");
        String firstWord = arr[0].trim();
        char x;
        int i = -1;
        if (firstWord.equals("done") || firstWord.equals("delete") || firstWord.equals("fdelete")) {
            x = s.charAt(s.length() - 1);
            i = Character.getNumericValue(x);
        }
        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(i);
            case "find":
                String description = s.substring(firstWord.length() + 2).trim();
                return new FindCommand(description);
            case "delete":
                return new DeleteCommand(i);
            case "todo":
                if (s.length() != "todo".length()) {
                    ToDo toDo = new ToDo(s.replace("todo ", ""));
                    return new AddCommand(toDo);
                } else {
                    throw new EmptyInputException("todo");
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
                    throw new EmptyInputException("deadline");
                }
            case "event":
                if (s.length() != "event".length()) {
                    String[] value = s.split(" /at ");
                    Event event = new Event(value[0].replace("event ", ""), value[1]);
                    return new AddCommand(event);
                } else {
                    throw new EmptyInputException("event");
                }
            case "flist":
                return new FriendListCommand();
            case "fdelete":
                return new DeleteFriendCommand(i);
            case "friend":
                String[] info = s.split(" /name ");
                String[] segmentedDetails = info[1].split("/phone number ");
                String[] finalSplit = segmentedDetails[1].split("/isClose ");
                Friend friend = new Friend(segmentedDetails[0].trim(), Integer.valueOf(finalSplit[0].trim()),
                        Boolean.valueOf(finalSplit[1].trim()));
                return new AddFriendCommand(friend);
            default:
                throw new IncorrectInputException("Sorry, wrong input lah");
        }
    }
}
