package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.AddTimedCommand;

/**
 * Represents a parser which parses user commands into Command objects.
 */
public class Parser {

    /**
     * Parses the given input and returns the corresponding command.
     *
     * @param input user input.
     * @return corresponding command.
     * @throws DukeException if there are any date/time parsing issues or unknown commands.
     */
    public static Command parse(String input) throws DukeException {
        String command = input.split(" ")[0];
        try {
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "find":
                String keyword = input.substring(input.indexOf(' ') + 1);
                return new FindCommand(keyword);
            case "done":
                int doneIdx = Integer.parseInt(input.split(" ")[1]) - 1;
                return new DoneCommand(doneIdx);
            case "delete":
                int deleteIdx = Integer.parseInt(input.split(" ")[1]) - 1;
                return new DeleteCommand(deleteIdx);
            case "todo":
                taskFormatCheck(command, input);
                String desc = input.substring(input.indexOf(' ') + 1);
                return new AddCommand(desc);
            case "deadline":
            case "event":
                taskFormatCheck(command, input);
                String tInfo = input.substring(input.indexOf(' ') + 1);
                String tDesc = tInfo.substring(0, tInfo.indexOf('/') - 1);
                String meta = tInfo.substring(tInfo.indexOf('/') + 4);
                LocalDate date;
                if (meta.contains(" ")) {
                    date = LocalDate.parse(meta.substring(0, meta.indexOf(' ')),
                            DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    LocalTime time = LocalTime.parse(meta.substring(meta.indexOf(' ') + 1),
                            DateTimeFormatter.ofPattern("HHmm"));
                    return new AddTimedCommand(command, tDesc, date, time);
                } else {
                    date = LocalDate.parse(meta, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    return new AddTimedCommand(command, tDesc, date);
                }
            default:
                throw new DukeException("Oh dear! I'm sorry, but I don't know what that means :(");
            }
        } catch (DateTimeParseException ex) {
            throw new DukeException("Oh dear! Please format the date and time as yyyy/MM/dd HHmm!");
        } catch (NumberFormatException ex) {
            throw new DukeException("Oh dear! Please give me a number!");
        }
    }

    /**
     * Checks for any task formatting issues
     *
     * @param type type of task.
     * @param input user input.
     * @throws DukeException if there are any formatting issues.
     */
    private static void taskFormatCheck(String type, String input) throws DukeException {
        int idxSpace = input.indexOf(' ');
        int idxMeta = input.indexOf('/');
        int infoLength = idxMeta - (idxSpace + 1);
        if (idxSpace == -1 ||
                (idxMeta != -1 && infoLength < 1)) {
            throw new DukeException("Oh dear! A task description cannot be empty!");
        }
        String info = input.substring(input.indexOf(' ') + 1);
        if (type.equals("todo") && info.contains("/")) {
            throw new DukeException("Oh dear! A todo shouldn't contain a timestamp!");
        }
        if (type.equals("deadline") && !info.contains("/by")) {
            throw new DukeException("Oh dear! A deadline must contain '/by'!");
        }
        if (type.equals("event") && !info.contains("/at")) {
            throw new DukeException("Oh dear! An event must contain '/at'!");
        }
    }
}