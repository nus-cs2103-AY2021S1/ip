package duke;


import java.io.InputStream;
import java.util.Scanner;

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
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
    }


    /**
     * Constructor for Duke.
     *
     * @param inputStream input stream to read user inputs from.
     */
    public Duke(InputStream inputStream) {
        this.ui = new Ui(inputStream);
    }


    /**
     * Determine if programme should quit given the input message.
     *
     * @param msgInput Message input from user.
     * @return if programme should quit.
     */
    private static Boolean shouldQuit(String msgInput) {
        return msgInput.equals(Duke.QUIT_STRING);
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
     * @param inputTextArr String array of the input text, split by " "
     * @return statusMsg to be printed.
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
     * Output index are the indexes in original dukeList.
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
        case TERMINATE:
            this.shouldQuit = true;
            statusMessage = "bye";
            break;

        case INVALID:
            this.ui.printErrorMessage(String.format("OOPS!!! I'm sorry, but I don't know what `%s` means :-(", msgArr[0]));
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
                this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case DELETE:
            try {
                statusMessage = this.delete(msgArr);
                this.ui.printMessage(statusMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case FIND:
            try {
                statusMessage = this.find(msgArr);
                this.ui.printMessage(statusMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
            } catch (IndexOutOfBoundsException e) {
                this.ui.printInvalidIndexErrorMsg();
            }
            break;

        case TASK:
            try {
                statusMessage = this.taskList.add(msgInput);
                this.ui.printMessage(statusMessage);
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            }
            break;

        default:
            statusMessage = "bye";
            Platform.exit();
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
     */
    public void start() {
        Ui.printStartMessage();
        this.taskList.loadFromFile();

        Scanner sc = new Scanner(System.in);
        this.dukeLogic();

        this.ui.printMessage("Bye. Hope to see you again soon!");

        this.taskList.writeToFile();

        sc.close();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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

        new Duke().start();

    }

}
