import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private static Command parseDone(String input) throws DukeException {
        if(input.matches("done\\s*")) {
            throw new EmptyArgumentException("Task Index");
        }
        if(!input.matches("done \\d+")) {
            throw new InvalidArgumentException("Task Index");
        }
        String[] arr = input.split(" ");
        int index = Integer.parseInt(arr[1]) - 1;
        return new DoneCommand(index);
    }
    private static Command parseTodoTask(String input) throws DukeException {
        if(input.matches("todo\\s*")) {
            throw new EmptyArgumentException("todo's description");
        }
        return new AddTaskCommand(new Todo(input.substring(5)));
    }
    private static Command parseDeadlineTask(String input) throws DukeException {
        if(input.matches("deadline\\s*")) {
            throw new EmptyArgumentException("deadline's description");
        }
        if(!input.matches("deadline .+/by \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
            throw new InvalidArgumentException("deadline's description (proper date format: yyyy-mm-dd HHmm)");
        }
        String[] split = input.substring(9).split("/by");
        String dateTimeString = split[1].stripLeading();
        LocalDateTime dateTime = parseDateTime(dateTimeString);
        return new AddTaskCommand(new Deadline(split[0].stripTrailing(), dateTime));
    }
    private static Command parseEventTask(String input) throws DukeException {
        if(input.matches("event\\s*")) {
            throw new EmptyArgumentException("event's description");
        }
        if(!input.matches("event .+/at \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
            throw new InvalidArgumentException("event description (proper date format: yyyy-mm-dd HHmm)");
        }
        String[] split = input.substring(6).split("/at");
        String dateTimeString = split[1].stripLeading();
        return new AddTaskCommand(new Event(split[0].stripTrailing(), parseDateTime(dateTimeString)));
    }
    private static Command parseDelete(String input) throws DukeException {
        if(input.matches("delete\\s*")) {
            throw new EmptyArgumentException("Task Index");
        }
        if(!input.matches("delete \\d+")) {
            throw new InvalidArgumentException("Task Index");
        }
        String[] arr = input.split(" ");
        int index = Integer.parseInt(arr[1]) - 1;
        return new DeleteCommand(index);
    }

    public static LocalDateTime parseDateTime(String s) {
        // s will be in the format : yyyy-mm-dd HHmm
        // return format : yyyy-mm-ddTHH:mm
        String[] dateTimeSplit = s.split(" ");
        String date = dateTimeSplit[0];
        String hour = dateTimeSplit[1].substring(0, 2);
        String min = dateTimeSplit[1].substring(2);
        return LocalDateTime.parse(date + "T" + hour + ":" + min);
    }
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("done.*")) {
            return parseDone(input);
        } else if (input.matches("todo.*")) {
            return parseTodoTask(input);
        } else if (input.matches("deadline.*")) {
            return parseDeadlineTask(input);
        } else if (input.matches("event.*")) {
            return parseEventTask(input);
        } else if (input.matches("delete.*")) {
            return parseDelete(input);
        } else {
            throw new InvalidCommandException();
        }
    }


}
