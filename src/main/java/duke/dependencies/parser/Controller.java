package duke.dependencies.parser;

import duke.UserAuthenticator;
import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.dukeexceptions.EmptyTaskException;
import duke.dependencies.dukeexceptions.InvalidDateException;
import duke.dependencies.dukeexceptions.UnknownCommandException;
import duke.dependencies.dukeexceptions.UnspecifiedDateException;
import duke.dependencies.executable.Command;
import duke.dependencies.executable.Executable;
import duke.dependencies.executor.Executor;


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

    /**
     * Object to authenticate the user.
     */
    private final UserAuthenticator userAuthenticator;

    private boolean isInUserAuthenticationMode = false;

    /**
     * Private constructor for a Parser object.
     */
    private Controller() {
        exe = Executor.initExecutor();
        userAuthenticator = UserAuthenticator.init();
    }

    /**
     * Initializer for Parser.
     *
     * @return The Parser object.
     */
    public static Controller init() {
        return new Controller();
    }

    /**
     * Checks the whether the user has entered his details.
     *
     * @return True if the user details has already been cached.
     */
    public boolean hasUserEnteredDetails() {
        return userAuthenticator.isUserCached();
    }

    /**
     * Saves the given user details.
     *
     * @param details User details to be saved.
     */
    public void saveUserDetails(String details) {
        userAuthenticator.save(details);
    }


    /**
     * Parses given command and determines if it is a valid command,
     * and calls an executor to execute a valid command.
     *
     * @param command The given input by the user.
     * @return Reply: what was done by the execution of input.
     */
    public String parseAndExec(String command) {
        Parser parser;
        if (!isInUserAuthenticationMode) {
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
                isInUserAuthenticationMode = true;
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

            // In user authentication mode. Check for user password validity.
            if (userAuthenticator.check(command)) {
                isInUserAuthenticationMode = false;
                String reply = exe.receiveAndExec(Command.createClearCacheCommand(null));
                return reply;
            } else {
                // Quits user authentication mode even if the password entered is wrong.
                // Clearing of task list will not execute.
                isInUserAuthenticationMode = false;
                return "You've entered the wrong pw!";
            }
        }

    }
}
