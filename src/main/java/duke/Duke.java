package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.response.Response;
import duke.scanner.CommandScanner;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke class
 */
public class Duke {
    private String fileString;
    private TaskList taskList;

    /**
     * Duke's constructor for gui based program
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Duke's constructor for cli based program
     *
     * @param fileString name of the file where the commands will be output to
     */
    public Duke(String fileString) {
        this.fileString = fileString;
        this.taskList = new TaskList();
    }

    private ByteArrayOutputStream setupOutStream() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        return outContent;
    }

    private void restoreOutStream() {
        System.setOut(System.out);
    }

    /**
     * Runs a command string and returns the response
     *
     * @param commandString Raw command from user
     * @return String containing the response from Duke
     */
    public Response runCommand(String commandString) {
        ByteArrayOutputStream outContent = setupOutStream();
        Response response = new Response();
        try {
            Command cmd = Parser.parseCommandString(commandString);
            cmd.execute(this.taskList);
            if (cmd.isExit()) {
                response.setExit(true);
            }
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        }
        response.setText(outContent.toString());
        restoreOutStream();
        return response;
    }

    /**
     * Runs the duke's cli based program
     */
    public void runCli() {
        Ui.showGreet();
        CommandScanner cmdScanner = new CommandScanner();

        try {
            Storage storage = Storage.create(this.fileString);

            while (true) {
                try {
                    Command cmd = cmdScanner.nextCommand();
                    cmd.execute(this.taskList);
                    storage.write(this.taskList);
                    if (cmd.isExit()) {
                        break;
                    }
                } catch (DukeException e) {
                    Ui.showError(e.getMessage());
                }
            }
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        } catch (Exception e) {
            Ui.showUnexpectedError(e.getMessage());
        }
    }
}
