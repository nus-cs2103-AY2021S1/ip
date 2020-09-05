package duke;


import java.io.InputStream;

import duke.commands.Command;
import duke.exceptions.DukeException;

import javafx.application.Platform;


/**
 * Main class for the Duke programme.
 */
public class Duke {

    private static final String QUIT_STRING = "bye";

    private final DukeList taskList = new DukeList();
    private final Ui ui;

    private boolean shouldQuit = false;


    /**
     * Creates a new Duke object.
     */
    public Duke() {
        this.ui = new Ui();
    }


    /**
     * Creates a new Duke object.
     *
     * @param inputStream Input stream from which user inputs will be read.
     */
    public Duke(InputStream inputStream) {
        this.ui = new Ui(inputStream);
    }


    /**
     * Checks if programme should quit given the input message.
     *
     * @param msgInput Message input from user.
     * @return True if programme should quit, false otherwise.
     */
    private static Boolean shouldQuit(String msgInput) {
        return msgInput.equals(Duke.QUIT_STRING);
    }


    /**
     * Marks an item as done.
     *
     * @param inputTextArr String array of the input text, split by " ".
     * @return Status message to be printed.
     * @throws IndexOutOfBoundsException Invalid index given or input text array is invalid.
     */
    private String markAsDone(String[] inputTextArr) throws IndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(inputTextArr[1]);
            String statusMsg = this.taskList.markAsDone(index);
            return statusMsg;

        } catch (ArrayIndexOutOfBoundsException e) {
            // forwards ArrayIndexOutOfBoundsException from Integer.parseInt()
            // inputTextArr is invalid.
            throw new ArrayIndexOutOfBoundsException("Invalid inputTextArr.");
        } catch (IndexOutOfBoundsException e) {
            // exception thrown from DukeList.markAsDone()
            // index given in input text is invalid.
            throw new IndexOutOfBoundsException("Invalid index given.");
        }
    }


    /**
     * Deletes an item from the taskList.
     *
     * @param inputTextArr String array of the input text, split by " ".
     * @return Status message to be printed.
     * @throws IndexOutOfBoundsException Invalid index given or input text array is invalid.
     */
    private String delete(String[] inputTextArr) throws IndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(inputTextArr[1]);
            String statusMsg = this.taskList.delete(index);
            return statusMsg;
        } catch (ArrayIndexOutOfBoundsException e) {
            // forwards ArrayIndexOutOfBoundsException from Integer.parseInt()
            // inputTextArr is invalid.
            throw new ArrayIndexOutOfBoundsException("Invalid inputTextArr.");
        } catch (IndexOutOfBoundsException e) {
            // exception thrown from DukeList.delete()
            // index given in input text is invalid.
            throw new IndexOutOfBoundsException("Invalid index given.");
        }
    }


    /**
     * Finds tasks based on keyword.
     * Output indexes are the indexes of elements in the original dukeList.
     *
     * @param inputTextArr String array of the input text, split by " ".
     */
    private String find(String[] inputTextArr) {
        String keyword = Parser.getItemSubstring(inputTextArr);
        String statusMsg = this.taskList.find(keyword);
        return statusMsg;
    }


    private String dukeLogicHelper(String msgInput) {

        String[] msgArr;
        Command keywordCommand;

        msgArr = Parser.parseLineToArray(msgInput);
        keywordCommand = Parser.getCommand(msgInput);

        String statusMessage = null;

        switch (keywordCommand) {

        case INVALID:
            statusMessage = this.ui.printErrorMessage(String.format("OOPS!!! I'm sorry, but I don't know what `%s` means :-(", msgArr[0]));
            break;

        case LIST:
            statusMessage = this.taskList.toString();
            this.ui.printMessage(statusMessage);
            break;

        case DONE:
            try {
                statusMessage = this.markAsDone(msgArr);
                this.ui.printMessage(statusMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                statusMessage = this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                statusMessage = this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case DELETE:
            try {
                statusMessage = this.delete(msgArr);
                this.ui.printMessage(statusMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                statusMessage = this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                statusMessage = this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case FIND:
            try {
                statusMessage = this.find(msgArr);
                this.ui.printMessage(statusMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                statusMessage = this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                statusMessage = this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case TASK:
            try {
                statusMessage = this.taskList.add(msgInput);
                this.ui.printMessage(statusMessage);
            } catch (DukeException e) {
                statusMessage = this.ui.printErrorMessage(e.getMessage());
            }
            break;

        case TERMINATE:
            // Fallthrough
        default:
            this.shouldQuit = true;
            statusMessage = "Bye. Hope to see you again soon!";
            this.exit();
            break;
        }

        return statusMessage;

    }


    /**
     * Logic framework of Duke.
     */
    private void dukeLogic() {
        this.shouldQuit = false;
        String msgInput;

        while (!shouldQuit && this.ui.hasNextLine()) {
            msgInput = this.ui.nextLine();
            dukeLogicHelper(msgInput);
        }
    }


    /**
     * Starts the Duke programme logic.
     *
     * @param isCli Whether programme should run as command line.
     *              Cmd launchers will pass in true, Gui launchers will pass in false.
     */
    public void start(boolean isCli) {
        this.taskList.loadFromFile();

        if (isCli) {
            // programme starts
            this.ui.printStartMessage();

            // programme execution here
            this.dukeLogic();

            // programme ends
            this.ui.printEndMessage();
            this.taskList.writeToFile();
        }

    }


    /**
     * Gets the start message for Duke.
     *
     * @return Start message string.
     */
    public String getStartMessage() {
        return Ui.getStartMessage();
    }


    /**
     * Gets the end message for Duke.
     *
     * @return End message string.
     */
    public String getEndMessage() {
        return Ui.getEndMessage();
    }


    /**
     * Exits duke and quit the Gui platform.
     */
    private void exit() {
        this.taskList.writeToFile();

        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }

        Platform.exit();
        System.exit(0);
    }


    /**
     * Gets user response to the input string.
     *
     * @param input Input string from user to be processed.
     */
    public String getResponse(String input) {
        return this.dukeLogicHelper(input);
    }


    /**
     * Programme entry point.
     *
     * @param args default argument array.
     */
    public static void main(String[] args) {

        new Duke().start(true);

    }

}
