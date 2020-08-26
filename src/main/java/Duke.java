import java.util.Scanner;


public class Duke {
    
    private final DukeList list;
    private final Ui ui;


    public Duke() {
        this.list = new DukeList();
        this.ui = new Ui(System.in);
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
     */
    private void dukeLogic() {
        String msgInput = "";

        while (!shouldQuit(msgInput) && this.ui.hasNextLine()) {
            msgInput = this.ui.nextLine();

            if (!shouldQuit(msgInput)) {
                String[] msgArr = msgInput.split(" ");
                String keyword = msgArr[0];

                switch (keyword) {
                case ("list"):
                    String listString = this.list.toString();
                    this.ui.printMessage(listString);
                    break;

                case ("done"):
                    try {
                        String statusMsg = this.markAsDone(msgArr);
                        this.ui.printMessage(statusMsg);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.ui.printMessage("OOPS!!! The index of `done` cannot be empty.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.printMessage("OOPS!!! The index given is invalid.");
                        break;
                    }

                case ("delete"):
                    try {
                        String statusMsg = this.delete(msgArr);
                        this.ui.printMessage(statusMsg);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.ui.printMessage("OOPS!!! The index of `delete` cannot be empty.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.printMessage("OOPS!!! The index given is invalid.");
                        break;
                    }


                default:
                    try {
                        String statusString = this.list.add(msgInput);
                        this.ui.printMessage(statusString);
                        break;
                    } catch (DukeException e) {
                        this.ui.printMessage(e.getMessage());
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
        Ui.printStartMessage();
        this.list.loadFromFile();

        Scanner sc = new Scanner(System.in);
        this.dukeLogic();

        this.ui.printMessage("Bye. Hope to see you again soon!");

        this.list.writeToFile();

        sc.close();
    }


    public static void main(String[] args) {

        new Duke().start();

    }

}
