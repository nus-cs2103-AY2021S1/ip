package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.response.Response;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke class
 */
public class Duke {
    private TaskList taskList;
    private final PrintStream originalSystemOut = System.out;

    /**
     * Duke's constructor
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    private ByteArrayOutputStream setupOutStream() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        return outContent;
    }

    private void restoreOutStream() {
        System.setOut(this.originalSystemOut);
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

    public TaskList getTaskList() {
        return taskList;
    }
}
