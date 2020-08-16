import java.util.Scanner;


public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final DukeList list;


    public Duke() {
        this.list = new DukeList();
    }


    /**
     * Prints a given message within line separators.
     *
     * @param msg Message to be printed.
     */
    private static void printMessage(String msg) {
        Duke.printSeparator();
        System.out.println(msg);
        Duke.printSeparator();
    }


    /**
     * Prints the start message.
     */
    private static void printStartMsg() {
        Duke.printSeparator();
        // System.out.println(Duke.LOGO);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        Duke.printSeparator();
    }


    /**
     * Prints a line separator.
     */
    private static void printSeparator() {
        System.out.println("____________________________________________________________");
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

        while (!shouldQuit(msgInput) && sc.hasNextLine()) {
            msgInput = sc.nextLine();

            if (!shouldQuit(msgInput)) {
                String[] msgArr = msgInput.split(" ");
                String keyword = msgArr[0];

                switch (keyword) {
                    case ("list"):
                        String listString = this.list.toString();
                        Duke.printMessage(listString);
                        break;

                    case ("done"):
                        int index = Integer.parseInt(msgArr[1]);
                        String statusMsg = this.list.markAsDone(index);
                        Duke.printMessage(statusMsg);
                        break;

                    default:
                        String statusString = this.list.add(msgInput);
                        Duke.printMessage(statusString);
                        break;
                }
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
