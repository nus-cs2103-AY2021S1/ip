package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Allows Focus to make sense of user's commands and parsing of
 * text files to convert them to Tasks.
 */
public class Parser {
    /**
     * Parses user's inputs to Command.
     * @param input User's input.
     * @return Different commands depending on user's input.
     */
    public static Command parse(String input) {
        Command command;
        if (input.startsWith("todo")) { // add to-do tasks
            command = new AddCommand("todo");
        } else if (input.startsWith("deadline")) { // add deadline tasks
            command = new AddCommand("deadline");
        } else if (input.startsWith("event")) { // add event tasks
            command = new AddCommand("event");
        } else if (input.startsWith("delete")) { // delete tasks
            command = new DeleteCommand();
        } else if (input.startsWith("done")) { // mark tasks done
            command = new DoneCommand();
        } else if (input.equals("list")) { // list out the tasks
            command = new ListCommand();
        } else if (input.equals("bye")) { // exit the bot
            return new ExitCommand();
        } else { // handle invalid inputs
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Parses strings from text file to different types of Task.
     * @param text Task in string form in text file.
     * @return Different types of Task.
     */
    public static Task textToTask(String text) {
        String[] task = text.split("\\|");
        Task existingTask = null;
        switch (task[0]) {
        case "T":
            existingTask = new ToDo(task[2]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "D":
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime by = LocalDateTime.parse(task[3], deadlineFormatter);
            existingTask = new Deadline(task[2], by);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "E":
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime at = LocalDateTime.parse(task[3], eventFormatter);
            existingTask = new Event(task[2], at);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        }
        return existingTask;
    }
}
