package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Utility Class to parse user input
 */
public class Parser {
    public Parser() {
    }

    /**
     * Returns the correct Command object to be executed based on user input.
     *
     * @param input User input.
     * @return Corresponding command to execute.
     */
    public static Command parse(String input) {
        String[] split = input.split(" ", 2);
        String command = split[0];
        String description = split.length == 2 ? split[1] : "";
        //Format for solution below adapted from https://github
        // .com/chloelee767/ip/blob/master/src/main/java/duke/parser/Parser.java
        try {
            if (command.equals("bye")) {
                return parseByeCommand();
            } else if (command.equals("list")) {
                return parseListCommand();
            } else if (command.equals("done")) {
                return parseDoneCommand(description);
            } else if (command.equals("delete")) {
                return parseDeleteCommand(description);
            } else if (command.equals("todo")) {
                return new AddCommand(parseToDo(description));
            } else if (command.equals("deadline")) {
                return new AddCommand(parseDeadline(description));
            } else if (command.equals("event")) {
                return new AddCommand(parseEvent(description));
            } else if (command.equals("find")) {
                return new FindCommand(description);
            } else if (command.equals("help") || command.equals("?")) {
                return parseHelpCommand();
            } else if (command.isBlank()) {
                return new CommandException("Try one of the following instead: todo, event, deadline, done or delete");
            } else {
                return new CommandException("Sorry, I don't recognise that command!");
            }
        } catch (DukeException e) {
            return new CommandException(e.getMessage());
        }
    }

    static ByeCommand parseByeCommand() {
        return new ByeCommand();
    }

    static ListCommand parseListCommand() {
        return new ListCommand();
    }

    static ToDo parseToDo(String description) {
        return new ToDo(description);
    }

    static DoneCommand parseDoneCommand(String description) throws DukeException {
        int taskIndex = parseTaskIndex(description, "done 1");
        return new DoneCommand(taskIndex);
    }

    static DeleteCommand parseDeleteCommand(String description) throws DukeException {
        int taskIndex = parseTaskIndex(description, "delete 1");
        return new DeleteCommand(taskIndex);
    }

    static Event parseEvent(String description) throws DukeException {
        try {
            String[] split = description.split(" /at ");
            if (split.length == 1) {
                throw new DukeException("Invalid event format! Here's an example: "
                        + "Eg. event meeting /at 2020-12-31 1800");
            }
            String eventDate = formatDate(split[1]);
            return new Event(split[0], eventDate);
        } catch (StringIndexOutOfBoundsException ex) {
            throw new DukeException("Invalid event format! Here's an example: Eg. event meeting /at "
                            + "2020-12-31 1800");
        }
    }

    static Deadline parseDeadline(String description) throws DukeException {
        try {
            String[] split = description.split(" /by ");
            if (split.length == 1) {
                throw new DukeException("Invalid deadline format! Here's an example: "
                        + "Eg. deadline math quiz /by 2020-12-31 1800");
            }
            String deadlineDate = formatDate(split[1]);
            return new Deadline(split[0], deadlineDate);
        } catch (StringIndexOutOfBoundsException ex) {
            throw new DukeException("Invalid deadline format! Here's an example: "
                            + "Eg. deadline math quiz /by 2020-12-31 1800");
        }
    }

    private static int parseTaskIndex(String args, String example)
            throws DukeException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeException(
                    String.format("I need the task index too! Eg. %s", example));
        }
    }

    static FindCommand parseFind(String description) throws DukeException {
        try {
            String keywords = description.substring(5);
            if (keywords.length() == 0) {
                throw new DukeException("OOPS!!! Please specify your task.");
            }
            return new FindCommand(keywords);
        } catch (StringIndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! Please specify your task.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    static HelpCommand parseHelpCommand() {
        return new HelpCommand();
    }

    private static String formatDate(String date) throws DukeException {
        try {
            LocalDate taskDate;
            LocalTime taskTime = null;
            date = date.replace('/', '-');
            String[] dateArguments = date.split(" ");

            taskDate = parseDate(dateArguments[0]);
            if (dateArguments.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH[:]mm");
                taskTime = LocalTime.parse(dateArguments[1], formatter);
            }
            String output = taskDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
            if (taskTime != null) {
                output += ", " + taskTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            }
            return output;
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    String.format("Invalid date/time! Here's an example format Eg"
                            + ". %s", "2019-12-12 1800"));
        }
    }

    private static LocalDate parseDate(String dateInput) throws DukeException {
        String[] validFormats = {"yyyy-M-d", "d-M-yyyy", "yyyy-MMM-d", "d-MMM-yyyy"};
        for (String format : validFormats) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
                return LocalDate.parse(dateInput, dateFormatter);
            } catch (DateTimeParseException e) {
                //Try other formats
            }
        }
        throw new DukeException("Invalid date format provided! Eg. Try 10-10-2020 or 10-Oct-2020 instead!");
    }

}
