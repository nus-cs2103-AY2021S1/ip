package duke;

import java.util.ArrayList;
import java.util.List;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList tmpTaskList;
        try {
            tmpTaskList = new TaskList(storage.load());
            ui.printWithWrapper(new ArrayList<>(List.of("Duke has loaded from a previously saved file!")),
                    false, false);
        } catch (DukeException e) {
            tmpTaskList = new TaskList();
            ui.printWithWrapper(new ArrayList<>(List.of(
                    e.getPrettyErrorMsg(),
                    "Duke will start from a clean taskList!")), false, true);
        }

        this.taskList = tmpTaskList;
    }

    private void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command cmd = Parser.parseInput(input);
                cmd.execute(ui, storage, taskList);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.printWithWrapper(new ArrayList<>(List.of(e.getPrettyErrorMsg())), false, true);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
