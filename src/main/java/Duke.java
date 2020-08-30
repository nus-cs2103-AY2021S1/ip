import java.io.FileNotFoundException;
import java.io.IOException;

import command.Command;
import exception.AnonymousException;
import exception.DescriptionException;
import exception.DukeCreateFileException;
import exception.DukeDateTimeParserException;
import exception.DukeFileException;
import exception.DukeFileNotFoundException;
import exception.DukeKeywordException;
import exception.NoIndexException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Duke is the main program that runs and
 * interact with the user with Command Line.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new Duke with the specified filepath.
     *
     * @param filepath file path
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
     * Runs the main program of the Duke. Terminates when
     * isExit is set to true.
     */
    public void mainProgram() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                ui.getExceptionTemplate(new DukeFileNotFoundException());
            } catch (IOException e) {
                ui.getExceptionTemplate(new DukeFileException());
            } catch (AnonymousException e) {
                ui.getExceptionTemplate(new AnonymousException(command));
            } catch (DescriptionException e) {
                ui.getExceptionTemplate(new DescriptionException());
            } catch (DukeDateTimeParserException e) {
                ui.getExceptionTemplate(new DukeDateTimeParserException());
            } catch (NoIndexException e) {
                ui.getExceptionTemplate(new NoIndexException());
            } catch (DukeKeywordException e) {
                ui.getExceptionTemplate(new DukeKeywordException());
            }
        }
    }


    /**
     * The main program of Duke.
     * @param args String[] arbitrary arguments.
     */
    public static void main (String[]args) {
        Duke duke = new Duke("data/data.txt");
        duke.mainProgram();
    }
}
