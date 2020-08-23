package duke;

import duke.command.Command;
import duke.command.ExitCommand;

import java.util.Scanner;

public class Duke {
    private final static Ui ui = new Ui();
    private final Storage storage;

    Duke(Storage storage) {
        this.storage = storage;
    }

    public static Duke createDuke(String filePath) {
        try {
            Storage storage = Storage.createStorage(filePath);
            storage.load();
            return new Duke(storage);
        } catch (DukeException e) {
            ui.fileCreationError(e.getMessage());
            return null;
        }
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && input.hasNextLine()) {
            try {
                String commandMessage = input.nextLine();
                Command c = Parser.parse(commandMessage);
                c.execute(commandMessage, storage, ui);
                if (c instanceof ExitCommand) {
                    isExit = true;
                    input.close();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
