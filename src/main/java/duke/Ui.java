package duke;

public class Ui {
    private static final String logo =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";

    /**
     * Shows the welcome message
     */
    public static void greet() {
        System.out.println(logo);
        System.out.println("Hello! This is Duke.\nWhat can I do for you?");
    }

    /**
     * Shows the exit message
     */
    public static void exit() {
        System.out.println("Bye. Duke is always there for you!");
        System.out.println(logo);
    }

    public static void commandError() {
        System.out.println("Sorry, I do not know what that means :(");
    }

    public static void fileError() {
        System.out.println("Oops! Something went wrong :(");
    }

    /**
     * Shows a given message to the user
     * @param msg message to show
     */
    public static void userMessage(String msg) {
        System.out.println(msg);
    }

}
