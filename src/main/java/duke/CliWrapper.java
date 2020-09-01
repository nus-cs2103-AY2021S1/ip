package duke;

import duke.command.Response;
import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.storage.Storage;
import duke.ui.Clui;

public class CliWrapper {

    private Duke duke;
    private Clui ui;

    private CliWrapper(String filePath) {
        ui = new Clui();
        try {
            duke = new Duke(filePath, true);
        } catch (DukeStorageException e) {
            ui.print(e.getMessage());
            duke = new Duke(filePath, false);
        }
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Response response = duke.getResponse(fullCommand);
                ui.print(response.getResponseMessage());
                isExit = response.shouldExit();
            } catch (IncompleteDukeCommandException e) {
                ui.print("Something went wrong, but I'm not sure what...");
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CliWrapper(Storage.FILE_PATH).run();
    }
}
