package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.CommandException;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.command.Command;
import duke.task.ToDo;
//import duke.util.Helper;

public class Parser {
    public Parser() {
    }

    public static Command parse(String nextCommand) {
        String[] split = nextCommand.split(" ", 2);
        String command = split[0];
        String description = split.length == 2 ? split[1] : "";

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
            } else if (command.isBlank()) {
                return new CommandException("Try one of the following instead: todo, event, deadline, done or delete");
            } else {
                return new CommandException("☹ Sorry, I don't recognise that command!");
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
        String[] split = description.split(" /at ");
        String eventDate = formatDate(split[1]);
        return new Event(split[0], eventDate);
    }

    static Deadline parseDeadline(String description) throws DukeException {
        String[] split = description.split(" /by ");
        String eventDate = formatDate(split[1]);
        return new Deadline(split[0], eventDate);
    }

//        switch (operation) {
//        case "bye":
//            return new ByeCommand();
//        case "list":
//            return new ListCommand();
//        case "done":
//            int index = parseTaskNumber(line[1], "done 1") - 1;
//            return new DoneCommand(index);
//        case "todo":
//        case "deadline":
//        case "event":
//
//            return new AddCommand(index, line[1]);
////            if (line.length == 1) {
////                System.out.println("☹ OOPS! Description for command '"
////                        + operation + "' not found, try again!");
////                break;
////            } else if (operation.equals("todo")) {
////                try {
////                    Helper.addToDo(line, taskList);
////                } catch (DukeException e) {
////                    System.out.println(e.getMessage());
////                }
////            } else if (operation.equals("deadline")) {
////                try {
////                    Helper.addDeadline(line, taskList);
////                } catch (DukeException e) {
////                    System.out.println(e.getMessage());
////                }
////            } else {
////                try {
////                    Helper.addEvent(line, taskList);
////                } catch (DukeException e) {
////                    System.out.println(e.getMessage());
////                }
////            }
//            break;
//        case "delete":
//            return new DeleteCommand();
////            try {
////                Helper.deleteTask(line, taskList);
////            } catch (DukeException e) {
////                System.out.println(e.getMessage());
////            }
//            break;
//        default:
//            return new InvalidCommand();
//            // Handles all other inputs
//            System.out.println("☹ Sorry, I don't recognise that command! "
//                    + "Try one of the following instead: todo, event, deadline, done or delete");

    private static int parseTaskIndex(String args, String example)
            throws DukeException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeException(
                    String.format("I need the task index too! Eg. %s", example));
        }
    }

    private static String formatDate(String date) throws DukeException {
        try {
            LocalDate taskDate;
            LocalTime taskTime = null;
            date = date.replace('/', '-');
            String[] dateArguments = date.split(" ");

            taskDate = LocalDate.parse(dateArguments[0]);
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
                    String.format("Invalid date/time! Here's an example format Eg" +
                            ". %s", "2019-12-12 1800"));
        }
    }

}