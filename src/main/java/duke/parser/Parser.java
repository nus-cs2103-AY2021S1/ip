package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class Parser {
    public static Command parse(String input) {
        input = input.strip();
        String[] split = input.split("\\s+", 2); // guranteed to contain at least ""
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        try {
            if (command.equals("bye")) {
                ensureNoArgs(args, command);
                return new ByeCommand();
            } else if (command.equals("list")) {
                ensureNoArgs(args, command);
                return new ListCommand();
            } else if (command.equals("done")) {
                int index = parseTaskNumber(args, "you have completed", "done 1") - 1;
                return new DoneCommand(index);
            } else if (command.equals("delete")) {
                int index = parseTaskNumber(args, "you want to remove", "delete 1") - 1;
                return new DeleteCommand(index);
            } else if (command.equals("todo")) {
                if (args.isEmpty()) {
                    return new ErrorCommand(
                            "Couldn't add todo! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Task(args));
            } else if (command.equals("deadline")) {
                String[] argsSplit = parseDeadlineEventArgs(args, "/by", command,
                        "deadline <description> /by <date>");
                return new AddCommand(new Deadline(argsSplit[0], argsSplit[1]));
            } else if (command.equals("event")) {
                String[] argsSplit = parseDeadlineEventArgs(args, "/at", command,
                        "event <description> /at <date>");
                return new AddCommand(new Event(argsSplit[0], argsSplit[1]));
            } else if (input.isBlank()) {
                return new ErrorCommand("You need to tell me what you want me to do!");
            } else {
                return new ErrorCommand("Sorry, I don't understand that!");
            }
        } catch (DukeParsingException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    // helpers for route function

    private static void ensureNoArgs(String args, String commandName) throws DukeParsingException {
        if (!args.isEmpty()) {
            throw new DukeParsingException(
                    String.format("I don't understand that. Did you mean %s?", commandName));
        }
    }

    private static int parseTaskNumber(String args, String taskDescription, String example)
            throws DukeParsingException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeParsingException(
                    String.format("You need to tell me the number of the task %s. Eg. %s",
                            taskDescription, example));
        }
    }

    private static String[] parseDeadlineEventArgs(String args, String splitAround, String taskType,
            String example) throws DukeParsingException {
        String[] argsSplit = args.split("\\s+" + splitAround + "\\s+", 2);
        if (argsSplit.length != 2 || argsSplit[0].isBlank() || argsSplit[1].isBlank()) {
            String n = taskType.matches("^a|e|i|o|u") ? "n" : ""; // starts with vowel
            throw new DukeParsingException(
                    String.format("Couldn't add %s! To add a%s %s, talk to me using the format: %s",
                            taskType, n, taskType, example));
        }
        return argsSplit;
    }
}
