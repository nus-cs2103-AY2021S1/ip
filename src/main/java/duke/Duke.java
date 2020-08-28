package duke;


import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.InputStream;
import java.util.Scanner;


/**
 * Main class for the Duke programme.
 */
public class Duke {

    private final DukeList taskList = new DukeList();
    private final Ui ui;


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


    /**
     * Logic framework of Duke.
     */
    private void dukeLogic() {
        boolean shouldQuit = false;
        String msgInput;

        String[] msgArr;
        Command keywordCommand;

        while (!shouldQuit && this.ui.hasNextLine()) {
            msgInput = this.ui.nextLine();

            msgArr = Parser.parseLineToArray(msgInput);
            keywordCommand = Parser.getCommand(msgInput);

            switch (keywordCommand) {
            case TERMINATE:
                shouldQuit = true;
                break;

            case INVALID:
                this.ui.printErrorMessage(String.format("OOPS!!! I'm sorry, but I don't know what `%s` means :-(", msgArr[0]));
                break;

            case LIST:
                String taskListString = this.taskList.toString();
                this.ui.printMessage(taskListString);
                break;

            case DONE:
                try {
                    String statusMsg = this.markAsDone(msgArr);
                    this.ui.printMessage(statusMsg);
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
                } catch (IndexOutOfBoundsException e) {
                    this.ui.printInvalidIndexErrorMsg();
                }
                break;

            case DELETE:
                try {
                    String statusMsg = this.delete(msgArr);
                    this.ui.printMessage(statusMsg);
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
                } catch (IndexOutOfBoundsException e) {
                    this.ui.printInvalidIndexErrorMsg();
                }
                break;

            case FIND:
                try {
                    String statusMsg = this.find(msgArr);
                    this.ui.printMessage(statusMsg);
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.ui.printEmptyIndexErrorMsg(keywordCommand.toString());
                } catch (IndexOutOfBoundsException e) {
                    this.ui.printInvalidIndexErrorMsg();
                }
                break;

            case TASK:
                try {
                    String statusString = this.taskList.add(msgInput);
                    this.ui.printMessage(statusString);
                } catch (DukeException e) {
                    this.ui.printErrorMessage(e.getMessage());
                }
                break;
            }
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
     * Programme entry point.
     *
     * @param args default argument array.
     */
    public static void main(String[] args) {

        new Duke().start();

    }

}
