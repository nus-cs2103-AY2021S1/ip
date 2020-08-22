import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;

    public Duke() {
        // set up the things needed to start Duke
        ui = new UI();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            taskList = new TaskList(storage.loadData());
        } else {
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean exit;
        Scanner sc = new Scanner(System.in);
        ui.greetUser();
        String name = sc.nextLine();
        ui.addressUser(name);

        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            Command command = Parser.parse(input);
            exit = command.isExit();
            ui.printDivider();
            if (exit) {
                ui.exitFocus();
                break;
            }
            try {
                command.execute(input, taskList, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printDividerWithSpacing();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}