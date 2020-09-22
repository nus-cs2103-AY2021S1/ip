package duke.logic;

import java.text.ParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Parser {
    /**
     * Parses a command as per the Duke specification.
     * Available command includes:
     *     <pre><code>bye, help, find, done, delete, todo, deadline, event</code></pre>
     * More information in the User Guide of Duke.
     * @param echo The command to be parsed, in String format.
     * @param tasks The TaskList list in which the task generated (if any) will be added to.
     * @return a command corresponding to the parsed String
     * @throws DukeException If the command is invalid or of the wrong form.
     * @throws ParseException If the date cannot be parsed correctly.
     */
    public static Command parseCommand(String echo, TaskList tasks) throws DukeException, ParseException {
        if (echo.equals("bye")) {
            assert echo != null : "Input should not be null";
            return new ExitCommand();
        } else if (echo.equals("help")) {
            return new HelpCommand();
        } else if (echo.matches("(?i)list\\s*")) { // Querying items
            return new ListCommand();
        } else if (echo.matches("(?i)find\\s+\\S+")) {
            return new FindCommand(echo);
        } else if (echo.matches("(?i)^done.*")) { // Checks if it matches done and an integer
            int index = Parser.parseDone(echo, tasks);
            return new DoneCommand(index);
        } else if (echo.matches("(?i)^delete.*")) { // Checks if it matches delete and an integer
            int index = Parser.parseDelete(echo, tasks.length());
            return new DeleteCommand(index);
        } else if (echo.matches("(?i)^todo.*")) {
            Task toAdd = Parser.parseToDo(echo);
            return new ToDoCommand(toAdd);
        } else if (echo.matches("(?i)deadline.*")) {
            Task toAdd = Parser.parseDeadline(echo);
            return new DeadlineCommand(toAdd);
        } else if (echo.matches("(?i)event.*")) {
            Task toAdd = Parser.parseEvent(echo);
            return new EventCommand(toAdd);
        } else {
            throw new DukeException("Input not recognized.");
        }
    }


    private static Task parseToDo(String echo) throws DukeException {
        if (echo.matches("(?i)^todo\\s+\\S+.*")) {
            String text = echo.replaceFirst("(?i)^todo\\s*", "");
            return new ToDo(text);
        } else if (echo.matches("(?i)^todo\\s*")) {
            throw new DukeException("Please put a description to your task.");
        } else {
            throw new DukeException("Please put a space between the command and description.");
        }
    }

    private static Task parseDeadline(String echo) throws DukeException {
        if (echo.matches("(?i)^deadline\\s+\\S+.*\\s+\\/by\\s+\\S+.*")) {
            String[] res = echo.replaceFirst("(?i)deadline\\s+", "").strip().split("(?i)/by\\s*", 2);
            return new Deadline(res[0], res[1]);
        } else {
            String[] badText = echo.split("\\s+", 2);
            if (badText.length == 1) {
                throw new DukeException("bruh you don't have spaces");
            }
            String badCommand = badText[1];
            if (badCommand.matches(".*\\/by.*")) { // The command contains by.
                throw new DukeException("Did you miss the description or date? Remember to put spaces.");
            } else {
                throw new DukeException("Please put /by followed by the date.");
            }
        }
    }

    private static Task parseEvent(String echo) throws DukeException, ParseException {
        if (echo.matches("(?i)^event\\s+\\S+.*\\s+\\/at\\s+\\S+.*")) {
            String[] res = echo.replaceFirst("(?i)event\\s+", "").strip().split("(?i)/at\\s*", 2);
            return new Event(res[0], res[1]);
        } else {
            String[] badText = echo.split("\\s+", 2);
            if (badText.length == 1) {
                throw new DukeException("bruh you don't have spaces");
            }
            String badCommand = badText[1];
            if (badCommand.matches("(?i).*\\/at.*")) { // The command contains by.
                throw new DukeException("Did you miss the description or date? Remember to put spaces.");
            } else {
                throw new DukeException("Please put /at followed by the date.");
            }
        }
    }

    private static int parseDone(String echo, TaskList tasks) throws DukeException {
        int listLength = tasks.length();
        String res = "";
        int toBeMarked;
        if (echo.matches("(?i)done\\s+[0-9]+")) {
            res = echo.replaceFirst("done\\s+", "");
        } else {
            throw new DukeException("Done must be followed by a space and an integer!");
        }

        toBeMarked = Integer.parseInt(res); // No error: regex above guarantees that.
        if (toBeMarked > listLength || toBeMarked <= 0) {
            throw new DukeException("The task does not exist! (Index out of bounds)");
        } else if (tasks.getThingsToDo().get(toBeMarked - 1).isDone()) {
            throw new DukeException("The task is already done.");
        }
        return toBeMarked - 1;
    }

    private static int parseDelete(String echo, int listLength) throws DukeException {
        String res = "";
        if (echo.matches("(?i)delete\\s+[0-9]+")) {
            res = echo.replaceFirst("(?i)delete\\s+", "");
        } else {
            throw new DukeException("Delete must be followed by a space and an integer!");
        }

        int toBeRemoved = Integer.parseInt(res); // No error: regex above guarantees that.

        if (toBeRemoved > listLength || toBeRemoved <= 0) {
            throw new DukeException("The task does not exist! (Index out of bounds)");
        }

        return toBeRemoved - 1;
    }
}
