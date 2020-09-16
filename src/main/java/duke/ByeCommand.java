package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.addGoodbye();
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, 3000);
    }
}
