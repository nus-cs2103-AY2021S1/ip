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

            assert statusMsg != null : "Status message not found";
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

            assert statusMsg != null : "Status message not found";
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
     * Sorts tasks chronologically (by date).
     * Todos always come first, in alphabetical order.
     * If tasks with dates have the same date, they are sorted alphabetically.
     *
     * @return Status message to be printed.
     */
    private String sort() {
        String statusMsg = this.taskList.sort();

        assert statusMsg != null;
        return statusMsg;
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

        assert statusMsg != null : "Status message not found";
        return statusMsg;
    }


    /**
     * Helper function to handle command cases.
     *
     * @param userInputStr   User input string.
     * @param keywordCommand Parsed command of user input.
     * @return Status message to be printed after command is executed .
     */
    private String handleCommands(String userInputStr, Command keywordCommand) {
        String[] msgArr = Parser.parseLineToArray(userInputStr);
        String statusMessage;

        switch (keywordCommand) {
        case INVALID:
            statusMessage = String.format("Yo whatchu mean by `%s`???", msgArr[0]);
            break;
        case LIST:
            statusMessage = this.taskList.toString();
            break;
        case DONE:
            statusMessage = this.markAsDone(msgArr);
            break;
        case DELETE:
            statusMessage = this.delete(msgArr);
            break;
        case FIND:
            statusMessage = this.find(msgArr);
            break;
        case SORT:
            statusMessage = this.sort();
            break;
        case TASK:
            statusMessage = this.taskList.add(userInputStr);
            break;
        case TERMINATE:
            // Fallthrough
        default:
            this.shouldQuit = true;
            statusMessage = "Bye. Hope to see you again soon!";
            this.exit();
            break;
        }

        assert statusMessage != null;
        return statusMessage;
    }


    /**
     * Logic helper function.
     *
     * @param msgInput User input string.
     * @return Status message to be printed.
     */
    private String dukeLogicHelper(String msgInput) {
        Command keywordCommand = Parser.getCommand(msgInput);
        String statusMessage;

        try {
            statusMessage = this.handleCommands(msgInput, keywordCommand);
            this.ui.printMessage(statusMessage);
        } catch (DukeException e) {
            statusMessage = this.ui.printErrorMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            statusMessage = this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
        } catch (IndexOutOfBoundsException e) {
            statusMessage = this.ui.printInvalidIndexErrorMsg();
        }

        assert statusMessage != null;
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
            assert msgInput != null;
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
            Thread.sleep(2000);
        } catch (Exception ignored) {
            // Failed thread sleep is ignored,
            // exit Duke normally.
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
