package duke.dependencies.parser;

import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.dukeexceptions.EmptyTaskException;
import duke.dependencies.dukeexceptions.InvalidDateException;
import duke.dependencies.dukeexceptions.UnknownCommandException;
import duke.dependencies.executable.Command;
import duke.dependencies.executable.Executable;
import duke.dependencies.task.Task;
import duke.dependencies.task.TaskDate;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * A Class to parse commands.
 */
public class Parser {
    private final Executable command;

    /**
     * Constructor for the checker object. Validates the command given/if and creates an Executable object
     * that can be passed to the executor.
     *
     * @param e String to be parsed into an Executable for Executor.
     */
    private Parser(Executable e) {
        this.command = e;
    }

    /**
     * Returns the Executable object.
     * Executable object holds the Task object inside.
     *
     * @return The Executable object.
     */
    public Executable getExecutable() {
        return this.command;
    }


    /**
     *
     * @param s String to be parsed.
     * @return A parser object wrapped around an Executable.
     * @throws DukeException Thrown if the given string input from
     * the user does not match any form of valid commands.
     */
    public static Parser parseAndCheck(String s) throws DukeException {
        try {
            return parseExplicitCommand(s);
        } catch (DukeException e) {
            throw e;
        }
    }

//    /**
//     *
//     *
//     * @param pw Password/name to be checked with.
//     * @return
//     * @throws DukeException Thrown if the given string is incorrect.
//     * AKA no authorisation.
//     */
//    public static Parser authenthicateUser(String pw) throws DukeException {
//        Storage s = new Storage();
//        if (s.checkUserAuth(pw)) {
//            try {
//                return parseExplicitCommand("authentication::success");
//            } catch (DukeException e) {
//                throw e;
//            }
//        } else {
//            throw new InvalidPassException("Wrong password");
//        }
//    }


    /* -------------------------------------- END OF PUBLIC METHODS ----------------------------------------------- */


    /**
     * Checks for command that is passed in explicit format.
     * eg.
     * 1) "event (task) /at (date)"
     * 2) "todo (task)"
     * 3) "deadline (task) /by (date)"
     *
     * @param s input
     * @return Checker object
     */
    private static Parser parseExplicitCommand(String s) throws DukeException {
        Executable e;

        /* LIST COMMAND */
        if (checkForWord(s, "list")) {
            e = Command.createListCommand(null);
        }
        /* DONE COMMAND */
        else if (checkForWord(s, "done")) {
            String task = cutOutTheWord(s, "done ");
            if (task.isBlank() || task.isEmpty()) {
                throw new EmptyTaskException("Error: Done task cannot be empty");
            }
            Task t = Task.createMiscTask(task);
            e = Command.createDoneCommand(t);

        }
        /* TODO_COMMAND */
        else if (checkForWord(s, "todo")) {
            String task = cutOutTheWord(s, "todo ");
            Task t = Task.createTodo(task);
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Error: Todo task cannot be empty");
            }
            e = Command.createAddCommand(t);

        }
        /* EVENT COMMAND */
        else if (checkForWord(s, "event")) {
            String task = cutOutTheWord(s, "event ");
            if (task.isBlank() || task.isEmpty()) {
                throw new EmptyTaskException("Error: Event task cannot be empty");
            }
            String[] arr = task.split("/at");
            if (!TaskDate.isValidFormat(arr[1].trim())) {
                throw new InvalidDateException("Error: Date format not accepted.");
            }
            Task t = Task.createEvent(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        }
        /* DEADLINE COMMAND */
        else  if (checkForWord(s, "deadline")) {
            String task = cutOutTheWord(s, "deadline ");
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Error: Deadline tasks cannot be empty");
            }
            String[] arr = task.split("/by");
            if (!TaskDate.isValidFormat(arr[1].trim())) {
                throw new InvalidDateException("Error: Date format not accepted.");
            }
            Task t = Task.createDeadline(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        }
        /* DELETE COMMAND */
        else if (checkForWord(s, "delete")) {
            String task = cutOutTheWord(s, "delete ");
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Error: Task to be deleted cannot be empty");
            }
            Task t = Task.createMiscTask(task);
            e = Command.createDeleteCommand(t);

        }
        /* FIND COMMAND */
        else if (checkForWord(s, "find")) {
            String task = cutOutTheWord(s,"find ");
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Error: Empty field for find: keyword");
            }
            Task t = Task.createMiscTask(task);
            e = Command.createFindCommand(t);
        }
        /* CHECK AUTHENTICATION COMMAND */
        else if (checkForWord(s, "clear data")) {
            e = Command.createCheckAuthCommand(null);
        }
        /* CLEAR CACHE(TASK LIST) COMMAND */
        else if (checkForWord(s, "authentication::success")) {
            e = Command.createClearCacheCommand(null);
        }
        /* MYSTERIOUS ERROR/COMMAND */
        else {
            throw new UnknownCommandException("Error: Unknown command");
        }

        return new Parser(e);
    }

    /**
     * Case insensitive check for a word.
     *
     * @param line Line to check.
     * @param word Word to check for.
     * @return True if the word is in the line.
     */
    public static boolean checkForWord(String line, String word) {
        return Pattern.compile(
                String.format(".*?\\b%s\\b.*?", word),
                Pattern.CASE_INSENSITIVE)
                .matcher(line)
                .find();
    }

    /**
     * Returns the string with the given word cut out, case-insensitive removal
     *
     * @param line Line to cut from.
     * @param word Word to cut out.
     * @return Line without the command.
     */
    public static String cutOutTheWord(String line, String word) {
        String c2 = word.toUpperCase(Locale.UK);
        String l2 = line.toUpperCase(Locale.UK);
        int idx = l2.indexOf(c2);
        return line.substring(idx + word.length()).trim();

    }


    /* ---------------------------------------- Additional Feature Section ------------------------------------------ */

    /**
     * Instead of parsing just natural language, this function will be able to parse a natural sentence.
     * eg. "I have a meeting on Monday." -> "event meeting /at Monday"
     * Remember to edit this only in the branch "add-ons".
     *
     * @param s
     * @return
     */
    private static Parser parseNaturalLanguage(String s) {

        return new Parser(null);

    }

}
