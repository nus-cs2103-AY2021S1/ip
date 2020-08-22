import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * Represents a todo manager bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke bot.
     * @param filePath the path of the file which store the previous data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the todo list feature of the .
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String res;
        ui.welcome();
        while (true) {
            try {
                res = sc.nextLine();
                if (res.equalsIgnoreCase(Operations.BYE.name())) {
                    break;
                } else if (res.equalsIgnoreCase(Operations.LIST.name())) {
                    ui.printList(tasks.getData());
                } else if (res.startsWith(Operations.DONE.name().toLowerCase())) {
                    tasks.done(res, ui, storage);
                } else if (res.startsWith(Operations.DELETE.name().toLowerCase())) {
                    tasks.delete(res, ui, storage);
                } else if (res.startsWith(Operations.TODO.name().toLowerCase())) {
                    tasks.todo(res, ui, storage);
                } else if (res.startsWith(Operations.DEADLINE.name().toLowerCase())) {
                    tasks.deadline(res, ui, storage);
                } else if (res.startsWith(Operations.EVENT.name().toLowerCase())) {
                    tasks.event(res, ui, storage);
                } else {
                    // Exception: eg. ???
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                // Exception: eg. done some words
                // Exception: eg. delete some words
                ui.showNumberFormatError();
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (FileNotFoundException e) {
                ui.showFileNotFoundError();
            }
        }
        ui.bye();
    }

    /**
     * Starts creating an Duke bot object.
     * @param args no need here.
     */
    public static void main(String[] args) {
        new Duke("./log.txt").run();
    }
}