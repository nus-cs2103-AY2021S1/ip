package duke;

import duke.command.Command;

import java.util.List;
import java.util.Scanner;

/**
 * Ui class is an Event Handler that reads user input and dictates the outcome
 */
public class Ui {
    /**
     * Shows initial start up ASCII art of Duke
     */
    public void showWelcome() {
        String hor_line = "____________________________________\n";
        String logo = " _ .-') _               .-. .-')     ('-.  \n"
                + "( (  OO) )              \\  ( OO )  _(  OO)  \n"
                + " \\     .'_  ,--. ,--.   ,--. ,--. (,------. \n"
                + " ,`'--..._) |  | |  |   |  .'   /  |  .---' \n"
                + " |  |  \\  ' |  | | .-') |      /,  |  |     \n"
                + " |  |   ' | |  |_|( OO )|     ' _)(|  '--.  \n"
                + " |  |   / : |  | | `-' /|  .   \\   |  .--'  \n"
                + " |  '--'  /('  '-'(_.-' |  |\\   \\  |  `---. \n"
                + " `-------'   `-----'    `--' '--'  `------' \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(hor_line + "Hello! I'm Duke la\n" + "What can I do for you ah?\n" + hor_line);
    }

    /**
     * Shows when there is an error accessing and loading duke.ser
     */
    public void showLoadingError() {
        System.out.println("☹ AIYO!!! I cannot load the file sia...");
    }

    /**
     * Runs and starts the Duke bot
     * @param list Loaded list of Tasks
     * @param storage Storage object that has access to duke.ser
     * @throws Exception File not found
     */
    public void initialise(TaskList list, Storage storage) throws Exception {
        String hor_line = "____________________________________\n";
        List<Task> todo_list = list.getList();
        showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String command = sc.nextLine();
            // parses into a command
            Command c = Parser.parse(command, list, storage);
            c.execute(command, list, storage);
            isExit = c.isExit();
        }
    }
}