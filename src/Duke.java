import input.UserInput;
import command.CommandParser;
import data.DukeState;
import storage.DukeStorage;
import ui.Ui;

import java.io.IOException;

public class Duke {

    private void run() throws IOException {
        DukeStorage.loadSavedTasks();
        Ui.greet();

        while (!DukeState.exitLoop) {
            String inputLine = UserInput.getOneLine();
            CommandParser.parse(inputLine);
        }

        DukeStorage.saveCurrentTasks();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
