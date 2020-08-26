import input.UserInput;
import function.DukeFunction;
import data.DukeState;
import ui.Ui;

import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        DukeFunction.loadSavedTasks();
        Ui.greet();

        while (!DukeState.exitLoop) {
            String inputLine = UserInput.getOneLine();
            DukeFunction.checkCommand(inputLine);
        }

        DukeFunction.saveCurrentTasks();
    }
}
