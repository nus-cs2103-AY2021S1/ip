package focus.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import focus.command.AddCommand;
import focus.command.Command;
import focus.command.DeleteCommand;
import focus.command.DoneCommand;
import focus.command.ExitCommand;
import focus.command.FindCommand;
import focus.command.HelpCommand;
import focus.command.ListCommand;
import focus.command.RemindCommand;
import focus.command.SettingsCommand;
import focus.exception.InvalidCommandException;
import focus.task.Deadline;
import focus.task.Event;
import focus.task.Task;
import focus.task.ToDo;

/**
 * Allows Focus to make sense of user's commands and parsing of
 * text files to convert them to Tasks.
 */
public class Parser {
    /**
     * Parses user's inputs to Command.
     *
     * @param input User's input.
     * @return Different commands depending on user's input.
     * @throws InvalidCommandException When user inputs an invalid command.
     */
    public static Command parse(String input) throws InvalidCommandException {
        Command command;
        if (input.startsWith("help")) { // list out the commands
            command = new HelpCommand();
        } else if (input.startsWith("todo")) { // add to-do tasks
            command = new AddCommand("todo");
        } else if (input.startsWith("deadline")) { // add deadline tasks
            command = new AddCommand("deadline");
        } else if (input.startsWith("event")) { // add event tasks
            command = new AddCommand("event");
        } else if (input.startsWith("delete")) { // delete tasks
            command = new DeleteCommand();
        } else if (input.startsWith("done")) { // mark tasks done
            command = new DoneCommand();
        } else if (input.startsWith("list")) { // list out the tasks
            command = new ListCommand();
        } else if (input.startsWith("find")) { // find the tasks
            command = new FindCommand();
        } else if (input.startsWith("remind")) { // reminders
            command = new RemindCommand();
        } else if (input.startsWith("settings")) { // settings
            command = new SettingsCommand();
        } else if (input.equals("bye")) { // exit the bot
            command = new ExitCommand();
        } else { // handle invalid inputs
            throw new InvalidCommandException();
        }
        assert !command.equals(null) : "Command should not be null here.";
        return command;
    }

    /**
     * Parses strings from text file to different types of Task.
     *
     * @param text Task in string form in text file.
     * @return Different types of Task.
     */
    public static Task textToTask(String text) {
        String[] task = text.split("\\|");
        Task existingTask = null;
        String pattern = "yyyy-MM-dd'T'HH:mm";
        switch (task[0]) {
        case "T":
            existingTask = new ToDo(task[2]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            return existingTask;
        case "D":
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime by = LocalDateTime.parse(task[3], deadlineFormatter);
            existingTask = new Deadline(task[2], by);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            return existingTask;
        case "E":
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime at = LocalDateTime.parse(task[3], eventFormatter);
            existingTask = new Event(task[2], at);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            return existingTask;
        default:
            assert false : "Program should not reach here.";
            return existingTask;
        }
    }
}
