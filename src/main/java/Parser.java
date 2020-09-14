import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * <p>Parser class makes sense of the user input.</p>
 */

public class Parser {

    /**
     * Creates different commands from user inputs.
     *
     * @param userInput String representing user input.
     * @return A Command that contains information on how to execute the command.
     * @throws DukeException thrown when there is any exception during the parsing process.
     */
    public static Command parse(String userInput) throws DukeException {
        int i = userInput.trim().indexOf(' ');
        String command = userInput;
        String detail = "";
        if (i > 0) {
            command = userInput.substring(0, i);
            detail = userInput.substring(i).trim();
        }
        if (command.trim().length() == 0) {
            throw new EmptyTaskException();
        }
        if (userInput.startsWith("#")) {
            return new HashtagCommand(parseTagToFind(userInput.substring(1)));
        }
        switch (command) {
        case ("bye") :
            return new ByeCommand();
        case ("list") :
            return new ListCommand();
        case ("todo") :
            return new AddCommand(parseTodo(detail));
        case ("deadline") :
            return new AddCommand(parseDeadline(detail));
        case ("event") :
            return new AddCommand(parseEvent(detail));
        case ("delete") :
            return new DeleteCommand(parseNumber(detail));
        case ("done") :
            return new DoneCommand(parseNumber(detail));
        case ("find") :
            return new FindCommand(detail);
        case ("help") :
            return new HelpCommand();
        default:
            throw new UnexpectedInputException();
        }
    }

    private static DateAndTime parseTime(String timeFormat) throws InvalidDateTimeInput {
        try {
            if (!timeFormat.contains(" ")) {
                LocalDate localDate = LocalDate.parse(timeFormat.trim());
                return new DateAndTime(localDate);
            } else {
                String[] split = timeFormat.trim().split(" ");
                LocalTime localTime = LocalTime.parse(split[0].trim());
                LocalDate localDate = LocalDate.parse(split[1].trim());
                return new DateAndTime(localDate, localTime);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeInput();
        }
    }

    private static ToDoTask parseTodo(String detail) throws DukeException {
        if (detail.strip().equals("")) {
            throw new MissingDescriptionException(TaskType.TODO);
        } else {
            if (detail.contains("#")) {
                TagList taglist = parseTag(detail);
                // excludes the tags in description
                int i = detail.indexOf("#");
                String newDetail = detail.substring(0, i);
                return new ToDoTask(newDetail, false, taglist);
            } else {
                return new ToDoTask(detail, false, null);
            }
        }
    }

    private static DeadlineTask parseDeadline(String detail) throws DukeException {
        if (detail.strip().equals("")) {
            throw new MissingDescriptionException(TaskType.DEADLINE);
        } else {
            if (!detail.contains("#")) {
                String descriptionAndTime = detail.replace("/by", "%");
                String description = descriptionAndTime.split("%")[0];
                String time = descriptionAndTime.split("%")[1];
                DateAndTime dateAndTime = parseTime(time.trim());
                return new DeadlineTask(description, false, dateAndTime, null);
            } else {
                int i = detail.indexOf("#");
                String newDetail = detail.substring(0, i);
                String descriptionAndTime = newDetail.replace("/by", "%");
                String description = descriptionAndTime.split("%")[0];
                String time = descriptionAndTime.split("%")[1];
                DateAndTime dateAndTime = parseTime(time.trim());
                TagList taglist = parseTag(detail);
                System.out.println(taglist.getTagList().size());
                return new DeadlineTask(description, false, dateAndTime, taglist);
            }
        }
    }

    private static EventTask parseEvent(String detail) throws DukeException {
        if (detail.strip().equals("")) {
            throw new MissingDescriptionException(TaskType.EVENT);
        } else {
            String descriptionAndTime = detail.replace("/at", "%");
            String description = descriptionAndTime.split("%")[0];
            String time = descriptionAndTime.split("%")[1];
            DateAndTime dateAndTime = parseTime(time.trim());
            if (!detail.contains("#")) {
                return new EventTask(description, false, dateAndTime, null);
            } else {
                TagList taglist = parseTag(detail);
                return new EventTask(description, false, dateAndTime, taglist);
            }
        }
    }

    private static int parseNumber(String detail) throws DukeException {
        if (detail.strip().length() == 0) {
            throw new MissingTaskIndexException();
        }
        try {
            return Integer.parseInt(detail.trim());
        } catch (NumberFormatException e) {
            throw new InvalidNumberInput();
        }
    }

    private static TagList parseTag(String detail) {
        int i = detail.trim().indexOf("#");
        String tagList = detail.substring(i).trim();
        String spaceRemovedString = tagList.replace(" ", "");
        String[] tagArr = spaceRemovedString.trim().split("#");
        TagList res = new TagList();
        for (String s : tagArr) {
            if (!s.equals("")) {
                Tag currTag = new Tag(s);
                res.addTag(currTag);
            }
        }
        System.out.println(res.getTagList().size());
        return res;
    }

    private static Tag parseTagToFind(String tag) {
        return new Tag(tag);
    }
}
