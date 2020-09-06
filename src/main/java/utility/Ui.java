package utility;

/**
 * UI class required for displaying information.
 */
public class Ui {

    /**
     * Prints out the welcome text.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String msg = "____________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "Your personal smart assistant\n"
                + "____________________________________________________________";
        System.out.println(msg);
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
     * Show general instructions.
     */
    public void showGeneralHelp() {

        System.out.println();
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}

