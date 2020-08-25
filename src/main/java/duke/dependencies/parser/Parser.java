package duke.dependencies.parser;

import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.dukeexceptions.EmptyTaskException;
import duke.dependencies.dukeexceptions.InvalidDateException;
import duke.dependencies.dukeexceptions.UnknownCommandException;
import duke.dependencies.dukeexceptions.UnspecifiedDateException;

import duke.dependencies.executor.Executor;

/**
 * Parser class which parses the given input. Checks if any command is given
 * and command is valid. Also validates the format of the dates given and ensures/enforces user compliance to
 * input formats before parsing into an Executable
 */
public class Parser {
    /** Object for executing the commands. */
    private static final Executor executor = Executor.initExecutor();

    /**
     * Private constructor for a Parser object.
     */
    private Parser() {}

    /**
     * Initializer for Parser.
     *
     * @return The Parser object.
     */
    public static Parser initParser() {
        return new Parser();
    }

    /**
     * Parses given command and determines if it is a valid command,
     * and calls an executor to execute a valid command.
     *
     * @param command
     * @return Reply: what was done by the execution of input.
     */
    public String parseAndExec(String command) {
        Checker checker = null;
        try {
            checker = Checker.parseAndCheck(command);
            String reply;
            reply = executor.receiveAndExec(checker.getExecutable());
            return reply;
//        } catch (EmptyTaskException e) {
//            return e.toString();
        } catch (EmptyTaskException e) {
             System.out.println(e.getMessage());
             return "You have to tell me what you want from me before I can do anything!!! O.o";

        } catch (UnspecifiedDateException e) {
            System.out.println(e.getMessage());
            return "You need to give me the date!!!";

        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            return "C'mon, you know I don't understand this!";

        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
            return "I don't understand the date you are giving -_-\n" +
                    "Please give in the following formats:\n" +
                    "MM/dd/yyyy or yyyy-MM-dd";

        } catch (DukeException e) {
            return "HUH???" + e.getMessage();
        }
    }


}
