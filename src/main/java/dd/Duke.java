package dd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import dd.commands.Command;
import dd.exception.DukeException;
import dd.parser.Parser;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * The main Duke class to create and manage a task list.
 */
public class Duke {

    private DataStorage ds;
    private Ui ui;
    private TaskList tasks;

    public boolean isExit = false;

    /**
     * Class Constructor.
     */
    public Duke() {
        this.ds = new DataStorage();
        this.ui = new Ui();

        try {
            tasks = new TaskList(ds.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String sendGreeting() {
        //@@author g-erm-reused
        //Reused from https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        //with adaptation

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        ui.greeting();

        // Put things back
        System.out.flush();
        System.setOut(old);

        return baos.toString();

        //@@author
    }

    /**
     * Takes in user input, parses into a command and executes
     * the next command till an exit command is given.
     */
    public String getResponse(String input) {

        //@@author g-erm-reused
        //Reused from https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        //with adaptation

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        try {
            Command c = Parser.parse(input);

            c.execute(tasks, ui, ds);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        // Put things back
        System.out.flush();
        System.setOut(old);

        return baos.toString();

        //@@author
    }
}
