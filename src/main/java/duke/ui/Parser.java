package duke.ui;

import static duke.commands.CommandReschedule.VALID_TIME_UNITS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.TimeModification;
import duke.commands.Command;
import duke.commands.CommandAddDeadline;
import duke.commands.CommandAddEvent;
import duke.commands.CommandAddToDo;
import duke.commands.CommandDelete;
import duke.commands.CommandDone;
import duke.commands.CommandFind;
import duke.commands.CommandList;
import duke.commands.CommandRescheduleAdvance;
import duke.commands.CommandReschedulePostpone;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentsException;
import duke.exceptions.InvalidTimeUnitException;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;
import duke.tasks.TaskToDo;

/**
 * Transforms user commands into {@code Command}.
 */
public class Parser {

    private static final Pattern COMMAND_INPUT_FORMAT = Pattern.compile("(?<command>^[ltdefpa]\\w+)" + "\\s?"
            + "(?<arguments>.*)");

    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Parser.
     * @param taskList task list.
     * @param ui ui.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Gets {@code Command} from user String input.
     * <p>
     * Takes in input for the following commands:
     * <li>List</li>
     * <li>Done</li>
     * <li>Delete</li>
     * <li>Add - Event, To-do, Deadline</li>
     * </p>
     *
     * @param input user command input in String
     * @return Command to be executed
     * @throws DukeException If input does not match the required format.
     */
    public Command getCommandFromInput(String input) throws DukeException {
        Matcher matcher = COMMAND_INPUT_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new DukeException("☹ BLEHHHHHH!!! I'm (not) sorry, but I don't know what that means :/");
        }
        String command = matcher.group("command").toLowerCase();
        String arguments = matcher.group("arguments");
        switch (command) {
        case CommandList.COMMAND_STRING:
            return new CommandList(taskList, ui);
        case CommandDone.COMMAND_STRING:
            return new CommandDone(taskList, ui, parseDoneOrDelete(arguments));
        case CommandDelete.COMMAND_STRING:
            return new CommandDelete(taskList, ui, parseDoneOrDelete(arguments));
        case CommandFind.COMMAND_STRING:
            return new CommandFind(taskList, ui, arguments);
        case CommandAddToDo.COMMAND_STRING:
            return new CommandAddToDo(taskList, ui, new TaskToDo(arguments));
        case CommandAddEvent.COMMAND_STRING:
            return new CommandAddEvent(taskList, ui, parseEvent(arguments));
        case CommandAddDeadline.COMMAND_STRING:
            return new CommandAddDeadline(taskList, ui, parseDeadline(arguments));
        case CommandReschedulePostpone.COMMAND_STRING:
            return new CommandReschedulePostpone(taskList, ui, parseReschedule(arguments));
        case CommandRescheduleAdvance.COMMAND_STRING:
            return new CommandRescheduleAdvance(taskList, ui, parseReschedule(arguments));
        default:
            throw new DukeException("☹ BLEHHHHHH!!! I'm (not) sorry, but I don't know what that means :/");
        }
    }

    private TimeModification parseReschedule(String arguments) throws DukeException {
        Pattern timeModPattern = Pattern.compile("(?<taskIndex>\\d+)\\s+(/by)\\s+(?<amount>\\d+)\\s+(?<unit>\\w+)");
        Matcher matcher = timeModPattern.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidArgumentsException("reschedule");
        }
        int index = Integer.parseInt(matcher.group("taskIndex")) - 1;
        int amount = Integer.parseInt(matcher.group("amount"));
        ChronoUnit timeUnit = ChronoUnit.CENTURIES;
        String timeUnitInput = matcher.group("unit").toLowerCase();
        boolean found = false;
        for (int i = 0; i < VALID_TIME_UNITS.size() && !found; i++) {
            if (VALID_TIME_UNITS.get(i).toString().toLowerCase().contains(timeUnitInput)) {
                timeUnit = VALID_TIME_UNITS.get(i);
                found = true;
            }
        }
        if (!found) {
            throw new InvalidTimeUnitException();
        }

        return new TimeModification(index, amount, timeUnit);
    }

    private int parseDoneOrDelete(String arguments) throws DukeException {
        Pattern integerPattern = Pattern.compile("(?<integer>\\d+)\\s?");
        Matcher matcher = integerPattern.matcher(arguments);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("integer")) - 1;
            if (index < 0 || index > taskList.getSize() - 1) {
                throw new DukeException(String.format("☹ BLEHHHHHH. Task no. %d does not exist. "
                        + "Please try again.", (index + 1)));
            } else {
                return index;
            }
        } else {
            throw new DukeException("☹ BLEHHHHHH. What are you talking about?? Tell me which task??");
        }
    }

    private static Task parseEvent(String arguments) throws DukeException {
        String[] output = arguments.split(" /");
        if (output.length < 2) {
            throw new DukeException(String.format("☹ BLEHHHHHH!!! The description of an event cannot be empty."));
        }
        String description = output[0];
        String pattern = ("(at?)(\\s)(\\S+)(\\s)(\\d{4})([-])(\\d{4})");
        LocalDate eventDate = LocalDate.parse(output[1].replaceAll(pattern, "$3"));
        String startTemp = output[1].replaceAll(pattern, "$5");
        String endTemp = output[1].replaceAll(pattern, "$7");
        LocalTime startTime = LocalTime.parse(startTemp.substring(0, 2) + ":"
                + startTemp.substring(2, 4));
        LocalTime endTime = LocalTime.parse(endTemp.substring(0, 2) + ":"
                + endTemp.substring(2, 4));
        return new TaskEvent(description, eventDate, startTime, endTime);
    }

    private static Task parseDeadline(String arguments) throws DukeException {
        String[] output = arguments.split(" /");
        if (output.length < 2) {
            throw new DukeException(String.format("☹ BLEHHHHHH!!! The description of a deadline cannot be empty."));
        }
        String description = output[0];
        String pattern = ("(by?)(\\s)([\\S]+)(\\s)([\\S]+)");
        LocalDate deadlineDate = LocalDate.parse(output[1].replaceAll(pattern, "$3"));
        String timeTemp = output[1].replaceAll(pattern, "$5");
        LocalTime deadlineTime = LocalTime.parse(timeTemp.substring(0, 2)
                + ":" + timeTemp.substring(2, 4));
        LocalDateTime deadline = deadlineDate.atTime(deadlineTime);
        return new TaskDeadline(description, deadline);
    }
}
