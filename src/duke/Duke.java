package duke;

import duke.input.UserInput;
import duke.command.CommandParser;
import duke.data.DukeState;
import duke.storage.DukeStorage;
import duke.ui.Ui;

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
