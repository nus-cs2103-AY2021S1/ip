package mattbot.uI;

import java.util.stream.Stream;

import mattbot.tasks.TaskManager;
import mattbot.tasks.task;

/**
 * Represents a UserInterface that is used by the main program. The UserInterface class handles all
 * the inputs entered by the user. It also helps to check for empty commands and prints most of the
 * replies from the bot.
 */
public class UserInterface {
    private static boolean isExit = false;
    private String input;
    private Stream<String> inputStream;

    /**
     * Saves the user input in the instance variable.
     *
     * @param input
     */
    public void input(String input) {
        this.input = input;
    }
    /**
     * Returns the task processing message when the user tries to mark a task as completed.
     */
    public static void done2() {
        System.out.println("Beep Boop Beep .....");
    }

    /**
     * Returns the starting message when the program first runs.
     *
     * @return String welcome message.
     */
    public static String welcomeMessage() {
        return "Welcome to MattBot v1.0!" + System.lineSeparator()
                + "How may I assist you today?";
    }

    /**
     * Returns the error message when an empty command is entered into the bot.
     *
     * @return String failure message.
     */
    private String failed2() {
        return "No commands entered, please enter a command!";
    }
    /**
     * Returns the message when calling the Parser and pass the user
     * input along to be broken down and understood.
     *
     * @return String action message.
     */
    private String action2(String command) {
        assert input != null;
        assert input.equals("") == false;
        return InitiateParser.parser2(command);
    }

    /**
     * Returns the ouput of the input message.
     * Determines if the user input is acceptable, whether it is null or not.
     * If the input is null, failed() is called.
     * If the input is not null, action() is called.
     *
     * @return String either the failure or action message.
     */
    public String parse2() {
        if (this.input.equals("")) {
            return failed2();
        } else {
            return action2(input);
        }
    }

    /**
     * Returns the value of the exit variable of the instance. This determines if the user entered the exit
     * command.
     *
     * @return boolean whether the user entered the exit command.
     */
    public static String stop2() {
        isExit = true;
        return "Awww, leaving so soon? Hope to see you again!";
    }

    /**
     * Returns the error message when an invalid command is entered by the user.
     *
     * @return String invalid command message.
     */
    public static String wrongCommand2() {
        return "Errroorrrr! Invalid command entered! Cannot compute!";
    }

    /**
     * Returns the message when a task is successfully added into the list.
     *
     * @param t the selected task to be added.
     * @return String the success or failed message.
     */
    public static String addedTask2(task t) {
        assert t != null;
        String result = "";
        String nLine = System.lineSeparator();
        String one = "Task has been successfully added!";
        String two = "    " + TaskManager.read(t);
        String three = "MattBot is tracking " + TaskManager.storeIndex() + " number of Tasks.task!";
        result = result + one + nLine + two + nLine + three;
        return result;
    }
}
