package duke.dependencies.parser;

import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.dukeexceptions.EmptyTaskException;
import duke.dependencies.dukeexceptions.InvalidDateException;
import duke.dependencies.dukeexceptions.UnknownCommandException;
import duke.dependencies.dukeexceptions.UnspecifiedDateException;
import duke.dependencies.executable.Executable;
import duke.dependencies.executor.Executor;
import duke.dependencies.storage.Storage;


/**
 * Parser class which parses the given input. Checks if any command is given
 * and command is valid. Also validates the format of the dates given and ensures/enforces user compliance to
 * input formats before parsing into an Executable
 */
public class Controller {
    /**
     * Object for executing the commands.
     */
    private final Executor exe;

    private boolean isInUserAuthenthicationMode = false;

    /**
     * Private constructor for a Parser object.
     */
    private Controller() {
        exe = Executor.initExecutor();
    }

    /**
     * Initializer for Parser.
     *
     * @return The Parser object.
     */
    public static Controller initController() {
        return new Controller();
    }

    /**
     * Returns a boolean to check if the user has a cached password.
     *
     * @return True if the user has already saved a password for Duke.
     */
    public boolean checkIsUserPwCached() {
        Storage s = new Storage();
        return s.checkPwCache();
    }

    /**
     * Saves user entered password/name to the file.
     *
     * @param pw The userpassword/name.
     */
    public void savedUserPw(String pw) {
        Storage s = new Storage();
        s.saveUserPw(pw);
    }


    /**
     * Parses given command and determines if it is a valid command,
     * and calls an executor to execute a valid command.
     *
     * @param command
     * @return Reply: what was done by the execution of input.
     */
    public String parseAndExec(String command) {
        Parser parser = null;
        if (!isInUserAuthenthicationMode) {
            try {
                parser = Parser.parseAndCheck(command);
            } catch (EmptyTaskException e) {
                return "You have to tell me what you want from me\nbefore I can do anything!!! O.o";

            } catch (UnspecifiedDateException e) {
                return "You need to give me the date!!!";

            } catch (UnknownCommandException e) {
                return "C'mon, you know I don't understand this!";

            } catch (InvalidDateException e) {
                return "I don't understand the date you are giving -_-\n" +
                        "Please give in either format:\n" +
                        "1. MM/dd/yyyy\n2. yyyy-MM-dd";

            } catch (DukeException e) {
                return "HUH???" + e.getMessage();
            }

            /* If no DukeException is thrown, it means user input is valid and follows all specific format */

            Executable e = parser.getExecutable();

            String reply;

            reply = exe.receiveAndExec(e);

            switch (e.getType()) {
                case LIST:
                    return String.format("Here are the tasks in your list:\n%s", reply);

                case DONE:
                    return String.format("Congratz! I will marked this task as completed for you!\n%s\n" +
                                    "Keep up the good work and continue to stay motivated.\n" +
                                    "You've only got %d task left to be completed!",
                            reply,
                            exe.getNumOfIncompleteTasks());

                case DELETE:
                    return String.format("Noted. I've removed this task:\n%s\n" +
                                    "Now you have %d tasks left in the list.",
                            reply,
                            exe.getListSize());

                case AUTHCHECK:
                    isInUserAuthenthicationMode = true;
                    return "Please enter your password before I clear your whole list.";

                case FIND:
                    return String.format("Here are the tasks matching: %s\n" +
                            reply, e.getTask().showTaskDescription());

                case ADD:
                    return String.format("Got it! I have added the task:\n%s\n"
                                    + "Now you have %s tasks in the list.",
                            reply,
                            exe.getListSize());

                default:
                    return "Something is not right. This should not be printed. Error in Controller.java";
            }
        } else {
            try {
                parser = Parser.authenthicateUser(command);
                isInUserAuthenthicationMode = false;
                Executable e = parser.getExecutable();
                String reply = exe.receiveAndExec(e);
                return reply;
            } catch (DukeException e) {
                return e.getMessage();
            }
        }

    }
}
