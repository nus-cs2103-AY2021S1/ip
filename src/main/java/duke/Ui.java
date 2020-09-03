package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user while the user is using DukeBot.
 */
public class Ui {

    private static final String DIVIDER = "----------------------------------------";

    /** Scanner object to read user inputs. */
    private final Scanner scanner;

    /**
     * Creates and initialises a new Ui object to deal with user interactions
     * using a scanner object to read user inputs.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting to welcome the user when a new session with DukeBot
     * is initialised.
     */
    public static String showWelcome() {
        /**
        String text = "      ______    ______           __      ___            __     _______  \n"
                + "      |__  __|   |  ___  |         |    \\   |     |           /_ \\    |   ___|   \n"
                + "         |  |      |  |   |  |         |      \\ |     |       //_\\ \\   |  |___   \n"
                + "  __    |  |      |  |   |  |         |   |  \\       |         / ____ \\  |____  |   \n"
                + "  |  |__|  |      |  |__|  |         |   |    \\     |        / /    \\ \\ _____| |  \n"
                + "  |______|      |______|        |__|       \\__|             /_/      \\_\\|______|  \n"
                + "                     _____   ______  ________        \n"
                + "                     |  _ \\ |  _  | |__   __|       \n"
                + "                     | |_| || | | |    | |           \n"
                + "                     |    / | | | |    | |           \n"
                + "                     |  _ \\ | |_| |    | |          \n"
                + "                     | |_| ||     |    | |           \n"
                + "                     |_____/|_____|    |_|           \n";
         **/

        String greeting = "Hello! I am JonasBot! Nice to meet you :) \n"
                + "  \nI am a task manager bot that will keep track of all your tasks. \n"
                + "  \nTo view a list of all my commands, input 'commands' \n"
                + "  \nNow that you are familiar with the commands, how may I assist you today?";

        // this.showLine();
        return (greeting);
    }

    /**
     * Scans for user inputs.
     *
     * @return String representing the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a reply when a new task has been successfully created.
     *
     * @param task Task that was created.
     * @param listSize Current number of tasks in the user's list of tasks.
     * @return String containing a reply for successful task creation.
     */
    public String printNewTask(Task task, int listSize) {
        String message = "Success! This task has been added:\n" + task.toString()
                + "\nYou have " + listSize + " tasks in your list now.";
        return message;
    }

    /**
     * Prints a reply when a task has been successfully deleted.
     *
     * @param task Task that was deleted.
     * @param listSize Current number of tasks in the user's list of tasks.
     * @return String containing a reply for successful task deletion.
     */
    public String printDeleteTask(Task task, int listSize) {
        String message = "Found it! This task has been successfully deleted: \n"
                + task.toString() + "\nYou have " + listSize + " tasks in your list now.";
        return message;
    }

    /**
     * Prints a reply when a task has been successfully marked as done.
     *
     * @param task Task which was marked as done.
     * @return String containing a reply for when a task is marked as done.
     */
    public String printDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task.toString();
        return message;
    }

    /**
     * Prints a reply containing a list of tasks in a well-ordered fashion.
     *
     * @param tasks List of tasks.
     * @return String containing the list of tasks.
     */
    public String printTasks(List<Task> tasks) {
        StringBuilder message;
        if (tasks.isEmpty()) {
            message = new StringBuilder("No tasks were found!");
        } else {
            message = new StringBuilder("Here are the tasks that I have found:");
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                String task = "\n" + String.format("%d. %s", index, tasks.get(i).toString());
                message.append(task);
            }
        }
        return message.toString();
    }

    /**
     * Prints a reply containing a list of Duke's functions and their respective commands.
     *
     * @return String containing Duke functions and commands.
     */
    public String printFunctions() {
        String commands = "  Below is a list of all the commands for my functions: \n\n"
                + "  1. Create a new task: \n\n"
                + "\t  1.1 Todo: 'todo' {task description}. For eg, todo eat \n"
                + "\t  1.2 Deadline: 'deadline' {task description} '/by' {deadline date}.\n\t\t"
                + "  Input the date using the format: 'dd/mm/yyyy hh:mm'. "
                + "For eg, deadline return book /by 12/2/2020 13:00 \n"
                + "\t  1.3 Event: 'event' {task description} '/at' {event date}.\n\t\t"
                + "  Input the date using the format: 'dd/mm/yyyy hh:mm'. "
                + "For eg, event project meeting /at 1/3/2020 12:00 \n"
                + "  \n  2. To display all tasks in your list: 'list' \n"
                + "  \n  3. To mark a task as completed: 'done' {task ID}. For eg, 'done 2' \n"
                + "  \n  4. To delete a task: 'delete' {task ID}. For eg, 'delete 2' \n"
                + "  \n  6. To search for a task by date: 'find_by_date' {date}. \n"
                + "     Input the date using the format: 'dd/mm/yyyy'. For eg, 'find_by_date 12/2/2020' \n"
                + "  \n  5. To end this chat: 'bye' \n";
        return commands;
    }


    /**
     * Prints a farewell message when the session with DukeBot is terminated.
     */
    public String showFarewell() {
        String farewellMessage = "Goodbye and I hope to see you soon! Have a fantastic day! ";
        scanner.close();
        return farewellMessage;
    }

    /**
     * Prints a line to divide and segment the chat text.
     */
    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }
}
