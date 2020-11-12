
package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HashtagCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeInputException;
import duke.exception.InvalidNumberInputException;
import duke.exception.InvalidTaskFormatException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingTaskIndexException;
import duke.exception.UnexpectedInputException;
import duke.tag.Tag;
import duke.tag.TagList;
import duke.task.DateAndTime;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskType;
import duke.task.ToDoTask;

/**
 * <p>duke.Parser class makes sense of the user input.</p>
 */

public class Parser {

    /**
     * Creates different commands from user inputs.
     *
     * @param userInput String representing user input.
     * @return A duke.command.Command that contains information on how to execute the command.
     * @throws DukeException thrown when there is any exception during the parsing process.
     */
    public static Command parse(String userInput) throws DukeException {
        String command = userInput;
        String detail = "";
        int i = userInput.trim().indexOf(' ');
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
        switch (command.trim()) {
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

    private static DateAndTime parseTime(String timeFormat) throws InvalidDateTimeInputException {
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
            throw new InvalidDateTimeInputException();
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
            try {
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
                    return new DeadlineTask(description, false, dateAndTime, taglist);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskFormatException();
            }
        }
    }

    private static EventTask parseEvent(String detail) throws DukeException {
        if (detail.strip().equals("")) {
            throw new MissingDescriptionException(TaskType.EVENT);
        } else {
            try {
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
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskFormatException();
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
            throw new InvalidNumberInputException();
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
        return res;
    }

    private static Tag parseTagToFind(String tag) {
        return new Tag(tag);
    }
}
