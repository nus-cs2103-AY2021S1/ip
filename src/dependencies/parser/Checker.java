package dependencies.parser;

import dependencies.dukeexceptions.DukeException;
import dependencies.dukeexceptions.EmptyTaskException;
import dependencies.dukeexceptions.UnknownCommandException;
import dependencies.executable.Command;
import dependencies.executable.Executable;
import dependencies.task.Task;

import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Checker {
    private Executable command;

    /**
     * Constructor for the checker object. Validates the command given/if and creates an Executable object
     * that can be passed to the executor.
     *
     * @param e string to be parsed into a command
     */
    private Checker(Executable e) {
        this.command = e;
    }

    /**
     * Returns the Executable object.
     * Executable object holds the task inside.
     *
     * @return the executable object
     */
    public Executable getExecutable() {
        return this.command;
    }

    /**
     * Static factory method for constructing the checker object.
     * The Checker should have an Executable containing the task.
     *
     * @param s command
     * @return Checker object
     */
    public static Checker parseAndCheck(String s) throws DukeException {
        try {
            return parseExplicitCommand(s);
        } catch (DukeException e) {
            throw e;
        }
    }


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
    private static Checker parseExplicitCommand(String s) throws DukeException {
        Executable e;
        if (s.contains("list") || s.contains("List")) {
            e = Command.createListCommand(null);
        }
        else if (checkForWord(s, "done")) {
            String task = cutOutTheWord(s, "done");
            if (task.isBlank() || task.isEmpty()) {
                throw new EmptyTaskException("Sorry, you have to tell me which task you have completed.");
            }
            Task t = Task.createMiscTask(task);
            e = Command.createDoneCommand(t);

        } else if (checkForWord(s, "todo")) {
            String task = cutOutTheWord(s, "todo");
            Task t = Task.createTodo(task);
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Sorry, but your description of a task cannot be empty. -_-");
            }
            e = Command.createAddCommand(t);

        } else if (checkForWord(s, "event")) {
            String task = cutOutTheWord(s, "event");
            if (task.isBlank() || task.isEmpty()) {
                throw new EmptyTaskException("Please tell me the event that will be happening!");
            }
            String[] arr = task.split("/?at");
            Task t = Task.createEvent(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        } else  if (checkForWord(s, "deadline")) {
            String task = cutOutTheWord(s, "deadline");
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("Please describe what you need to do.");
            }
            String[] arr = task.split("/?by");
            Task t = Task.createDeadline(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        } else if (checkForWord(s, "delete")) {
            String task = cutOutTheWord(s, "delete");
            if (task.isEmpty() || task.isBlank()) {
                throw new EmptyTaskException("You have to tell me what to delete! LOL");
            }
            Task t = Task.createMiscTask(task);
            e = Command.createDeleteCommand(t);

        } else {
            throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return new Checker(e);
    }


    /**
     * Case insensitive check for a word.
     * @param line
     * @param word
     * @return
     */
    private static boolean checkForWord(String line, String word) {
        return Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(line).find();
    }

    /**
     * Case Insensitive cutting out of the word.
     * @param line
     * @param cmd
     * @return
     */
    private static String cutOutTheWord(String line, String cmd) {
        // TODO: Should I use ENGLISH or UK????
        String c2 = cmd.toUpperCase(Locale.UK);
        String l2 = line.toUpperCase(Locale.UK);
        int idx = l2.indexOf(c2);
        return line.substring(idx + cmd.length()).trim();

    }

    private boolean isTodo(String s) {return false;}

    public boolean isValidCommand(String s) {
        return false;
    }

    /* -------------------------------------- Additional Feature Section ---------------------------------- */

    /**
     * Instead of parsing just natural language, this function will be able to parse a natural sentence.
     * eg. "I have a meeting on Monday." -> "event meeting /at Monday"
     * Remember to edit this only in the branch "add-ons".
     *
     * @param s
     * @return
     */
    private static Checker parseNaturalLanguage(String s) {

        return new Checker(null);

    }

}
