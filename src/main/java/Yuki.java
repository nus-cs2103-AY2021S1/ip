import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.command.ExitCommand;

/**
 * <code>Main</code> starts the entire application by creating a <code>Duke</code> object and checking for existing
 * tasks.
 */
public class Yuki {
    protected static final Ui UI = new Ui();
    protected Storage storage;
    protected TaskList taskList;

    Yuki() {
        try {
            this.storage = Storage.createStorage("data/duke.txt");
            if (storage.isNew()) {
                UI.print(UI.fileCreationSuccess());
            } else {
                UI.print(UI.welcome());
            }
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the whole program by taking in input commands.
     * Terminates only when an <Code>ExitCommand</Code> is given.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && input.hasNextLine()) {
            try {
                String commandMessage = input.nextLine();
                Command c = Parser.parse(commandMessage.trim());
                String s = c.execute(commandMessage, storage, UI, taskList);
                UI.print(s);
                if (c instanceof ExitCommand) {
                    isExit = true;
                    input.close();
                }
            } catch (DukeException e) {
                UI.print(e.getMessage());
            }
        }
    }

    /**
     * Creates a <code>Duke</code> object.
     *
     * @param args array for command-line arguments
     */
    public static void main(String[] args) {
        Yuki yuki = new Yuki();
        yuki.run();
    }

}
