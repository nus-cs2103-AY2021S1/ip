package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.SaveCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class Parser {

    public static Command parseInput(String input) throws DukeException {
        validateNotEmpty(input);

        String[] segments = input.split(" ");
        String cmd = segments[0].trim();

        // CHECK FOR NON-TASKS-RELATED COMMANDS
        switch (cmd) {
        case "bye":
            return new ByeCommand();
        case "save":
            return new SaveCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(segments[1].trim()));
        case "delete":
            return new DeleteCommand(Integer.parseInt(segments[1].trim()));
        case "todo":
        case "deadline":
        case "event":
            break;
        default:
            throw new DukeException("Aww! The first word of your input is wrong!");
        }

        String[] parsedSegments = parseSegments(input, cmd, getTaskBreakPt(cmd));
        // CHECK FOR TASKS-RELATED COMMANDS
        switch (cmd) {
        case "todo":
            return new TodoCommand(parsedSegments[0]);
        case "deadline":
            return new DeadlineCommand(parsedSegments[0], parsedSegments[1]);
        case "event":
            return new EventCommand(parsedSegments[0], parsedSegments[1]);
        default:
            throw new DukeException("Aww! The first word of your input is wrong!");
        }
    }

    public static Task parseTaskData(String data) throws DukeException {
        String[] segments = data.split("\\|");
        Task task;
        boolean isDone = segments[1].equals("1");

        try {
            switch (segments[0]) {
            case "T":
                task = new TodoTask(segments[2], isDone);
                break;
            case "D":
                task = new DeadlineTask(segments[2], isDone, LocalDate.parse(segments[3]));
                break;
            case "E":
                task = new EventTask(segments[2], isDone, LocalDate.parse(segments[3]));
                break;
            default:
                task = null;
            }
            return task;
        } catch (DateTimeParseException e) {
            throw new DukeException("supplied data: " + segments[3] + " does not conform to yyyy-mm-dd");
        }

    }

    private static String[] parseSegments(String input, String cmd, String breakPt) throws DukeException {
        checkIllegalChar(input);

        int size = 2;
        if (breakPt.equals("")) {
            size = 1;
        }
        String[] res = new String[size];

        String[] splitByBP1 = input.split(cmd);
        if (splitByBP1.length <= 1) {
            throw new DukeException(cmd + " description cannot be empty!");
        }

        String description = splitByBP1[1].trim();
        if (description.equals("")) {
            throw new DukeException(cmd + " description cannot be empty!");
        }

        if (size == 1) {
            res[0] = description;
            return res;
        }

        String[] splitByBP2 = description.split(breakPt);
        if (!description.contains(breakPt)) {
            throw new DukeException(breakPt + " keyword must be in input for all " + cmd + " commands!");
        }

        if (splitByBP2.length <= 1 || splitByBP2[1].trim().equals("")) {
            throw new DukeException("Content after " + breakPt + " should not be empty!");
        }

        res[0] = splitByBP2[0].trim();
        res[1] = splitByBP2[1].trim();

        return res;
    }

    private static void checkIllegalChar(String input) throws DukeException {
        char[] illegalChar = {'|'};
        for (char c : illegalChar) {
            if (input.indexOf(c) != -1) {
                throw new DukeException("Illegal Character: " + c);
            }
        }
    }

    private static void validateNotEmpty(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Input cannot be empty!");
        }
    }

    private static String getTaskBreakPt(String taskName) {
        switch (taskName) {
        case "deadline":
            return "/by";
        case "event":
            return "/at";
        default:
            return "";
        }

    }
}
