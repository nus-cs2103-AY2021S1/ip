/**
 * <h1>DukeUi Class</h1>
 * The DukeUi class manages the interactions with
 * the User.
 * It consist of mainly void
 * methods that display a formatted text that is readable
 * by the User.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class DukeUi {

    public DukeUi() { }

    /**
     * Prints out a line to mark an individual message
     */
    public void showLine() {
        System.out.println("\n_________________________________________\n");
    }

    /**
     * Prints out the Welcome Message when Duke is intialised
     * as an opening message
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, Sir?");
    }

    /**
     * Prints out the Goodbye Message when Duke is exited
     */
    public void goodbyeMessage() {
        System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
    }

    /**
     * Print out the error message when an exception is catch
     * by wrapping it in separating lines to format the message.
     * @param error The error message
     */
    public void showError(String error) {
        System.out.println("Sorry an unexpected error occured!! :( \n" +
                            "\n_________________________________________\n"
                            + error
                            + "\n_________________________________________");
    }

}
