package duke.launcher;

import duke.Duke;
import duke.exception.DukeException;
import duke.response.Response;
import duke.scanner.CommandScanner;
import duke.storage.Storage;
import duke.ui.Ui;

public class CliLauncher {
    /**
     * File name of the file where the current task list is saved (under ./data/ directory)
     */
    private static final String FILE_STRING = "duke.txt";

    /**
     * Runs Duke's cli based program. Saves output in the file specified in the fileString
     */
    public static void run() {
        Duke duke = new Duke();
        CommandScanner cmdScanner = new CommandScanner();

        Ui.showGreetings();

        try {
            Storage storage = Storage.create(CliLauncher.FILE_STRING);

            while (true) {
                String cmdString = cmdScanner.nextCommand();
                Response response = duke.runCommand(cmdString);
                System.out.println(response.getText());

                if (response.getExit()) {
                    break;
                }
            }

            storage.write(duke.getTaskList());
            storage.close();
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        } catch (Exception e) {
            Ui.showUnexpectedError(e.getMessage());
        }
    }
}
