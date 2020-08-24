package seedu.bob;

import seedu.bob.commands.Command;
import seedu.bob.common.Messages;
import seedu.bob.data.task.Tasklist;
import seedu.bob.parser.Parser;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;
import seedu.bob.exceptions.BobException;

import java.io.IOException;

/**
 * Represents the task-managing ChatBot.
 * @author Lim Zi Yang
 */
public class Bob {
    private final Storage storage;
    private final Tasklist tasks;
    private final Ui ui;

    public Bob(String filePath) throws IOException {
        Tasklist tempTasks;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tempTasks = new Tasklist(storage);
        } catch (BobException | IOException e) {
            System.out.println(Messages.LOADINGERROR);
            tempTasks = new Tasklist();
        }
        this.tasks = tempTasks;
    }

    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExited();
            } catch (IOException e){
                ui.showUpdatingError();
            } catch (BobException e) {
                ui.showError(e);
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * The program initializes Bob and reads user inputs for Bob.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
         //File path of saved task list
         String filePath = System.getProperty("user.dir").endsWith("text-ui-test")
                 ? "test.txt"
                 : System.getProperty("user.dir").endsWith("ip")
                 ? "data/bob.txt"
                 // Creates a save file on the user's home directory if user is not in ip directory
                 : System.getProperty("user.home") + "/bob.txt";
         try {
             new Bob(filePath).run();
         } catch (IOException e) {
             System.out.println(Messages.INVALIDPATHNAME);
         }
    }
}
