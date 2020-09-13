package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import command.Command;
import exception.AnonymousException;
import exception.DukeCreateFileException;
import exception.DukeException;
import exception.DukeFileException;
import exception.DukeFileNotFoundException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;




/**
 * duke.Duke is the main program that runs and
 * interact with the user with Command Line.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        this("data/data.txt");
    }

    /**
     * Constructs a new duke.Duke with the specified filepath.
     *
     * @param filepath file path.
     */
    public Duke(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.getExceptionTemplate(new DukeFileNotFoundException());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.getExceptionTemplate(new DukeCreateFileException());
            storage = new Storage();
            System.exit(-1);
        }
    }

    /**
     * Runs the main program of the duke.Duke. Terminates when
     * isExit is set to true.
     */
    public void mainProgram() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command c = Parser.parseCommand(command);
                String output = c.execute(tasks, ui, storage);
                System.out.println(output);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                ui.getExceptionTemplate(new DukeFileNotFoundException());
            } catch (IOException e) {
                ui.getExceptionTemplate(new DukeFileException());
            } catch (AnonymousException e) {
                ui.getExceptionTemplate(new AnonymousException(command));
            } catch (DukeException e) {
                ui.getExceptionTemplate(e);
            }
        }
    }


    public String getResponse(String command) {
        try {
            System.out.println(command);
            Command c = Parser.parseCommand(command);
            return c.execute(tasks, ui, storage);
        } catch (FileNotFoundException e) {
            return new DukeFileNotFoundException().toString();
        } catch (IOException e) {
            return new DukeFileException().toString();
        } catch (AnonymousException e) {
            return new AnonymousException(command).toString();
        } catch (DukeException e) {
            return e.toString();
        }
    }


    /**
     * The main program of duke.Duke.
     * @param args String[] arbitrary arguments.
     */
    public static void main (String[]args) {
        Duke duke = new Duke("data/data.txt");
        duke.mainProgram();

    }
}
