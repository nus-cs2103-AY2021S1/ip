package duke;

import duke.command.Command;

/**
 * Ui class is an Event Handler that reads user input and dictates the outcome.
 */
public class Ui {
    /**
     * Shows initial start up ASCII art of Duke.
     */
    public static String showWelcome() {
        String horizontalLine = "____________________________________\n";
        String logo = " _ .-') _               .-. .-')     ('-.  \n"
                + "( (  OO) )              \\  ( OO )  _(  OO)  \n"
                + " \\     .'_  ,--. ,--.   ,--. ,--. (,------. \n"
                + " ,`'--..._) |  | |  |   |  .'   /  |  .---' \n"
                + " |  |  \\  ' |  | | .-') |      /,  |  |     \n"
                + " |  |   ' | |  |_|( OO )|     ' _)(|  '--.  \n"
                + " |  |   / : |  | | `-' /|  .   \\   |  .--'  \n"
                + " |  '--'  /('  '-'(_.-' |  |\\   \\  |  `---. \n"
                + " `-------'   `-----'    `--' '--'  `------' \n";

        return "Hello from\n" + logo + horizontalLine
                + "Hello! I'm Duke la\n" + "What can I do for you ah?\n" + horizontalLine;
    }

    /**
     * Shows when there is an error accessing and loading duke.txt.
     */
    public void showLoadingError() {
        System.out.println("☹ AIYO!!! I cannot load the file sia...");
    }

    /**
     * Runs and starts the Duke bot.
     * @param list Loaded list of Tasks
     * @param storage Storage object that has access to duke.txt
     * @throws Exception File not found
     */
    public String initialise(TaskList list, Storage storage, String input) {
        // parses into a command
        Command c = Parser.parse(input, list, storage);
        return c.execute(input, list, storage);
    }
}
