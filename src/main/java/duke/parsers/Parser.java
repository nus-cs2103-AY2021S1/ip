package duke.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.StatsCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDukeCommandException;
import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingTaskDescriptionException;
import duke.exceptions.MissingTaskIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/** Represents a parser that parses the user inputs and the list saved in the hard disk into a suitable format
 * for Duke to process. */
public class Parser {

    /** The formatter for user inputs. */
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** The formatter for user inputs that are displayed in the list saved in the hard disk. */
    private static final DateTimeFormatter FORMATTER_DISPLAY = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** Parses the user inputs into a suitable format for Duke to process.
     *
     * @param userInput The user input fed into Duke.
     * @return An appropriate command to be executed.
     * @throws DukeException If the userInput is invalid or missing.
     */
    public Command parse(String userInput) throws DukeException {
        String[] inputCommandAndArgument = userInput.split(" ", 2);
        String command = inputCommandAndArgument[0];
        String argument = inputCommandAndArgument.length == 2 ? inputCommandAndArgument[1] : "";

        if (userInput.equals("bye")) {
            return parseBye();
        } else if (userInput.equals("list")) {
            return parseList();
        } else if (command.equals("done") || command.equals("delete")) {
            if (argument.isEmpty()) {
                throw new MissingTaskIndexException();
            }
            return parseDoneDelete(command, Integer.parseInt(argument));
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (argument.isEmpty()) {
                throw new MissingTaskDescriptionException();
            }
            return parseAdd(command, argument);
        } else if (command.equals("date")) {
            return parseDate(argument);
        } else if (command.equals("find")) {
            return parseFind(argument);
        } else if (command.equals("stats")) {
            return parseStats();
        } else {
            throw new InvalidDukeCommandException();
        }
    }

    private Command parseBye() {
        return new ByeCommand();
    }

    private Command parseList() {
        return new ListCommand();
    }

    private Command parseDoneDelete(String command, int index) {
        if (command.equals("done")) {
            return new DoneCommand(index - 1);
        } else if (command.equals("delete")) {
            return new DeleteCommand(index - 1);
        } else {
            assert false : "Oh no! This invalid Duke Command scenario should be handled earlier.";
            throw new InvalidDukeCommandException();
        }
    }

    private Command parseAdd(String command, String argument) {
        try {
            if (command.equals("todo")) {
                return new AddCommand(new ToDo(argument));
            } else if (command.equals("deadline")) {
                if (!argument.contains(" /by ")) {
                    throw new MissingDateTimeException();
                }
                String[] descriptionAndDate = argument.split(" /by ");
                return new AddCommand(new Deadline(descriptionAndDate[0],
                        LocalDate.parse(descriptionAndDate[1], FORMATTER_INPUT)));
            } else if (command.equals("event")) {
                if (!argument.contains(" /at ")) {
                    throw new MissingDateTimeException();
                }
                String[] descriptionAndDate = argument.split(" /at ");
                return new AddCommand(new Event(descriptionAndDate[0],
                        LocalDate.parse(descriptionAndDate[1], FORMATTER_INPUT)));
            } else {
                assert false : "Oh no! This invalid Duke Command scenario should be handled earlier.";
                throw new InvalidDukeCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    private Command parseDate(String queryDate) {
        try {
            return new DateCommand(LocalDate.parse(queryDate, FORMATTER_INPUT));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    private Command parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    private Command parseStats() {
        return new StatsCommand();
    }

    /** Parses the user inputs in the list saved in the hard disk into a suitable format for Duke to process.
     *
      * @param savedTaskList The list of user inputs in the list saved in the hard disk.
     * @return A list of tasks.
     */
    public ArrayList<Task> parseSavedTaskList(ArrayList<String> savedTaskList) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String task : savedTaskList) {
            String[] taskParts = task.split(" ", 2);
            String type = "" + taskParts[0].charAt(1);
            String status = "" + taskParts[0].charAt(4);
            String argument = taskParts[1];

            if (type.equals("T")) {
                tasks.add(new ToDo(isDone(status), argument));
            } else if (type.equals("D")) {
                String[] descriptionAndDate = splitArgument(argument, " \\(by: ");
                tasks.add(new Deadline(isDone(status), descriptionAndDate[0],
                        LocalDate.parse(descriptionAndDate[1], FORMATTER_DISPLAY)));
            } else if (type.equals("E")) {
                String[] descriptionAndDate = splitArgument(argument, " \\(at: ");
                tasks.add(new Event(isDone(status), descriptionAndDate[0],
                        LocalDate.parse(descriptionAndDate[1], FORMATTER_DISPLAY)));
            } else {
                assert false : "Oh no! An invalid task type has been passed.";
            }
        }
        return tasks;
    }

    private String[] splitArgument(String argument, String regex) {
        String[] descriptionAndDate = argument.split(regex);
        descriptionAndDate[1] = descriptionAndDate[1].replace(")", "");
        return descriptionAndDate;
    }

    private boolean isDone(String symbol) {
        return symbol.equals("O");
    }
}
