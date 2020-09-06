package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.SaveCommand;
import duke.commands.TodoCommand;
import duke.commands.UpdateCommand;
import duke.exception.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * Class that is responsible for parsing raw inputs and translating them to the correct Command or Task.
 */
public class Parser {

    private static final int SINGLE_INPUT_COMMAND_SIZE = 1;
    private static final int DOUBLE_INPUT_COMMAND_SIZE = 2;


    /**
     * Parses user input.
     *
     * @param input The user input.
     * @return The Command subclass that correctly represents the user intent.
     * @throws DukeException If input is unexpected or exceptional.
     */
    public static Command parseInput(String input) throws DukeException {
        validateNotEmpty(input);

        String[] segments = input.split(" ", 2);
        String cmd = segments[0].trim();

        // CHECK FOR NON-TASKS-RELATED COMMANDS
        try {
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
            case "find":
                if (segments[1].length() == 0) {
                    throw new IndexOutOfBoundsException();
                }
                return new FindCommand(segments[1].trim().split(" "));
            case "update":
                String[] updateSegments = segments[1].split(" ", 2);
                if (updateSegments[1].trim().length() == 0) {
                    throw new DukeException("Empty description is not valid");
                }
                return new UpdateCommand(
                        Integer.parseInt(updateSegments[0].trim()),
                        updateSegments[1].trim());
            case "todo":
            case "deadline":
            case "event":
                break;
            default:
                throw new DukeException("Aww! The first word of your input is wrong!");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please check your inputs again, ensure words are spaced and numbers"
                    + "(if any) are correct.");
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

    /**
     * Parses raw data that is loaded from save file.
     *
     * @param data The raw data.
     * @return The correct Task that corresponds to what the raw data represents.
     * @throws DukeException If data is unexpected or exceptional.
     */
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

        int size = DOUBLE_INPUT_COMMAND_SIZE;
        if (breakPt.equals("")) {
            size = SINGLE_INPUT_COMMAND_SIZE;
        }
        String[] res = new String[size];

        String[] splitByCmd = input.split(cmd);
        if (splitByCmd.length <= 1) {
            throw new DukeException(cmd + " description cannot be empty!");
        }

        String description = splitByCmd[1].trim();
        if (description.equals("")) {
            throw new DukeException(cmd + " description cannot be empty!");
        }

        if (size == SINGLE_INPUT_COMMAND_SIZE) {
            res[0] = description;
            return res;
        }

        String[] splitByBreakPt = description.split(breakPt);
        if (!description.contains(breakPt)) {
            throw new DukeException(breakPt + " keyword must be in input for all " + cmd + " commands!");
        }

        if (splitByBreakPt.length <= 1 || splitByBreakPt[1].trim().equals("")) {
            throw new DukeException("Content after " + breakPt + " should not be empty!");
        }

        res[0] = splitByBreakPt[0].trim();
        res[1] = splitByBreakPt[1].trim();

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
