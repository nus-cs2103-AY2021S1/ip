package duke;

/**
 * Ui class will handle the interactions with the user.
 */
public class Ui {

    /**
     * Prints logo and greets the user.
     */
    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "\n____________________________________________________________"
                + "\n Hello! I'm duke.Duke"
                + "\n What can I do for you?"
                + "\n____________________________________________________________\n";
        System.out.println(logo + greet);
    }

    /**
     * Prints a list of commands which the user can use.
     */
    public static void getListOfCommands() {
        String commands = "   ____________________________________________________________"
                + "\n    Here are all your commands:"
                + "\n     list - show all tasks"
                + "\n     todo <your task> - add task"
                + "\n     deadline <your task> /by <your deadline> - add task with deadline"
                + "\n     event <your event> /at <event's timing> - add event"
                + "\n     done <index of task> - mark task as done"
                + "\n     delete <index of task> - delete task from list"
                + "\n   ____________________________________________________________\n";
        System.out.println(commands);
    }

    /**
     * Prints bye and quit the bot.
     */
    public static void exit() {
        String bye = "   ____________________________________________________________"
                + "\n    Bye! Hope to see you again soon."
                + "\n   ____________________________________________________________";
        System.out.println(bye);
    }

    /**
     * Prints error in loading the data from the file.
     */
    public static void showLoadingError() {
        System.err.println("    ERROR IN LOADING YOUR DATA :-(\n");
    }
}
