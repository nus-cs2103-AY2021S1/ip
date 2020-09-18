package utility;

/**
 * UI class required for displaying information.
 */
public class Ui {

    /**
     * Prints out the welcome text.
     */
    public static String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String msg = "________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "Your personal smart assistant\n"
                + "________________________________________________________\n";

        return "Hello from\n" + logo + msg;
    }

    /**
     * Prints out text to show help.
     * @return a string.
     */
    public String showHelp() {
        return "List of commands:\n"
                    + "list: lists out all tasks\n"
                    + "todo {detail}: add a todo item\n"
                    + "event {detail} /at {datetime}: add an event item\n"
                    + "deadline {detail} /at {datetime}: add a deadline item\n"
                    + "delete {position(s)}: removes the item(s) at the specified position(s)\n"
                    + "done {position}: marks the item as done\n"
                    + "find {detail}: searches for a task contain that detail\n";
    }

    /**
     * Prints out instructional messages for adding tasks.
     */
    public void showTaskHelp() {
        String msg = "You can add 3 types of tasks, Todo, Event or Deadline. \n"
                + "Use the following format: \n"
                + "[type] [description of task] /[at or by] [Date in DD/MM/YYYY] [Time in HHMM]"
                + "For example: \nevent project meeting /at 08/10/2020 2000\n";
        System.out.println(msg);
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}

