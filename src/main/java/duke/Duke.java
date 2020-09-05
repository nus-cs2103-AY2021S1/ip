package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;
import javafx.application.Platform;

/**
 * Duke is the name of this program. It acts as a CLI app that reads and save
 * the user inputs. You can use it to record down tasks and marking the progress
 * of it.
 */

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initialize the Duke object
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.retrieve(tasks);
        ui = new Ui();
    }
    //@@ Oleg Mikhailov
    //Reused https://stackoverflow.com/questions/26311470/what-is-the-equivalent-of-javascript-settimeout-in-java
    // added Lambdas before.
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String reply = c.execute(tasks, ui, storage);
            if (reply.equals(ui.goodBye())) {
                setTimeout(()-> {
                    Platform.exit();
                    System.exit(0);
                }, 1000);
            }
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    public TaskList retrieveTaskList() {
        return tasks;
    }
}
