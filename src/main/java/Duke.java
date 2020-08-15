import java.util.Scanner;


public class Duke {

    private static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Prints a given message within line separators.
     *
     * @param msg Message to be printed.
     */
    private static void printMessage(String msg) {
        Duke.printSeparator();
        System.out.println("\t" + msg);
        Duke.printSeparator();
    }


    /**
     * Prints the start message.
     */
    private static void printStartMsg() {
        Duke.printSeparator();
        System.out.println(Duke.LOGO);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        Duke.printSeparator();
    }


    /**
     * Prints a line separator.
     */
    private static void printSeparator() {
        System.out.println("\t____________________________________________________________");
    }


    /**
     * Determine if programme should quit given the input message.
     *
     * @param msgInput Message input from user.
     * @return if programme should quit.
     */
    private static Boolean shouldQuit(String msgInput) {
        final String QUIT_STRING = "bye";

        return msgInput.equals(QUIT_STRING);
    }


    /**
     * Logic framework of Duke.
     *
     * @param sc Scanner object.
     */
    private void dukeLogic(Scanner sc) {
        String msgInput = "";

        while (!shouldQuit(msgInput)) {
            msgInput = sc.nextLine();

            if (!shouldQuit(msgInput)) {
                Duke.printMessage(msgInput);
            }
        }
    }


    /**
     * Method to start the Duke programme.
     */
    public void start() {
        Duke.printStartMsg();

        Scanner sc = new Scanner(System.in);
        this.dukeLogic(sc);

        Duke.printMessage("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {

        new Duke().start();

    }

}
