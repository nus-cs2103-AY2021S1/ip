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
     * Marks an item as done.
     *
     * @param inputTextArr String array of the input text, split by " ".
     * @return statusMSg to be printed.
     * @throws IndexOutOfBoundsException Invalid index given or input text array is invalid.
     */
    private String markAsDone(String[] inputTextArr) throws IndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(inputTextArr[1]);
            String statusMsg = this.list.markAsDone(index);
            return statusMsg;
        } catch (NullPointerException e) {
            // exception thrown from DukeList.markAsDone()
            // index given in input text is invalid.
            throw new IndexOutOfBoundsException("Invalid index given.");
        } catch (ArrayIndexOutOfBoundsException e) {
            // forwards ArrayIndexOutOfBoundsException from Integer.parseInt()
            // inputTextArr is invalid.
            throw new ArrayIndexOutOfBoundsException("Invalid inputTextArr.");
        }
    }


    /**
     * Deletes an item from the list.
     *
     * @param inputTextArr String array of the input text, split by " "
     * @return statusMsg to be printed.
     * @throws IndexOutOfBoundsException Invalid index given or input text array is invalid.
     */
    private String delete(String[] inputTextArr) throws IndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(inputTextArr[1]);
            String statusMsg = this.list.delete(index);
            return statusMsg;
        } catch (NullPointerException e) {
            // exception thrown from DukeList.delete()
            // index given in input text is invalid.
            throw new IndexOutOfBoundsException("Invalid index given.");
        } catch (ArrayIndexOutOfBoundsException e) {
            // forwards ArrayIndexOutOfBoundsException from Integer.parseInt()
            // inputTextArr is invalid.
            throw new ArrayIndexOutOfBoundsException("Invalid inputTextArr.");
        }

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
                    try {
                        String statusMsg = this.markAsDone(msgArr);
                        Duke.printMessage(statusMsg);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.printMessage("OOPS!!! The index of `done` cannot be empty.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        Duke.printMessage("OOPS!!! The index given is invalid.");
                        break;
                    }

                case ("delete"):
                    try {
                        String statusMsg = this.delete(msgArr);
                        Duke.printMessage(statusMsg);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.printMessage("OOPS!!! The index of `delete` cannot be empty.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        Duke.printMessage("OOPS!!! The index given is invalid.");
                        break;
                    }


                default:
                    try {
                        String statusString = this.list.add(msgInput);
                        Duke.printMessage(statusString);
                        break;
                    } catch (DukeException e) {
                        Duke.printMessage(e.getMessage());
                        break;
                    }
                }
            }
        }
    }


    /**
     * Method to start the Duke programme.
     */
    public void start() {
        Duke.printStartMsg();
        this.list.loadFromFile();

        Scanner sc = new Scanner(System.in);
        this.dukeLogic(sc);

        Duke.printMessage("Bye. Hope to see you again soon!");
        
        this.list.writeToFile();

        sc.close();
    }


    public static void main(String[] args) {

        new Duke().start();

    }

}
