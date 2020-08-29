package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Duke is the name of this program. It acts as a CLI app that reads and save
 * the user inputs. You can use it to record down tasks and marking the progress
 * of it.
 */

public class Duke  {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Initialize the Duke object
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.retrieve(tasks);
        this.ui = new Ui();
    }
    
    //@@ Oleg Mikhailov
    //Reused https://stackoverflow.com/questions/26311470/what-is-the-equivalent-of-javascript-settimeout-in-java
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String reply = c.execute(tasks, ui, storage);
            setTimeout(()-> {
                Platform.exit();
                System.exit(0);
            }, 1000);
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Activates the duke bot.
     */
    public void echo() {
        ui.greetings();
        isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.getExitStatus();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
    }
}
